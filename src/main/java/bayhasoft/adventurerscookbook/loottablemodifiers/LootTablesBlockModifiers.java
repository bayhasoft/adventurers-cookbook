package bayhasoft.adventurerscookbook.loottablemodifiers;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;

public class LootTablesBlockModifiers {

    public static void modifyLootTables() {
        AdventurersCookBook.LOGGER.info(AdventurersCookBook.MOD_ID + "modifying loot tables");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            
            if(Blocks.JUNGLE_LEAVES.getLootTableKey().equals(key)) {
                LootPool.Builder PoolBuilderMango = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(ModItems.MANGO));
                    //.conditionally(TableBonusLootCondition.builder(FORTUNE, 1.0f));
                tableBuilder.pool(PoolBuilderMango.build());
            }

            if (Blocks.SHORT_GRASS.getLootTableKey().equals(key) || Blocks.TALL_GRASS.getLootTableKey().equals(key)){
                LootPool.Builder PoolBuilderRiceSeeds = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(ModItems.RICE_SEEDS));
                    //.conditionally(TableBonusLootCondition.builder((RegistryEntry<Enchantment>) FORTUNE, null));
                tableBuilder.pool(PoolBuilderRiceSeeds.build());
            } 
        });
     }
}
