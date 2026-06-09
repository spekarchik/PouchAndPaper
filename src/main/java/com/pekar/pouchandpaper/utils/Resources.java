package com.pekar.pouchandpaper.utils;

import net.minecraft.resources.Identifier;

public class Resources
{
    Resources()
    {

    }

    public Identifier createResourceLocation(String namespace, String name)
    {
        return Identifier.fromNamespaceAndPath(namespace, name); //was: new ResourceLocation(name),
    }
}
