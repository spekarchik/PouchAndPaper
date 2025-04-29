package com.pekar.compactmod.blocks;

import com.mojang.serialization.MapCodec;
import com.pekar.compactmod.blocks.entity.BlockEntityRegistry;
import com.pekar.compactmod.blocks.entity.BurntPaperBlockEntity;
import com.pekar.compactmod.utils.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BurntPaperBlock extends PaperBlock implements EntityBlock
{
    private static final MapCodec<BurntPaperBlock> CODEC = simpleCodec(BurntPaperBlock::new);

    public BurntPaperBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource)
    {
        if (serverLevel.getBlockState(pos.above()).is(Blocks.FIRE))
        {
            int val = randomSource.nextIntBetweenInclusive(0, 10);
            switch (val)
            {
                case 0 -> serverLevel.removeBlock(pos, false);
                case 1 -> serverLevel.removeBlock(pos.above(), false);
            }
        }

        super.tick(blockState, serverLevel, pos, randomSource);
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState blockState, BlockState blockState1, FallingBlockEntity fallingBlockEntity)
    {
        level.destroyBlock(pos, false);
    }

    @Override
    public void onBlockExploded(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion)
    {
        level.destroyBlock(pos, false);
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity instanceof Creeper && fallDistance < 0.5f) return;
        level.destroyBlock(pos, false);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        if (entity instanceof Creeper) return;
        level.destroyBlock(pos, false);
    }

    @Override
    public boolean canDropFromExplosion(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion)
    {
        return false;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face)
    {
        return 60; // ignite: 5..60
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return 0; // burn: 5..100 - how long it burns (100 - short)
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side)
    {
        return false;
    }

    @Override
    public @Nullable PushReaction getPistonPushReaction(BlockState state)
    {
        return PushReaction.DESTROY;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return BlockEntityRegistry.BURNT_PAPER_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return level.isClientSide()
                ? null
                :((level1, blockPos, blockState, blockEntity) -> ((BurntPaperBlockEntity)blockEntity).tick(level1, blockPos, blockState, (BurntPaperBlockEntity)blockEntity));
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec()
    {
        return CODEC;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
    {
        if (!Utils.instance.text.showExtendedDescription(tooltipComponents)) return;

        for (int i = 1; i <= 4; i++)
        {
            var component = getDisplayName(asItem(), i).withStyle(ChatFormatting.DARK_GRAY);
            if (i == 1)
                component.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY);
            tooltipComponents.add(component);
        }
    }
}
