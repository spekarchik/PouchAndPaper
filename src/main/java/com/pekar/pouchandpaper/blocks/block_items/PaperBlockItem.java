package com.pekar.pouchandpaper.blocks.block_items;

import com.pekar.pouchandpaper.tooltip.ITooltip;
import com.pekar.pouchandpaper.tooltip.ITooltipProvider;
import com.pekar.pouchandpaper.tooltip.TextStyle;
import com.pekar.pouchandpaper.utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class PaperBlockItem extends ModBlockItem implements ITooltipProvider
{
    public PaperBlockItem(Block block, Properties properties)
    {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> component, TooltipFlag flag)
    {
        ITooltipProvider.appendHoverText(this, stack, context, display, component, flag);
    }

    @Override
    public void addTooltip(ItemStack stack, Item.TooltipContext context, ITooltip tooltip, TooltipFlag flag)
    {
        if (!Utils.instance.text.showExtendedDescription(tooltip)) return;

        for (int i = 1; i <= 5; i++)
        {
            tooltip.addLine(getDescriptionId(), i).styledAs(TextStyle.DarkGray, i >= 3).apply();
        }
    }
}
