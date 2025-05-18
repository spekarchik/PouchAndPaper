package com.pekar.pouchandpaper.blocks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Function;

public abstract class PouchOfSeeds extends FarmContainer
{

    private static final VoxelShape SHAPE_X = Shapes.create(0.234375, 0.0, 0.25, 0.671875, 0.4375, 0.5625);
    private static final VoxelShape SHAPE_X1 = Shapes.create(0.234375, 0.0, 0.4375, 0.671875, 0.4375, 0.75);
    private static final VoxelShape SHAPE_X2 = Shapes.create(0.28125, 0, 0.375, 0.765625, 0.4375, 0.796875);
    private static final VoxelShape SHAPE_X3 = Shapes.create(0.25, 0.0, 0.25, 0.75, 0.4375, 0.6875);
    private static final VoxelShape SHAPE_Z = Shapes.create(0.40625, 0.0, 0.328125, 0.71875, 0.4375, 0.765625);
    private static final VoxelShape SHAPE_Z1 = Shapes.create(0.28125, 0.0, 0.265625, 0.59375, 0.4375, 0.703125);
    private static final VoxelShape SHAPE_Z2 = Shapes.create(0.25, 0.0, 0.296875, 0.65625, 0.4375, 0.765625);
    private static final VoxelShape SHAPE_Z3 = Shapes.create(0.3125, 0.0, 0.25, 0.734375, 0.4375, 0.71875);

    public static final BooleanProperty FACING_ALONG_X = BooleanProperty.create("facing_along_x");

    public PouchOfSeeds(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING_ALONG_X, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING_ALONG_X, PLACING_OPTION);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context)
    {
        var pos = context.getClickedPos();
        long seed = mixCoords(pos.getX(), pos.getY(), pos.getZ());
        var rand = new Random(seed);
        int placingOption = rand.nextInt(4);
        var direction = context.getHorizontalDirection();
        return switch (direction)
        {
            case EAST, WEST -> defaultBlockState().setValue(FACING_ALONG_X, false).setValue(PLACING_OPTION, placingOption);
            default -> defaultBlockState().setValue(FACING_ALONG_X, true).setValue(PLACING_OPTION, placingOption);
        };
    }

    @Override
    protected ImmutableMap<BlockState, VoxelShape> getShapeForEachState(Function<BlockState, VoxelShape> voxelShapeFunction)
    {
        var defaultBlockState = defaultBlockState();
        return ImmutableMap.of(
                defaultBlockState.setValue(FACING_ALONG_X, true).setValue(PLACING_OPTION, 0), SHAPE_X,
                defaultBlockState.setValue(FACING_ALONG_X, true).setValue(PLACING_OPTION, 1), SHAPE_X1,
                defaultBlockState.setValue(FACING_ALONG_X, true).setValue(PLACING_OPTION, 2), SHAPE_X2,
                defaultBlockState.setValue(FACING_ALONG_X, true).setValue(PLACING_OPTION, 3), SHAPE_X3,
                defaultBlockState.setValue(FACING_ALONG_X, false).setValue(PLACING_OPTION, 0), SHAPE_Z,
                defaultBlockState.setValue(FACING_ALONG_X, false).setValue(PLACING_OPTION, 1), SHAPE_Z1,
                defaultBlockState.setValue(FACING_ALONG_X, false).setValue(PLACING_OPTION, 2), SHAPE_Z2,
                defaultBlockState.setValue(FACING_ALONG_X, false).setValue(PLACING_OPTION, 3), SHAPE_Z3
        );
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
    {
        return getShapeByBlockState(blockState);
    }

    private VoxelShape getShapeByBlockState(BlockState blockState)
    {
        boolean facingAlongX = blockState.getValue(FACING_ALONG_X);
        int placingOption = blockState.getValue(PLACING_OPTION);

        if (facingAlongX)
        {
            return switch (placingOption)
            {
                case 0 -> SHAPE_X;
                case 1 -> SHAPE_X1;
                case 2 -> SHAPE_X2;
                case 3 -> SHAPE_X3;
                default -> throw new IllegalStateException("Unexpected value: " + placingOption);
            };
        }
        else
        {
            return switch (placingOption)
            {
                case 0 -> SHAPE_Z;
                case 1 -> SHAPE_Z1;
                case 2 -> SHAPE_Z2;
                case 3 -> SHAPE_Z3;
                default -> throw new IllegalStateException("Unexpected value: " + placingOption);
            };
        }
    }
}
