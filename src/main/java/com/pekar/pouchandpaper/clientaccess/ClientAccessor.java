package com.pekar.pouchandpaper.clientaccess;

public final class ClientAccessor
{
    private static ITooltipClientAccessor tooltipClientAccessor;

    public static void init(
            ITooltipClientAccessor tooltipAccessor
    )
    {
        tooltipClientAccessor = tooltipAccessor;
    }

    public static ITooltipClientAccessor tooltipAccessor()
    {
        return tooltipClientAccessor;
    }
}
