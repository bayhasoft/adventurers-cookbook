package bayhasoft.adventurerscookbook.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CARROT_JUICE = new FoodComponent.Builder()
        .nutrition(3)
        .saturationModifier(0.6F)
        .snack()
        .build();
    
    public static final FoodComponent GOLDEN_CARROT_JUICE = new FoodComponent.Builder()
        .nutrition(6)
        .saturationModifier(1.2F)
        .snack()
        .build();
    
    public static final FoodComponent GREEN_TOMATO = new FoodComponent.Builder()
		.nutrition(4)
		.saturationModifier(0.3F)
		.statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.6F)
		.build();

    public static final FoodComponent ANCIENT_FRUIT = new FoodComponent.Builder()
        .nutrition(4)
        .saturationModifier(1.5F)
        .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 0), 1.0F)
        .build();
}
