package net.bielszabiel.whisperers.Item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModItemFoodComponents {
    public static final FoodComponent DEEPBERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.4f).build();
    public static final FoodComponent SPIDER_THORAX = new FoodComponent.Builder().nutrition(2).saturationModifier(0.45f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6f).build();
}
