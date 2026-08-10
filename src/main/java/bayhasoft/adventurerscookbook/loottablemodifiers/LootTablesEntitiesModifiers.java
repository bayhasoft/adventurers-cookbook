package bayhasoft.adventurerscookbook.loottablemodifiers;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;

public class LootTablesEntitiesModifiers {
    public static void modifyLootTables() {
        AdventurersCookBook.LOGGER.info(AdventurersCookBook.MOD_ID + " modifying even more loot tables");
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            
            if(Identifier.fromNamespaceAndPath("minecraft", "entities/frog").equals(key.identifier())) {
                LootPool.Builder PoolBuilderMango = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(ModItems.FROG_LEGS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 1.0f)).build())
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0f, 1.0f)));
                tableBuilder.pool(PoolBuilderMango.build());
            }
        });
    }
}
