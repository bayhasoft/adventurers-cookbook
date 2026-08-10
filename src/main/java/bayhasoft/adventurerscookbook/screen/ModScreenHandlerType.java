package bayhasoft.adventurerscookbook.screen;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlerType {

    public static final MenuType<SeedMakerScreemHandler> SEED_MAKER =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, "seed_maker"),
                    new ExtendedMenuType<>(SeedMakerScreemHandler::new, BlockPos.STREAM_CODEC));
    
    public static void registerModScreenHandlers() {
        AdventurersCookBook.LOGGER.info("Registering ScreenHandlers for " + AdventurersCookBook.MOD_ID);
    }
}
