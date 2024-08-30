package bayhasoft.adventurerscookbook.loottablemodifiers;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;

public class LootTablesEntitiesModifiers {
    public static void modifyLootTables() {
        AdventurersCookBook.LOGGER.info(AdventurersCookBook.MOD_ID + "modifying even more loot tables");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            
            if(EntityType.FROG.getLootTableId().equals(key)) {
                LootPool.Builder PoolBuilderMango = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(ModItems.FROG_LEGS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build())
                    .apply(EnchantedCountIncreaseLootFunction.builder(registries, UniformLootNumberProvider.create(0.0f, 1.0f)));
                tableBuilder.pool(PoolBuilderMango.build());
            }
        });
    }
}
