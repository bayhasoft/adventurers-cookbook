package bayhasoft.adventurerscookbook;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AdventurersCookBookClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        // blocks
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RICE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOMATO_CROP, RenderLayer.getCutout());
    }
}
