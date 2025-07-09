package com.pekar.pouchandpaper.events;

import com.pekar.pouchandpaper.blocks.FarmContainer;
import net.minecraft.util.TriState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class PlayerInteractionEvents implements IEventHandler
{
    @SubscribeEvent
    public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        var player = event.getEntity();

        if (!player.isShiftKeyDown()) return;

        var level = player.level();
        var pos = event.getPos();
        var blockUseOn = level.getBlockState(pos);

        if (blockUseOn.getBlock() instanceof FarmContainer container)
        {
            event.setUseBlock(TriState.TRUE);
        }
    }
}
