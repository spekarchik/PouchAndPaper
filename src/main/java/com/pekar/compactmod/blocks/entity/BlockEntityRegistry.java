package com.pekar.compactmod.blocks.entity;

import com.pekar.compactmod.Main;
import com.pekar.compactmod.blocks.BlockRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockEntityRegistry
{
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BurningPaperBlockEntity>> BURNING_PAPER_BLOCK_ENTITY =
            Main.BLOCK_ENTITIES.register("burning_paperblock_entity", () ->
                    new BlockEntityType<>(BurningPaperBlockEntity::new, BlockRegistry.BURNING_PAPER_BLOCK.get()));

    public static void initStatic()
    {
        // just to initialize static members
    }
}
