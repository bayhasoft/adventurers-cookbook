package bayhasoft.adventurerscookbook.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record SeedMakerRecipe(Ingredient inputItem, ItemStack output) implements Recipe<SingleStackRecipeInput>{
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    
    // read Recipe JSON files --> new Recipe

    @Override
    public ItemStack craft(SingleStackRecipeInput input, WrapperLookup lookup) {
        return output.copy();
    }

    // @Override
    // public boolean fits(int width, int height) {
    //     return true;
    // }

    // @Override
    // public ItemStack getResult(WrapperLookup registriesLookup) {
    //     return output;
    // }

    @Override
    public RecipeSerializer<? extends Recipe<SingleStackRecipeInput>> getSerializer() {
        return ModRecipes.SEED_MAKER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SingleStackRecipeInput>> getType() {
        return ModRecipes.SEED_MAKER_TYPE;
    }

    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        if (world.isClient()) {
         return false;   
        }
        return inputItem.test(input.getStackInSlot(0));
    }
    
    public static class Serializer implements RecipeSerializer<SeedMakerRecipe> {
        public static final MapCodec<SeedMakerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(SeedMakerRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(SeedMakerRecipe::output)
        ).apply(inst, SeedMakerRecipe::new));

        public static final PacketCodec<RegistryByteBuf, SeedMakerRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, SeedMakerRecipe::inputItem,
                        ItemStack.PACKET_CODEC, SeedMakerRecipe::output,
                        SeedMakerRecipe::new);

        @Override
        public MapCodec<SeedMakerRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, SeedMakerRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
         return IngredientPlacement.forSingleSlot(inputItem);
    }


    @Override
    public RecipeBookCategory getRecipeBookCategory() {
         return RecipeBookCategories.CRAFTING_MISC;
    }
}
