package com.pekar.pouchandpaper.utils;

import com.pekar.pouchandpaper.tooltip.ITooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.TooltipFlag;

public class Text
{
    Text()
    {

    }

    public boolean showExtendedDescription(ITooltip tooltip, TooltipFlag flag)
    {
        if (!flag.hasShiftDown())
        {
            tooltip.addLineById("description.press_shift").apply();
            return false;
        }

        return true;
    }
}
