package bayhasoft.adventurerscookbook.block.custom;

import bayhasoft.adventurerscookbook.block.ModBlocks;
import bayhasoft.adventurerscookbook.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class CornCropBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final MapCodec<CornCropBlock> CODEC = CornCropBlock.simpleCodec(CornCropBlock::new);
    public static int MaxAge = 8;
    public static int MatureAge = 6;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MaxAge);
    public static final EnumProperty<DoubleBlockHalf> HALF;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),

            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    @Override
    public MapCodec<? extends DoublePlantBlock> codec() {
        return CODEC;
    }

    public CornCropBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, HALF);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }

    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getAge(BlockState state) {
        return (Integer)state.getValue(this.getAgeProperty());
    }

    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (isDoubleTallAtAge((Integer)state.getValue(AGE))) {
            return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        } else {
            return state.canSurvive(world, pos) ? state : Blocks.AIR.defaultBlockState();
        }
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return isLowerHalf(state) && !canPlaceAt(world, pos) ? false : super.canSurvive(state, world, pos);
    }

    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(Blocks.FARMLAND);
    }

    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl) {
        if (world instanceof ServerLevel serverWorld) {
            if (entity instanceof Ravager && (Boolean)serverWorld.getGameRules().get(GameRules.MOB_GRIEFING)) {
                serverWorld.destroyBlock(pos, true, entity);
            }
        }

    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return false;
    }

    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {

    }

    private boolean isFullyGrown(BlockState state) {
        return (Integer)state.getValue(AGE) >= MaxAge;
    }

    private static boolean isDoubleTallAtAge(int age) {
        return age >= 4;
    }

    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        BlockPos pos2;
        BlockState blockState2;
        if (isFullyGrown(state)) {
            popResource(world, pos, new ItemStack(ModItems.CORN, RandomSource.create().nextIntBetweenInclusive(1, 3)));
            world.playSound((Player)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);
            BlockState blockState = (BlockState)state.setValue(AGE, 6);
            world.setBlockAndUpdate(pos, blockState);
            if (isLowerHalf(state)){
                pos2 = pos.above();
                blockState2 = world.getBlockState(pos2).setValue(HALF, DoubleBlockHalf.UPPER).setValue(AGE, 3);
            }
            else {
                pos2 = pos.below();
                blockState2 = world.getBlockState(pos2).setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, 3);
            }
            world.setBlock(pos2, blockState2, 2);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, world, pos, player, hit);
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && !this.isFullyGrown(state);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        float f = getAvailableMoisture(this, world, pos);
        boolean bl = random.nextInt((int)(25.0F / f) + 1) == 0;
        if (bl) {
            this.tryGrow(world, state, pos, 1);
        }

    }

//    from minecraft.block.CropBlock    //
    protected static float getAvailableMoisture(Block block, BlockGetter world, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockPos = pos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float g = 0.0F;
                BlockState blockState = world.getBlockState(blockPos.offset(i, 0, j));
                if (blockState.is(Blocks.FARMLAND)) {
                    g = 1.0F;
                    if ((Integer)blockState.getValue(FarmlandBlock.MOISTURE) > 0) {
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
        boolean bl = world.getBlockState(blockPos4).is(block) || world.getBlockState(blockPos5).is(block);
        boolean bl2 = world.getBlockState(blockPos2).is(block) || world.getBlockState(blockPos3).is(block);
        if (bl && bl2) {
            f /= 2.0F;
        } else {
            boolean bl3 = world.getBlockState(blockPos4.north()).is(block) || world.getBlockState(blockPos5.north()).is(block) || world.getBlockState(blockPos5.south()).is(block) || world.getBlockState(blockPos4.south()).is(block);
            if (bl3) {
                f /= 2.0F;
            }
        }

        return f;
    }

    private void tryGrow(ServerLevel world, BlockState state, BlockPos pos, int amount) {
        int i = Math.min((Integer)state.getValue(AGE) + amount, MaxAge);
        if (this.canGrow(world, pos, state, i)) {
            BlockState blockState = (BlockState)state.setValue(AGE, i);
            world.setBlock(pos, blockState, 2);
            if (isDoubleTallAtAge(i)) {
                world.setBlock(pos.above(), (BlockState)blockState.setValue(HALF, DoubleBlockHalf.UPPER), 3);
            }

        }
    }

    private static boolean canGrowAt(LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isAir() || blockState.is(ModBlocks.CORN_CROP);
    }

    private static boolean canPlaceAt(LevelReader world, BlockPos pos) {
        return hasEnoughLightAt(world, pos);
    }

    protected static boolean hasEnoughLightAt(LevelReader world, BlockPos pos) {
        return world.getRawBrightness(pos, 0) >= 8;
    }

    private static boolean isLowerHalf(BlockState state) {
        return state.is(ModBlocks.CORN_CROP) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private boolean canGrow(LevelReader world, BlockPos pos, BlockState state, int age) {
        return !this.isFullyGrown(state) && canPlaceAt(world, pos) && (!isDoubleTallAtAge(age) || canGrowAt(world, pos.above()));
    }

    private CornCropBlock.@Nullable LowerHalfContext getLowerHalfContext(LevelReader world, BlockPos pos, BlockState state) {
        if (isLowerHalf(state)) {
            return new CornCropBlock.LowerHalfContext(pos, state);
        } else {
            BlockPos blockPos = pos.below();
            BlockState blockState = world.getBlockState(blockPos);
            return isLowerHalf(blockState) ? new CornCropBlock.LowerHalfContext(blockPos, blockState) : null;
        }
    }

    public boolean isValidBonemealTarget(LevelReader World, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < MatureAge;
    }

    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        CornCropBlock.LowerHalfContext lowerHalfContext = this.getLowerHalfContext(world, pos, state);
        if (lowerHalfContext != null) {
            this.tryGrow(world, lowerHalfContext.state, lowerHalfContext.pos, 1);
        }
    }

    static {
        HALF = DoublePlantBlock.HALF;
    }

    record LowerHalfContext(BlockPos pos, BlockState state) {
        }
    }
