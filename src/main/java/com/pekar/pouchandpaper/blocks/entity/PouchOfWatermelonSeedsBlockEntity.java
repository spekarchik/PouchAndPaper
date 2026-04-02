package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PouchOfWatermelonSeedsBlockEntity extends FarmContainerBlockEntity
{

    public PouchOfWatermelonSeedsBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.POUCH_OF_WATERMELON_BLOCK_ENTITY.get(), pos, blockState);
    }

}
