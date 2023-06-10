package com.pekar.compactmod.blocks;

import com.pekar.compactmod.CompactMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry
{
//    public static final RegistryObject<Block> GUNPOWDER_BLOCK = CompactMod.BLOCKS.register("gunpowderblock", GunPowderBlock::new);
    public static final RegistryObject<Block> PAPER_BLOCK = CompactMod.BLOCKS.register("paperblock", PaperBlock::new);
    public static final RegistryObject<BlockItem> PAPER_BLOCK_ITEM = CompactMod.ITEMS.register("paperblock",
            () -> new ModBlockItem(PAPER_BLOCK.get()));

    public static void initStatic()
    {
        // just to initialize static members
    }
}
