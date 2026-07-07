package net.bielszabiel.whisperers.Item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemFoodComponents {
    public static final FoodComponent DEEPBERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.4f).build();
    public static final FoodComponent WHISPERERS_MASH = new FoodComponent.Builder().nutrition(10).saturationModifier(5.0f).usingConvertsTo(Items.BOWL).build();
    public static final FoodComponent SPIDER_THORAX = new FoodComponent.Builder().nutrition(2).saturationModifier(0.45f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6f).build();


}

