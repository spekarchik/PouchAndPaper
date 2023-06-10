package com.pekar.compactmod.blocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PaperBlock extends ModBlock
{
    public PaperBlock()
    {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
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
