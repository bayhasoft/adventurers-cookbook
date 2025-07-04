package bayhasoft.adventurerscookbook.recipe;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<SeedMakerRecipe> SEED_MAKER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(AdventurersCookBook.MOD_ID, "seed_maker"),
                    new SeedMakerRecipe.Serializer());
    public static final RecipeType<SeedMakerRecipe> SEED_MAKER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(AdventurersCookBook.MOD_ID, "seed_maker"), new RecipeType<SeedMakerRecipe>() {
                @Override
                public String toString() {
                    return "seed_maker";
                }
            });
            
            
    public static void registerRecipes() {
        AdventurersCookBook.LOGGER.info("Registering Custom Recipes for " + AdventurersCookBook.MOD_ID);
    }
}
