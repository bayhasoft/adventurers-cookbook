package bayhasoft.adventurerscookbook.item.potion;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

public class ModPotions {
    public static Holder<Potion> VITALITY_POTION, LONG_VITALITY_POTION, STRONG_VITALITY_POTION;

    public static Holder<MobEffect> HEALTH_BOOST = MobEffects.HEALTH_BOOST;
    
    public static void registerPotions() {    
        AdventurersCookBook.LOGGER.info("Registering Mod Items for " + AdventurersCookBook.MOD_ID);
        
        VITALITY_POTION = registerPotion("vitality_potion", new Potion("vitality_potion", new MobEffectInstance(HEALTH_BOOST, 20 * 180, 0)));
        LONG_VITALITY_POTION = registerPotion("long_vitality_potion", new Potion("vitality_potion", new MobEffectInstance(HEALTH_BOOST, 20 * 480, 0)));
        STRONG_VITALITY_POTION = registerPotion("strong_vitality_potion", new Potion("vitality_potion", new MobEffectInstance(HEALTH_BOOST, 20 * 180, 1)));
        
        registerRecipes();
    }
    
    public static Reference<Potion> registerPotion( String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name), potion);
    }

    public static void registerRecipes() {
        FabricPotionBrewingBuilder.BUILD.register((recipes -> {
            recipes.addMix(Potions.AWKWARD, ModItems.ANCIENT_FRUIT, VITALITY_POTION);

            recipes.addMix(VITALITY_POTION, Items.REDSTONE, LONG_VITALITY_POTION);
            recipes.addMix(STRONG_VITALITY_POTION, Items.REDSTONE, LONG_VITALITY_POTION);

            recipes.addMix(VITALITY_POTION, Items.GLOWSTONE_DUST, STRONG_VITALITY_POTION);
            recipes.addMix(LONG_VITALITY_POTION, Items.GLOWSTONE_DUST, STRONG_VITALITY_POTION);
        }));
    }
}
