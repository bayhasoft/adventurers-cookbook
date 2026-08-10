package bayhasoft.adventurerscookbook.loottablemodifiers;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;

public class LootTablesBlockModifiers {

    public static void modifyLootTables() {
        AdventurersCookBook.LOGGER.info(AdventurersCookBook.MOD_ID + " modifying loot tables");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            
            if (Identifier.fromNamespaceAndPath("minecraft", "blocks/jungle_leaves").equals(key.identifier())) {
                LootPool.Builder PoolBuilderMango = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.005f))
                    .add(LootItem.lootTableItem(ModItems.MANGO));
                    //.conditionally(TableBonusLootCondition.builder(FORTUNE, 1.0f));
                tableBuilder.pool(PoolBuilderMango.build());
            }

            if (Identifier.fromNamespaceAndPath("minecraft", "blocks/short_grass").equals(key.identifier()) || Identifier.fromNamespaceAndPath("minecraft", "blocks/tall_grass").equals(key.identifier())){
                LootPool.Builder PoolBuilderRiceSeeds = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.125f))
                    .add(LootItem.lootTableItem(ModItems.RICE_SEEDS));
                    //.conditionally(TableBonusLootCondition.builder((RegistryEntry<Enchantment>) FORTUNE, null));
                tableBuilder.pool(PoolBuilderRiceSeeds.build());
            } 
        });
     }
}
