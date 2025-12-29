package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.Main;
import com.pekar.pouchandpaper.blocks.block_items.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockRegistry
{
    public static final DeferredBlock<Block> PAPER_BLOCK = register("paperblock", PaperBlock::new, PaperBlockItem::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.BAMBOO_WOOD).strength(0.6f));

    public static final DeferredBlock<Block> BURNT_PAPER_BLOCK = register("burnt_paper_block", BurntPaperBlock::new, BurntPaperBlockItem::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.GRASS).strength(0.6f));

    public static final DeferredBlock<Block> POUCH_OF_WHEAT = register("seedpocket_wheat", PouchOfWheatSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.HARD_CROP),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> POUCH_OF_BEET = register("seedpocket_beet", PouchOfBeetrootSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.HARD_CROP),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> POUCH_OF_PUMPKIN = register("seedpocket_pumpkin", PouchOfPumpkinSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.HARD_CROP),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> POUCH_OF_WATERMELON = register("seedpocket_watermelon", PouchOfWatermelonSeeds::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.HARD_CROP),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> POUCH_OF_COCOA = register("seedpocket_cocoa", PouchOfCocoaBeans::new, PouchOfSeedsBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.HARD_CROP),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> SACK_OF_POTATO = register("sack_of_potato", PotatoSack::new, FarmSackBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.NETHERRACK),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> SACK_OF_BEETROOT = register("sack_of_beetroot", BeetrootSack::new, FarmSackBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.NETHERRACK),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> SACK_OF_CARROT = register("sack_of_carrot", CarrotSack::new, FarmSackBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.NETHERRACK),
            new FarmContainerConfiguration(4, 16, 64, 16));

    public static final DeferredBlock<Block> PACK_OF_FEATHERS = register("feathers_pack", PackOfFeathers::new, PackOfFeathersBlockItem::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.BIT).strength(0.1f, 9f)
                    .sound(SoundType.WOOL),
            new FarmContainerConfiguration(9, 32, 64, 16));

    public static void initStatic()
    {
        // just to initialize static members
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> blockSupplier)
    {
        var blockObject = Main.BLOCKS.register(name, blockSupplier);
        Main.ITEMS.registerItem(name, p -> new ModBlockItem(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> blockSupplier)
    {
        var blockObject = Main.BLOCKS.registerBlock(name, blockSupplier);
        Main.ITEMS.registerItem(name, p -> new ModBlockItem(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> blockSupplier, BlockBehaviour.Properties properties)
    {
        var blockObject = Main.BLOCKS.registerBlock(name, blockSupplier, properties);
        Main.ITEMS.registerItem(name, p -> new ModBlockItem(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> blockSupplier, BiFunction<Block, Item.Properties, ? extends ModBlockItem> blockItemSupplier, BlockBehaviour.Properties blockProperties)
    {
        var blockObject = Main.BLOCKS.registerBlock(name, blockSupplier, blockProperties);
        Main.ITEMS.registerItem(name, p -> blockItemSupplier.apply(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> register(String name, BiFunction<BlockBehaviour.Properties, FarmContainerConfiguration, T> blockSupplier, BiFunction<Block, Item.Properties, ? extends ModBlockItem> blockItemSupplier,
                                                               BlockBehaviour.Properties blockProperties, FarmContainerConfiguration containerConfiguration)
    {
        var blockObject = Main.BLOCKS.registerBlock(name, prop -> blockSupplier.apply(prop, containerConfiguration), () -> blockProperties);
        Main.ITEMS.registerItem(name, p -> blockItemSupplier.apply(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> registerSkipTab(String name, Supplier<T> supplier)
    {
        return Main.BLOCKS.register(name, supplier);
    }

    private static <T extends Block> DeferredBlock<T> registerSkipTab(String name, Function<BlockBehaviour.Properties, T> supplier)
    {
        return Main.BLOCKS.registerBlock(name, supplier);
    }

    private static <T extends Block> DeferredBlock<T> registerSkipTab(String name, Function<BlockBehaviour.Properties, T> supplier, BlockBehaviour.Properties properties)
    {
        return Main.BLOCKS.registerBlock(name, supplier, properties);
    }
}
