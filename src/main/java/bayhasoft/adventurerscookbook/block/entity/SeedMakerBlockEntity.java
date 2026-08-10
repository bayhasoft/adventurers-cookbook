package bayhasoft.adventurerscookbook.block.entity;

import java.util.Optional;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import org.jetbrains.annotations.Nullable;

import bayhasoft.adventurerscookbook.recipe.ModRecipes;
import bayhasoft.adventurerscookbook.recipe.SeedMakerRecipe;
import bayhasoft.adventurerscookbook.screen.SeedMakerScreemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class SeedMakerBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedMenuProvider<BlockPos> {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);    
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    
    protected final ContainerData propertyDelegate;
    private int progress = 0;
    private int maxProgress = 3000;

    public SeedMakerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SEED_MAKER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new ContainerData() {
            @Override
            public int get(int index){
                return switch (index){
                    case 0 -> SeedMakerBlockEntity.this.progress;
                    case 1 -> SeedMakerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                    switch (index) {
                        case 0 -> SeedMakerBlockEntity.this.progress = value;
                        case 1 -> SeedMakerBlockEntity.this.maxProgress = value;
                    }
                }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        ContainerHelper.saveAllItems(view, inventory);
        view.putInt("seed_maker.progress", progress);
        view.putInt("seed_maker.maxprogress", maxProgress);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        ContainerHelper.loadAllItems(view, inventory);
        progress = view.getIntOr("seed_maker.progress", 0);
        maxProgress = view.getIntOr("seed_maker.maxprogress", 0);
        super.loadAdditional(view);
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        Optional<RecipeHolder<SeedMakerRecipe>> recipe = getCurrentRecipe();

        ItemStack output = recipe.get().value().output().create();
        this.removeItem(INPUT_SLOT, 1);
        this.setItem(OUTPUT_SLOT, new ItemStack(output.getItem(),
                this.getItem(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 3000;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<SeedMakerRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
           return false;
        }
        ItemStack output = recipe.get().value().output().create();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<SeedMakerRecipe>> getCurrentRecipe() {
        return ((ServerLevel) this.getLevel()).recipeAccess()
        .getRecipeFor(ModRecipes.SEED_MAKER_TYPE, new SingleRecipeInput(inventory.get(INPUT_SLOT)), this.level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getItem(OUTPUT_SLOT).isEmpty() || this.getItem(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getItem(OUTPUT_SLOT).isEmpty() ? 64 : this.getItem(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = this.getItem(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("seed_maker_display_name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncid, Inventory playerInventory, Player player) {
        return new SeedMakerScreemHandler(syncid, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }
}
