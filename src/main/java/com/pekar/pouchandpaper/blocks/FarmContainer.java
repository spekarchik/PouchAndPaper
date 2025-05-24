package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.blocks.entity.FarmContainerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public abstract class FarmContainer extends ModBlock
{
    public static final IntegerProperty PLACING_OPTION = IntegerProperty.create("placing_option", 0, 3);
    public static final IntegerProperty FILL_LEVEL = IntegerProperty.create("fill_level", 0, 2);
    private static final int MAX_SEEDS_INSIDE = 124;

    public FarmContainer(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(PLACING_OPTION, 0));
        registerDefaultState(stateDefinition.any().setValue(FILL_LEVEL, 0));
    }

    protected abstract DeferredBlock<Block> getPouchBlock();

    protected abstract Item getSeedsItem();

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FILL_LEVEL, PLACING_OPTION);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context)
    {
        var pos = context.getClickedPos();
        long seed = mixCoords(pos.getX(), pos.getY(), pos.getZ());
        var rand = new Random(seed);
        int placingOption = rand.nextInt(4);
        return defaultBlockState().setValue(FILL_LEVEL, 0).setValue(PLACING_OPTION, placingOption);
    }

    private static long mixCoords(int x, int y, int z)
    {
        long a = x * 341873128712L;
        long b = y * 132897987541L;
        long c = z * 42317861L;
        return a ^ b ^ c;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        var consume = useItemOnInternal(stack, state, level, pos, player, hand);

        updateFillLevel(state, level, pos);

        if (consume != null)
        {
            return consume;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    private ItemInteractionResult useItemOnInternal(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand)
    {
        if (stack.is(getPouchBlock().asItem()))
        {
            var blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof FarmContainerBlockEntity containerBlockEntity))
            {
                return ItemInteractionResult.CONSUME;
            }

            int seedsInside = containerBlockEntity.getSeedsInside();
            if (seedsInside + 4 > MAX_SEEDS_INSIDE)
            {
                return ItemInteractionResult.CONSUME;
            }

            containerBlockEntity.setSeedsInside(seedsInside + 4);

            if (!level.isClientSide())
            {
                player.getItemInHand(hand).shrink(1);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
        else if (stack.is(getSeedsItem()))
        {
            var blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof FarmContainerBlockEntity containerBlockEntity))
            {
                return ItemInteractionResult.CONSUME;
            }

            int seedsInside = containerBlockEntity.getSeedsInside();
            if (seedsInside >= MAX_SEEDS_INSIDE)
            {
                return ItemInteractionResult.CONSUME;
            }

            containerBlockEntity.setSeedsInside(seedsInside + 1);

            if (!level.isClientSide())
            {
                player.getItemInHand(hand).shrink(1);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }

        return null;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        var result = useWithoutItemInternal(level, pos, player);
        updateFillLevel(state, level, pos);
        return result;
    }

    private InteractionResult useWithoutItemInternal(Level level, BlockPos pos, Player player)
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

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
    {
        if (!level.isClientSide() && blockEntity instanceof FarmContainerBlockEntity containerBlockEntity)
        {
            int seedsInside = containerBlockEntity.getSeedsInside();
            popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seedsInside + 4));
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        var blockBelow = level.getBlockState(pos.below());
        return blockBelow.isSolidRender(level, pos)
                || blockBelow.is(Blocks.FARMLAND)
                || blockBelow.is(Blocks.DIRT_PATH);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston)
    {
        if (!canSurvive(state, level, pos))
        {
            var blockEntity = level.getBlockEntity(pos);
            if (!level.isClientSide() && blockEntity instanceof FarmContainerBlockEntity containerBlockEntity)
            {
                int seedsInside = containerBlockEntity.getSeedsInside();
                if (seedsInside > 0)
                {
                    popResource(level, pos, new ItemStack(getSeedsItem(), seedsInside));
                }

                popResource(level, pos, getPouchBlock().toStack());
                level.removeBlock(pos, false);
            }
        }

        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    private void updateFillLevel(BlockState state, Level level, BlockPos pos)
    {
        var blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FarmContainerBlockEntity containerBlockEntity)
        {
            int seeds = containerBlockEntity.getSeedsInside();
            int fillLevel;

            if (seeds > MAX_SEEDS_INSIDE - 4)
                fillLevel = 2;
            else if (seeds > 3)
                fillLevel = 1;
            else
                fillLevel = 0;

            if (state.getValue(FILL_LEVEL) != fillLevel)
            {
                var newState = state.setValue(FILL_LEVEL, fillLevel);
                level.setBlock(pos, newState, Block.UPDATE_ALL);
            }
        }
    }
}
