package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.blocks.entity.BlockEntityRegistry;
import com.pekar.pouchandpaper.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

public class PackOfLeaves extends PlasticPack implements EntityBlock
{
    public PackOfLeaves(Properties properties, FarmContainerConfiguration containerConfiguration)
    {
        super(properties, containerConfiguration);
    }

    @Override
    protected DeferredBlock<Block> getPouchBlock()
    {
        return BlockRegistry.PACK_OF_LEAVES;
    }

    @Override
    protected Item getSeedsItem()
    {
        return Blocks.LEAF_LITTER.asItem();
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return BlockEntityRegistry.PACK_OF_LEAVES_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    protected void playAddSeedsSound(Player player, BlockPos pos)
    {
        Utils.instance.sound.playSoundByBlock(player, pos, SoundEvents.LEAF_LITTER_BREAK);
    }

    @Override
    protected void playExtractSeedsSound(Player player, BlockPos pos)
    {
        Utils.instance.sound.playSoundByBlock(player, pos, SoundEvents.AZALEA_LEAVES_HIT);
    }
}
