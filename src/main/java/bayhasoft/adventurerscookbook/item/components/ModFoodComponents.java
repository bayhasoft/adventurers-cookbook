package bayhasoft.adventurerscookbook.item.components;

import net.minecraft.world.food.FoodProperties;

public class ModFoodComponents {
    public static final FoodProperties GREEN_TOMATO = new FoodProperties.Builder()
		.nutrition(4)
		.saturationModifier(0.3F)
		.build();

    public static final FoodProperties ANCIENT_FRUIT = new FoodProperties.Builder()
        .nutrition(4)
        .saturationModifier(1.5F)
        .build();
}
