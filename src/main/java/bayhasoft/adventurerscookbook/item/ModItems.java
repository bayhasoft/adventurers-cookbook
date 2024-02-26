package bayhasoft.adventurerscookbook.item;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
// import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CARROT_JUICE = registerItem( "carrot_juice",
            new JuiceItem(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(3)
                        .saturationModifier(0.6f)
                        .snack()
                        .build()
            )
        )
    );

    public static final Item DRINKING_GLASS = registerItem("drinking_glass",
            new Item(new FabricItemSettings()));


    public static final Item FROG_LEGS = registerItem("frog_legs",
        new Item(new FabricItemSettings()
        .food(
            new FoodComponent
                .Builder()
                .hunger(3)
                .saturationModifier(0.3f)
                .build()
        ))
    );

    public static final Item FROG_LEGS_COOKED = registerItem("frog_legs_cooked",
        new Item(new FabricItemSettings()
        .food(
            new FoodComponent
                .Builder()
                .hunger(5)
                .saturationModifier(0.6f)
                .build()
        ))
    );

    public static final Item GOLDEN_CARROT_JUICE = registerItem( "golden_carrot_juice",
            new JuiceItem(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(6)
                        .saturationModifier(1.2f)
                        .snack()
                        .build()
            )
        )
    );

    public static final Item GREEN_TOMATO = registerItem( "green_tomato",
            new Item(new FabricItemSettings()
            .food(
                new FoodComponent
                    .Builder()
                    .hunger(4)
                    .saturationModifier(0.3f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6f)
                    .build()
            )
        )
    );

    public static final Item MANGO = registerItem( "mango",
            new Item(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(4)
                        .saturationModifier(0.3f)
                        .build()
            )
        )
    );

    public static final Item RICE = registerItem( "rice",
            new Item(new FabricItemSettings()
        )
    );

    public static final Item RICE_BAG = registerItem( "rice_bag",
            new Item(new FabricItemSettings()
        )
    );

    public static final Item RICE_SEEDS = registerItem("rice_seeds",
            new AliasedBlockItem(ModBlocks.RICE_CROP, new  FabricItemSettings()
        )
    );

    public static final Item RICE_BOWL = registerItem( "rice_bowl",
            new FoodBowlItem(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(5)
                        .saturationModifier(0.6f)
                        .build()
            )
        )
    );

    public static final Item SUSHI = registerItem( "sushi",
            new Item(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(8)
                        .saturationModifier(0.8f)
                        .build()
            )
        )
    );

    public static final Item TOMATO = registerItem( "tomato",
            new Item(new FabricItemSettings()
            .food(
                new FoodComponent
                    .Builder()
                    .hunger(4)
                    .saturationModifier(0.3f)
                    .build()
        )
        )
    );

    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new AliasedBlockItem(ModBlocks.TOMATO_CROP, new  FabricItemSettings()
        )
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AdventurersCookBook.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, CARROT_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, FROG_LEGS);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, FROG_LEGS_COOKED);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, GOLDEN_CARROT_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, GREEN_TOMATO);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, MANGO);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, RICE_BOWL);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, SUSHI);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, TOMATO);
        addToItemGroup(ItemGroups.INGREDIENTS, DRINKING_GLASS);
        addToItemGroup(ItemGroups.INGREDIENTS, RICE);
        addToItemGroup(ItemGroups.INGREDIENTS, RICE_BAG);
        addToItemGroup(ItemGroups.NATURAL, RICE_SEEDS);
        addToItemGroup(ItemGroups.NATURAL, TOMATO_SEEDS);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    
    public static void registerModItems() {
        AdventurersCookBook.LOGGER.info("Registering Mod Items for " + AdventurersCookBook.MOD_ID);
    
        addItemsToItemGroup();
    }
}
