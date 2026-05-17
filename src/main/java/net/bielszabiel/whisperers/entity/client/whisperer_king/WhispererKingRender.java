package net.bielszabiel.whisperers.entity.client.whisperer_king;

import net.bielszabiel.whisperers.Whisperers;
import net.bielszabiel.whisperers.entity.client.whisperer.WhispererModel;
import net.bielszabiel.whisperers.entity.custom.WhispererKingEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class WhispererKingRender extends MobEntityRenderer<WhispererKingEntity, WhispererKingModel> {

    private static final Identifier TEXTURE = Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer_king.png");

    public WhispererKingRender(EntityRendererFactory.Context context) {

        super(context, new WhispererKingModel(context.getPart(WhispererKingModel.Wisperer_King)), 0.5f);
    }
}
