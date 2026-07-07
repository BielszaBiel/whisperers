package net.bielszabiel.whisperers.entity.custom;

import net.bielszabiel.whisperers.entity.ModEntitys;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WhispererEntity extends VillagerEntity {

    // Trzymamy profesję jako int (ordinal enuma) w DataTrackerze - to jedyny sposób,
    // żeby klient w ogóle wiedział jaka to profesja (np. do wyboru tekstury/ubrania).
    private static final TrackedData<Integer> PROFESSION =
            DataTracker.registerData(WhispererEntity.class, TrackedDataHandlerRegistry.INTEGER);

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

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(PROFESSION, WhispererProfession.PLEBS.ordinal());
    }

    public WhispererProfession getProfession() {
        return WhispererProfession.values()[this.dataTracker.get(PROFESSION)];
    }

    public void setProfession(WhispererProfession profession) {
        this.dataTracker.set(PROFESSION, profession.ordinal());
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        // Zapisujemy nazwę, nie ordinal - bezpieczne nawet jeśli kiedyś zmienisz
        // kolejność wartości w enumie WhispererProfession.
        nbt.putString("WhispererProfession", this.getProfession().name());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("WhispererProfession")) {
            try {
                this.setProfession(WhispererProfession.valueOf(nbt.getString("WhispererProfession")));
            } catch (IllegalArgumentException e) {
                // Zapis pochodzi ze starszej wersji moda / nieznana wartość - wracamy do domyślnej.
                this.setProfession(WhispererProfession.PLEBS);
            }
        }
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

    @Override
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
            // Dziecko startuje jako Plebs bez pracy - dokładnie jak wanilla villager,
            // profesję "zdobywa" samo później (np. przez job site block), a nie po rodzicach.
            child.setProfession(WhispererProfession.PLEBS);
        }

        return child;
    }
}