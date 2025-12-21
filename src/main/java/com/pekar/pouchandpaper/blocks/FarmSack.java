package com.pekar.pouchandpaper.blocks;

import com.pekar.pouchandpaper.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

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

    // FULL – height 1.0 → 0.99, width -2 pixels for each side (0.125 = 2px)
    private static final VoxelShape COLLISION_FULL = Shapes.create(0.1875 + 0.125, 0.0, 0.0625 + 0.125, 0.84375 - 0.125, 0.99, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_FULL1 = Shapes.create(0.0625 + 0.125, 0.0, 0.25 + 0.125, 0.9375 - 0.125, 0.99, 0.90625 - 0.125);
    private static final VoxelShape COLLISION_FULL2 = Shapes.create(0.078125 + 0.125, 0.0, 0.09375 + 0.125, 0.828125 - 0.125, 0.99, 0.953125 - 0.125);
    private static final VoxelShape COLLISION_FULL3 = Shapes.create(0.109375 + 0.125, 0.0, 0.109375 + 0.125, 0.953125 - 0.125, 0.99, 0.953125 - 0.125);

    // SEMI – height 0.75 → 0.74, width -2 pixels
    private static final VoxelShape COLLISION_SEMI = Shapes.create(0.1875 + 0.125, 0.0, 0.0625 + 0.125, 0.84375 - 0.125, 0.74, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_SEMI1 = Shapes.create(0.0625 + 0.125, 0.0, 0.25 + 0.125, 0.9375 - 0.125, 0.74, 0.90625 - 0.125);
    private static final VoxelShape COLLISION_SEMI2 = Shapes.create(0.078125 + 0.125, 0.0, 0.09375 + 0.125, 0.828125 - 0.125, 0.74, 0.953125 - 0.125);
    private static final VoxelShape COLLISION_SEMI3 = Shapes.create(0.109375 + 0.125, 0.0, 0.109375 + 0.125, 0.953125 - 0.125, 0.74, 0.953125 - 0.125);

    // EMPTY – height w/o changes, only width updated
    private static final VoxelShape COLLISION_EMPTY = Shapes.create(0.1875 + 0.125, 0.0, 0.0625 + 0.125, 0.84375 - 0.125, 0.5, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_EMPTY1 = Shapes.create(0.0625 + 0.125, 0.0, 0.234375 + 0.125, 0.953125 - 0.125, 0.5, 0.890625 - 0.125);
    private static final VoxelShape COLLISION_EMPTY2 = Shapes.create(0.046875 + 0.125, 0.0, 0.0625 + 0.125, 0.8125 - 0.125, 0.5, 0.9375 - 0.125);
    private static final VoxelShape COLLISION_EMPTY3 = Shapes.create(0.09375 + 0.125, 0.0, 0.125 + 0.125, 0.90625 - 0.125, 0.5, 0.9375 - 0.125);

    public FarmSack(Properties properties)
    {
        super(properties);
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

    @Override
    protected void playAddSeedsSound(Player player, BlockPos pos)
    {
        Utils.instance.sound.playSoundByBlock(player, pos, SoundEvents.NETHERRACK_BREAK);
    }

    @Override
    protected void playExtractSeedsSound(Player player, BlockPos pos)
    {
        Utils.instance.sound.playSoundByBlock(player, pos, SoundEvents.NETHERRACK_HIT);
    }
}
