package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.blocks.entity.BlockEntityRegistry;
import com.pekar.pouchandpaper.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

public class PackOfFeathers extends PlasticPack implements EntityBlock
{
    public PackOfFeathers(Properties properties, FarmContainerConfiguration containerConfiguration)
    {
        super(properties, containerConfiguration);
    }

    @Override
    protected DeferredBlock<Block> getPouchBlock()
    {
        return BlockRegistry.PACK_OF_FEATHERS;
    }

    @Override
    protected Item getSeedsItem()
    {
        return Items.FEATHER;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return BlockEntityRegistry.PACK_OF_FEATHERS_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    protected void playAddSeedsSound(Player player, BlockPos pos)
    {
        Utils.instance.sound.playSoundByBlock(player, pos, SoundEvents.BUNDLE_INSERT);
    }

    @Override
    protected void playExtractSeedsSound(Player player, BlockPos pos)
    {
        Utils.instance.sound.playSoundByBlock(player, pos, SoundEvents.BUNDLE_REMOVE_ONE);
    }
}
