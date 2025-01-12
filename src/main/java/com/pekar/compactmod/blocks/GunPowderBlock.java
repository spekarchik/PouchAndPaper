package com.pekar.compactmod.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GunPowderBlock extends ModFallingBlock
{
    //    /give @p compactmod:gunpowderblock
    public static final MapCodec<GunPowderBlock> CODEC = simpleCodec(GunPowderBlock::new);
            //RecordCodecBuilder.mapCodec(
            //(p_344654_) -> p_344654_.group(
              //      BuiltInRegistries.BLOCK.byNameCodec().fieldOf("concrete").forGetter((p_304618_) -> p_304618_.concrete), propertiesCodec()).apply(p_344654_, ConcretePowderBlock::new));

    public GunPowderBlock()
    {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
                .sound(SoundType.SNOW)
                .strength(0.2f));
    }

    public GunPowderBlock(BlockBehaviour.Properties properties)
    {
        super(properties);
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face)
    {
        return 100;
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec()
    {
        return CODEC;
    }
}
