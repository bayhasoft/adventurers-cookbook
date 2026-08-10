package bayhasoft.adventurerscookbook.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import bayhasoft.adventurerscookbook.item.ModItems;

public class BerryTestBlock extends VegetationBlock implements BonemealableBlock {
   public static final MapCodec<BerryTestBlock> CODEC = simpleCodec(BerryTestBlock::new);
   public static final int MAX_AGE = 2;
   public static final IntegerProperty AGE;
   private static final VoxelShape SMALL_SHAPE;
   private static final VoxelShape LARGE_SHAPE;

   public MapCodec<BerryTestBlock> codec() {
      return CODEC;
   }

   public BerryTestBlock(BlockBehaviour.Properties settings) {
      super(settings);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(AGE, 0));
   }

   public ItemStack getPickStack(LevelReader world, BlockPos pos, BlockState state) {
      return new ItemStack(ModItems.ANCIENT_FRUIT);
   }

   protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      if ((Integer)state.getValue(AGE) == 0) {
         return SMALL_SHAPE;
      } else {
         return (Integer)state.getValue(AGE) < 2 ? LARGE_SHAPE : super.getShape(state, world, pos, context);
      }
   }

   protected boolean isRandomlyTicking(BlockState state) {
      return (Integer)state.getValue(AGE) < 2;
   }

   protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      int i = (Integer)state.getValue(AGE);
      if (i < 2 && random.nextInt(5) == 0 && world.getRawBrightness(pos.above(), 0) >= 9) {
         BlockState blockState = (BlockState)state.setValue(AGE, i + 1);
         world.setBlock(pos, blockState, 2);
         world.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(blockState));
      }

   }

   protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
      int i = (Integer)state.getValue(AGE);
      boolean bl = i == 2;
		return (InteractionResult)(!bl && stack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(stack, state, world, pos, player, hand, hit));
   }

   protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
      int i = (Integer)state.getValue(AGE);
      if (i > 1) {
         popResource(world, pos, new ItemStack(ModItems.ANCIENT_FRUIT, 1));
         world.playSound((Player)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);
         BlockState blockState = (BlockState)state.setValue(AGE, 1);
         world.setBlock(pos, blockState, 2);
         world.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(player, blockState));
         return InteractionResult.SUCCESS;
      } else {
         return super.useWithoutItem(state, world, pos, player, hit);
      }
   }

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
      builder.add(new Property[]{AGE});
   }

   public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
      return (Integer)state.getValue(AGE) < 1;
   }

   public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
      return true;
   }

   public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
      int i = Math.min(3, (Integer)state.getValue(AGE) + 1);
      world.setBlock(pos, (BlockState)state.setValue(AGE, i), 2);
   }

   static {
      AGE = BlockStateProperties.AGE_2;
      SMALL_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
      LARGE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
   }
}
