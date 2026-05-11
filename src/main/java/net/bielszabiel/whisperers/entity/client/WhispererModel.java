package net.bielszabiel.whisperers.entity.client;

import net.bielszabiel.whisperers.Whisperers;
import net.bielszabiel.whisperers.entity.custom.WhispererEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WhispererModel extends SinglePartEntityModel<WhispererEntity> {
    public static final EntityModelLayer Whisperer = new EntityModelLayer(Identifier.of(Whisperers.MOD_ID,"whisperer"),"whisperer");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart arm_left;
    private final ModelPart arm_right;
    private final ModelPart legs;
    private final ModelPart bone;
    private final ModelPart bone2;

    public WhispererModel(ModelPart root) {
        this.root = root.getChild("root"); // Ustawiamy root z Blockbencha
        this.body = this.root.getChild("body");
        this.head = this.root.getChild("head");
        this.arm_left = this.root.getChild("arm_left");
        this.arm_right = this.root.getChild("arm_right");


        this.legs = root.getChild("legs");
        this.bone = this.legs.getChild("bone");
        this.bone2 = this.legs.getChild("bone2");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.1F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 18).cuboid(-4.0F, -0.1F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(-0.3F))
                .uv(32, 0).cuboid(-4.0F, -6.35F, -4.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -9.6F, 0.0F));

        ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, -4.0F, -4.8F, 2.0F, 4.0F, 2.0F, new Dilation(-0.3F))
                .uv(32, 15).cuboid(-3.0F, -6.0F, -3.2F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(40, 31).cuboid(-3.0F, -8.0F, -3.2F, 6.0F, 3.0F, 6.0F, new Dilation(0.3F))
                .uv(34, 40).cuboid(-5.0F, -5.85F, -5.2F, 10.0F, 0.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -9.4F, -0.7F));

        ModelPartData arm_left = root.addChild("arm_left", ModelPartBuilder.create().uv(32, 27).cuboid(0.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(32, 34).mirrored().cuboid(0.0F, -1.3F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)).mirrored(false), ModelTransform.pivot(4.0F, -8.0F, 0.0F));

        ModelPartData arm_right = root.addChild("arm_right", ModelPartBuilder.create().uv(32, 27).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(32, 34).cuboid(-2.0F, -1.3F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(-4.0F, -8.0F, 0.0F));

        ModelPartData legs = modelPartData.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.05F, -0.5F));

        ModelPartData bone = legs.addChild("bone", ModelPartBuilder.create().uv(37, 31).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 0.0F, 0.5F));

        ModelPartData bone2 = legs.addChild("bone2", ModelPartBuilder.create().uv(37, 31).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, 0.0F, 0.5F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(WhispererEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

       this.animateMovement(WhispererAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
    this.updateAnimation(entity.idleAnimationState, WhispererAnimations.IDLE, ageInTicks, 1f);

    }

    private void setHeadAngles(float headYaw, float headPitch) {
        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}