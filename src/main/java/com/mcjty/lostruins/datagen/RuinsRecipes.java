package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RuinsRecipes extends RecipeProvider {

    public RuinsRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Registration.WALLS1.get(), 3)
                .define('S', ItemTags.STONE_BRICKS)
                .define('b', Tags.Items.BONES)
                .unlockedBy("stone", has(ItemTags.STONE_BRICKS))
                .group("")
                .pattern("SbbS").save(consumer);
    }

}
