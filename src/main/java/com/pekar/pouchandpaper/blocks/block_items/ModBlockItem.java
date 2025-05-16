package com.pekar.pouchandpaper.blocks.block_items;

import com.pekar.pouchandpaper.Main;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModBlockItem extends BlockItem
{
    public ModBlockItem(Block block, Item.Properties properties)
    {
        super(block, properties);
    }

    @Override
    public String getDescriptionId()
    {
        return super.getDescriptionId().replace("block." + Main.MODID, "item." + Main.MODID);
    }
}
