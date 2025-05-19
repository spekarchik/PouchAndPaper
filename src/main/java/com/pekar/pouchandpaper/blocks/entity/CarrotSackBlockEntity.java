package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CarrotSackBlockEntity extends FarmContainerBlockEntity
{
    public CarrotSackBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.SACK_OF_CARROT_BLOCK_ENTITY.get(), pos, blockState);
    }
}
