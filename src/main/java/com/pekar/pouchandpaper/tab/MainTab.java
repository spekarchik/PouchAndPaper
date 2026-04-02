package com.pekar.pouchandpaper.tab;

import com.pekar.pouchandpaper.Main;
import com.pekar.pouchandpaper.blocks.BlockRegistry;
import com.pekar.pouchandpaper.items.ItemRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Collection;

public class MainTab extends ModTab
{
    @Override
    protected String getTabName()
    {
        return "compactmod_tab";
    }

    @Override
    protected DeferredItem<Item> getIconItem()
    {
        return ItemRegistry.PAPER_STACK;
    }

    @Override
    protected Collection<DeferredHolder<Item, ? extends Item>> getTabItems()
    {
        ItemRegistry.initStatic();
        BlockRegistry.initStatic();
        return Main.ITEMS.getEntries(); // block items are also included
    }

    @Override
    protected ResourceKey<CreativeModeTab>[] getTabsBefore()
    {
        return new ResourceKey[]
                {
                        CreativeModeTabs.SPAWN_EGGS
                };
    }
}
