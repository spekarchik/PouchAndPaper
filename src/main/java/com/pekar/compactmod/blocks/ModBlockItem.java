package com.pekar.compactmod.blocks;

import com.pekar.compactmod.tab.CompactModTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class ModBlockItem extends BlockItem
{
    public ModBlockItem(Block block)
    {
        super(block, new Properties().tab(CompactModTab.COMPACT_MOD_TAB));
    }
}
