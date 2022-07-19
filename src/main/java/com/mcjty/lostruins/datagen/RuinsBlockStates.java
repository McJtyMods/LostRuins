package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.setup.BlockWithItem;
import com.mcjty.lostruins.setup.Registration;
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
        simple(Registration.BRICKS1A);
        simple(Registration.BRICKS1B);
        simple(Registration.BRICKS1C);
        rubble(Registration.BRICKS1_RUBBLE.getBlock(), "bricks1a");
        rubble(Registration.STONE_RUBBLE.getBlock(), mcLoc("block/stone"));
        rubble(Registration.STONEBRICKS_RUBBLE.getBlock(), mcLoc("block/stone_bricks"));
        simple(Registration.GLASSOLD);
        simple(Registration.GLASSBROKEN1);
        simple(Registration.GLASSBROKEN2);
        simple(Registration.GLASSBROKEN3);
        simple(Registration.GLASSBROKEN4);
        simple(Registration.GLASSBROKEN5);
        simple(Registration.GLASSBROKENALL);
        simple(Registration.GLASSBROKENFRAME);
        paneBlock(Registration.GLASSPANE_OLD.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassold"), new ResourceLocation(LostRuins.MODID, "block/glassold"));
        paneBlock(Registration.GLASSPANE_BROKEN1.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassbroken1"), new ResourceLocation(LostRuins.MODID, "block/glassbroken1"));
        paneBlock(Registration.GLASSPANE_BROKEN2.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassbroken2"), new ResourceLocation(LostRuins.MODID, "block/glassbroken2"));
        paneBlock(Registration.GLASSPANE_BROKEN3.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassbroken3"), new ResourceLocation(LostRuins.MODID, "block/glassbroken3"));
        paneBlock(Registration.GLASSPANE_BROKEN4.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassbroken4"), new ResourceLocation(LostRuins.MODID, "block/glassbroken4"));
        paneBlock(Registration.GLASSPANE_BROKEN5.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassbroken5"), new ResourceLocation(LostRuins.MODID, "block/glassbroken5"));
        paneBlock(Registration.GLASSPANE_BROKENALL.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassbrokenall"), new ResourceLocation(LostRuins.MODID, "block/glassbrokenall"));
        paneBlock(Registration.GLASSPANE_BROKENFRAME.getBlock().get(), new ResourceLocation(LostRuins.MODID, "block/glassbrokenframe"), new ResourceLocation(LostRuins.MODID, "block/glassbrokenframe"));

    }

    private void simple(BlockWithItem bwi) {
        simple(bwi.getBlock());
    }

    private void simple(RegistryObject<Block> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }

    private void rubble(RegistryObject<Block> block, String texture) {
        ResourceLocation txt = modLoc("block/" + texture);
        rubble(block, txt);
    }

    private void rubble(RegistryObject<Block> block, ResourceLocation txt) {
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
