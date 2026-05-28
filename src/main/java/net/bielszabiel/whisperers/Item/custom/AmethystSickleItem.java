package net.bielszabiel.whisperers.Item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Vec3d;

public class AmethystSickleItem extends SwordItem {

    public AmethystSickleItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (attacker instanceof PlayerEntity player && !player.getWorld().isClient()) {

            boolean isCritical = player.fallDistance > 0.0F && !player.isOnGround() && !player.isClimbing() && !player.isTouchingWater();

            if (isCritical) {

                Vec3d playerPos = player.getPos();
                Vec3d targetPos = target.getPos();

                Vec3d direction = playerPos.subtract(targetPos).normalize();
                double pullStrength = 1.0;

                Vec3d pullVelocity = new Vec3d(
                        direction.x * pullStrength,
                        0.3,
                        direction.z * pullStrength
                );

                target.setVelocity(pullVelocity);
                target.velocityModified = true;

            }
        }
        return super.postHit(stack, target, attacker);
    }


}