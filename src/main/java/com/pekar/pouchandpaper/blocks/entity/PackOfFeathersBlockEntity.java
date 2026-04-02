package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PackOfFeathersBlockEntity extends FarmContainerBlockEntity
{
    public PackOfFeathersBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.PACK_OF_FEATHERS_BLOCK_ENTITY.get(), pos, blockState);
    }
}
