package net.bielszabiel.whisperers.Item;

import net.bielszabiel.whisperers.Item.custom.AmethystSickleItem;
import net.bielszabiel.whisperers.Whisperers;
import net.bielszabiel.whisperers.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // tu dodajesz itemy
    public static final Item DEEPBERRIES = registerItem("deepberries",
            new AliasedBlockItem(ModBlocks.DEEPBERRY_BUSH,
                    new Item.Settings().food(ModItemFoodComponents.DEEPBERRIES)));

    public static final Item SPIDER_THORAX = registerItem("spider_thorax",
            new Item(new Item.Settings().food(ModItemFoodComponents.SPIDER_THORAX)));

    public static final Item AMETHYST_SICKLE = registerItem("amethyst_sickle",
            new AmethystSickleItem(ToolMaterials.IRON, new Item.Settings()
                    .maxCount(1)
                    .maxDamage(250)
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 4, -2.4F))
            ));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Whisperers.MOD_ID, name), item);
    }


    public static void registermoditems() {
        Whisperers.LOGGER.info("registering items for" + Whisperers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(DEEPBERRIES);
            entries.add(SPIDER_THORAX); // DODANE: głowotułów ląduje w zakładce z jedzeniem!
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries ->
                entries.add(AMETHYST_SICKLE)    );
    }
}