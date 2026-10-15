package com.pekar.pouchandpaper;

import com.pekar.pouchandpaper.blocks.BlockRegistry;
import com.pekar.pouchandpaper.client.PlasticPackModel;
import com.pekar.pouchandpaper.client.TooltipClientAccessor;
import com.pekar.pouchandpaper.clientaccess.ClientAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;

public class ClientMain implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModelLoadingPlugin.register(context -> context.modifyBlockModelAfterBake().register(ModelModifier.WRAP_PHASE, (model, bakeContext) ->
        {
            var state = bakeContext.state();
            if (state.is(BlockRegistry.PACK_OF_FEATHERS) || state.is(BlockRegistry.PACK_OF_LEAVES))
            {
                return new PlasticPackModel(model);
            }
            return model;
        }));
        ClientAccessor.init(
                new TooltipClientAccessor()
        );
    }
}
