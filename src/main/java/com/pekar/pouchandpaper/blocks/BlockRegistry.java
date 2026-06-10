package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.blocks.block_items.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.pekar.pouchandpaper.Main.MODID;
import static com.pekar.pouchandpaper.utils.Resources.createResourceLocation;

public class BlockRegistry
{
    private static final List<Item> BLOCK_ITEMS = new ArrayList<>();

    public static final Block PAPER_BLOCK = register("paperblock", PaperBlock::new, PaperBlockItem::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.BAMBOO_WOOD).strength(0.6f));

    public static final Block BURNT_PAPER_BLOCK = register("burnt_paper_block", BurntPaperBlock::new, BurntPaperBlockItem::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.GRASS).strength(0.6f).pushReaction(PushReaction.DESTROY));

    public static final Block POUCH_OF_WHEAT = register("seedpocket_wheat", PouchOfWheatSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.1f, 9f)
                    .sound(SoundType.VINE),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block POUCH_OF_BEET = register("seedpocket_beet", PouchOfBeetrootSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.1f, 9f)
                    .sound(SoundType.VINE),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block POUCH_OF_PUMPKIN = register("seedpocket_pumpkin", PouchOfPumpkinSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.1f, 9f)
                    .sound(SoundType.VINE),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block POUCH_OF_WATERMELON = register("seedpocket_watermelon", PouchOfWatermelonSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f, 9f)
                    .sound(SoundType.VINE),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block POUCH_OF_COCOA = register("seedpocket_cocoa", PouchOfCocoaBeans::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.1f, 9f)
                    .sound(SoundType.VINE),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block SACK_OF_POTATO = register("sack_of_potato", PotatoSack::new, FarmSackBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.1f, 9f)
                    .sound(SoundType.NETHERRACK),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block SACK_OF_BEETROOT = register("sack_of_beetroot", BeetrootSack::new, FarmSackBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(0.1f, 9f)
                    .sound(SoundType.NETHERRACK),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block SACK_OF_CARROT = register("sack_of_carrot", CarrotSack::new, FarmSackBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.1f, 9f)
                    .sound(SoundType.NETHERRACK),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final Block PACK_OF_FEATHERS = register("feathers_pack", PackOfFeathers::new, PackOfFeathersBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.1f, 9f)
                    .sound(SoundType.WOOL),
            new FarmContainerConfiguration(9, 32, 64, 16));

    public static void initStatic()
    {
        // just to initialize static members
    }

    public static Collection<Item> getBlockItems()
    {
        return BLOCK_ITEMS;
    }

    private static <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> blockSupplier, BiFunction<Block, Item.Properties, ? extends ModBlockItem> blockItemSupplier, BlockBehaviour.Properties blockProperties)
    {
        var id = createResourceLocation(MODID, name);
        var block = Registry.register(BuiltInRegistries.BLOCK, id, blockSupplier.apply(blockProperties));
        var blockItem = blockItemSupplier.apply(block, new Item.Properties());
        BLOCK_ITEMS.add(blockItem);
        Registry.register(BuiltInRegistries.ITEM, id, blockItem);
        return block;
    }

    private static <T extends Block> T register(String name, BiFunction<BlockBehaviour.Properties, FarmContainerConfiguration, T> blockSupplier, BiFunction<Block, Item.Properties, ? extends ModBlockItem> blockItemSupplier,
                                                               BlockBehaviour.Properties blockProperties, FarmContainerConfiguration containerConfiguration)
    {
        return register(name, prop -> blockSupplier.apply(prop, containerConfiguration), blockItemSupplier, blockProperties);
    }
}
