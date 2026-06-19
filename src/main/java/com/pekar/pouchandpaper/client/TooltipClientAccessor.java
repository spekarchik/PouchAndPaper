package com.pekar.pouchandpaper.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.pekar.pouchandpaper.clientaccess.ITooltipClientAccessor;
import net.minecraft.client.Minecraft;

public class TooltipClientAccessor implements ITooltipClientAccessor
{
    @Override
    public boolean hasShiftDown()
    {
        var window = Minecraft.getInstance().getWindow();
        return InputConstants.isKeyDown(window, InputConstants.KEY_LSHIFT)
                || InputConstants.isKeyDown(window, InputConstants.KEY_RSHIFT);
    }
}
