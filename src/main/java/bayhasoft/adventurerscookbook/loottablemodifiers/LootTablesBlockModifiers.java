package bayhasoft.adventurerscookbook.loottablemodifiers;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import bayhasoft.adventurerscookbook.item.ModItems;

public class LootTablesBlockModifiers {
    
    private static final Identifier GRASS_ID
    = new Identifier("minecraft", "blocks/grass");

    private static final Identifier JUNGLE_LEAVES_ID
    = new Identifier("minecraft", "blocks/jungle_leaves");

    private static final Identifier TALL_GRASS_ID
    = new Identifier("minecraft", "blocks/tall_grass");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(JUNGLE_LEAVES_ID.equals(id)) {
                LootPool.Builder PoolBuilderMango = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.005f))
                    .with(ItemEntry.builder(ModItems.MANGO))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE));
                tableBuilder.pool(PoolBuilderMango.build());
            } else if (GRASS_ID.equals(id) || TALL_GRASS_ID.equals(id)){
                LootPool.Builder PoolBuilderRiceSeeds = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(0.125f))
                    .with(ItemEntry.builder(ModItems.RICE_SEEDS))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build())
                    .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE));
                tableBuilder.pool(PoolBuilderRiceSeeds.build());
            } 
        });
    }
}
