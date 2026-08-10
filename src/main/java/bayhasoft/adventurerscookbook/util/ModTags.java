package bayhasoft.adventurerscookbook.util;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        // none

        @SuppressWarnings("unused")
        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name));
        }
    }

    public static class Items {
        
        public static final TagKey<Item> SEED_MAKER_INPUT = createTag("seed_maker_input");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name));
        }
    }
}
