package com.pekar.pouchandpaper.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static com.pekar.pouchandpaper.Main.MODID;
import static com.pekar.pouchandpaper.utils.Resources.createResourceLocation;

public class ItemRegistry
{
    private static final List<Item> ITEMS = new ArrayList<>();

    public static final Item PAPER_STACK = registerItem("paperstack", ModItem::new);
    public static final Item INK_BOTTLE = registerItem("ink_bottle", ModItem::new);
    public static final Item GLOW_INK_BOTTLE = registerItem("glow_ink_bottle", ModItem::new);
    public static final Item LEATHER_PACK = registerItem("leatherpack", ModItem::new);

    public static void initStatic()
    {
        // just to initialize static members
    }

    public static Collection<Item> getEntries()
    {
        return ITEMS;
    }

    public static Item registerItem(String name, Function<Item.Properties, Item> itemFactory)
    {
        var id = createResourceLocation(MODID, name);
        var key = ResourceKey.create(Registries.ITEM, id);
        var item = itemFactory.apply(new Item.Properties().setId(key));
        ITEMS.add(item);
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }
}

