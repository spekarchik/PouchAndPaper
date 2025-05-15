package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PouchOfBeetBlockEntity extends PouchOfSeedsBlockEntity
{

    public PouchOfBeetBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.POUCH_OF_BEET_BLOCK_ENTITY.get(), pos, blockState);
    }

}
