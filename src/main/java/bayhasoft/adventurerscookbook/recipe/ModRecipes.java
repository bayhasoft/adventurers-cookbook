package bayhasoft.adventurerscookbook.recipe;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final RecipeSerializer<SeedMakerRecipe> SEED_MAKER_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, "seed_maker"),
                    new RecipeSerializer<>(SeedMakerRecipe.CODEC, SeedMakerRecipe.STREAM_CODEC));

    public static final RecipeType<SeedMakerRecipe> SEED_MAKER_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, "seed_maker"), new RecipeType<SeedMakerRecipe>() {
                @Override
                public String toString() {
                    return "seed_maker";
                }
            });
            
            
    public static void registerRecipes() {
        AdventurersCookBook.LOGGER.info("Registering Custom Recipes for " + AdventurersCookBook.MOD_ID);
    }
}
