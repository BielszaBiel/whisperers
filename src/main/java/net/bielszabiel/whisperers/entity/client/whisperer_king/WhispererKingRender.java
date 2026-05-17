package net.bielszabiel.whisperers.entity.client.whisperer_king;

import net.bielszabiel.whisperers.Whisperers;
import net.bielszabiel.whisperers.entity.custom.WhispererKingEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class WhispererKingRender extends MobEntityRenderer<WhispererKingEntity, WhispererKingModel> {

    // Poprawiona literówka w ścieżce (zwykle folder w zasobach nazywa się "entity", a nie "entitys")
    private static final Identifier TEXTURE = Identifier.of(Whisperers.MOD_ID, "textures/entitys/whisperers/whisperer_king.png");

    public WhispererKingRender(EntityRendererFactory.Context context) {
        // Podpinamy nowo utworzoną warstwę statyczną z modelu Króla
        super(context, new WhispererKingModel(context.getPart(WhispererKingModel.WHISPERER_KING_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(WhispererKingEntity entity) {
        return TEXTURE;
    }
}