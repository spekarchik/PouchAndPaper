package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class BurntPaperBlockEntity extends BlockEntity implements BlockEntityTicker<BurntPaperBlockEntity>
{
    private static final int CREEPER_SEEK_RADUIS = 70;
    private static final int BEE_SEEK_RADUIS = 50;

    public BurntPaperBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BlockEntityRegistry.BURNT_PAPER_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState blockState, BurntPaperBlockEntity burntPaperBlockEntity)
    {
        if (level.getGameTime() % 40 != 0) return;
        if (!(level instanceof ServerLevel serverLevel)) return;
        if (level.getBlockState(pos.above()).is(Blocks.FIRE)) return;

        var creepers = level.getEntitiesOfClass(Creeper.class, new AABB(pos).inflate(CREEPER_SEEK_RADUIS));

        for (var creeper : creepers)
        {
            var navigation = creeper.getNavigation();
            navigation.moveTo(pos.getX(), pos.getY(), pos.getZ(), 1.0);
        }

        makeBeesAvoid(serverLevel, pos);
    }

    private void makeBeesAvoid(ServerLevel level, BlockPos pos)
    {
        List<Bee> bees = level.getEntitiesOfClass(Bee.class, new AABB(pos).inflate(BEE_SEEK_RADUIS));

        for (Bee bee : bees)
        {
            var navigation = bee.getNavigation();

            if (navigation.isDone() || navigation.isStuck())
            {
                Vec3 beePos = bee.position();
                Vec3 blockCenter = Vec3.atCenterOf(pos);
                Vec3 escapeDirection = beePos.subtract(blockCenter).normalize().scale(BEE_SEEK_RADUIS);
                Vec3 escapePosVec = beePos.add(escapeDirection);

                int escapeY = escapePosVec.y < blockCenter.y ? pos.getY() + 1 : (int)escapePosVec.y;
                BlockPos escapePos = new BlockPos((int)escapePosVec.x, escapeY, (int)escapePosVec.z);
                bee.setHivePos(null);
                if (bee.hasSavedFlowerPos() && level.getBlockState(bee.getSavedFlowerPos()).is(BlockTags.FLOWERS))
                    bee.setSavedFlowerPos(bee.getSavedFlowerPos().above(2));

                if (level.getBlockState(escapePos).isAir())
                {
                    navigation.moveTo(escapePos.getX(), escapePos.getY(), escapePos.getZ(), 1.2);
                }
            }
        }
    }
}
