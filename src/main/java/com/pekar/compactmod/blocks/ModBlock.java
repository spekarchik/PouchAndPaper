package com.pekar.compactmod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public abstract class ModBlock extends Block
{
    protected ModBlock(BlockBehaviour behaviour)
    {
        super(BlockBehaviour.Properties.ofFullCopy(behaviour));
    }

    protected ModBlock(BlockBehaviour.Properties properties)
    {
        super(properties);
    }
}
