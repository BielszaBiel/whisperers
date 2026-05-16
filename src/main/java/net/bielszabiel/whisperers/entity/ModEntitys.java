package net.bielszabiel.whisperers.entity;

import net.bielszabiel.whisperers.Whisperers;
import net.bielszabiel.whisperers.entity.custom.WhispererEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModEntitys {

    public static final EntityType<WhispererEntity> WHISPERER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Whisperers.MOD_ID, "whisperer"),
            EntityType.Builder.create(WhispererEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 1f)
                    .build()
    );

    public static final EntityType<WhispererEntity> WHISPERER_KING = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Whisperers.MOD_ID, "whisperer"),
            EntityType.Builder.create(WhispererEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 1f)
                    .build()
    );

    public static void registerModEntitys() {
            Whisperers.LOGGER.info("registering items for" + Whisperers.MOD_ID);


            }
    }

