package net.bielszabiel.whisperers.entity.client;

import net.bielszabiel.whisperers.Whisperers;
import net.bielszabiel.whisperers.entity.custom.WhispererEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WhispererRender extends MobEntityRenderer<WhispererEntity, WhispererModel> {

    private static final Identifier TEXTURE = Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer.png");

    public WhispererRender(EntityRendererFactory.Context context) {

        super(context, new WhispererModel(context.getPart(WhispererModel.Whisperer)), 0.5f);
    }

    @Override
    public Identifier getTexture(WhispererEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(WhispererEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i){

        if(livingEntity.isBaby()){
            matrixStack.scale(0.5f, 0.5f,0.5f);
        } else {
            matrixStack.scale(1f,1f,1f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}