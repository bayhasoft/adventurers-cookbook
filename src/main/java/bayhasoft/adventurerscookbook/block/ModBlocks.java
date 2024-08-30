package bayhasoft.adventurerscookbook.block;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.custom.AncientFruitCropBlock;
import bayhasoft.adventurerscookbook.block.custom.BerryTestBlock;
import bayhasoft.adventurerscookbook.block.custom.RiceCropBlock;
import bayhasoft.adventurerscookbook.block.custom.TomatoCropBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
   
    public static final Block RICE_CROP = registerBlockWithOutItem("rice_crop",
        new RiceCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
   
    public static final Block TOMATO_CROP = registerBlockWithOutItem("tomato_crop",
        new TomatoCropBlock(AbstractBlock.Settings.copy(Blocks.CARROTS))); 

    public static final Block BERRY_TEST_BUSH = registerBlockWithOutItem("berry_test_bush", 
        new BerryTestBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final Block ANCIENT_FRUIT_CROP = registerBlockWithOutItem("ancient_fruit_crop", 
        new AncientFruitCropBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.EMERALD_GREEN)
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .sounds(BlockSoundGroup.CROP)
            .pistonBehavior(PistonBehavior.DESTROY)));
  
    private static Block registerBlockWithOutItem(String name, Block block ) {
        return Registry.register(Registries.BLOCK, Identifier.of(AdventurersCookBook.MOD_ID, name), block);}

    @SuppressWarnings("unused")
    private static Block registerBlock(String name, Block block, RegistryKey<ItemGroup> itemgroup) {
        registerBlockItem(name, block, itemgroup);
        return Registry.register(Registries.BLOCK, Identifier.of(AdventurersCookBook.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, RegistryKey<ItemGroup> itemgroup) {
        Item item = Registry.register(Registries.ITEM, Identifier.of(AdventurersCookBook.MOD_ID, name),
                new BlockItem(block, new Settings()));
        ItemGroupEvents.modifyEntriesEvent(itemgroup).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        AdventurersCookBook.LOGGER.info("Registering ModBlocks for " + AdventurersCookBook.MOD_ID);
    }
}
