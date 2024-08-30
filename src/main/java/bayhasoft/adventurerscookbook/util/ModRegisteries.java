package bayhasoft.adventurerscookbook.util;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.block.ComposterBlock;

public class ModRegisteries {
        public static void registerModThings() {
        registerCompostables();
    }

    private static void registerCompostables() {
        AdventurersCookBook.LOGGER.info("Registering Compostables from " + AdventurersCookBook.MOD_ID);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MANGO.asItem(),0.65F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RICE.asItem(),0.3F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RICE_SEEDS.asItem(),0.3F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TOMATO.asItem(),0.65F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TOMATO_SEEDS.asItem(),0.3F);
    }

}
