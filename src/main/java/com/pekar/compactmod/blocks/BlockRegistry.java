package com.pekar.compactmod.blocks;

import com.pekar.compactmod.Main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.pekar.compactmod.Main.BLOCKS;

public class BlockRegistry
{
    public static final DeferredBlock<Block> PAPER_BLOCK = register("paperblock", PaperBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.GRASS).strength(0.6f));

    public static final DeferredBlock<Block> BURNT_PAPER_BLOCK = register("burnt_paper_block", BurntPaperBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).sound(SoundType.GRAVEL).strength(0.6f));

    public static void initStatic()
    {
        // just to initialize static members
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> supplier)
    {
        var blockObject = BLOCKS.register(name, supplier);
        Main.ITEMS.registerItem(name, p -> new ModBlockItem(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> supplier)
    {
        var blockObject = BLOCKS.registerBlock(name, supplier);
        Main.ITEMS.registerItem(name, p -> new ModBlockItem(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> supplier, BlockBehaviour.Properties properties)
    {
        var blockObject = BLOCKS.registerBlock(name, supplier, properties);
        Main.ITEMS.registerItem(name, p -> new ModBlockItem(blockObject.get(), p));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> blockSupplier, Function<Block, ? extends ModBlockItem> blockItemSupplier)
    {
        var blockObject = BLOCKS.register(name, blockSupplier);
        Main.ITEMS.register(name, () -> blockItemSupplier.apply(blockObject.get()));
        return blockObject;
    }

    private static <T extends Block> DeferredBlock<T> registerSkipTab(String name, Supplier<T> supplier)
    {
        return BLOCKS.register(name, supplier);
    }

    private static <T extends Block> DeferredBlock<T> registerSkipTab(String name, Function<BlockBehaviour.Properties, T> supplier)
    {
        return BLOCKS.registerBlock(name, supplier);
    }

    private static <T extends Block> DeferredBlock<T> registerSkipTab(String name, Function<BlockBehaviour.Properties, T> supplier, BlockBehaviour.Properties properties)
    {
        return BLOCKS.registerBlock(name, supplier, properties);
    }
}
