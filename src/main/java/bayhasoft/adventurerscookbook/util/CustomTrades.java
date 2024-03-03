package bayhasoft.adventurerscookbook.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class CustomTrades {
    public static void registerCustomTrades() {

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 2,
            factories -> {
                factories.add((entity, random) -> new TradeOffer(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(ModItems.SUSHI, 5),
                        6, 5, 0.05f));
            });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.TOMATO, 20),
                            new ItemStack(Items.EMERALD, 1),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.RICE, 20),
                            new ItemStack(Items.EMERALD, 1),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(ModItems.RICE_BOWL, 6),
                            6, 5, 0.05f));
                });

        // TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2,
        //         factories -> {
        //         });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
            factories -> {
                factories.add((entity, random) -> new TradeOffer(
                        new ItemStack(Items.EMERALD, 1),
                        new ItemStack(ModItems.SUSHI, 5),
                        6, 5, 0.05f));
            });

        // TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1,
        //         factories -> {

        //         });


        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> {

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(ModItems.TOMATO_SEEDS, 1),
                            1, 12, 0.075f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(ModItems.RICE_SEEDS, 1),
                            1, 12, 0.075f));

                });

        // TradeOfferHelper.registerWanderingTraderOffers(2,
        //         factories -> {

        //         });
    }
}
