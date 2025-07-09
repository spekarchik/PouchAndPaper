package com.pekar.pouchandpaper.blocks.block_items;

import com.pekar.pouchandpaper.tooltip.ITooltip;
import com.pekar.pouchandpaper.tooltip.ITooltipProvider;
import com.pekar.pouchandpaper.utils.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class FarmSackBlockItem extends ModBlockItem implements ITooltipProvider
{
    protected final Utils utils = new Utils();

    public FarmSackBlockItem(Block block, Properties properties)
    {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag tooltipFlag)
    {
        ITooltipProvider.appendHoverText(this, stack, context, tooltipDisplay, componentConsumer, tooltipFlag);
    }

    @Override
    public void addTooltip(ItemStack stack, TooltipContext context, ITooltip tooltip, TooltipFlag flag)
    {
        if (!utils.text.showExtendedDescription(tooltip)) return;

        for (int i = 1; i <= 8; i++)
        {
            tooltip.addLine(getItemDescriptionId(), i).apply();
        }
    }

    private String getItemDescriptionId()
    {
        return "item.pouchandpaper.crop_sack";
    }
}
