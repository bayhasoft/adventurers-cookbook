package bayhasoft.adventurerscookbook.block.custom;

import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.phys.BlockHitResult;


public class AncientFruitCropBlock extends CropBlock{
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);

    public AncientFruitCropBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }
    
    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.ANCIENT_SEED;
    }
   
    @Override
    public int getMaxAge() {
        return 5;
    }

    
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        int i = (Integer)state.getValue(AGE);
        if (i == 5) {
            popResource(world, pos, new ItemStack(ModItems.ANCIENT_FRUIT, 1));
            world.playSound((Player)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);
            BlockState blockState = (BlockState)state.setValue(AGE, 3);
            world.setBlock(pos, blockState, 2);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, world, pos, player, hit);
        }
    }

    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
      return (Integer)state.getValue(AGE) < 3;
    }

    public void growCrops(Level world, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + 1;
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }
        world.setBlock(pos, this.getStateForAge(i), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }    
}
