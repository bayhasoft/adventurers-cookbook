package bayhasoft.adventurerscookbook.loottablemodifiers;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class LootTablesChestModifiers {
    public static void modifyLootTables() {
        AdventurersCookBook.LOGGER.info(AdventurersCookBook.MOD_ID + "modifying more loot tables");
        
        // Mineshaft
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.273f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // dungeon
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.185f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(1, 4)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // desert pyramid
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (LootTables.DESERT_PYRAMID_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.18f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // jungle pyramid
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.374f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // shipwreck treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (LootTables.SHIPWRECK_TREASURE_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.974f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Stronghold Corridor & Stronghold Crossing
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(key) || LootTables.STRONGHOLD_CROSSING_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.25f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Woodland Mansion
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (LootTables.WOODLAND_MANSION_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.096f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(1, 4)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // End City Treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (LootTables.END_CITY_TREASURE_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(.212f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(2, 7)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Village Armorer
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.VILLAGE_ARMORER_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.542f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Village Toolsmith
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.VILLAGE_TOOLSMITH_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.412f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Village Weaponsmith
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.451f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
    }
}
