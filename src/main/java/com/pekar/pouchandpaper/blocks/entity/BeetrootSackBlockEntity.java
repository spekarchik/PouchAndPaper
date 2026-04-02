package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BeetrootSackBlockEntity extends FarmContainerBlockEntity
{
    public BeetrootSackBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.SACK_OF_BEETROOT_BLOCK_ENTITY.get(), pos, blockState);
    }
}
