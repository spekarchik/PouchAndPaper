package com.pekar.pouchandpaper.blocks.entity;

import com.pekar.pouchandpaper.Main;
import com.pekar.pouchandpaper.blocks.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockEntityRegistry
{
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BurntPaperBlockEntity>> BURNT_PAPER_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("burnt_paper_block_entity", () ->
                    BlockEntityType.Builder.of(BurntPaperBlockEntity::new, BlockRegistry.BURNT_PAPER_BLOCK.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PouchOfWheatBlockEntity>> POUCH_OF_WHEAT_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("pouch_of_wheat_block_entity", () ->
                    new BlockEntityType<>(PouchOfWheatBlockEntity::new, BlockRegistry.POUCH_OF_WHEAT.get()));

    public static void initStatic()
    {
        // just to initialize static members
    }
}
