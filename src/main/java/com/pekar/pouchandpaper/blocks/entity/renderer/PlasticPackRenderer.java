package com.pekar.pouchandpaper.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.pekar.pouchandpaper.Main;
import com.pekar.pouchandpaper.blocks.FarmContainer;
import com.pekar.pouchandpaper.blocks.entity.FarmContainerBlockEntity;
import net.fabricmc.fabric.api.client.model.loading.v1.ExtraModelKey;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.SimpleUnbakedExtraModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlasticPackRenderer<T extends FarmContainerBlockEntity> implements BlockEntityRenderer<T, PlasticPackRenderer.RenderState>
{
    private static final Map<Identifier, ExtraModelKey<BlockStateModel>> PLASTIC_MODELS = createPlasticModels();
    private final String packName;

    public PlasticPackRenderer(String packName)
    {
        this.packName = packName;
    }

    public static void registerModels(ModelLoadingPlugin.Context context)
    {
        PLASTIC_MODELS.forEach((id, key) -> context.addModel(key, SimpleUnbakedExtraModel.blockStateModel(id)));
    }

    private static Map<Identifier, ExtraModelKey<BlockStateModel>> createPlasticModels()
    {
        var models = new HashMap<Identifier, ExtraModelKey<BlockStateModel>>();
        for (var pack : List.of("feathers_pack", "leaves_pack"))
        {
            for (var fillLevel : List.of("empty", "semi", "full"))
            {
                for (int option = 0; option < 4; option++)
                {
                    var suffix = option == 0 ? "" : Integer.toString(option + 1);
                    var id = Identifier.fromNamespaceAndPath(Main.MODID, "block/" + pack + "_plastic_" + fillLevel + suffix);
                    models.put(id, ExtraModelKey.create(id::toString));
                }
            }
        }
        return models;
    }

    @Override
    public RenderState createRenderState()
    {
        return new RenderState();
    }

    @Override
    public void extractRenderState(T blockEntity, RenderState renderState, float partialTick, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress)
    {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        var state = blockEntity.getBlockState();
        var fillLevel = switch (state.getValue(FarmContainer.FILL_LEVEL))
        {
            case 0 -> "empty";
            case 1 -> "semi";
            default -> "full";
        };
        var option = state.getValue(FarmContainer.PLACING_OPTION);
        var suffix = option == 0 ? "" : Integer.toString(option + 1);
        var id = Identifier.fromNamespaceAndPath(Main.MODID, "block/" + packName + "_plastic_" + fillLevel + suffix);
        renderState.model = Minecraft.getInstance().getModelManager().getModel(PLASTIC_MODELS.get(id));
    }

    @Override
    public void submit(RenderState renderState, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraRenderState)
    {
        if (renderState.model != null)
        {
            collector.submitBlockModel(poseStack, RenderTypes.translucentMovingBlock(), renderState.model,
                    1F, 1F, 1F, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        }
    }

    public static class RenderState extends BlockEntityRenderState
    {
        public BlockStateModel model;
    }
}
