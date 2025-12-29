package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.blocks.entity.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

public class PouchOfCocoaBeans extends PouchOfSeeds implements EntityBlock
{

    protected PouchOfCocoaBeans(Properties properties, FarmContainerConfiguration containerConfiguration)
    {
        super(properties, containerConfiguration);
    }

    @Override
    protected DeferredBlock<Block> getPouchBlock()
    {
        return BlockRegistry.POUCH_OF_COCOA;
    }

    @Override
    protected Item getSeedsItem()
    {
        return Items.COCOA_BEANS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return BlockEntityRegistry.POUCH_OF_COCOA_BLOCK_ENTITY.get().create(blockPos, blockState);
    }
}
