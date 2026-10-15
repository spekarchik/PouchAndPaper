package com.pekar.pouchandpaper.client;

import com.pekar.pouchandpaper.Main;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperBlockStateModel;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class PlasticPackModel extends WrapperBlockStateModel
{
    private final Mesh mesh;

    public PlasticPackModel(BlockStateModel model)
    {
        super(model);
        var mutableMesh = Renderer.get().mutableMesh();
        var emitter = mutableMesh.emitter();
        for (var part : model.collectParts(RandomSource.create(0)))
        {
            emitPart(emitter, part, null);
            for (var direction : Direction.values())
            {
                emitPart(emitter, part, direction);
            }
        }
        mesh = mutableMesh.immutableCopy();
    }

    private static void emitPart(QuadEmitter emitter, BlockModelPart part, @Nullable Direction cullFace)
    {
        for (var quad : part.getQuads(cullFace))
        {
            // Shells must share the sorted terrain pass with glass and water.
            // Drawing them as block entities writes depth before those blocks render.
            emitter.fromBakedQuad(quad)
                    .cullFace(cullFace)
                    .ambientOcclusion(part.useAmbientOcclusion() ? TriState.TRUE : TriState.FALSE)
                    .renderLayer(isShell(quad) ? ChunkSectionLayer.TRANSLUCENT : ChunkSectionLayer.SOLID)
                    .emit();
        }
    }

    private static boolean isShell(BakedQuad quad)
    {
        var texture = quad.sprite().contents().name();
        return texture.getNamespace().equals(Main.MODID)
                && List.of("block/plastic", "block/leaves_plastic", "block/tie").contains(texture.getPath());
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter blockView, BlockPos pos, BlockState state, RandomSource random, Predicate<@Nullable Direction> cullTest)
    {
        mesh.outputTo(emitter);
    }

    @Override
    public Object createGeometryKey(BlockAndTintGetter blockView, BlockPos pos, BlockState state, RandomSource random)
    {
        return mesh;
    }
}
