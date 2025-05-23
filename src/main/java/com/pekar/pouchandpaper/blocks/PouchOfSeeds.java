package com.pekar.pouchandpaper.blocks;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public abstract class PouchOfSeeds extends FarmContainer
{
    private static final VoxelShape SHAPE_EMPTY = Shapes.create(0.25, 0.0, 0.265625, 0.671875, 0.125, 0.53125);
    private static final VoxelShape SHAPE_EMPTY1 = Shapes.create(0.296875, 0, 0.375, 0.765625, 0.125, 0.765625);
    private static final VoxelShape SHAPE_EMPTY2 = Shapes.create(0.265625, 0.0, 0.296875, 0.65625, 0.125, 0.765625);
    private static final VoxelShape SHAPE_EMPTY3 = Shapes.create(0.34375, 0.0, 0.25, 0.734375, 0.125, 0.71875);

    private static final VoxelShape SHAPE_SEMI = Shapes.create(0.234375, 0.0, 0.25, 0.671875, 0.25, 0.5625);
    private static final VoxelShape SHAPE_SEMI1 = Shapes.create(0.28125, 0, 0.375, 0.765625, 0.25, 0.796875);
    private static final VoxelShape SHAPE_SEMI2 = Shapes.create(0.25, 0.0, 0.296875, 0.65625, 0.25, 0.765625);
    private static final VoxelShape SHAPE_SEMI3 = Shapes.create(0.3125, 0.0, 0.25, 0.734375, 0.25, 0.71875);

    private static final VoxelShape SHAPE_FULL = Shapes.create(0.234375, 0.0, 0.25, 0.671875, 0.4375, 0.5625);
    private static final VoxelShape SHAPE_FULL1 = Shapes.create(0.28125, 0, 0.375, 0.765625, 0.4375, 0.796875);
    private static final VoxelShape SHAPE_FULL2 = Shapes.create(0.25, 0.0, 0.296875, 0.65625, 0.4375, 0.765625);
    private static final VoxelShape SHAPE_FULL3 = Shapes.create(0.3125, 0.0, 0.25, 0.734375, 0.4375, 0.71875);

    public PouchOfSeeds(Properties properties)
    {
        super(properties);
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
}
