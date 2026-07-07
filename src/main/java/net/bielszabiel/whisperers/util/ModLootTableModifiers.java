package net.bielszabiel.whisperers.util;

import net.bielszabiel.whisperers.Item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {

    private static final Identifier SPIDER_ID
            = Identifier.of("minecraft", "entities/spider");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {

            if(SPIDER_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0f))

                        // Sprawdzamy źródło obrażeń -> czy atakujący trzymał siekierę
                        .conditionally(DamageSourcePropertiesLootCondition.builder(
                                DamageSourcePredicate.Builder.create()
                                        .sourceEntity(EntityPredicate.Builder.create()
                                                .equipment(EntityEquipmentPredicate.Builder.create()
                                                        .mainhand(ItemPredicate.Builder.create()
                                                                .tag(ItemTags.AXES)
                                                        )
                                                )
                                        )
                        ))

                        .with(ItemEntry.builder(ModItems.SPIDER_THORAX))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}