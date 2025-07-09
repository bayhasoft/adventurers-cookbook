package bayhasoft.adventurerscookbook.block;

import java.util.function.Function;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.custom.AncientFruitCropBlock;
import bayhasoft.adventurerscookbook.block.custom.BerryTestBlock;
import bayhasoft.adventurerscookbook.block.custom.RiceCropBlock;
import bayhasoft.adventurerscookbook.block.custom.SeedMakerBlock;
import bayhasoft.adventurerscookbook.block.custom.TomatoCropBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
   
    public static final Block RICE_CROP = registerBlock("rice_crop", RiceCropBlock::new,
        AbstractBlock.Settings.copy(Blocks.WHEAT), false);
   
    public static final Block TOMATO_CROP = registerBlock("tomato_crop", TomatoCropBlock::new,
        AbstractBlock.Settings.copy(Blocks.CARROTS),false); 

    public static final Block BERRY_TEST_BUSH = registerBlock("berry_test_bush",
        BerryTestBlock::new, AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH), false);

    public static final Block ANCIENT_FRUIT_CROP = registerBlock("ancient_fruit_crop", AncientFruitCropBlock::new, 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.EMERALD_GREEN)
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .sounds(BlockSoundGroup.CROP)
            .pistonBehavior(PistonBehavior.DESTROY),
        false);

    public static final Block SEED_MAKER = registerBlock("seed_maker", SeedMakerBlock::new, 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.BROWN)
            //.requiresTool()
            .strength(3.5F),
        true);

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean needsItem) {
            RegistryKey<Block> blockKey = keyOfBlock(name);
            Block block = blockFactory.apply(settings.registryKey(blockKey));    
            if (needsItem) {
                RegistryKey<Item> itemKey = keyOfItem(name);

			    BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
			    Registry.register(Registries.ITEM, itemKey, blockItem);
            }

		return Registry.register(Registries.BLOCK, blockKey, block);
        }
        private static RegistryKey<Block> keyOfBlock(String name) {
		    return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AdventurersCookBook.MOD_ID, name));
	    }

	    private static RegistryKey<Item> keyOfItem(String name) {
		    return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AdventurersCookBook.MOD_ID, name));
	    }
    
    public static void registerModBlocks() {
        AdventurersCookBook.LOGGER.info("Registering ModBlocks for " + AdventurersCookBook.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((itemGroup) -> {
	        itemGroup.add(ModBlocks.SEED_MAKER.asItem());
        });
    }
}