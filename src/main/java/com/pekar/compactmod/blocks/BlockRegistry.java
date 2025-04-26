package com.pekar.compactmod.blocks;

import com.pekar.compactmod.Main;
import com.pekar.compactmod.blocks.block_items.BurntPaperBlockItem;
import com.pekar.compactmod.blocks.block_items.ModBlockItem;
import com.pekar.compactmod.blocks.block_items.PaperBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockRegistry
{
    public static final DeferredBlock<Block> PAPER_BLOCK = register("paperblock", PaperBlock::new, PaperBlockItem::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.GRASS).strength(0.6f));

    public static final DeferredBlock<Block> BURNT_PAPER_BLOCK = register("burnt_paper_block", BurntPaperBlock::new, BurntPaperBlockItem::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.GRAVEL).strength(0.6f));

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
