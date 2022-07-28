package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.blocks.RubbleBlock;
import com.mcjty.lostruins.setup.BlockWithItem;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuinsBlockStates extends BlockStateProvider {

    public RuinsBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, LostRuins.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BlockWithItem.getSimpleBlocks().forEach(entry -> simple(entry.getKey(), entry.getValue().texture()));
        BlockWithItem.getVariantBlocks().forEach(entry -> variant(entry.getKey(), entry.getValue().textures()));
        BlockWithItem.getGlassBlocks().forEach(entry -> variant(entry.getKey(), entry.getValue().textures()));
        BlockWithItem.getPaneBlocks().forEach(entry -> pane(entry.getKey().getBlock().get(), entry.getValue().textures()));
        BlockWithItem.getRubbleBlocks().forEach(entry -> rubble(entry.getKey().getBlock(), entry.getValue().texture()));
    }

    public void pane(IronBarsBlock block, List<String> textures) {
        String baseName = block.getRegistryName().toString();
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        List<ModelFile> post = new ArrayList<>();
        List<ModelFile> side = new ArrayList<>();
        List<ModelFile> sideAlt = new ArrayList<>();
        List<ModelFile> noSide = new ArrayList<>();
        List<ModelFile> noSideAlt = new ArrayList<>();
        for (String texture : textures) {
            String suffix = texture.substring(texture.lastIndexOf('/') + 1);
            ResourceLocation txt = new ResourceLocation(texture);
            post.add(models().panePost(baseName + "_post_" + suffix, txt, txt));
            side.add(models().paneSide(baseName + "_side_" + suffix, txt, txt));
            sideAlt.add(models().paneSideAlt(baseName + "_side_alt_" + suffix, txt, txt));
            noSide.add(models().paneNoSide(baseName + "_noside_" + suffix, txt));
            noSideAlt.add(models().paneNoSideAlt(baseName + "_noside_alt_" + suffix, txt));
        }

        ConfiguredModel.Builder<MultiPartBlockStateBuilder.PartBuilder> part = builder.part();
        {
            Iterator<ModelFile> it = post.iterator();
            part.modelFile(it.next());
            while (it.hasNext()) {
                part = part.nextModel();
                part.modelFile(it.next());
            }
            part.addModel().end();
        }

        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            if (dir.getAxis().isHorizontal()) {
                boolean alt = dir == Direction.SOUTH;
                ConfiguredModel.Builder<MultiPartBlockStateBuilder.PartBuilder> part1;
                {
                    int rot = dir.getAxis() == Direction.Axis.X ? 90 : 0;
                    Iterator<ModelFile> it = (alt || dir == Direction.WEST ? sideAlt : side).iterator();
                    part1 = builder.part();
                    part1.modelFile(it.next()).rotationY(rot);
                    while (it.hasNext()) {
                        part1 = part1.nextModel();
                        part1.modelFile(it.next()).rotationY(rot);
                    }
                    part1 = part1.addModel().condition(e.getValue(), true).end().part();
                }

                {
                    int rot = dir == Direction.WEST ? 270 : dir == Direction.SOUTH ? 90 : 0;
                    Iterator<ModelFile> it = (alt || dir == Direction.EAST ? noSideAlt : noSide).iterator();
                    part1.modelFile(it.next()).rotationY(rot);
                    while (it.hasNext()) {
                        part1 = part1.nextModel();
                        part1.modelFile(it.next()).rotationY(rot);
                    }
                    part1.addModel().condition(e.getValue(), false);
                }
            }
        });
    }

    private void simple(BlockWithItem<?> bwi) {
        simple(bwi.getBlock());
    }

    private <T extends Block> void simple(BlockWithItem<T> bwi, String texture) {
        T block = bwi.getBlock().get();
        ResourceLocation txt = new ResourceLocation(texture);
        simpleBlock(block, models().cubeAll(block.getRegistryName().getPath(), txt));
    }

    private <T extends Block> void variant(BlockWithItem<T> bwi, List<String> textures) {
        T block = bwi.getBlock().get();
        VariantBlockStateBuilder bld = getVariantBuilder(block);

        int idx = 0;
        for (String texture : textures) {
            ResourceLocation txt = new ResourceLocation(texture);
            BlockModelBuilder model = models().cubeAll(block.getRegistryName().getPath() + (idx == 0 ? "" : idx), txt);
            idx++;

            bld.partialState().addModels(
                    new ConfiguredModel(model),
                    new ConfiguredModel(model, 0, 180, false));
        }
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

        BlockModelBuilder frame2 = models().getBuilder("block/" + block.getId().getPath()+"1");
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
