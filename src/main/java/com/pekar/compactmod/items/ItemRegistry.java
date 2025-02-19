package com.pekar.compactmod.items;

import com.pekar.compactmod.CompactMod;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemRegistry
{
    public static final DeferredItem<Item> PAPER_STACK = CompactMod.ITEMS.register("paperstack", ModItem::new);
    public static final DeferredItem<Item> INK_BOTTLE = CompactMod.ITEMS.register("ink_bottle", ModItem::new);
    public static final DeferredItem<Item> GLOW_INK_BOTTLE = CompactMod.ITEMS.register("glow_ink_bottle", ModItem::new);
    public static final DeferredItem<Item> LEATHER_PACK = CompactMod.ITEMS.register("leatherpack", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_BEET = CompactMod.ITEMS.register("seedpocket_beet", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_PUMPKIN = CompactMod.ITEMS.register("seedpocket_pumpkin", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_WATERMELON = CompactMod.ITEMS.register("seedpocket_watermelon", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_WHEAT = CompactMod.ITEMS.register("seedpocket_wheat", ModItem::new);
    public static final DeferredItem<Item> SEEDPOCKET_COCOA = CompactMod.ITEMS.register("seedpocket_cocoa", ModItem::new);
    public static final DeferredItem<Item> FEATHERS_PACK = CompactMod.ITEMS.register("feathers_pack", ModItem::new);

    public static void initStatic()
    {
        // just to initialize static members
    }
}

