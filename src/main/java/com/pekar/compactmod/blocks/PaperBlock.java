package com.pekar.compactmod.blocks;

import com.mojang.serialization.MapCodec;
import com.pekar.compactmod.items.ItemRegistry;
import com.pekar.compactmod.utils.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PaperBlock extends FallingBlock implements ISupportHoverText
{
    private static final MapCodec<PaperBlock> CODEC = simpleCodec(PaperBlock::new);

    public PaperBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side)
    {
        return false;
    }

    @Override
    public boolean canDropFromExplosion(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion)
    {
        return false;
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, float fallDistance)
    {
        if (fallDistance > 3.0F)
        {
            dropBlock(level, pos);
        }
    }

    private static void dropBlock(Level level, BlockPos pos)
    {
        int paperStacks = level.random.nextIntBetweenInclusive(0, 4);
        int papers = 12 - paperStacks * 3;
        popResource(level, pos, new ItemStack(ItemRegistry.PAPER_STACK.get(), paperStacks));
        popResource(level, pos, new ItemStack(Items.PAPER, papers));
        level.removeBlock(pos, false);
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState blockState, BlockState blockState1, FallingBlockEntity fallingBlockEntity)
    {
        dropBlock(level, pos);
    }

    @Override
    public void onBlockExploded(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion)
    {
        dropBlock(level, pos);
    }

//    @Override
//    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter)
//    {
//        level.scheduleTick(pos, this, 400);
//    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource)
    {
        if (serverLevel.getBlockState(pos.above()).is(Blocks.FIRE))
        {
            int val = randomSource.nextIntBetweenInclusive(0, 4);
            if (val == 0)
            {
                serverLevel.setBlock(pos, BlockRegistry.BURNING_PAPER_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
            }
        }

        super.tick(blockState, serverLevel, pos, randomSource);
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face)
    {
        return 200; // ignite: 5..60
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return 10; // burn: 5..100
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

        for (int i = 1; i <= 5; i++)
        {
            var component = getDisplayName(asItem(), i).withStyle(ChatFormatting.DARK_GRAY);
            tooltipComponents.add(component);
        }
    }
}
