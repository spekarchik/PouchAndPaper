package com.pekar.compactmod.blocks;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;

public interface ISupportHoverText
{
    default MutableComponent getDisplayName(Item item, int lineNumber)
    {
        return Component.translatable(item.getDescriptionId() + ".desc" + lineNumber);
    }
}
