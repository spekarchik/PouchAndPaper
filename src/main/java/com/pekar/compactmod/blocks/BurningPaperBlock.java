package com.pekar.compactmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BurningPaperBlock extends PaperBlock
{
    public BurningPaperBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter)
    {
        System.out.println("Burning Paper - caught fire");
        level.scheduleTick(pos, this, 400);
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource)
    {
        System.out.println("Burning Paper - tick");
        if (serverLevel.getBlockState(pos.above()).is(Blocks.FIRE))
        {
            int val = randomSource.nextIntBetweenInclusive(0, 10);
            switch (val)
            {
                case 0 -> serverLevel.removeBlock(pos, false);
                case 1 -> serverLevel.removeBlock(pos.above(), false);
            }
        }
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
}
