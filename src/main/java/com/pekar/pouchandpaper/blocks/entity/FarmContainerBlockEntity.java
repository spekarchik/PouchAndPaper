package com.pekar.pouchandpaper.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public abstract class FarmContainerBlockEntity extends BlockEntity
{
    private int seedsInside;

    public FarmContainerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState)
    {
        super(type, pos, blockState);
    }

    @Override
    protected void loadAdditional(ValueInput input)
    {
        super.loadAdditional(input);
        seedsInside = input.getIntOr("seeds_inside", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput output)
    {
        super.saveAdditional(output);
        output.putInt("seeds_inside", seedsInside);
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
