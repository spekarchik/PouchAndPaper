package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PouchOfPumpkinSeedsBlockEntity extends FarmContainerBlockEntity
{

    public PouchOfPumpkinSeedsBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.POUCH_OF_PUMPKIN_BLOCK_ENTITY.get(), pos, blockState);
    }

}
