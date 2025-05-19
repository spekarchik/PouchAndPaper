package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.blocks.entity.FarmContainerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

public abstract class FarmContainer extends ModBlock
{
    public static final IntegerProperty PLACING_OPTION = IntegerProperty.create("placing_option", 0, 3);
    private static final int MAX_SEEDS_INSIDE = 60;

    public FarmContainer(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(PLACING_OPTION, 0));
    }

    protected static long mixCoords(int x, int y, int z)
    {
        long a = x * 341873128712L;
        long b = y * 132897987541L;
        long c = z * 42317861L;
        return a ^ b ^ c;
    }

    protected abstract DeferredBlock<Block> getPouchBlock();

    protected abstract Item getSeedsItem();

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.is(getPouchBlock().asItem()))
        {
            var blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof FarmContainerBlockEntity containerBlockEntity))
            {
                return InteractionResult.CONSUME;
            }

            int seedsInside = containerBlockEntity.getSeedsInside();
            if (seedsInside + 4 > MAX_SEEDS_INSIDE)
            {
                return InteractionResult.CONSUME;
            }

            containerBlockEntity.setSeedsInside(seedsInside + 4);

            if (!level.isClientSide())
            {
                player.getItemInHand(hand).shrink(1);
            }

            return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }
        else if (stack.is(getSeedsItem()))
        {
            var blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof FarmContainerBlockEntity containerBlockEntity))
            {
                return InteractionResult.CONSUME;
            }

            int seedsInside = containerBlockEntity.getSeedsInside();
            if (seedsInside >= MAX_SEEDS_INSIDE)
            {
                return InteractionResult.CONSUME;
            }

            containerBlockEntity.setSeedsInside(seedsInside + 1);

            if (!level.isClientSide())
            {
                player.getItemInHand(hand).shrink(1);
            }

            return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        final int SeedsToDropNormally = 4;
        var blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof FarmContainerBlockEntity containerBlockEntity))
        {
            return InteractionResult.FAIL;
        }

        int seedsInside = containerBlockEntity.getSeedsInside();
        if (seedsInside < 1)
        {
            return InteractionResult.CONSUME;
        }

        int seedsToDrop = Math.min(seedsInside, SeedsToDropNormally);
        if (!level.isClientSide())
        {
            popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seedsToDrop));
        }
        containerBlockEntity.setSeedsInside(seedsInside - seedsToDrop);

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
    {
        if (!level.isClientSide() && blockEntity instanceof FarmContainerBlockEntity containerBlockEntity)
        {
            int seedsInside = containerBlockEntity.getSeedsInside();
            if (seedsInside > 0)
            {
                popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seedsInside));
            }
        }

        popResourceFromFace(level, pos, player.getDirection().getOpposite(), getPouchBlock().toStack());
    }
}
