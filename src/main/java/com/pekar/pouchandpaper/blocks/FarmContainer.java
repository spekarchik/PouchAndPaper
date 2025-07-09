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
    private static final int POUCHES_TO_ADD_PER_USE = 16;
    private static final int SEEDS_TO_ADD_PER_USE = 64;
    private static final int SEEDS_TO_EXTRACT_PER_USE = 16;
    private static final int SEEDS_PER_POUCH_TO_CRAFT = 4;
    private static final int MAX_SEEDS_INSIDE = 63 * SEEDS_PER_POUCH_TO_CRAFT;

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
            if (seedsInside + SEEDS_PER_POUCH_TO_CRAFT > MAX_SEEDS_INSIDE)
            {
                return ItemInteractionResult.CONSUME;
            }

            int pouchesToAdd = 1;
            if (!player.isShiftKeyDown())
            {
                int pouchesInHand = player.isCreative() && stack.getCount() > 0 ? stack.getMaxStackSize() : stack.getCount();
                int freeSpace = (MAX_SEEDS_INSIDE - seedsInside) / SEEDS_PER_POUCH_TO_CRAFT;
                pouchesToAdd = Math.min(Math.min(pouchesInHand, POUCHES_TO_ADD_PER_USE), freeSpace);
            }

            containerBlockEntity.setSeedsInside(seedsInside + pouchesToAdd * SEEDS_PER_POUCH_TO_CRAFT);

            if (!level.isClientSide())
            {
                if (!player.isCreative())
                    player.getItemInHand(hand).shrink(pouchesToAdd);
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

            int seedsToAdd = 1;
            if (!player.isShiftKeyDown())
            {
                int seedsInHand = player.isCreative() && stack.getCount() > 0 ? stack.getMaxStackSize() : stack.getCount();
                int freeSpace = MAX_SEEDS_INSIDE - seedsInside;
                seedsToAdd = Math.min(Math.min(seedsInHand, SEEDS_TO_ADD_PER_USE), freeSpace);
            }

            containerBlockEntity.setSeedsInside(seedsInside + seedsToAdd);

            if (!level.isClientSide())
            {
                if (!player.isCreative())
                    player.getItemInHand(hand).shrink(seedsToAdd);
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

        int seedsToWithdraw = player.isShiftKeyDown()
                ? 1
                : Math.min(seedsInside, SEEDS_TO_EXTRACT_PER_USE);

        if (!level.isClientSide())
        {
            popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seedsToWithdraw));
        }
        containerBlockEntity.setSeedsInside(seedsInside - seedsToWithdraw);

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
    {
        if (!level.isClientSide() && blockEntity instanceof FarmContainerBlockEntity containerBlockEntity)
        {
            int seedsInside = containerBlockEntity.getSeedsInside();
            if (player.isShiftKeyDown())
            {
                int pouches = seedsInside / SEEDS_PER_POUCH_TO_CRAFT;
                int seeds = seedsInside % SEEDS_PER_POUCH_TO_CRAFT;
                popResourceFromFace(level, pos, player.getDirection().getOpposite(), getPouchBlock().toStack(pouches + 1));
                if (seeds > 0)
                    popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seeds));
            }
            else
            {
                popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seedsInside + SEEDS_PER_POUCH_TO_CRAFT));
            }
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

            if (seeds > MAX_SEEDS_INSIDE - SEEDS_PER_POUCH_TO_CRAFT)
                fillLevel = 2;
            else if (seeds >= SEEDS_PER_POUCH_TO_CRAFT)
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
