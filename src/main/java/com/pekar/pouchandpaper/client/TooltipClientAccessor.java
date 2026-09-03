package com.pekar.pouchandpaper.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.pekar.pouchandpaper.clientaccess.ITooltipClientAccessor;

public class TooltipClientAccessor implements ITooltipClientAccessor
{
    @Override
    public boolean hasShiftDown()
    {
        return InputConstants.isKeyDown(InputConstants.KEY_LSHIFT)
                || InputConstants.isKeyDown(InputConstants.KEY_RSHIFT);
    }
}
