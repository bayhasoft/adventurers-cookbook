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

public class LootTableChestVillageModifier {

    private static final Identifier VILLAGE_ARMORER_CHEST_ID = new 
        Identifier("minecraft",
            "chests/village/village_armorer");

    private static final Identifier VILLAGE_TOOLSMITH_CHEST_ID = new 
        Identifier("minecraft",
            "chests/village/village_toolsmith");

    private static final Identifier VILLAGE_WEAPONSMITH_CHEST_ID = new 
        Identifier("minecraft",
            "chests/village/village_weaponsmith");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> { // Armorer
            if (VILLAGE_ARMORER_CHEST_ID.equals(id)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.542f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> { // Toolsmith
            if (VILLAGE_TOOLSMITH_CHEST_ID.equals(id)) {
                LootPool.Builder PoolBuilderTomatoSeeds = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(.412f))
                        .with(ItemEntry.builder(ModItems.TOMATO_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 5)).build());
                tableBuilder.pool(PoolBuilderTomatoSeeds.build());
            }
        });

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> { // Weaponsmith
            if (VILLAGE_WEAPONSMITH_CHEST_ID.equals(id)) {
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
