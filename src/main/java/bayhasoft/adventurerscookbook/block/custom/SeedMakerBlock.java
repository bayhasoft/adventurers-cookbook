package bayhasoft.adventurerscookbook.block.custom;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import bayhasoft.adventurerscookbook.block.entity.ModBlockEntities;
import bayhasoft.adventurerscookbook.block.entity.SeedMakerBlockEntity;

public class SeedMakerBlock extends BaseEntityBlock {
    public static final MapCodec<SeedMakerBlock> CODEC = SeedMakerBlock.simpleCodec(SeedMakerBlock::new);
    	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

    public SeedMakerBlock(Properties settings) {
        super(settings);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)));
    }

    // TODO: Tooltip
    // @Override
    // public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        
    //     if(Screen.hasShiftDown()){
    //     tooltip.add(Text.translatable("tooltip.adventurerscookbook.line1"));
    //     tooltip.add(Text.translatable("tooltip.adventurerscookbook.line2"));
    //     }else{
    //     tooltip.add(Text.translatable("tooltip.adventurerscookbook.press.shift").formatted(Formatting.BOLD, Formatting.AQUA));
    //     }
    //     super.appendTooltip(stack, context, tooltip, options);
        
    // }

    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite()));
    }
    
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.SEED_MAKER_BLOCK_ENTITY.create(pos, state);
    }
    
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

	@Override
	protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel world, BlockPos pos, boolean moved) {
		Containers.updateNeighboursAfterDestroy(state, world, pos);
	}

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide()) {
             if(world.getBlockEntity(pos) instanceof SeedMakerBlockEntity seedmaker) {
                player.openMenu(seedmaker);
            }
        }
        
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        if(world.isClientSide()) {
            return null;
        }

        return createTickerHelper(type, ModBlockEntities.SEED_MAKER_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        return (BlockState)state.setValue(FACING, rotation.rotate((Direction)state.getValue(FACING)));
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

//     static {
//     FACING = HorizontalFacingBlock.FACING;
//    }
}
