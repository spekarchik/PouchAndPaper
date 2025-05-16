package com.pekar.pouchandpaper.blocks;

import com.google.common.collect.ImmutableMap;
import com.pekar.pouchandpaper.blocks.entity.PouchOfSeedsBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.function.Function;

public abstract class PouchOfSeeds extends ModBlock
{
    private static final int MAX_SEEDS_INSIDE = 60;

    private static final VoxelShape SHAPE_X = Shapes.create(0.234375, 0.0, 0.25, 0.671875, 0.4375, 0.5625);
    private static final VoxelShape SHAPE_X1 = Shapes.create(0.234375, 0.0, 0.4375, 0.671875, 0.4375, 0.75);
    private static final VoxelShape SHAPE_X2 = Shapes.create(0.28125, 0, 0.375, 0.765625, 0.4375, 0.796875);
    private static final VoxelShape SHAPE_X3 = Shapes.create(0.25, 0.0, 0.25, 0.75, 0.4375, 0.6875);
    private static final VoxelShape SHAPE_Z = Shapes.create(0.40625, 0.0, 0.328125, 0.71875, 0.4375, 0.765625);
    private static final VoxelShape SHAPE_Z1 = Shapes.create(0.28125, 0.0, 0.265625, 0.59375, 0.4375, 0.703125);
    private static final VoxelShape SHAPE_Z2 = Shapes.create(0.25, 0.0, 0.296875, 0.65625, 0.4375, 0.765625);
    private static final VoxelShape SHAPE_Z3 = Shapes.create(0.3125, 0.0, 0.25, 0.734375, 0.4375, 0.71875);

    public static final BooleanProperty FACING_ALONG_X = BooleanProperty.create("facing_along_x");
    public static final IntegerProperty PLACING_OPTION = IntegerProperty.create("placing_option", 0, 3);

    public PouchOfSeeds(Properties properties)
    {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING_ALONG_X, true));
        registerDefaultState(stateDefinition.any().setValue(PLACING_OPTION, 0));
    }

    protected abstract DeferredBlock<Block> getPouchBlock();

    protected abstract Item getSeedsItem();

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.is(getPouchBlock().asItem()))
        {
            var blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof PouchOfSeedsBlockEntity pouchOfSeedsBlockEntity))
            {
                return InteractionResult.CONSUME;
            }

            int seedsInside = pouchOfSeedsBlockEntity.getSeedsInside();
            if (seedsInside + 4 > MAX_SEEDS_INSIDE)
            {
                return InteractionResult.CONSUME;
            }

            pouchOfSeedsBlockEntity.setSeedsInside(seedsInside + 4);

            if (!level.isClientSide())
            {
                player.getItemInHand(hand).shrink(1);
            }

            return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }
        else if (stack.is(getSeedsItem()))
        {
            var blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof PouchOfSeedsBlockEntity pouchOfSeedsBlockEntity))
            {
                return InteractionResult.CONSUME;
            }

            int seedsInside = pouchOfSeedsBlockEntity.getSeedsInside();
            if (seedsInside >= MAX_SEEDS_INSIDE)
            {
                return InteractionResult.CONSUME;
            }

            pouchOfSeedsBlockEntity.setSeedsInside(seedsInside + 1);

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
        if (!(blockEntity instanceof PouchOfSeedsBlockEntity pouchOfSeedsBlockEntity))
        {
            return InteractionResult.FAIL;
        }

        int seedsInside = pouchOfSeedsBlockEntity.getSeedsInside();
        if (seedsInside < 1)
        {
            return InteractionResult.CONSUME;
        }

        int seedsToDrop = Math.min(seedsInside, SeedsToDropNormally);
        if (!level.isClientSide())
        {
            popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seedsToDrop));
        }
        pouchOfSeedsBlockEntity.setSeedsInside(seedsInside - seedsToDrop);

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
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

    private static long mixCoords(int x, int y, int z)
    {
        long a = x * 341873128712L;
        long b = y * 132897987541L;
        long c = z * 42317861L;
        return a ^ b ^ c;
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

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
    {
        if (!level.isClientSide() && blockEntity instanceof PouchOfSeedsBlockEntity pouchOfSeedsBlockEntity)
        {
            int seedsInside = pouchOfSeedsBlockEntity.getSeedsInside();
            if (seedsInside > 0)
            {
                popResourceFromFace(level, pos, player.getDirection().getOpposite(), new ItemStack(getSeedsItem(), seedsInside));
            }
        }

        popResourceFromFace(level, pos, player.getDirection().getOpposite(), getPouchBlock().toStack());
    }
}
