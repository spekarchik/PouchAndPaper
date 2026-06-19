package com.pekar.pouchandpaper.client;

import com.pekar.pouchandpaper.clientaccess.ITooltipClientAccessor;
import net.minecraft.client.gui.screens.Screen;

public class TooltipClientAccessor implements ITooltipClientAccessor
{
    @Override
    public boolean hasShiftDown()
    {
        return Screen.hasShiftDown();
    }
}
