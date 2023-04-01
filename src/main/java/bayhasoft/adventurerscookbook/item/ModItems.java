package bayhasoft.adventurerscookbook.item;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.StewItem;
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
                        .saturationModifier(3.6f)
                        .snack()
                        .build()
            )
        )
    );

    public static final Item DRINKING_GLASS = registerItem("drinking_glass",
            new Item(new FabricItemSettings()));

    public static final Item GOLDEN_CARROT_JUICE = registerItem( "golden_carrot_juice",
            new JuiceItem(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(6)
                        .saturationModifier(14.4f)
                        .snack()
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
                        .saturationModifier(2.4f)
                        .build()
            )
        )
    );

    public static final Item RICE_SEEDS = registerItem("rice_seeds",
            new AliasedBlockItem(ModBlocks.RICE_CROP, new  FabricItemSettings()
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


    public static final Item SUSHI = registerItem( "sushi",
            new Item(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(8)
                        .saturationModifier(12.8f)
                        .build()
            )
        )
    );

    public static final Item RICE_BOWL = registerItem( "rice_bowl",
            new FoodBowlItem(new FabricItemSettings()
                .food(
                    new FoodComponent
                        .Builder()
                        .hunger(5)
                        .saturationModifier(6f)
                        .build()
            )
        )
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AdventurersCookBook.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, CARROT_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, GOLDEN_CARROT_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, MANGO);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, RICE_BOWL);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, SUSHI);
        addToItemGroup(ItemGroups.INGREDIENTS, DRINKING_GLASS);
        addToItemGroup(ItemGroups.INGREDIENTS, RICE);
        addToItemGroup(ItemGroups.INGREDIENTS, RICE_BAG);
        addToItemGroup(ItemGroups.NATURAL, RICE_SEEDS);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    
    public static void registerModItems() {
        AdventurersCookBook.LOGGER.info("Registering Mod Items for " + AdventurersCookBook.MOD_ID);
    
        addItemsToItemGroup();
    }
}
