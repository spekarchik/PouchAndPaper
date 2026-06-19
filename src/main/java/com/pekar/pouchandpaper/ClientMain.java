package com.pekar.pouchandpaper;

import com.pekar.pouchandpaper.client.TooltipClientAccessor;
import com.pekar.pouchandpaper.clientaccess.ClientAccessor;
import net.fabricmc.api.ClientModInitializer;

public class ClientMain implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ClientAccessor.init(
                new TooltipClientAccessor()
        );
    }
}
