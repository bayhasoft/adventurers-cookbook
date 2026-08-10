package bayhasoft.adventurerscookbook.item.components;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModConsumableComponents extends Consumables{
    public static final Consumable JUICE = defaultDrink().consumeSeconds(0.8F).build();
    public static final Consumable ANCIENT_FRUIT = defaultFood()
    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 0), 1.0F))
    .build();
    public static final Consumable GREEN_TOMATO = defaultFood()
    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.6F))
    .build();
}