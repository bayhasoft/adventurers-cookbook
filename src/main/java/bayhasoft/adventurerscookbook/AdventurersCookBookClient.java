package bayhasoft.adventurerscookbook;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.screen.ModScreenHandlerType;
import bayhasoft.adventurerscookbook.screen.SeedMakerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;





public class AdventurersCookBookClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // blocks
        BlockRenderLayerMap.putBlock(ModBlocks.RICE_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TOMATO_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BERRY_TEST_BUSH, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ANCIENT_FRUIT_CROP, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.CORN_CROP, BlockRenderLayer.CUTOUT);

        // Bind Screens to Handlers
        HandledScreens.register(ModScreenHandlerType.SEED_MAKER, SeedMakerScreen::new);
    }
}
