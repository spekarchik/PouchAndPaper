package com.pekar.pouchandpaper;

import com.pekar.pouchandpaper.blocks.entity.BlockEntityRegistry;
import com.pekar.pouchandpaper.blocks.entity.renderer.PlasticPackRenderer;
import com.pekar.pouchandpaper.client.TooltipClientAccessor;
import com.pekar.pouchandpaper.clientaccess.ClientAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class ClientMain implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModelLoadingPlugin.register(PlasticPackRenderer::registerModels);
        BlockEntityRenderers.register(BlockEntityRegistry.PACK_OF_FEATHERS_BLOCK_ENTITY,
                context -> new PlasticPackRenderer<>("feathers_pack"));
        BlockEntityRenderers.register(BlockEntityRegistry.PACK_OF_LEAVES_BLOCK_ENTITY,
                context -> new PlasticPackRenderer<>("leaves_pack"));
        ClientAccessor.init(
                new TooltipClientAccessor()
        );
    }
}
