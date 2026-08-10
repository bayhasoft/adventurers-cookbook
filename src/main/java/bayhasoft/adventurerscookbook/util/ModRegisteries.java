package bayhasoft.adventurerscookbook.util;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.world.level.block.ComposterBlock;

public class ModRegisteries {
        public static void registerModThings() {
        registerCompostables();
    }

    private static void registerCompostables() {
        AdventurersCookBook.LOGGER.info("Registering Compostables from " + AdventurersCookBook.MOD_ID);
		ComposterBlock.COMPOSTABLES.put(ModItems.MANGO.asItem(),0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.RICE.asItem(),0.3F);
		ComposterBlock.COMPOSTABLES.put(ModItems.RICE_SEEDS.asItem(),0.3F);
		ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO.asItem(),0.65F);
		ComposterBlock.COMPOSTABLES.put(ModItems.TOMATO_SEEDS.asItem(),0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CORN.asItem(),0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CORN_SEEDS.asItem(),0.3F);
    }

}
