package bayhasoft.adventurerscookbook.block.entity;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import bayhasoft.adventurerscookbook.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<SeedMakerBlockEntity> SEED_MAKER_BLOCK_ENTITY = register("seed_maker_block_entity",
            FabricBlockEntityTypeBuilder.create(SeedMakerBlockEntity::new, ModBlocks.SEED_MAKER)
                    .build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(AdventurersCookBook.MOD_ID, name), type);
    }

    public static void registerBlockEntities() {
        AdventurersCookBook.LOGGER.info("Registering Block Entities for " + AdventurersCookBook.MOD_ID);
    }
}