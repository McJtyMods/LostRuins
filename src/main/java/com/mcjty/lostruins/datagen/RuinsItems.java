package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.setup.BlockWithItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
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
        BlockWithItem.getSimpleBlocks().forEach(entry -> parented(entry.getKey()));
        BlockWithItem.getVariantBlocks().forEach(entry -> parented(entry.getKey()));
        BlockWithItem.getGlassBlocks().forEach(entry -> parented(entry.getKey()));
        BlockWithItem.getPaneBlocks().forEach(entry -> generated(entry.getKey(), entry.getValue().textures().get(0)));
        BlockWithItem.getRubbleBlocks().forEach(entry -> parented(entry.getKey()));
    }

    private void parented(BlockWithItem<? extends Block> bwi) {
        parented(bwi.getBlock());
    }

    private void parented(RegistryObject<? extends Block> block) {
        getBuilder(block.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + block.getId().getPath())));
    }

    private void generated(BlockWithItem<? extends Block> bwi, String texture) {
        getBuilder(bwi.getBlock().getId().getPath()).parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", new ResourceLocation(texture));
    }


    @Override
    public String getName() {
        return "LostRuins Item Models";
    }
}
