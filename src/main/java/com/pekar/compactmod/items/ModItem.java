package com.pekar.compactmod.items;

import com.pekar.compactmod.IHaveRecipe;
import com.pekar.compactmod.tab.CompactModTab;
import net.minecraft.world.item.Item;

public abstract class ModItem extends Item implements IHaveRecipe
{
    public ModItem()
    {
        super(new Properties().tab(CompactModTab.COMPACT_MOD_TAB));

        // TODO: recipe
    }
}
