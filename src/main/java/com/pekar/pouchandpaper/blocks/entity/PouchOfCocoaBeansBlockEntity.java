package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PouchOfCocoaBeansBlockEntity extends PouchOfSeedsBlockEntity
{

    public PouchOfCocoaBeansBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.POUCH_OF_COCOA_BLOCK_ENTITY.get(), pos, blockState);
    }

}
