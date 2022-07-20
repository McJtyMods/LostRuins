package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.blocks.RubbleBlock;
import com.mcjty.lostruins.setup.BlockWithItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class RuinsBlockStates extends BlockStateProvider {

    public RuinsBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, LostRuins.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BlockWithItem.getSimpleBlocks().forEach(entry -> simple(entry.getKey(), entry.getValue().texture()));
        BlockWithItem.getGlassBlocks().forEach(entry -> simple(entry.getKey(), entry.getValue().texture()));
        BlockWithItem.getPaneBlocks().forEach(entry -> {
            ResourceLocation txt = new ResourceLocation(entry.getValue().texture());
            paneBlock(entry.getKey().getBlock().get(), txt, txt);
        });
        BlockWithItem.getRubbleBlocks().forEach(entry -> rubble(entry.getKey().getBlock(), entry.getValue().texture()));
    }

    private void simple(BlockWithItem<?> bwi) {
        simple(bwi.getBlock());
    }

    private <T extends Block> void simple(BlockWithItem<T> bwi, String texture) {
        T block = bwi.getBlock().get();
        ResourceLocation txt = new ResourceLocation(texture);
        simpleBlock(block, models().cubeAll(block.getRegistryName().getPath(), txt));
    }

    private <T extends Block> void simple(RegistryObject<T> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }

    private void rubble(RegistryObject<RubbleBlock> block, String texture) {
        ResourceLocation txt = new ResourceLocation(texture);
        rubble(block, txt);
    }

    private void rubble(RegistryObject<RubbleBlock> block, ResourceLocation txt) {
        BlockModelBuilder frame1 = models().getBuilder("block/" + block.getId().getPath());
        frame1.parent(models().getExistingFile(mcLoc("cube")));
        cube(frame1, 1f, 0f, 1f, 5f, 5f, 6f);
        cube(frame1, 6f, 0f, 2f, 11f, 3f, 4f);
        cube(frame1, 4f, 0f, 5f, 8f, 4f, 11f);
        frame1.texture("txt", txt);
        frame1.texture("particle", txt);

        BlockModelBuilder frame2 = models().getBuilder("block/" + block.getId().getPath());
        frame2.parent(models().getExistingFile(mcLoc("cube")));
        cube(frame2, 7f, 0f, 2f, 13f, 6f, 7f);
        cube(frame2, 1f, 0f, 11f, 3f, 3f, 13f);
        cube(frame2, 4f, 0f, 6f, 9f, 5f, 9f);
        frame2.texture("txt", txt);
        frame2.texture("particle", txt);

        VariantBlockStateBuilder bld = getVariantBuilder(block.get());
        bld.partialState().addModels(
                new ConfiguredModel(frame1),
                new ConfiguredModel(frame1, 0, 90, false),
                new ConfiguredModel(frame1, 0, 180, false),
                new ConfiguredModel(frame1, 0, 270, false),
                new ConfiguredModel(frame2),
                new ConfiguredModel(frame2, 0, 90, false),
                new ConfiguredModel(frame2, 0, 180, false),
                new ConfiguredModel(frame2, 0, 270, false)
        );
    }

    private void cube(BlockModelBuilder builder, float fx, float fy, float fz, float tx, float ty, float tz) {
        builder.element()
                .from(fx, fy, fz)
                .to(tx, ty, tz)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#txt"))
                .end();
    }



}
