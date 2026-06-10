package com.pekar.pouchandpaper;

import com.pekar.pouchandpaper.blocks.entity.BlockEntityRegistry;
import com.pekar.pouchandpaper.blocks.entity.renderer.PackOfFeathersRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ClientMain implements ClientModInitializer
{
    public static final List<ResourceLocation> FEATHERS_PACK_PLASTIC_MODELS = createFeathersPackPlasticModels();

    @Override
    public void onInitializeClient()
    {
        ModelLoadingPlugin.register(context -> context.addModels(FEATHERS_PACK_PLASTIC_MODELS));
        BlockEntityRenderers.register(BlockEntityRegistry.PACK_OF_FEATHERS_BLOCK_ENTITY, PackOfFeathersRenderer::new);
    }

    private static List<ResourceLocation> createFeathersPackPlasticModels()
    {
        var models = new ArrayList<ResourceLocation>();
        for (var fillLevel : List.of("empty", "semi", "full"))
        {
            models.add(ResourceLocation.fromNamespaceAndPath(Main.MODID, "block/feathers_pack_plastic_" + fillLevel));
            models.add(ResourceLocation.fromNamespaceAndPath(Main.MODID, "block/feathers_pack_plastic_" + fillLevel + "2"));
            models.add(ResourceLocation.fromNamespaceAndPath(Main.MODID, "block/feathers_pack_plastic_" + fillLevel + "3"));
            models.add(ResourceLocation.fromNamespaceAndPath(Main.MODID, "block/feathers_pack_plastic_" + fillLevel + "4"));
        }
        return models;
    }
}
