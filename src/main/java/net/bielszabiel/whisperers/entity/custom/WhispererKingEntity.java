package net.bielszabiel.whisperers.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
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

        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, WhispererEntity.class, 50.0f));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 40.0f));
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();


        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }


        if (!this.getWorld().isClient()) {
            BlockPos currentPos = this.getBlockPos();


            BlockPos solidBlockPos = null;
            for (int i = 0; i <= 20; i++) {
                BlockPos checkPos = currentPos.down(i);
                net.minecraft.block.BlockState state = this.getWorld().getWorldChunk(checkPos).getBlockState(checkPos);

                if (!state.isAir() && state.isSolid()) {
                    solidBlockPos = checkPos;
                    break;
                }
            }

            if (solidBlockPos != null) {

                double idealY = solidBlockPos.getY() + 2.0;


                if (this.getY() != idealY) {

                    this.refreshPositionAndAngles(this.getX(), idealY, this.getZ(), this.getYaw(), this.getPitch());
                }
            }

            this.setVelocity(0, 0, 0);
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

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {

        if (damageSource.getAttacker() instanceof net.minecraft.entity.player.PlayerEntity) {
            return true;
        }

        return super.isInvulnerableTo(damageSource);
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

}