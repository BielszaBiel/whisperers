package net.bielszabiel.whisperers.entity.custom;

import net.bielszabiel.whisperers.entity.ModEntitys;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WhispererEntity extends VillagerEntity {
//doatak żeby push zrobić
    private WhispererProfession profession = WhispererProfession.FARMER;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public WhispererEntity(EntityType<? extends VillagerEntity> entityType, World world) {
        super(entityType, world);
    }
    public enum WhispererProfession {
        PLEBS,
        FARMER,
        BLACKSMITH,
        TOOLSMITH,
        HUNTER
    }


    public WhispererProfession getProfession() {
        return profession;
    }

    public void setProfession(WhispererProfession profession) {
        this.profession = profession;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
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

    @Nullable
    public WhispererEntity createChild(ServerWorld world, PassiveEntity entity) {
        WhispererEntity child = ModEntitys.WHISPERER.create(world);

        if (child != null) {
            child.initialize(
                    world,
                    world.getLocalDifficulty(child.getBlockPos()),
                    net.minecraft.entity.SpawnReason.BREEDING,
                    null
            );
        }

        return child;
    }
}