package com.pekar.pouchandpaper.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public abstract class ModBlock extends Block
{
    protected ModBlock(BlockBehaviour.Properties properties)
    {
        super(properties);
    }
}
