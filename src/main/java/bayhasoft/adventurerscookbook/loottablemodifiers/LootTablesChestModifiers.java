package bayhasoft.adventurerscookbook.loottablemodifiers;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LootTablesChestModifiers {
    public static void modifyLootTables() {
        AdventurersCookBook.LOGGER.info(AdventurersCookBook.MOD_ID + " modifying more loot tables");
        
        // Mineshaft
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (BuiltInLootTables.ABANDONED_MINESHAFT.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.273f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // dungeon
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (BuiltInLootTables.SIMPLE_DUNGEON.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.185f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(1, 4)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // desert pyramid
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (BuiltInLootTables.DESERT_PYRAMID.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.18f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // jungle pyramid
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (BuiltInLootTables.JUNGLE_TEMPLE.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.374f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // shipwreck treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (BuiltInLootTables.SHIPWRECK_TREASURE.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.974f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Stronghold Corridor & Stronghold Crossing
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (BuiltInLootTables.STRONGHOLD_CORRIDOR.equals(key) || BuiltInLootTables.STRONGHOLD_CROSSING.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.25f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Woodland Mansion
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (BuiltInLootTables.WOODLAND_MANSION.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.096f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(1, 4)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // End City Treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registers) -> {
            if (BuiltInLootTables.END_CITY_TREASURE.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(2))
                        .when(LootItemRandomChanceCondition.randomChance(.212f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(
                            UniformGenerator.between(2, 7)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Village Armorer
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (BuiltInLootTables.VILLAGE_ARMORER.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.542f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Village Toolsmith
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (BuiltInLootTables.VILLAGE_TOOLSMITH.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.412f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
        // Village Weaponsmith
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (BuiltInLootTables.VILLAGE_WEAPONSMITH.equals(key)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(.451f))
                        .add(LootItem.lootTableItem(ModItems.TOMATO_SEEDS))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
    }
}
