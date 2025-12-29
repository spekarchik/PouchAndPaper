package com.pekar.pouchandpaper.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public abstract class PlasticPack extends FarmContainer
{
    // 			"from": [2.75, 0, 2],
    //			"to": [13.25, 14.75, 14],
    private static final VoxelShape SHAPE_FULL = Shapes.create(0.171875, 0.0, 0.125, 0.828125, 0.921875, 0.875);
//		"from": [1.75, 0, 1],
//                    "to": [14.5, 14.75, 15],
    private static final VoxelShape SHAPE_FULL1 = Shapes.create(0.109375, 0.0, 0.0625, 0.90625, 0.921875, 0.9375);
//		"from": [2.25, 0, 1.5],
//                    "to": [14, 14.75, 14.5],
    private static final VoxelShape SHAPE_FULL2 = Shapes.create(0.140625, 0.0, 0.09375, 0.875, 0.921875, 0.90625);
//			"from": [1.5, 0, 2.75],
//                    "to": [13.5, 14.75, 13.75],
    private static final VoxelShape SHAPE_FULL3 = Shapes.create(0.09375, 0.0, 0.171875, 0.84375, 0.921875, 0.859375);

//		"from": [3, 0, 2.25],
//                    "to": [13, 9.5, 14],
    private static final VoxelShape SHAPE_SEMI = Shapes.create(0.1875, 0.0, 0.140625, 0.8125, 0.59375, 0.875);
//		"from": [2, 0, 1.25],
//                    "to": [14.25, 9.5, 14.5],
    private static final VoxelShape SHAPE_SEMI1 = Shapes.create(0.125, 0.0, 0.078125, 0.890625, 0.59375, 0.90625);
//		"from": [2.5, 0, 1.75],
//                    "to": [13.75, 9.5, 14.25],
    private static final VoxelShape SHAPE_SEMI2 = Shapes.create(0.15625, 0.0, 0.109375, 0.859375, 0.59375, 0.890625);
//		"from": [2.5, 0, 2],
//                    "to": [13.5, 9.5, 14.25],
//			"from": [1.25, 0, 3],
//                    "to": [13.5, 9.5, 14],
    private static final VoxelShape SHAPE_SEMI3 = Shapes.create(0.078125, 0.0, 0.1875, 0.84375, 0.59375, 0.875);

//		"from": [3.25, 0, 2.5],
//                    "to": [12.5, 3.5, 13.5],
    private static final VoxelShape SHAPE_EMPTY = Shapes.create(0.203125, 0.0, 0.15625, 0.78125, 0.21875, 0.84375);
//		"from": [2.25, 0, 1.5],
//                    "to": [14, 3.5, 14.25],
    private static final VoxelShape SHAPE_EMPTY1 = Shapes.create(0.140625, 0.0, 0.09375, 0.875, 0.21875, 0.890625);
//		"from": [2.75, 0, 2.25],
//                    "to": [13.25, 3.5, 13.5],
    private static final VoxelShape SHAPE_EMPTY2 = Shapes.create(0.171875, 0.0, 0.140625, 0.828125, 0.21875, 0.84375);
//			"from": [1.75, 0, 3.25],
//                    "to": [13.25, 3.5, 13.75],
    private static final VoxelShape SHAPE_EMPTY3 = Shapes.create(0.109375, 0.0, 0.203125, 0.828125, 0.21875, 0.859375);

    // collision shapes use the same base values as SHAPE_* but keep the +/- 0.125 deltas that were present previously
    private static final VoxelShape COLLISION_FULL = Shapes.create(0.296875, 0.0, 0.25, 0.703125, 0.921875, 0.75);
    private static final VoxelShape COLLISION_FULL1 = Shapes.create(0.234375, 0.0, 0.1875, 0.78125, 0.921875, 0.8125);
    private static final VoxelShape COLLISION_FULL2 = Shapes.create(0.265625, 0.0, 0.21875, 0.75, 0.921875, 0.78125);
    private static final VoxelShape COLLISION_FULL3 = Shapes.create(0.09375 + 0.125, 0.0, 0.171875 + 0.125, 0.84375 - 0.125, 0.921875, 0.859375 - 0.125);

    // SEMI – height 0.75 → 0.74, width -2 pixels
    private static final VoxelShape COLLISION_SEMI = Shapes.create(0.3125, 0.0, 0.265625, 0.6875, 0.59375, 0.75);
    private static final VoxelShape COLLISION_SEMI1 = Shapes.create(0.25, 0.0, 0.203125, 0.765625, 0.59375, 0.78125);
    private static final VoxelShape COLLISION_SEMI2 = Shapes.create(0.28125, 0.0, 0.234375, 0.734375, 0.59375, 0.765625);
    private static final VoxelShape COLLISION_SEMI3 = Shapes.create(0.078125 + 0.125, 0.0, 0.1875 + 0.125, 0.84375 - 0.125, 0.59375, 0.875 - 0.125);

    // EMPTY – height w/o changes, only width updated
    private static final VoxelShape COLLISION_EMPTY = Shapes.create(0.328125, 0.0, 0.28125, 0.65625, 0.21875, 0.71875);
    private static final VoxelShape COLLISION_EMPTY1 = Shapes.create(0.265625, 0.0, 0.21875, 0.75, 0.21875, 0.765625);
    private static final VoxelShape COLLISION_EMPTY2 = Shapes.create(0.296875, 0.0, 0.265625, 0.703125, 0.21875, 0.71875);
    private static final VoxelShape COLLISION_EMPTY3 = Shapes.create(0.109375 + 0.125, 0.0, 0.203125 + 0.125, 0.828125 - 0.125, 0.21875, 0.84375 - 0.125);

    public PlasticPack(Properties properties, FarmContainerConfiguration containerConfiguration)
    {
        super(properties, containerConfiguration);
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

        // Assumption: fillLevel values map as follows:
        // 3 -> FULL, 2 -> SEMI, 1 or 0 -> EMPTY
        // placingOption selects variant 0..3 -> (no suffix, 1, 2, 3)
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
}
