package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.blocks.entity.FarmContainerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Function;

public abstract class FarmSack extends FarmContainer
{
    private static final VoxelShape SHAPE_FULL = Shapes.create(0.1875, 0.0, 0.0625, 0.84375, 1.0, 0.9375);
    private static final VoxelShape SHAPE_FULL1 = Shapes.create(0.0625, 0.0, 0.25, 0.9375, 1.0, 0.90625);
    private static final VoxelShape SHAPE_FULL2 = Shapes.create(0.078125, 0.0, 0.09375, 0.828125, 1.0, 0.953125);
    private static final VoxelShape SHAPE_FULL3 = Shapes.create(0.109375, 0.0, 0.109375, 0.953125, 1.0, 0.953125);

    private static final VoxelShape SHAPE_SEMI = Shapes.create(0.1875, 0.0, 0.0625, 0.84375, 0.75, 0.9375);
    private static final VoxelShape SHAPE_SEMI1 = Shapes.create(0.0625, 0.0, 0.25, 0.9375, 0.75, 0.90625);
    private static final VoxelShape SHAPE_SEMI2 = Shapes.create(0.078125, 0.0, 0.09375, 0.828125, 0.75, 0.953125);
    private static final VoxelShape SHAPE_SEMI3 = Shapes.create(0.109375, 0.0, 0.109375, 0.953125, 0.75, 0.953125);

    private static final VoxelShape SHAPE_EMPTY = Shapes.create(0.1875, 0.0, 0.0625, 0.84375, 0.5, 0.9375);
    private static final VoxelShape SHAPE_EMPTY1 = Shapes.create(0.0625, 0.0, 0.234375, 0.953125, 0.5, 0.890625);
    private static final VoxelShape SHAPE_EMPTY2 = Shapes.create(0.046875, 0.0, 0.0625, 0.8125, 0.5, 0.9375);
    private static final VoxelShape SHAPE_EMPTY3 = Shapes.create(0.09375, 0.0, 0.125, 0.90625, 0.5, 0.9375);

    public static final IntegerProperty FILL_LEVEL = IntegerProperty.create("fill_level", 0, 2);

    public FarmSack(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FILL_LEVEL, 0));
    }

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

    @Override
    protected Function<BlockState, VoxelShape> getShapeForEachState(Function<BlockState, VoxelShape> voxelShapeFunction)
    {
        return this::getShapeByBlockState;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
    {
        return getShapeByBlockState(blockState);
    }

    private VoxelShape getShapeByBlockState(BlockState blockState)
    {
        int fillLevel = blockState.getValue(FILL_LEVEL);
        int placingOption = blockState.getValue(PLACING_OPTION);

        if (fillLevel == 0)
        {
            return switch (placingOption)
            {
                case 0 -> SHAPE_EMPTY;
                case 1 -> SHAPE_EMPTY1;
                case 2 -> SHAPE_EMPTY2;
                case 3 -> SHAPE_EMPTY3;
                default -> throw new IllegalStateException("Unexpected value: " + placingOption);
            };
        }
        else if (fillLevel == 1)
        {
            return switch (placingOption)
            {
                case 0 -> SHAPE_SEMI;
                case 1 -> SHAPE_SEMI1;
                case 2 -> SHAPE_SEMI2;
                case 3 -> SHAPE_SEMI3;
                default -> throw new IllegalStateException("Unexpected value: " + placingOption);
            };
        }
        else
        {
            return switch (placingOption)
            {
                case 0 -> SHAPE_FULL;
                case 1 -> SHAPE_FULL1;
                case 2 -> SHAPE_FULL2;
                case 3 -> SHAPE_FULL3;
                default -> throw new IllegalStateException("Unexpected value: " + placingOption);
            };
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.is(getPouchBlock().asItem()) || stack.is(getSeedsItem()))
        {
            updateFillLevel(state, level, pos);
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        updateFillLevel(state, level, pos);
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    private void updateFillLevel(BlockState state, Level level, BlockPos pos)
    {
        var blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FarmContainerBlockEntity containerBlockEntity)
        {
            int seeds = containerBlockEntity.getSeedsInside();
            int fillLevel;

            if (seeds > 42)
                fillLevel = 2;
            else if (seeds > 21)
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
