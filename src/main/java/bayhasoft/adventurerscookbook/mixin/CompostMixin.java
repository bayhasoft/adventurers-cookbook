package bayhasoft.adventurerscookbook.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;

import org.apache.commons.lang3.NotImplementedException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import bayhasoft.adventurerscookbook.item.ModItems;

@Mixin(ComposterBlock.class)
public class CompostMixin extends Block {
	public CompostMixin(Settings settings) {
		super(settings);
	}

	@Invoker
	private static void invokeRegisterCompostableItem(float levelIncreaseChance, ItemConvertible item)    {
        throw new NotImplementedException("The invoke failed.");
    }
	

	@Inject(at = @At("HEAD"), method = "registerDefaultCompostableItems()V")
	private static void injectRegisterDefaultCompostableItems(CallbackInfo info) {
		CompostMixin.invokeRegisterCompostableItem(0.3f, ModItems.RICE_SEEDS);
        CompostMixin.invokeRegisterCompostableItem(0.3f, ModItems.TOMATO_SEEDS);
        CompostMixin.invokeRegisterCompostableItem(0.65f, ModItems.MANGO);
		CompostMixin.invokeRegisterCompostableItem(0.65f, ModItems.RICE);
        CompostMixin.invokeRegisterCompostableItem(0.65f, ModItems.TOMATO);
        CompostMixin.invokeRegisterCompostableItem(0.85f, ModItems.RICE_BAG);
	}

}