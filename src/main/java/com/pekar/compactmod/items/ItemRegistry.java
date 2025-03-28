package com.pekar.compactmod.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.pekar.compactmod.Main.ITEMS;

public class ItemRegistry
{
    public static final DeferredItem<Item> PAPER_STACK = ITEMS.registerItem("paperstack", ModItem::new);
    public static final DeferredItem<Item> INK_BOTTLE = ITEMS.registerItem("ink_bottle", ModItem::new);
    public static final DeferredItem<Item> GLOW_INK_BOTTLE = ITEMS.registerItem("glow_ink_bottle", ModItem::new);
    public static final DeferredItem<Item> LEATHER_PACK = ITEMS.registerItem("leatherpack", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_BEET = ITEMS.registerItem("seedpocket_beet", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_PUMPKIN = ITEMS.registerItem("seedpocket_pumpkin", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_WATERMELON = ITEMS.registerItem("seedpocket_watermelon", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_WHEAT = ITEMS.registerItem("seedpocket_wheat", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_COCOA = ITEMS.registerItem("seedpocket_cocoa", ModItem::new);
    public static final DeferredItem<Item> FEATHERS_PACK = ITEMS.registerItem("feathers_pack", ModItem::new);

    public static void initStatic()
    {
        // just to initialize static members
    }
}

