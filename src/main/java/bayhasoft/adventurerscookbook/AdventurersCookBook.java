package bayhasoft.adventurerscookbook;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.ComposterBlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.item.ModItems;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTableBlockModifiers;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTableChestModifiers;
import bayhasoft.adventurerscookbook.loottablemodifiers.LootTableChestVillageModifier;
import bayhasoft.adventurerscookbook.util.CustomTrades;

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
		LootTableBlockModifiers.modifyLootTables();
		LootTableChestModifiers.modifyLootTables();
		LootTableChestVillageModifier.modifyLootTables();

		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MANGO.asItem(),0.65F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RICE.asItem(),0.3F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RICE_SEEDS.asItem(),0.3F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TOMATO.asItem(),0.65F);
		ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.TOMATO_SEEDS.asItem(),0.3F);

		LOGGER.info("Hello Fabric world!");
	}
}