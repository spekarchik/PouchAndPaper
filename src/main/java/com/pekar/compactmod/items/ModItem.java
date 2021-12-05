package com.pekar.compactmod.items;

import com.pekar.compactmod.tab.CompactModTab;
import net.minecraft.world.item.Item;

public class ModItem extends Item
{
    public ModItem()
    {
        super(new Properties().tab(CompactModTab.COMPACT_MOD_TAB));
    }
}
