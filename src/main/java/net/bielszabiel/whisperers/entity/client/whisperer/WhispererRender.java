package net.bielszabiel.whisperers.entity.client.whisperer;

import net.bielszabiel.whisperers.Whisperers;
import net.bielszabiel.whisperers.entity.custom.WhispererEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WhispererRender extends MobEntityRenderer<WhispererEntity, WhispererModel> {

    private static final Identifier PLEBS_TEXTURE =
            Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer.png");

    private static final Identifier FARMER_TEXTURE =
            Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer_farmer.png");

    private static final Identifier BLACKSMITH_TEXTURE =
            Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer_blacksmith.png");

    private static final Identifier TOOLSMITH_TEXTURE =
            Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer_toolsmith.png");

    private static final Identifier HUNTER_TEXTURE =
            Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer_hunter.png");

    public WhispererRender(EntityRendererFactory.Context context) {

        super(context, new WhispererModel(context.getPart(WhispererModel.Whisperer)), 0.5f);
    }

    @Override
    public Identifier getTexture(WhispererEntity entity) {
        return switch (entity.getProfession()) {
            case PLEBS -> PLEBS_TEXTURE;
            case FARMER -> FARMER_TEXTURE;
            case BLACKSMITH -> BLACKSMITH_TEXTURE;
            case TOOLSMITH -> TOOLSMITH_TEXTURE;
            case HUNTER -> HUNTER_TEXTURE;
        };
    }


    @Override
    public void render(WhispererEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i){

        if(livingEntity.isBaby()){
            matrixStack.scale(0.7f, 0.7f,0.7f);
        } else {
            matrixStack.scale(1f,1f,1f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}