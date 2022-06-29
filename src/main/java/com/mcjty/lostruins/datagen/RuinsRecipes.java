package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RuinsRecipes extends RecipeProvider {

    public RuinsRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Registration.BRICKS1A.getBlock().get(), 4)
                .define('S', Items.BRICKS)
                .unlockedBy("stone", has(Items.BRICKS))
                .group("")
                .pattern("SS").pattern("SS").save(consumer);
        ShapedRecipeBuilder.shaped(Registration.BRICKS1B.getBlock().get(), 3)
                .define('S', Items.BRICKS)
                .define('b', Items.STRING)
                .unlockedBy("stone", has(Items.BRICKS))
                .group("")
                .pattern("SS").pattern("Sb").save(consumer);
        ShapedRecipeBuilder.shaped(Registration.BRICKS1C.getBlock().get(), 3)
                .define('S', Items.BRICKS)
                .define('b', Items.STRING)
                .unlockedBy("stone", has(Items.BRICKS))
                .group("")
                .pattern("SS").pattern("bS").save(consumer);
    }

}
