package bayhasoft.adventurerscookbook;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.block.entity.ModBlockEntities;
import bayhasoft.adventurerscookbook.item.ModItems;
import bayhasoft.adventurerscookbook.item.potion.ModPotions;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTablesBlockModifiers;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTablesChestModifiers;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTablesEntitiesModifiers;
import bayhasoft.adventurerscookbook.recipe.ModRecipes;
import bayhasoft.adventurerscookbook.screen.ModScreenHandlerType;

import bayhasoft.adventurerscookbook.util.CustomTrades;
import bayhasoft.adventurerscookbook.util.ModRegisteries;

public class AdventurersCookBook implements ModInitializer {
	public static final String MOD_ID = "adventurerscookbook";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		CustomTrades.registerCustomTrades();
		LootTablesBlockModifiers.modifyLootTables();
		LootTablesChestModifiers.modifyLootTables();
		LootTablesEntitiesModifiers.modifyLootTables();
		ModRegisteries.registerModThings();
		ModPotions.registerPotions();
		ModScreenHandlerType.registerModScreenHandlers();
		ModRecipes.registerRecipes();
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
	}
}