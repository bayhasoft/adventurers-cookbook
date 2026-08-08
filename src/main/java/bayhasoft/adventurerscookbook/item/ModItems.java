package bayhasoft.adventurerscookbook.item;

import java.util.function.Consumer;
import java.util.function.Function;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.item.components.ModConsumableComponents;
import bayhasoft.adventurerscookbook.item.components.ModFoodComponents;
import bayhasoft.adventurerscookbook.item.custom.FoodBowlItem;
import bayhasoft.adventurerscookbook.item.custom.JuiceItem;
import bayhasoft.adventurerscookbook.item.custom.MudBallItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item DRINKING_GLASS = registerItem("drinking_glass", Item::new);

    public static final Item CARROT_JUICE = registerItem( "carrot_juice", 
        setting -> new JuiceItem(setting.food(FoodComponents.CARROT, ModConsumableComponents.JUICE)));

    public static final Item GOLDEN_CARROT_JUICE = registerItem( "golden_carrot_juice",
            setting -> new JuiceItem(setting.food(FoodComponents.GOLDEN_CARROT, ModConsumableComponents.JUICE)));

    public static final Item APPLE_JUICE = registerItem( "apple_juice",
            setting -> new JuiceItem(setting.food(FoodComponents.APPLE, ModConsumableComponents.JUICE)));

    public static final Item MELLON_JUICE = registerItem( "mellon_juice",
            setting -> new JuiceItem(setting.food(FoodComponents.MELON_SLICE, ModConsumableComponents.JUICE)));

    public static final Item FRIED_EGG = registerItem("fried_egg",
            setting -> new Item(setting.food(FoodComponents.BAKED_POTATO)));

    public static final Item FROG_LEGS = registerItem("frog_legs",
        setting -> new Item(setting.food(FoodComponents.BEEF)));

    public static final Item FROG_LEGS_COOKED = registerItem("frog_legs_cooked",
        setting -> new Item(setting.food(FoodComponents.COOKED_COD)));

    public static final Item GREEN_TOMATO = registerItem( "green_tomato", 
        setting -> new Item(setting.food(ModFoodComponents.GREEN_TOMATO, ModConsumableComponents.GREEN_TOMATO)));

    public static final Item MANGO = registerItem( "mango", 
        setting -> new Item(setting.food(FoodComponents.APPLE)));

    public static final Item RICE = registerItem( "rice", Item::new);

    public static final Item RICE_BAG = registerItem( "rice_bag", Item::new);

    public static final Item RICE_SEEDS = registerItem("rice_seeds",
       setting -> new BlockItem(ModBlocks.RICE_CROP, setting));

    public static final Item RICE_BOWL = registerItem( "rice_bowl", 
        setting -> new FoodBowlItem(setting.food(FoodComponents.BREAD)));

    public static final Item SUSHI = registerItem( "sushi", 
        setting -> new Item(setting.food(FoodComponents.COOKED_BEEF)));

    public static final Item TOMATO = registerItem( "tomato", 
        setting -> new Item(setting.food(FoodComponents.APPLE)));

    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
        setting -> new BlockItem(ModBlocks.TOMATO_CROP, setting));

    public static final Item Corn = registerItem( "corn",
            setting -> new Item(setting.food(FoodComponents.CARROT)));

    public static final Item CORN_SEEDS = registerItem("corn_seeds",
            setting -> new BlockItem(ModBlocks.CORN_CROP, setting));

    public static final Item ANCIENT_FRUIT = registerItem("ancient_fruit", 
        setting -> new Item(setting.food(ModFoodComponents.ANCIENT_FRUIT, ModConsumableComponents.ANCIENT_FRUIT).rarity(Rarity.RARE)));

    public static final Item ANCIENT_SEED = registerItem("ancient_seed", 
        setting -> new BlockItem(ModBlocks.ANCIENT_FRUIT_CROP, setting){
        @SuppressWarnings("deprecation")
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("tooltip.adventurerscookbook.ancient_seed"));
                    super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });

    public static final Item MUD_BALL = registerItem("mud_ball",
            setting -> new MudBallItem(setting.maxCount(1)));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(AdventurersCookBook.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AdventurersCookBook.MOD_ID, name)))));
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, CARROT_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, GOLDEN_CARROT_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, APPLE_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, MELLON_JUICE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, FRIED_EGG);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, FROG_LEGS);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, FROG_LEGS_COOKED);
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
        addToItemGroup(ItemGroups.NATURAL, CORN_SEEDS);

    }

    private static void addToItemGroup(RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    
    public static void registerModItems() {
        AdventurersCookBook.LOGGER.info("Registering Potions for " + AdventurersCookBook.MOD_ID);
    
        addItemsToItemGroup();
    }
}
