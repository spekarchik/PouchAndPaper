package com.pekar.pouchandpaper.events;

import com.pekar.pouchandpaper.blocks.FarmContainer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;

public class PlayerInteractionEvents implements IEventHandler
{
    public static void init()
    {
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) ->
        {
            if (!player.isShiftKeyDown()) return InteractionResult.PASS;

            var pos = hitResult.getBlockPos();
            var state = level.getBlockState(pos);

            if (state.getBlock() instanceof FarmContainer container)
            {
                return container.useItemOnWhileSneaking(player.getItemInHand(hand), state, level, pos, player, hand);
            }

            return InteractionResult.PASS;
        });
    }
}
