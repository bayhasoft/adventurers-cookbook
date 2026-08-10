package bayhasoft.adventurerscookbook.block;

import java.util.function.Function;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.custom.*;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
   
    public static final Block RICE_CROP = registerBlock("rice_crop", RiceCropBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT), false);
   
    public static final Block TOMATO_CROP = registerBlock("tomato_crop", TomatoCropBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS),false); 

//    public static final Block BERRY_TEST_BUSH = registerBlock("berry_test_bush",
//        BerryTestBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), false);

    public static final Block CORN_CROP = registerBlock("corn_crop", CornCropBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PITCHER_CROP),false);

    public static final Block ANCIENT_FRUIT_CROP = registerBlock("ancient_fruit_crop", AncientFruitCropBlock::new, 
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.EMERALD)
            .noCollision()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY),
        false);

    public static final Block SEED_MAKER = registerBlock("seed_maker", SeedMakerBlock::new, 
        BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            //.requiresTool()
            .strength(3.5F),
        true);

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean needsItem) {
            ResourceKey<Block> blockKey = keyOfBlock(name);
            Block block = blockFactory.apply(settings.setId(blockKey));    
            if (needsItem) {
                ResourceKey<Item> itemKey = keyOfItem(name);

			    BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
			    Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
            }

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
        }
        private static ResourceKey<Block> keyOfBlock(String name) {
		    return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name));
	    }

	    private static ResourceKey<Item> keyOfItem(String name) {
		    return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name));
	    }
    
    public static void registerModBlocks() {
        AdventurersCookBook.LOGGER.info("Registering ModBlocks for " + AdventurersCookBook.MOD_ID);


        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register((itemGroup) -> {
	        itemGroup.accept(ModBlocks.SEED_MAKER.asItem());
        });
    }
}