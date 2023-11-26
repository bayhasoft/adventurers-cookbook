package bayhasoft.adventurerscookbook.block;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.custom.RiceCropBlock;
import bayhasoft.adventurerscookbook.block.custom.TomatoCropBlock;
// import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
// import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
// import net.minecraft.item.BlockItem;
// import net.minecraft.item.Item;
// import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
   
    public static final Block RICE_CROP = registerBlockWithOutItem("rice_crop",
        new RiceCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
   
    public static final Block TOMATO_CROP = registerBlockWithOutItem("tomato_crop",
        new TomatoCropBlock(FabricBlockSettings.copyOf(Blocks.CARROTS))); 
  
    private static Block registerBlockWithOutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(AdventurersCookBook.MOD_ID, name), block);}

    // private static Block registerBlock(String name, Block block, ItemGroup group) {
    //     registerBlockItem(name, block, group);
    //     return Registry.register(Registries.BLOCK, new Identifier(AdventurersCookBook.MOD_ID, name), block);
    // }

    // private static Item registerBlockItem(String name, Block block, ItemGroup group) {
    //     Item item = Registry.register(Registries.ITEM, new Identifier(AdventurersCookBook.MOD_ID, name),
    //             new BlockItem(block, new FabricItemSettings()));
    //     ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    //     return item;
    // }

    public static void registerModBlocks() {
        AdventurersCookBook.LOGGER.info("Registering ModBlocks for " + AdventurersCookBook.MOD_ID);
    }
}
