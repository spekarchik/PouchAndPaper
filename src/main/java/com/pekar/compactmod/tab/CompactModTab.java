package com.pekar.compactmod.tab;

import com.pekar.compactmod.CompactMod;
import com.pekar.compactmod.items.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CompactModTab extends CreativeModeTab
{
    public static CreativeModeTab COMPACT_MOD_TAB = new CompactModTab(CompactMod.MODID);

    public CompactModTab(String tabName)
    {
        super(tabName);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(ItemRegistry.PAPER_STACK.get());
    }
}
