package bayhasoft.adventurerscookbook.item;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PotionItem;
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


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AdventurersCookBook.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, CARROT_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, GOLDEN_CARROT_JUICE);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    
    public static void registerModItems() {
        AdventurersCookBook.LOGGER.info("Registering Mod Items for " + AdventurersCookBook.MOD_ID);
    
        addItemsToItemGroup();
    }
}
