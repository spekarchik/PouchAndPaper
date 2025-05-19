package com.pekar.pouchandpaper.blocks;

import com.google.common.collect.ImmutableMap;
import com.pekar.pouchandpaper.blocks.entity.FarmContainerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
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

    // FULL – высота 1.0 → 0.99, ширина -2 пикселя с каждой стороны (0.125 = 2px)
    private static final VoxelShape COLLISION_FULL = Shapes.create(0.1875 + 0.125, 0.0, 0.0625 + 0.125, 0.84375 - 0.125, 0.99, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_FULL1 = Shapes.create(0.0625 + 0.125, 0.0, 0.25 + 0.125, 0.9375 - 0.125, 0.99, 0.90625 - 0.125);
    private static final VoxelShape COLLISION_FULL2 = Shapes.create(0.078125 + 0.125, 0.0, 0.09375 + 0.125, 0.828125 - 0.125, 0.99, 0.953125 - 0.125);
    private static final VoxelShape COLLISION_FULL3 = Shapes.create(0.109375 + 0.125, 0.0, 0.109375 + 0.125, 0.953125 - 0.125, 0.99, 0.953125 - 0.125);

    // SEMI – высота 0.75 → 0.74, ширина -2 пикселя
    private static final VoxelShape COLLISION_SEMI = Shapes.create(0.1875 + 0.125, 0.0, 0.0625 + 0.125, 0.84375 - 0.125, 0.74, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_SEMI1 = Shapes.create(0.0625 + 0.125, 0.0, 0.25 + 0.125, 0.9375 - 0.125, 0.74, 0.90625 - 0.125);
    private static final VoxelShape COLLISION_SEMI2 = Shapes.create(0.078125 + 0.125, 0.0, 0.09375 + 0.125, 0.828125 - 0.125, 0.74, 0.953125 - 0.125);
    private static final VoxelShape COLLISION_SEMI3 = Shapes.create(0.109375 + 0.125, 0.0, 0.109375 + 0.125, 0.953125 - 0.125, 0.74, 0.953125 - 0.125);

    // EMPTY – высота без изменений, только ширина
    private static final VoxelShape COLLISION_EMPTY = Shapes.create(0.1875 + 0.125, 0.0, 0.0625 + 0.125, 0.84375 - 0.125, 0.5, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_EMPTY1 = Shapes.create(0.0625 + 0.125, 0.0, 0.234375 + 0.125, 0.953125 - 0.125, 0.5, 0.890625 - 0.125);
    private static final VoxelShape COLLISION_EMPTY2 = Shapes.create(0.046875 + 0.125, 0.0, 0.0625 + 0.125, 0.8125 - 0.125, 0.5, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_EMPTY3 = Shapes.create(0.09375 + 0.125, 0.0, 0.125 + 0.125, 0.90625 - 0.125, 0.5, 0.9375 - 0.125);

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
    protected ImmutableMap<BlockState, VoxelShape> getShapeForEachState(Function<BlockState, VoxelShape> voxelShapeFunction)
    {
        var defaultBlockState = defaultBlockState();
        return ImmutableMap.<BlockState, VoxelShape>builder()
                .put(defaultBlockState.setValue(FILL_LEVEL, 0).setValue(PLACING_OPTION, 0), SHAPE_EMPTY)
                .put(defaultBlockState.setValue(FILL_LEVEL, 0).setValue(PLACING_OPTION, 1), SHAPE_EMPTY1)
                .put(defaultBlockState.setValue(FILL_LEVEL, 0).setValue(PLACING_OPTION, 2), SHAPE_EMPTY2)
                .put(defaultBlockState.setValue(FILL_LEVEL, 0).setValue(PLACING_OPTION, 3), SHAPE_EMPTY3)
                .put(defaultBlockState.setValue(FILL_LEVEL, 1).setValue(PLACING_OPTION, 0), SHAPE_SEMI)
                .put(defaultBlockState.setValue(FILL_LEVEL, 1).setValue(PLACING_OPTION, 1), SHAPE_SEMI1)
                .put(defaultBlockState.setValue(FILL_LEVEL, 1).setValue(PLACING_OPTION, 2), SHAPE_SEMI2)
                .put(defaultBlockState.setValue(FILL_LEVEL, 1).setValue(PLACING_OPTION, 3), SHAPE_SEMI3)
                .put(defaultBlockState.setValue(FILL_LEVEL, 2).setValue(PLACING_OPTION, 0), SHAPE_FULL)
                .put(defaultBlockState.setValue(FILL_LEVEL, 2).setValue(PLACING_OPTION, 1), SHAPE_FULL1)
                .put(defaultBlockState.setValue(FILL_LEVEL, 2).setValue(PLACING_OPTION, 2), SHAPE_FULL2)
                .put(defaultBlockState.setValue(FILL_LEVEL, 2).setValue(PLACING_OPTION, 3), SHAPE_FULL3)
                .build();
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
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
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

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        int fillLevel = state.getValue(FILL_LEVEL);
        int placingOption = state.getValue(PLACING_OPTION);

        return switch (fillLevel)
        {
            case 0 -> switch (placingOption)
            {
                case 0 -> COLLISION_EMPTY;
                case 1 -> COLLISION_EMPTY1;
                case 2 -> COLLISION_EMPTY2;
                case 3 -> COLLISION_EMPTY3;
                default -> throw new IllegalStateException("Unexpected placingOption: " + placingOption);
            };
            case 1 -> switch (placingOption)
            {
                case 0 -> COLLISION_SEMI;
                case 1 -> COLLISION_SEMI1;
                case 2 -> COLLISION_SEMI2;
                case 3 -> COLLISION_SEMI3;
                default -> throw new IllegalStateException("Unexpected placingOption: " + placingOption);
            };
            default -> switch (placingOption)
            {
                case 0 -> COLLISION_FULL;
                case 1 -> COLLISION_FULL1;
                case 2 -> COLLISION_FULL2;
                case 3 -> COLLISION_FULL3;
                default -> throw new IllegalStateException("Unexpected placingOption: " + placingOption);
            };
        };
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
