package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PouchOfWheatSeedsBlockEntity extends PouchOfSeedsBlockEntity
{

    public PouchOfWheatSeedsBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.POUCH_OF_WHEAT_BLOCK_ENTITY.get(), pos, blockState);
    }

}
