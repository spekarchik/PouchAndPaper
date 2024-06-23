package com.pekar.compactmod.blocks;

import com.pekar.compactmod.CompactMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class BlockRegistry
{
//    public static final DeferredItem<Block> GUNPOWDER_BLOCK = CompactMod.BLOCKS.register("gunpowderblock", GunPowderBlock::new);
    public static final DeferredBlock<Block> PAPER_BLOCK = CompactMod.BLOCKS.register("paperblock", PaperBlock::new);
    public static final DeferredItem<BlockItem> PAPER_BLOCK_ITEM = CompactMod.ITEMS.register("paperblock",
            () -> new ModBlockItem(PAPER_BLOCK.get()));

    public static void initStatic()
    {
        // just to initialize static members
    }
}
