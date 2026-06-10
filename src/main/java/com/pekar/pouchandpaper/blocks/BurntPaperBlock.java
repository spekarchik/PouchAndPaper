package com.pekar.pouchandpaper.blocks;

import com.mojang.serialization.MapCodec;
import com.pekar.pouchandpaper.blocks.entity.BlockEntityRegistry;
import com.pekar.pouchandpaper.blocks.entity.BurntPaperBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

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
    public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion)
    {
        level.destroyBlock(pos, false);
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, double fallDistance)
    {
        if (entity instanceof Player || fallDistance > 1.5F)
            level.destroyBlock(pos, false);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        if (entity instanceof Player)
            level.destroyBlock(pos, false);
    }

    @Override
    public boolean dropFromExplosion(Explosion explosion)
    {
        return false;
    }

    public static int getFireSpreadSpeed()
    {
        return 0;
    }

    public static int getFlammability()
    {
        return 0;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return BlockEntityRegistry.BURNT_PAPER_BLOCK_ENTITY.create(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return level.isClientSide()
                ? null
                :((level1, blockPos, blockState, blockEntity) -> ((BurntPaperBlockEntity)blockEntity).tick(level1, blockPos, blockState, (BurntPaperBlockEntity)blockEntity));
    }

    @Override
    public int getDustColor(BlockState blockState, net.minecraft.world.level.BlockGetter blockGetter, BlockPos blockPos)
    {
        return 0x333333;
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec()
    {
        return CODEC;
    }
}
