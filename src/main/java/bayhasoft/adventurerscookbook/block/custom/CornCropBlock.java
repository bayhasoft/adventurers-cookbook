package bayhasoft.adventurerscookbook.block.custom;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.rule.GameRules;
import net.minecraft.world.tick.ScheduledTickView;
import org.jspecify.annotations.Nullable;

public class CornCropBlock extends TallPlantBlock implements Fertilizable {
    public static final MapCodec<CornCropBlock> CODEC = CornCropBlock.createCodec(CornCropBlock::new);
    public static int MaxAge = 8;
    public static int MatureAge = 6;
    public static final IntProperty AGE = IntProperty.of("age", 0, MaxAge);
    public static final EnumProperty<DoubleBlockHalf> HALF;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),

            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    @Override
    public MapCodec<? extends TallPlantBlock> getCodec() {
        return CODEC;
    }

    public CornCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, HALF);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    protected IntProperty getAgeProperty() {
        return AGE;
    }

    public int getAge(BlockState state) {
        return (Integer)state.get(this.getAgeProperty());
    }

    public BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (isDoubleTallAtAge((Integer)state.get(AGE))) {
            return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        } else {
            return state.canPlaceAt(world, pos) ? state : Blocks.AIR.getDefaultState();
        }
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return isLowerHalf(state) && !canPlaceAt(world, pos) ? false : super.canPlaceAt(state, world, pos);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.FARMLAND);
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl) {
        if (world instanceof ServerWorld serverWorld) {
            if (entity instanceof RavagerEntity && (Boolean)serverWorld.getGameRules().getValue(GameRules.DO_MOB_GRIEFING)) {
                serverWorld.breakBlock(pos, true, entity);
            }
        }

    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return false;
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

    }

    private boolean isFullyGrown(BlockState state) {
        return (Integer)state.get(AGE) >= MaxAge;
    }

    private static boolean isDoubleTallAtAge(int age) {
        return age >= 4;
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        BlockPos pos2;
        BlockState blockState2;
        if (isFullyGrown(state)) {
            dropStack(world, pos, new ItemStack(ModItems.Corn, Random.create().nextBetween(1, 3)));
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = (BlockState)state.with(AGE, 6);
            world.setBlockState(pos, blockState);
            if (isLowerHalf(state)){
                pos2 = pos.up();
                blockState2 = world.getBlockState(pos2).with(HALF, DoubleBlockHalf.UPPER).with(AGE, 3);
            }
            else {
                pos2 = pos.down();
                blockState2 = world.getBlockState(pos2).with(HALF, DoubleBlockHalf.LOWER).with(AGE, 3);
            }
            world.setBlockState(pos2, blockState2, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }

    public boolean hasRandomTicks(BlockState state) {
        return state.get(HALF) == DoubleBlockHalf.LOWER && !this.isFullyGrown(state);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float f = getAvailableMoisture(this, world, pos);
        boolean bl = random.nextInt((int)(25.0F / f) + 1) == 0;
        if (bl) {
            this.tryGrow(world, state, pos, 1);
        }

    }

//    from minecraft.block.CropBlock    //
    protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockPos = pos.down();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float g = 0.0F;
                BlockState blockState = world.getBlockState(blockPos.add(i, 0, j));
                if (blockState.isOf(Blocks.FARMLAND)) {
                    g = 1.0F;
                    if ((Integer)blockState.get(FarmlandBlock.MOISTURE) > 0) {
                        g = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    g /= 4.0F;
                }

                f += g;
            }
        }

        BlockPos blockPos2 = pos.north();
        BlockPos blockPos3 = pos.south();
        BlockPos blockPos4 = pos.west();
        BlockPos blockPos5 = pos.east();
        boolean bl = world.getBlockState(blockPos4).isOf(block) || world.getBlockState(blockPos5).isOf(block);
        boolean bl2 = world.getBlockState(blockPos2).isOf(block) || world.getBlockState(blockPos3).isOf(block);
        if (bl && bl2) {
            f /= 2.0F;
        } else {
            boolean bl3 = world.getBlockState(blockPos4.north()).isOf(block) || world.getBlockState(blockPos5.north()).isOf(block) || world.getBlockState(blockPos5.south()).isOf(block) || world.getBlockState(blockPos4.south()).isOf(block);
            if (bl3) {
                f /= 2.0F;
            }
        }

        return f;
    }

    private void tryGrow(ServerWorld world, BlockState state, BlockPos pos, int amount) {
        int i = Math.min((Integer)state.get(AGE) + amount, MaxAge);
        if (this.canGrow(world, pos, state, i)) {
            BlockState blockState = (BlockState)state.with(AGE, i);
            world.setBlockState(pos, blockState, 2);
            if (isDoubleTallAtAge(i)) {
                world.setBlockState(pos.up(), (BlockState)blockState.with(HALF, DoubleBlockHalf.UPPER), 3);
            }

        }
    }

    private static boolean canGrowAt(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isAir() || blockState.isOf(ModBlocks.CORN_CROP);
    }

    private static boolean canPlaceAt(WorldView world, BlockPos pos) {
        return hasEnoughLightAt(world, pos);
    }

    protected static boolean hasEnoughLightAt(WorldView world, BlockPos pos) {
        return world.getBaseLightLevel(pos, 0) >= 8;
    }

    private static boolean isLowerHalf(BlockState state) {
        return state.isOf(ModBlocks.CORN_CROP) && state.get(HALF) == DoubleBlockHalf.LOWER;
    }

    private boolean canGrow(WorldView world, BlockPos pos, BlockState state, int age) {
        return !this.isFullyGrown(state) && canPlaceAt(world, pos) && (!isDoubleTallAtAge(age) || canGrowAt(world, pos.up()));
    }

    private CornCropBlock.@Nullable LowerHalfContext getLowerHalfContext(WorldView world, BlockPos pos, BlockState state) {
        if (isLowerHalf(state)) {
            return new CornCropBlock.LowerHalfContext(pos, state);
        } else {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            return isLowerHalf(blockState) ? new CornCropBlock.LowerHalfContext(blockPos, blockState) : null;
        }
    }

    public boolean isFertilizable(WorldView World, BlockPos pos, BlockState state) {
        return state.get(AGE) < MatureAge;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        CornCropBlock.LowerHalfContext lowerHalfContext = this.getLowerHalfContext(world, pos, state);
        if (lowerHalfContext != null) {
            this.tryGrow(world, lowerHalfContext.state, lowerHalfContext.pos, 1);
        }
    }

    static {
        HALF = TallPlantBlock.HALF;
    }

    record LowerHalfContext(BlockPos pos, BlockState state) {
        }
    }
