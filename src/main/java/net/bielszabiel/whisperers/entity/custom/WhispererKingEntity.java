package net.bielszabiel.whisperers.entity.custom;

import net.bielszabiel.whisperers.entity.ModEntitys;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class WhispererKingEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public WhispererKingEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected void initGoals() {

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));

    }


    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 00.0);
    }

    private void setupAnimationStates() {

        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();


        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }


    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {

        return null;
    }

    public void setSleeping(boolean b) {
    }
}