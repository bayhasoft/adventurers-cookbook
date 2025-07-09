package bayhasoft.adventurerscookbook.item.components;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent CARROT_JUICE = new FoodComponent.Builder()
        .nutrition(3)
        .saturationModifier(0.6F)
        .build();
    
    public static final FoodComponent GOLDEN_CARROT_JUICE = new FoodComponent.Builder()
        .nutrition(6)
        .saturationModifier(1.2F)
        .build();
    
    public static final FoodComponent GREEN_TOMATO = new FoodComponent.Builder()
		.nutrition(4)
		.saturationModifier(0.3F)
		.build();

    public static final FoodComponent ANCIENT_FRUIT = new FoodComponent.Builder()
        .nutrition(4)
        .saturationModifier(1.5F)
        .build();
}
