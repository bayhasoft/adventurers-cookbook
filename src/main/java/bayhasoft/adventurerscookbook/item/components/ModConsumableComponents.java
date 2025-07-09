package bayhasoft.adventurerscookbook.item.components;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModConsumableComponents extends ConsumableComponents{
    public static final ConsumableComponent JUICE = drink().consumeSeconds(0.8F).build();
    public static final ConsumableComponent ANCIENT_FRUIT = food()
    .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 0), 1.0F))
    .build();
    public static final ConsumableComponent GREEN_TOMATO = food()
    .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0), 0.6F))
    .build();
}