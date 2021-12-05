package com.pekar.compactmod.blocks;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class PaperBlock extends ModBlock
{
    public PaperBlock()
    {
        super(BlockBehaviour.Properties.of(Material.PLANT)
                .sound(SoundType.STONE)
                .strength(0.6f));
    }

//    @Override
//    public Item getItemDropped(IBlockState state, Random rand, int fortune)
//    {
//        return ItemRegistry.PAPER_STACK;
//    }
//
//    @Override
//    public int quantityDropped(Random random)
//    {
//        return 4;
//    }
}
