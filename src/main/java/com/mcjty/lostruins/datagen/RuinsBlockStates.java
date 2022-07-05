package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.setup.BlockWithItem;
import com.mcjty.lostruins.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
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
    }

    private void simple(BlockWithItem bwi) {
        simple(bwi.getBlock());
    }

    private void simple(RegistryObject<Block> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }

    private void rubble(RegistryObject<Block> block, String texture) {
        BlockModelBuilder frame1 = models().getBuilder("block/" + block.getId().getPath());
        frame1.parent(models().getExistingFile(mcLoc("cube")));
        cube(frame1, 1f, 0f, 1f, 5f, 5f, 6f);
        cube(frame1, 6f, 0f, 2f, 11f, 3f, 4f);
        cube(frame1, 4f, 0f, 5f, 8f, 4f, 11f);
        frame1.texture("txt", modLoc("block/" + texture));
        frame1.texture("particle", modLoc("block/" + texture));

        BlockModelBuilder frame2 = models().getBuilder("block/" + block.getId().getPath());
        frame2.parent(models().getExistingFile(mcLoc("cube")));
        cube(frame2, 7f, 0f, 2f, 13f, 6f, 7f);
        cube(frame2, 1f, 0f, 11f, 3f, 3f, 13f);
        cube(frame2, 4f, 0f, 6f, 9f, 5f, 9f);
        frame2.texture("txt", modLoc("block/" + texture));
        frame2.texture("particle", modLoc("block/" + texture));

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
