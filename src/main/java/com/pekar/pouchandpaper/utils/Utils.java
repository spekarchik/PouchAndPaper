package com.pekar.pouchandpaper.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;

import java.util.Random;

public class Utils
{
    public Utils()
    {}

    public static final int TICKS_PER_SECOND = 20;

    public static final Utils instance = new Utils();

    public static Random random = new Random();

    public final Text text = new Text();
    public final Sound sound = new Sound();

    public AABB getRenderBoundingBox(BlockPos pos)
    {
        return new AABB(pos);
    }
}
