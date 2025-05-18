package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PotatoSackBlockEntity extends FarmContainerBlockEntity
{
    public PotatoSackBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.SACK_OF_POTATO_BLOCK_ENTITY.get(), pos, blockState);
    }
}
