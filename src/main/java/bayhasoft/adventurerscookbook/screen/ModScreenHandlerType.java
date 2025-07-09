package bayhasoft.adventurerscookbook.screen;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlerType {

    public static final ScreenHandlerType<SeedMakerScreemHandler> SEED_MAKER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(AdventurersCookBook.MOD_ID, "seed_maker"),
                    new ExtendedScreenHandlerType<>(SeedMakerScreemHandler::new, BlockPos.PACKET_CODEC));
    
    public static void registerModScreenHandlers() {
        AdventurersCookBook.LOGGER.info("Registering ScreenHandlers for " + AdventurersCookBook.MOD_ID);
    }
}
