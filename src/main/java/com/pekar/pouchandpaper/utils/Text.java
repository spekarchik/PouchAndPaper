package com.pekar.pouchandpaper.utils;

import com.mojang.blaze3d.platform.InputConstants;
import com.pekar.pouchandpaper.tooltip.ITooltip;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.TooltipFlag;

public class Text
{
    Text()
    {

    }

    public boolean showExtendedDescription(ITooltip tooltip, TooltipFlag flag)
    {
        if (!hasShiftDown())
        {
            tooltip.addLineById("description.press_shift").apply();
            return false;
        }

        return true;
    }

    private static boolean hasShiftDown()
    {
        var window = Minecraft.getInstance().getWindow();
        return InputConstants.isKeyDown(window, InputConstants.KEY_LSHIFT)
                || InputConstants.isKeyDown(window, InputConstants.KEY_RSHIFT);
    }
}
