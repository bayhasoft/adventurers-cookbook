package bayhasoft.adventurerscookbook.block.custom;

import com.mojang.serialization.MapCodec;

import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;

public class BerryTestBlock extends PlantBlock implements Fertilizable {
   public static final MapCodec<BerryTestBlock> CODEC = createCodec(BerryTestBlock::new);
   public static final int MAX_AGE = 2;
   public static final IntProperty AGE;
   private static final VoxelShape SMALL_SHAPE;
   private static final VoxelShape LARGE_SHAPE;

   public MapCodec<BerryTestBlock> getCodec() {
      return CODEC;
   }

   public BerryTestBlock(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
   }

   public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
      return new ItemStack(ModItems.ANCIENT_FRUIT);
   }

   protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      if ((Integer)state.get(AGE) == 0) {
         return SMALL_SHAPE;
      } else {
         return (Integer)state.get(AGE) < 2 ? LARGE_SHAPE : super.getOutlineShape(state, world, pos, context);
      }
   }

   protected boolean hasRandomTicks(BlockState state) {
      return (Integer)state.get(AGE) < 2;
   }

   protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      int i = (Integer)state.get(AGE);
      if (i < 2 && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
         BlockState blockState = (BlockState)state.with(AGE, i + 1);
         world.setBlockState(pos, blockState, 2);
         world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, Emitter.of(blockState));
      }

   }

   protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      int i = (Integer)state.get(AGE);
      boolean bl = i == 2;
      return !bl && stack.isOf(Items.BONE_MEAL) ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION : super.onUseWithItem(stack, state, world, pos, player, hand, hit);
   }

   protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
      int i = (Integer)state.get(AGE);
      if (i > 1) {
         dropStack(world, pos, new ItemStack(ModItems.ANCIENT_FRUIT, 1));
         world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
         BlockState blockState = (BlockState)state.with(AGE, 1);
         world.setBlockState(pos, blockState, 2);
         world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, Emitter.of(player, blockState));
         return ActionResult.success(world.isClient);
      } else {
         return super.onUse(state, world, pos, player, hit);
      }
   }

   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(new Property[]{AGE});
   }

   public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
      return (Integer)state.get(AGE) < 1;
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      int i = Math.min(3, (Integer)state.get(AGE) + 1);
      world.setBlockState(pos, (BlockState)state.with(AGE, i), 2);
   }

   static {
      AGE = Properties.AGE_2;
      SMALL_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
      LARGE_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
   }
}
