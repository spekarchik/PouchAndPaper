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

public class CarrotSack extends FarmSack implements EntityBlock
{
    public CarrotSack(Properties properties, FarmContainerConfiguration containerConfiguration)
    {
        super(properties, containerConfiguration);
    }

    @Override
    protected DeferredBlock<Block> getPouchBlock()
    {
        return BlockRegistry.SACK_OF_CARROT;
    }

    @Override
    protected Item getSeedsItem()
    {
        return Items.CARROT;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return BlockEntityRegistry.SACK_OF_CARROT_BLOCK_ENTITY.get().create(blockPos, blockState);
    }
}
