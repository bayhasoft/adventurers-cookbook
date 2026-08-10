package bayhasoft.adventurerscookbook.block.entity;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<SeedMakerBlockEntity> SEED_MAKER_BLOCK_ENTITY = register("seed_maker_block_entity",
            FabricBlockEntityTypeBuilder.create(SeedMakerBlockEntity::new, ModBlocks.SEED_MAKER)
                    .build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(AdventurersCookBook.MOD_ID, name), type);
    }

    public static void registerBlockEntities() {
        AdventurersCookBook.LOGGER.info("Registering Block Entities for " + AdventurersCookBook.MOD_ID);
    }
}