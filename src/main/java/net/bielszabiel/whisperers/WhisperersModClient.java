package net.bielszabiel.whisperers;

import net.bielszabiel.whisperers.block.ModBlocks;
import net.bielszabiel.whisperers.entity.ModEntitys;
import net.bielszabiel.whisperers.entity.client.whisperer.WhispererModel;
import net.bielszabiel.whisperers.entity.client.whisperer.WhispererRender;
import net.bielszabiel.whisperers.entity.client.whisperer_king.WhispererKingModel;
import net.bielszabiel.whisperers.entity.client.whisperer_king.WhispererKingRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class WhisperersModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEEPBERRY_BUSH, RenderLayer.getCutout());

            // 1. Rejestracja warstwy modelu (geometria/siatka)
            EntityModelLayerRegistry.registerModelLayer(WhispererModel.Whisperer, WhispererModel::getTexturedModelData);

            // 2. Rejestracja renderera (wygląd/tekstura/animacje)
            EntityRendererRegistry.register(ModEntitys.WHISPERER, WhispererRender::new);

        EntityModelLayerRegistry.registerModelLayer(WhispererKingModel.WHISPERER_KING_LAYER, WhispererKingModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntitys.WHISPERER_KING, WhispererKingRender::new);


        }
    }


