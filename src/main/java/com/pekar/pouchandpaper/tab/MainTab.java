package com.pekar.pouchandpaper.tab;

import com.pekar.pouchandpaper.blocks.BlockRegistry;
import com.pekar.pouchandpaper.items.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class MainTab extends ModTab
{
    @Override
    protected String getTabName()
    {
        return "compactmod_tab";
    }

    @Override
    protected ItemStack getIconItem()
    {
        return new ItemStack(ItemRegistry.PAPER_STACK);
    }

    @Override
    protected Collection<Item> getTabItems()
    {
        ItemRegistry.initStatic();
        BlockRegistry.initStatic();
        var items = new ArrayList<Item>();
        items.addAll(ItemRegistry.getEntries());
        items.addAll(BlockRegistry.getBlockItems());
        return items;
    }
}
