package bayhasoft.adventurerscookbook.block.custom;

import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class RiceCropBlock extends CropBlock {
    // public static final IntProperty AGE = IntProperty.of("age", 0, 7);

    public RiceCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.RICE_SEEDS;
    }

    // @Override
    // public int getMaxAge() {
    //     return 7;
    // }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }      
}
