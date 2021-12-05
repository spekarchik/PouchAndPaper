package com.pekar.compactmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class GunPowderBlock extends ModFallingBlock
{
    //    /give @p compactmod:gunpowderblock

    public GunPowderBlock()
    {
        super(BlockBehaviour.Properties.of(Material.SAND)
                .sound(SoundType.SNOW)
                .strength(0.2f));
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face)
    {
        return 100;
    }
}
