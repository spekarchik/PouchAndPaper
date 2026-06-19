package com.pekar.pouchandpaper.utils;

import com.pekar.pouchandpaper.clientaccess.ClientAccessor;
import com.pekar.pouchandpaper.tooltip.ITooltip;
import net.minecraft.world.item.TooltipFlag;

public final class Text
{
    Text()
    {

    }

    public static boolean showExtendedDescription(ITooltip tooltip, TooltipFlag flag)
    {
        if (!ClientAccessor.tooltipAccessor().hasShiftDown())
        {
            tooltip.addLineById("description.press_shift").apply();
            return false;
        }

        return true;
    }
}
