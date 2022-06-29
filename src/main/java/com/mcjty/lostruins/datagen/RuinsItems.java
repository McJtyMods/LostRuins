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
        simple(Registration.BRICKS1A);
        simple(Registration.BRICKS1B);
        simple(Registration.BRICKS1C);
    }

    private void simple(BlockWithItem bwi) {
        simple(bwi.getBlock());
    }

    private void simple(RegistryObject<Block> block) {
        getBuilder(block.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + block.getId().getPath())));
    }

    @Override
    public String getName() {
        return "LostRuins Item Models";
    }
}
