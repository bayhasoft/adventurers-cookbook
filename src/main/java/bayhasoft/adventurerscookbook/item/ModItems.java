package bayhasoft.adventurerscookbook.item;

import java.util.List;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.item.custom.FoodBowlItem;
import bayhasoft.adventurerscookbook.item.custom.JuiceItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item DRINKING_GLASS = registerItem("drinking_glass",
        new Item(new Item.Settings()));

    public static final Item CARROT_JUICE = registerItem( "carrot_juice",
        new JuiceItem(new Item.Settings().food(ModFoodComponents.CARROT_JUICE)));

    public static final Item FROG_LEGS = registerItem("frog_legs",
        new Item(new Item.Settings().food(FoodComponents.BEEF)));

    public static final Item FROG_LEGS_COOKED = registerItem("frog_legs_cooked",
        new Item(new Item.Settings().food(FoodComponents.COOKED_COD)));

    public static final Item GOLDEN_CARROT_JUICE = registerItem( "golden_carrot_juice",
        new JuiceItem(new Item.Settings()
            .food(ModFoodComponents.GOLDEN_CARROT_JUICE)));

    public static final Item GREEN_TOMATO = registerItem( "green_tomato",
        new Item(new Item.Settings()
            .food(ModFoodComponents.GREEN_TOMATO)));

    public static final Item MANGO = registerItem( "mango",
        new Item(new Item.Settings()
            .food(FoodComponents.APPLE)));

    public static final Item RICE = registerItem( "rice",
        new Item(new Item.Settings()));

    public static final Item RICE_BAG = registerItem( "rice_bag",
        new Item(new Item.Settings()));

    public static final Item RICE_SEEDS = registerItem("rice_seeds",
        new AliasedBlockItem(ModBlocks.RICE_CROP, new Item.Settings()));

    public static final Item RICE_BOWL = registerItem( "rice_bowl",
        new FoodBowlItem(new Item.Settings().food(FoodComponents.BREAD)));

    public static final Item SUSHI = registerItem( "sushi",
        new Item(new Item.Settings().food(FoodComponents.COOKED_BEEF)));

    public static final Item TOMATO = registerItem( "tomato",
        new Item(new Item.Settings().food(FoodComponents.APPLE)));

    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
        new AliasedBlockItem(ModBlocks.TOMATO_CROP, new Item.Settings()));

    public static final Item ANCIENT_FRUIT = registerItem("ancient_fruit", 
        new Item(new Item.Settings().food(ModFoodComponents.ANCIENT_FRUIT).rarity(Rarity.RARE)));

    public static final Item ANCIENT_SEED = registerItem("ancient_seed", new AliasedBlockItem(ModBlocks.ANCIENT_FRUIT_CROP, new Item.Settings()){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.adventurerscookbook.ancient_seed"));
                   super.appendTooltip(stack, context, tooltip, type);
        }
    });

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(AdventurersCookBook.MOD_ID, name), item);
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
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ANCIENT_FRUIT);
        addToItemGroup(ItemGroups.INGREDIENTS, DRINKING_GLASS);
        addToItemGroup(ItemGroups.INGREDIENTS, RICE);
        addToItemGroup(ItemGroups.INGREDIENTS, RICE_BAG);
        addToItemGroup(ItemGroups.NATURAL, RICE_SEEDS);
        addToItemGroup(ItemGroups.NATURAL, TOMATO_SEEDS);
        addToItemGroup(ItemGroups.NATURAL, ANCIENT_SEED);
    }

    private static void addToItemGroup(RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    
    public static void registerModItems() {
        AdventurersCookBook.LOGGER.info("Registering Potions for " + AdventurersCookBook.MOD_ID);
    
        addItemsToItemGroup();
    }
}
