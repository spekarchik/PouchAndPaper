package com.pekar.compactmod.items;

import com.pekar.compactmod.CompactMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry
{
    public static final RegistryObject<Item> PAPER_STACK = CompactMod.ITEMS.register("paperstack", ModItem::new);
    public static final RegistryObject<Item> INK_BOTTLE = CompactMod.ITEMS.register("inkbottle", ModItem::new);
    public static final RegistryObject<Item> LEATHER_PACK = CompactMod.ITEMS.register("leatherpack", ModItem::new);
    public static final RegistryObject<Item> SEEDPOCKET_BEET = CompactMod.ITEMS.register("seedpocket_beet", ModItem::new);
    public static final RegistryObject<Item> SEEDPOCKET_PUMPKIN = CompactMod.ITEMS.register("seedpocket_pumpkin", ModItem::new);
    public static final RegistryObject<Item> SEEDPOCKET_WATERMELON = CompactMod.ITEMS.register("seedpocket_watermelon", ModItem::new);
    public static final RegistryObject<Item> SEEDPOCKET_WHEAT = CompactMod.ITEMS.register("seedpocket_wheat", ModItem::new);
    public static final RegistryObject<Item> FEATHERS_PACK = CompactMod.ITEMS.register("feathers_pack", ModItem::new);

    public static void initStatic()
    {
        // just to initialize static members
    }
}

