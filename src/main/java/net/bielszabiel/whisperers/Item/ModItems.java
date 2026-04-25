package net.bielszabiel.whisperers.Item;

import net.bielszabiel.whisperers.Whisperers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //tu dodajesz itemy
    public static final Item DEEPBERRIES = registerItem("deepberries", new Item(new Item.Settings().food(ModItemFoodComponents.DEEPBERRIES)));

   private static Item registerItem(String name, Item item){
       return Registry.register(Registries.ITEM, Identifier.of(Whisperers.MOD_ID, name), item);
   }


    public static void registermoditems() {
        Whisperers.LOGGER.info("registering items for" + Whisperers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(DEEPBERRIES);
        });
    }


}
