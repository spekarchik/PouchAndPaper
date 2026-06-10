package com.pekar.pouchandpaper;

import com.pekar.pouchandpaper.blocks.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class ClientMain implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.PACK_OF_FEATHERS, RenderType.translucent());
    }
}
