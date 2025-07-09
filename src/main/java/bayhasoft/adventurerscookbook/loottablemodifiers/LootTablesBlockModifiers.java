package bayhasoft.adventurerscookbook.loottablemodifiers;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;

public class LootTablesBlockModifiers {

    public static void modifyLootTables() {
        AdventurersCookBook.LOGGER.info(AdventurersCookBook.MOD_ID + "modifying loot tables");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            
            if (Identifier.of("minecraft", "blocks/jungle_leaves").equals(key.getValue())) {
                LootPool.Builder PoolBuilderMango = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.005f))
                    .with(ItemEntry.builder(ModItems.MANGO));
                    //.conditionally(TableBonusLootCondition.builder(FORTUNE, 1.0f));
                tableBuilder.pool(PoolBuilderMango.build());
            }

            if (Identifier.of("minecraft", "blocks/short_grass").equals(key.getValue()) || Identifier.of("minecraft", "blocks/tall_grass").equals(key.getValue())){
                LootPool.Builder PoolBuilderRiceSeeds = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.125f))
                    .with(ItemEntry.builder(ModItems.RICE_SEEDS));
                    //.conditionally(TableBonusLootCondition.builder((RegistryEntry<Enchantment>) FORTUNE, null));
                tableBuilder.pool(PoolBuilderRiceSeeds.build());
            } 
        });
     }
}
