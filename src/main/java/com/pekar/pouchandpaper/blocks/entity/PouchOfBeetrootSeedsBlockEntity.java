package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PouchOfBeetrootSeedsBlockEntity extends FarmContainerBlockEntity
{

    public PouchOfBeetrootSeedsBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.POUCH_OF_BEET_BLOCK_ENTITY.get(), pos, blockState);
    }

}
