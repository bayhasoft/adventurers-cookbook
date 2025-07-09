package bayhasoft.adventurerscookbook.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class CustomTrades {
    public static void registerCustomTrades() {
        AdventurersCookBook.LOGGER.info("Registering Villager Trades From " + AdventurersCookBook.MOD_ID);

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 2,
            factories -> {
                factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    new ItemStack(ModItems.SUSHI, 5),
                    16, 5, 0.05f));
            });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
            factories -> {
                factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.TOMATO, 20),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
                factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.RICE, 20),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f));
                factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    new ItemStack(ModItems.RICE_BOWL, 6),
                    16, 1, 0.05f));
            });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5,
            factories -> {
                factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.ANCIENT_FRUIT, 1),
                    new ItemStack(Items.EMERALD, 2),
                    16, 30, 0.05f));
                    AdventurersCookBook.LOGGER.info("this should print once");
            });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
            factories -> {
                factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    new ItemStack(ModItems.SUSHI, 5),
                    16, 5, 0.05f));
            });
        TradeOfferHelper.registerWanderingTraderOffers(
            factories -> {
                factories.addAll(Identifier.of(AdventurersCookBook.MOD_ID, "emerald_for_tomato_seeds"), (entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    new ItemStack(ModItems.TOMATO_SEEDS, 1),
                    5, 1, 0.075f));
                factories.addAll(Identifier.of(AdventurersCookBook.MOD_ID, "emerald_for_rice_seeds"), (entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 1),
                    new ItemStack(ModItems.RICE_SEEDS, 1),
                    5, 1, 0.075f));
            });
    }
}
