package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PouchOfSeedsBlockEntity extends BlockEntity
{
    private int seedsInside;

    public PouchOfSeedsBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState)
    {
        super(type, pos, blockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        seedsInside = tag.getIntOr("seeds_inside", 0);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);
        saveModTag(tag);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries)
    {
        var tag = new CompoundTag();
        saveModTag(tag);
        return tag;
    }

    public void setSeedsInside(int pouchesInside)
    {
        this.seedsInside = pouchesInside;
        setChanged();
    }

    public int getSeedsInside()
    {
        return this.seedsInside;
    }

    private void saveModTag(CompoundTag tag)
    {
        tag.putInt("seeds_inside", seedsInside);
    }
}
