package bayhasoft.adventurerscookbook;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.screen.ModScreenHandlerType;
import bayhasoft.adventurerscookbook.screen.SeedMakerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;





public class AdventurersCookBookClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // Bind Screens to Handlers
        MenuScreens.register(ModScreenHandlerType.SEED_MAKER, SeedMakerScreen::new);
    }
}
