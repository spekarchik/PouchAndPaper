package com.pekar.pouchandpaper.blocks.block_items;

import com.pekar.pouchandpaper.tooltip.ITooltip;
import com.pekar.pouchandpaper.tooltip.ITooltipProvider;
import com.pekar.pouchandpaper.tooltip.TextStyle;
import com.pekar.pouchandpaper.utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class BurntPaperBlockItem extends ModBlockItem implements ITooltipProvider
{
    public BurntPaperBlockItem(Block block, Properties properties)
    {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipDisplay, TooltipFlag tooltipFlag)
    {
        ITooltipProvider.appendHoverText(this, stack, context, tooltipDisplay, tooltipFlag);
    }

    @Override
    public void addTooltip(ItemStack stack, TooltipContext context, ITooltip tooltip, TooltipFlag flag)
    {
        if (!Utils.instance.text.showExtendedDescription(tooltip)) return;

        for (int i = 1; i <= 4; i++)
        {
            tooltip.addLine(getDescriptionId(), i)
                    .styledAs(TextStyle.Notice, i == 1)
                    .styledAs(TextStyle.DarkGray, i == 4)
                    .apply();
        }
    }
}
