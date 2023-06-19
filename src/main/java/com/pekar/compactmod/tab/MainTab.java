package com.pekar.compactmod.tab;

import com.pekar.compactmod.CompactMod;
import com.pekar.compactmod.blocks.BlockRegistry;
import com.pekar.compactmod.items.ItemRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class MainTab extends ModTab
{
    @Override
    protected String getTabName()
    {
        return "compactmod_tab";
    }

    @Override
    protected RegistryObject<Item> getIconItem()
    {
        return ItemRegistry.PAPER_STACK;
    }

    @Override
    protected Collection<RegistryObject<Item>> getTabItems()
    {
        ItemRegistry.initStatic();
        BlockRegistry.initStatic();
        return CompactMod.ITEMS.getEntries(); // block items are also included
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
