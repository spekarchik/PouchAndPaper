package com.pekar.compactmod.items;

import com.pekar.compactmod.CompactMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry
{
    public static final RegistryObject<Item> PAPER_STACK = CompactMod.ITEMS.register("paperstack", PaperStack::new);
    public static final RegistryObject<Item> INK_BOTTLE = CompactMod.ITEMS.register("inkbottle", InkBottle::new);
    public static final RegistryObject<Item> LEATHER_PACK = CompactMod.ITEMS.register("leatherpack", LeatherPack::new);
    public static final RegistryObject<Item> SEEDPOCKET_BEET = CompactMod.ITEMS.register("seedpocket_beet", SeedPocketBeet::new);
    public static final RegistryObject<Item> SEEDPOCKET_PUMPKIN = CompactMod.ITEMS.register("seedpocket_pumpkin", SeedPocketPumpkin::new);
    public static final RegistryObject<Item> SEEDPOCKET_WATERMELON = CompactMod.ITEMS.register("seedpocket_watermelon", SeedPocketWaterMelon::new);
    public static final RegistryObject<Item> SEEDPOCKET_WHEAT = CompactMod.ITEMS.register("seedpocket_wheat", SeedPocketWheat::new);

    public static void initStatic()
    {
        // just to initialize static members
    }

    public static void add(Item item)
    {
        if (item instanceof ModItem)
        {
//            ((ModItem)item).addRecipeNames(RecipeRegistry.getRecipeNames());
        }
    }
}

