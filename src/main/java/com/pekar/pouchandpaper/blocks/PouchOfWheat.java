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

public class PouchOfWheat extends PouchOfSeeds implements EntityBlock
{

    protected PouchOfWheat(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING_ALONG_X, true));
    }

    @Override
    protected DeferredBlock<Block> getPouchBlock()
    {
        return BlockRegistry.POUCH_OF_WHEAT;
    }

    @Override
    protected Item getSeedsItem()
    {
        return Items.WHEAT_SEEDS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return BlockEntityRegistry.POUCH_OF_WHEAT_BLOCK_ENTITY.get().create(blockPos, blockState);
    }
}
