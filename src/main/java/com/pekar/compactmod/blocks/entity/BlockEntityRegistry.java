package com.pekar.compactmod.blocks.entity;

import com.pekar.compactmod.Main;
import com.pekar.compactmod.blocks.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockEntityRegistry
{
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BurntPaperBlockEntity>> BURNT_PAPER_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("burnt_paper_block_entity", () ->
                    new BlockEntityType<>(BurntPaperBlockEntity::new, BlockRegistry.BURNT_PAPER_BLOCK.get()));

    public static void initStatic()
    {
        // just to initialize static members
    }
}
