package com.pekar.pouchandpaper.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.pekar.pouchandpaper.Main;
import com.pekar.pouchandpaper.blocks.FarmContainer;
import com.pekar.pouchandpaper.blocks.entity.PackOfFeathersBlockEntity;
import net.fabricmc.fabric.api.client.model.loading.v1.FabricBakedModelManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class PackOfFeathersRenderer implements BlockEntityRenderer<PackOfFeathersBlockEntity>
{
    private final BlockRenderDispatcher blockRenderDispatcher;

    public PackOfFeathersRenderer(BlockEntityRendererProvider.Context context)
    {
        blockRenderDispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PackOfFeathersBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay)
    {
        var level = blockEntity.getLevel();
        if (level == null)
        {
            return;
        }

        var state = blockEntity.getBlockState();
        var model = ((FabricBakedModelManager)Minecraft.getInstance().getModelManager()).getModel(getPlasticModelLocation(state));
        var vertexConsumer = bufferSource.getBuffer(RenderType.translucent());
        blockRenderDispatcher.getModelRenderer().tesselateBlock(level, model, state, blockEntity.getBlockPos(), poseStack, vertexConsumer, false, RandomSource.create(), state.getSeed(blockEntity.getBlockPos()), packedOverlay);
    }

    private static ResourceLocation getPlasticModelLocation(BlockState state)
    {
        var fillLevel = switch (state.getValue(FarmContainer.FILL_LEVEL))
        {
            case 0 -> "empty";
            case 1 -> "semi";
            default -> "full";
        };

        var placingOption = state.getValue(FarmContainer.PLACING_OPTION);
        var suffix = placingOption == 0 ? "" : Integer.toString(placingOption + 1);

        return ResourceLocation.fromNamespaceAndPath(Main.MODID, "block/feathers_pack_plastic_" + fillLevel + suffix);
    }
}
