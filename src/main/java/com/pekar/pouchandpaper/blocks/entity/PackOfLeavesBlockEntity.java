package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PackOfLeavesBlockEntity extends FarmContainerBlockEntity
{
    public PackOfLeavesBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.PACK_OF_LEAVES_BLOCK_ENTITY.get(), pos, blockState);
    }
}
