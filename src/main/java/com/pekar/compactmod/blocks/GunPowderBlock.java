package com.pekar.compactmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GunPowderBlock extends ModFallingBlock
{
    //    /give @p compactmod:gunpowderblock

    public GunPowderBlock()
    {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
                .sound(SoundType.SNOW)
                .strength(0.2f));
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face)
    {
        return 100;
    }
}
