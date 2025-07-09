package bayhasoft.adventurerscookbook.block.custom;

import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;


public class AncientFruitCropBlock extends CropBlock{
    public static final IntProperty AGE = IntProperty.of("age", 0, 5);

    public AncientFruitCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }
    
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.ANCIENT_SEED;
    }
   
    @Override
    public int getMaxAge() {
        return 5;
    }

    
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int i = (Integer)state.get(AGE);
        if (i == 5) {
            dropStack(world, pos, new ItemStack(ModItems.ANCIENT_FRUIT, 1));
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = (BlockState)state.with(AGE, 3);
            world.setBlockState(pos, blockState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, Emitter.of(player, blockState));
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }

    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
      return (Integer)state.get(AGE) < 3;
    }

    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + 1;
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }
        world.setBlockState(pos, this.withAge(i), 2);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }    
}
