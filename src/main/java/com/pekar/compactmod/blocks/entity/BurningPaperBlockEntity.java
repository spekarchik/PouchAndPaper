package com.pekar.compactmod.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BurningPaperBlockEntity extends BlockEntity implements BlockEntityTicker<BurningPaperBlockEntity>
{
    private static final int CREEPER_SEEK_RADUIS = 70;

    public BurningPaperBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.BURNING_PAPER_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState blockState, BurningPaperBlockEntity burningPaperBlockEntity)
    {
        if (level.getGameTime() % 40 != 0) return;
        if (level.getBlockState(pos.above()).is(Blocks.FIRE)) return;

        var creepers = level.getEntitiesOfClass(Creeper.class, new AABB(pos).inflate(CREEPER_SEEK_RADUIS));

        for (var creeper : creepers)
        {
            var navigation = creeper.getNavigation();
            navigation.moveTo(pos.getX(), pos.getY(), pos.getZ(), 1.0);
        }
    }
}
