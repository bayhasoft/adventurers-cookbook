package bayhasoft.adventurerscookbook.loottablemodifiers;

import bayhasoft.adventurerscookbook.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableChestModifiers {

    private static final Identifier ABANDONED_MINESHAFT_CHEST_ID = new 
        Identifier("minecraft",
            "chests/abandoned_mineshaft");

    private static final Identifier SIMPLE_DUNGEON_CHEST_ID = new 
        Identifier("minecraft",
            "chests/simple_dungeon");

    private static final Identifier DESERT_PYRAMID_CHEST_ID = new 
        Identifier("minecraft",
            "chests/desert_pyramid");

    private static final Identifier JUNGLE_PYRAMID_CHEST_ID = new 
        Identifier("minecraft",
            "chests/jungle_pyramid");

    private static final Identifier SHIPWRECK_TREASURE_CHEST_ID = new 
        Identifier("minecraft",
            "chests/shipwreck_treasure");

    private static final Identifier STRONGHOLD_CORRIDOR_CHEST_ID = new 
        Identifier("minecraft",
            "chests/stronghold_corridor");

    private static final Identifier STRONGHOLD_CROSSING_CHEST_ID = new 
        Identifier("minecraft",
            "chests/stronghold_crossing");

    private static final Identifier WOODLAND_MANSION_CHEST_ID = new 
        Identifier("minecraft",
            "chests/woodland_mansion");

    private static final Identifier END_CITY_TREASURE_CHEST_ID = new 
        Identifier("minecraft",
            "chests/end_city_treasure");

    public static void modifyLootTables() {
        // Mineshaft
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (ABANDONED_MINESHAFT_CHEST_ID.equals(id)) {
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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (SIMPLE_DUNGEON_CHEST_ID.equals(id)) {
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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (DESERT_PYRAMID_CHEST_ID.equals(id)) {
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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (JUNGLE_PYRAMID_CHEST_ID.equals(id)) {
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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (SHIPWRECK_TREASURE_CHEST_ID.equals(id)) {
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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (STRONGHOLD_CORRIDOR_CHEST_ID.equals(id) || STRONGHOLD_CROSSING_CHEST_ID.equals(id)) {
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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (WOODLAND_MANSION_CHEST_ID.equals(id)) {
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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (END_CITY_TREASURE_CHEST_ID.equals(id)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))
                        .conditionally(RandomChanceLootCondition.builder(.212f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(2, 7)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });
    }
}
