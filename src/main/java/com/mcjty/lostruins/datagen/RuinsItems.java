package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.setup.BlockWithItem;
import com.mcjty.lostruins.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class RuinsItems extends ItemModelProvider {

    public RuinsItems(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, LostRuins.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        parented(Registration.BRICKS1A);
        parented(Registration.BRICKS1B);
        parented(Registration.BRICKS1C);
        parented(Registration.BRICKS1_RUBBLE);
        parented(Registration.STONE_RUBBLE);
        parented(Registration.STONEBRICKS_RUBBLE);
        parented(Registration.GLASSOLD);
        parented(Registration.GLASSBROKEN1);
        parented(Registration.GLASSBROKEN2);
        parented(Registration.GLASSBROKEN3);
        parented(Registration.GLASSBROKEN4);
        parented(Registration.GLASSBROKEN5);
        parented(Registration.GLASSBROKENALL);
        parented(Registration.GLASSBROKENFRAME);
        parented(Registration.GLASSPANE_BROKEN1);
        parented(Registration.GLASSPANE_BROKEN2);
        parented(Registration.GLASSPANE_BROKEN3);
        parented(Registration.GLASSPANE_BROKEN4);
        parented(Registration.GLASSPANE_BROKEN5);
        parented(Registration.GLASSPANE_BROKENALL);
        parented(Registration.GLASSPANE_BROKENFRAME);
    }

    private void parented(BlockWithItem bwi) {
        parented(bwi.getBlock());
    }

    private void parented(RegistryObject<Block> block) {
        getBuilder(block.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + block.getId().getPath())));
    }

    @Override
    public String getName() {
        return "LostRuins Item Models";
    }
}
