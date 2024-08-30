package bayhasoft.adventurerscookbook.item.potion;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static RegistryEntry<Potion> VITALITY_POTION, LONG_VITALITY_POTION, STRONG_VITALITY_POTION;

    public static RegistryEntry<StatusEffect> HEALTH_BOOST = StatusEffects.HEALTH_BOOST;
    
    public static void registerPotions() {    
        AdventurersCookBook.LOGGER.info("Registering Mod Items for " + AdventurersCookBook.MOD_ID);
        
        VITALITY_POTION = registerPotion("vitality_potion", new Potion(new StatusEffectInstance(HEALTH_BOOST, 20 * 180, 0)));
        LONG_VITALITY_POTION = registerPotion("long_vitality_potion", new Potion(new StatusEffectInstance(HEALTH_BOOST, 20 * 480, 0)));
        STRONG_VITALITY_POTION = registerPotion("strong_vitality_potion", new Potion(new StatusEffectInstance(HEALTH_BOOST, 20 * 180, 1)));
        
        registerRecipes();
    }
    
    public static Reference<Potion> registerPotion( String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(AdventurersCookBook.MOD_ID, name), potion);
    }

    public static void registerRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register((recipes -> {
            recipes.registerPotionRecipe(Potions.AWKWARD, ModItems.ANCIENT_FRUIT, VITALITY_POTION);

            recipes.registerPotionRecipe(VITALITY_POTION, Items.REDSTONE, LONG_VITALITY_POTION);
            recipes.registerPotionRecipe(STRONG_VITALITY_POTION, Items.REDSTONE, LONG_VITALITY_POTION);

            recipes.registerPotionRecipe(VITALITY_POTION, Items.GLOWSTONE_DUST, STRONG_VITALITY_POTION);
            recipes.registerPotionRecipe(LONG_VITALITY_POTION, Items.GLOWSTONE_DUST, STRONG_VITALITY_POTION);
        }));
    }
}
