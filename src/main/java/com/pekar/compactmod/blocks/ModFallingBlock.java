package com.pekar.compactmod.blocks;

import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public abstract class ModFallingBlock extends FallingBlock
{
    protected ModFallingBlock(BlockBehaviour behaviour)
    {
        super(BlockBehaviour.Properties.ofFullCopy(behaviour));
    }

    protected ModFallingBlock(BlockBehaviour.Properties properties)
    {
        super(properties);
    }
}
