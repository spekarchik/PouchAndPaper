package com.pekar.pouchandpaper.utils;

import net.minecraft.resources.ResourceLocation;

public class Resources
{
    Resources()
    {

    }

    public static ResourceLocation createResourceLocation(String namespace, String name)
    {
        return ResourceLocation.fromNamespaceAndPath(namespace, name);
    }
}
