package com.pekar.pouchandpaper;

import com.pekar.pouchandpaper.blocks.BlockRegistry;
import com.pekar.pouchandpaper.blocks.BurntPaperBlock;
import com.pekar.pouchandpaper.blocks.PaperBlock;
import com.pekar.pouchandpaper.blocks.entity.BlockEntityRegistry;
import com.pekar.pouchandpaper.events.EventRegistry;
import com.pekar.pouchandpaper.items.ItemRegistry;
import com.pekar.pouchandpaper.tab.MainTab;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class Main implements ModInitializer
{
    public static final String MODID = "pouchandpaper";

    @Override
    public void onInitialize()
    {
        initializeRegistry();
        registerFlammables();
        new MainTab().createTab();
        EventRegistry.registerEvents();
    }

    private void initializeRegistry()
    {
        BlockRegistry.initStatic();
        BlockEntityRegistry.initStatic();
        ItemRegistry.initStatic();
    }

    private void registerFlammables()
    {
        var flammableBlocks = FlammableBlockRegistry.getDefaultInstance();
        flammableBlocks.add(BlockRegistry.PAPER_BLOCK, PaperBlock.getFireSpreadSpeed(), PaperBlock.getFlammability());
        flammableBlocks.add(BlockRegistry.BURNT_PAPER_BLOCK, BurntPaperBlock.getFireSpreadSpeed(), BurntPaperBlock.getFlammability());
    }
}
