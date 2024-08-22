package bayhasoft.adventurerscookbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.item.ModItems;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTablesBlockModifiers;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTablesChestModifiers;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTablesChestVillageModifier;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTablesEntitiesModifiers;
import bayhasoft.adventurerscookbook.util.CustomTrades;
import bayhasoft.adventurerscookbook.util.ModRegisteries;
import net.fabricmc.api.ModInitializer;

public class AdventurersCookBook implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "adventurerscookbook";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems();
		
		ModBlocks.registerModBlocks();

		CustomTrades.registerCustomTrades();
		ModRegisteries.registerModThings();
		
		LootTablesBlockModifiers.modifyLootTables();
		LootTablesChestModifiers.modifyLootTables();
		LootTablesChestVillageModifier.modifyLootTables();
		LootTablesEntitiesModifiers.modifyLootTables();
		
		LOGGER.info("Hello Fabric world!");
	}
}