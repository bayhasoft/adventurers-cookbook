package bayhasoft.adventurerscookbook.loottablemodifiers;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
// import net.minecraft.enchantment.Enchantments;
// import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
// import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
// import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import bayhasoft.adventurerscookbook.item.ModItems;

public class LootTablesEntitiesModifiers {
    
    private static final Identifier FROG_ID
    = new Identifier("minecraft", "entities/frog");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(FROG_ID.equals(id)) {
                LootPool.Builder PoolBuilderMango = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    // .conditionally(RandomChanceLootCondition.builder(0.5f))
                    .with(ItemEntry.builder(ModItems.FROG_LEGS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)).build())
                    .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)));
                tableBuilder.pool(PoolBuilderMango.build());
            } 
        });
    }
}
