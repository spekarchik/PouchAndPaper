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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PouchOfWheatSeedsBlockEntity>> POUCH_OF_WHEAT_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("pouch_of_wheat_block_entity", () ->
                    BlockEntityType.Builder.of(PouchOfWheatSeedsBlockEntity::new, BlockRegistry.POUCH_OF_WHEAT.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PouchOfBeetrootSeedsBlockEntity>> POUCH_OF_BEET_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("pouch_of_beet_block_entity", () ->
                    BlockEntityType.Builder.of(PouchOfBeetrootSeedsBlockEntity::new, BlockRegistry.POUCH_OF_BEET.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PouchOfPumpkinSeedsBlockEntity>> POUCH_OF_PUMPKIN_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("pouch_of_pumpkin_block_entity", () ->
                    new BlockEntityType<>(PouchOfPumpkinSeedsBlockEntity::new, BlockRegistry.POUCH_OF_PUMPKIN.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PouchOfWatermelonSeedsBlockEntity>> POUCH_OF_WATERMELON_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("pouch_of_watermelon_block_entity", () ->
                    new BlockEntityType<>(PouchOfWatermelonSeedsBlockEntity::new, BlockRegistry.POUCH_OF_WATERMELON.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PouchOfCocoaBeansBlockEntity>> POUCH_OF_COCOA_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("pouch_of_cocoa_block_entity", () ->
                    new BlockEntityType<>(PouchOfCocoaBeansBlockEntity::new, BlockRegistry.POUCH_OF_COCOA.get()));

    public static void initStatic()
    {
        // just to initialize static members
    }
}
