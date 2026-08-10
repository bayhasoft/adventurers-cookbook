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
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;

public class ModItems {

    public static final Item DRINKING_GLASS = registerItem("drinking_glass", Item::new);

    public static final Item CARROT_JUICE = registerItem( "carrot_juice", 
        setting -> new JuiceItem(setting.food(Foods.CARROT, ModConsumableComponents.JUICE)));

    public static final Item GOLDEN_CARROT_JUICE = registerItem( "golden_carrot_juice",
            setting -> new JuiceItem(setting.food(Foods.GOLDEN_CARROT, ModConsumableComponents.JUICE)));

    public static final Item APPLE_JUICE = registerItem( "apple_juice",
            setting -> new JuiceItem(setting.food(Foods.APPLE, ModConsumableComponents.JUICE)));

    public static final Item MELLON_JUICE = registerItem( "mellon_juice",
            setting -> new JuiceItem(setting.food(Foods.MELON_SLICE, ModConsumableComponents.JUICE)));

//    public static final Item FRIED_EGG = registerItem("fried_egg",
//            setting -> new Item(setting.food(Foods.BAKED_POTATO)));

    public static final Item FROG_LEGS = registerItem("frog_legs",
        setting -> new Item(setting.food(Foods.BEEF)));

    public static final Item FROG_LEGS_COOKED = registerItem("frog_legs_cooked",
        setting -> new Item(setting.food(Foods.COOKED_COD)));

    public static final Item GREEN_TOMATO = registerItem( "green_tomato", 
        setting -> new Item(setting.food(ModFoodComponents.GREEN_TOMATO, ModConsumableComponents.GREEN_TOMATO)));

    public static final Item MANGO = registerItem( "mango", 
        setting -> new Item(setting.food(Foods.APPLE)));

    public static final Item RICE = registerItem( "rice", Item::new);

    public static final Item RICE_BAG = registerItem( "rice_bag", Item::new);

    public static final Item RICE_SEEDS = registerItem("rice_seeds",
       setting -> new BlockItem(ModBlocks.RICE_CROP, setting));

    public static final Item RICE_BOWL = registerItem( "rice_bowl", 
        setting -> new FoodBowlItem(setting.food(Foods.BREAD)));

    public static final Item SUSHI = registerItem( "sushi", 
        setting -> new Item(setting.food(Foods.COOKED_BEEF)));

    public static final Item TOMATO = registerItem( "tomato", 
        setting -> new Item(setting.food(Foods.APPLE)));

    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
        setting -> new BlockItem(ModBlocks.TOMATO_CROP, setting));

    public static final Item CORN = registerItem( "corn",
            setting -> new Item(setting.food(Foods.CARROT)));

    public static final Item CORN_SEEDS = registerItem("corn_seeds",
            setting -> new BlockItem(ModBlocks.CORN_CROP, setting));

    public static final Item ANCIENT_FRUIT = registerItem("ancient_fruit", 
        setting -> new Item(setting.food(ModFoodComponents.ANCIENT_FRUIT, ModConsumableComponents.ANCIENT_FRUIT).rarity(Rarity.RARE)));

    public static final Item ANCIENT_SEED = registerItem("ancient_seed", 
        setting -> new BlockItem(ModBlocks.ANCIENT_FRUIT_CROP, setting){
        @SuppressWarnings("deprecation")
        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("tooltip.adventurerscookbook.ancient_seed"));
                    super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        }
    });

    public static final Item MUD_BALL = registerItem("mud_ball",
            setting -> new MudBallItem(setting.stacksTo(1)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name)))));
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, CARROT_JUICE);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, GOLDEN_CARROT_JUICE);
//        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, APPLE_JUICE);
//        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, MELLON_JUICE);
//        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, FRIED_EGG);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, FROG_LEGS);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, FROG_LEGS_COOKED);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, GREEN_TOMATO);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, MANGO);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, RICE_BOWL);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, SUSHI);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, TOMATO);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, ANCIENT_FRUIT);
//        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, CORN);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, DRINKING_GLASS);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, RICE);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, RICE_BAG);
        addToItemGroup(CreativeModeTabs.NATURAL_BLOCKS, RICE_SEEDS);
        addToItemGroup(CreativeModeTabs.NATURAL_BLOCKS, TOMATO_SEEDS);
        addToItemGroup(CreativeModeTabs.NATURAL_BLOCKS, ANCIENT_SEED);
//        addToItemGroup(CreativeModeTabs.NATURAL_BLOCKS, CORN_SEEDS);
    }

    private static void addToItemGroup(ResourceKey<@NotNull CreativeModeTab> group, Item item) {
        CreativeModeTabEvents.modifyOutputEvent(group).register(entries -> entries.accept(item));
    }
    
    public static void registerModItems() {
        AdventurersCookBook.LOGGER.info("Registering Potions for " + AdventurersCookBook.MOD_ID);
    
        addItemsToItemGroup();
    }
}
