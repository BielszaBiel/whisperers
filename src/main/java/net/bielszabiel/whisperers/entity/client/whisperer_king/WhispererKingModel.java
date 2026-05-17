package net.bielszabiel.whisperers.entity.client.whisperer_king;

import net.bielszabiel.whisperers.entity.client.whisperer.WhispererAnimations;
import net.bielszabiel.whisperers.entity.custom.WhispererEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class WhispererKingModel {

    public class Wisperer_King extends EntityModel<Entity> {
        private final ModelPart all;
        private final ModelPart body;
        private final ModelPart head;
        private final ModelPart headbone;
        private final ModelPart arm_left;
        private final ModelPart arm_right;
        private final ModelPart legs;
        private final ModelPart bone;
        private final ModelPart bone2;
        public Wisperer_King(ModelPart root) {
            this.all = root.getChild("all");
            this.body = this.all.getChild("body");
            this.head = this.all.getChild("head");
            this.headbone = this.head.getChild("headbone");
            this.arm_left = this.all.getChild("arm_left");
            this.arm_right = this.all.getChild("arm_right");
            this.legs = this.all.getChild("legs");
            this.bone = this.legs.getChild("bone");
            this.bone2 = this.legs.getChild("bone2");
        }
        public static TexturedModelData getTexturedModelData() {
            ModelData modelData = new ModelData();
            ModelPartData modelPartData = modelData.getRoot();
            ModelPartData all = modelPartData.addChild("all", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, 0.0F));

            ModelPartData body = all.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.1F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F))
                    .uv(0, 18).cuboid(-4.0F, -0.1F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(-0.3F)), ModelTransform.pivot(0.0F, -9.6F, 0.0F));

            ModelPartData head = all.addChild("head", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -7.55F, -4.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -8.4F, 0.0F));

            ModelPartData headbone = head.addChild("headbone", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, -4.0F, -4.8F, 2.0F, 4.0F, 2.0F, new Dilation(-0.3F))
                    .uv(32, 15).cuboid(-3.0F, -6.0F, -3.2F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
                    .uv(0, 43).cuboid(-3.0F, -10.2F, -3.2F, 6.0F, 3.0F, 6.0F, new Dilation(-0.3F))
                    .uv(0, 52).cuboid(-3.0F, -7.8F, -3.2F, 6.0F, 2.0F, 6.0F, new Dilation(-0.2F)), ModelTransform.pivot(0.0F, -1.0F, -0.7F));

            ModelPartData arm_left = all.addChild("arm_left", ModelPartBuilder.create().uv(32, 27).cuboid(0.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                    .uv(32, 34).mirrored().cuboid(0.0F, -1.3F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)).mirrored(false), ModelTransform.pivot(4.0F, -8.0F, 0.0F));

            ModelPartData arm_right = all.addChild("arm_right", ModelPartBuilder.create().uv(32, 27).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                    .uv(32, 34).cuboid(-2.0F, -1.3F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.1F)), ModelTransform.pivot(-4.0F, -8.0F, 0.0F));

            ModelPartData legs = all.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.05F, -0.5F));

            ModelPartData bone = legs.addChild("bone", ModelPartBuilder.create().uv(37, 31).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 0.0F, 0.5F));

            ModelPartData bone2 = legs.addChild("bone2", ModelPartBuilder.create().uv(37, 31).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.5F, 0.0F, 0.5F));
            return TexturedModelData.of(modelData, 64, 64);
        }
        @Override
        public void setAngles(WhispererEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            this.getPart().traverse().forEach(ModelPart::resetTransform);
            this.setHeadAngles(netHeadYaw, headPitch);

            this.updateAnimation(entity.idleAnimationState, WhispererAnimations.IDLE, ageInTicks, 1f);

        }

        private void setHeadAngles(float headYaw, float headPitch) {
            this.head.yaw = headYaw * 0.017453292F;
            this.head.pitch = headPitch * 0.017453292F;
        }

        @Override
        public ModelPart getPart() {
            return all;
        }
    }