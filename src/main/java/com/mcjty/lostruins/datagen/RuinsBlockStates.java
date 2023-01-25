package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.blocks.RubbleBlock;
import com.mcjty.lostruins.setup.BlockWithItem;
import mcjty.lib.datagen.BaseBlockStateProvider;
import mcjty.lib.datagen.DataGen;
import mcjty.lib.datagen.Dob;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuinsBlockStates {

    public static void generate(DataGen dataGen) {
        BlockWithItem.getSimpleBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .blockState(p -> simple(p, entry.getKey(), entry.getValue().texture())));
        });
        BlockWithItem.getVariantBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .blockState(p -> variant(p, entry.getKey(), entry.getValue().textures())));
        });
        BlockWithItem.getGlassBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .blockState(p -> variant(p, entry.getKey(), entry.getValue().textures())));
        });
        BlockWithItem.getPaneBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .blockState(p -> pane(p, entry.getKey().getBlock().get(), entry.getValue().textures())));
        });
        BlockWithItem.getRubbleBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .blockState(p -> rubble(p, entry.getKey().getBlock(), entry.getValue().texture())));
        });
    }

    private static void pane(BaseBlockStateProvider p, IronBarsBlock block, List<String> textures) {
        String baseName = ForgeRegistries.BLOCKS.getKey(block).toString();
        MultiPartBlockStateBuilder builder = p.getMultipartBuilder(block);
        List<ModelFile> post = new ArrayList<>();
        List<ModelFile> side = new ArrayList<>();
        List<ModelFile> sideAlt = new ArrayList<>();
        List<ModelFile> noSide = new ArrayList<>();
        List<ModelFile> noSideAlt = new ArrayList<>();
        for (String texture : textures) {
            String suffix = texture.substring(texture.lastIndexOf('/') + 1);
            ResourceLocation txt = new ResourceLocation(texture);
            post.add(p.models().panePost(baseName + "_post_" + suffix, txt, txt));
            side.add(p.models().paneSide(baseName + "_side_" + suffix, txt, txt));
            sideAlt.add(p.models().paneSideAlt(baseName + "_side_alt_" + suffix, txt, txt));
            noSide.add(p.models().paneNoSide(baseName + "_noside_" + suffix, txt));
            noSideAlt.add(p.models().paneNoSideAlt(baseName + "_noside_alt_" + suffix, txt));
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

    private static void simple(BaseBlockStateProvider p, BlockWithItem<?> bwi) {
        simple(p, bwi.getBlock());
    }

    private static <T extends Block> void simple(BaseBlockStateProvider p, BlockWithItem<T> bwi, String texture) {
        T block = bwi.getBlock().get();
        ResourceLocation txt = new ResourceLocation(texture);
        p.simpleBlock(block, p.models().cubeAll(ForgeRegistries.BLOCKS.getKey(block).getPath(), txt));
    }

    private static <T extends Block> void variant(BaseBlockStateProvider p, BlockWithItem<T> bwi, List<String> textures) {
        T block = bwi.getBlock().get();
        VariantBlockStateBuilder bld = p.getVariantBuilder(block);

        int idx = 0;
        for (String texture : textures) {
            ResourceLocation txt = new ResourceLocation(texture);
            BlockModelBuilder model = p.models().cubeAll(ForgeRegistries.BLOCKS.getKey(block).getPath() + (idx == 0 ? "" : idx), txt);
            idx++;

            bld.partialState().addModels(
                    new ConfiguredModel(model),
                    new ConfiguredModel(model, 0, 180, false));
        }
    }

    private static <T extends Block> void simple(BaseBlockStateProvider p, RegistryObject<T> block) {
        p.simpleBlock(block.get(), p.cubeAll(block.get()));
    }

    private static void rubble(BaseBlockStateProvider p, RegistryObject<RubbleBlock> block, String texture) {
        ResourceLocation txt = new ResourceLocation(texture);
        rubble(p, block, txt);
    }

    private static void rubble(BaseBlockStateProvider p, RegistryObject<RubbleBlock> block, ResourceLocation txt) {
        BlockModelBuilder frame1 = p.models().getBuilder("block/" + block.getId().getPath());
        frame1.parent(p.models().getExistingFile(p.mcLoc("cube")));
        cube(frame1, 1f, 0f, 1f, 5f, 5f, 6f);
        cube(frame1, 6f, 0f, 2f, 11f, 3f, 4f);
        cube(frame1, 4f, 0f, 5f, 8f, 4f, 11f);
        frame1.texture("txt", txt);
        frame1.texture("particle", txt);

        BlockModelBuilder frame2 = p.models().getBuilder("block/" + block.getId().getPath()+"1");
        frame2.parent(p.models().getExistingFile(p.mcLoc("cube")));
        cube(frame2, 7f, 0f, 2f, 13f, 6f, 7f);
        cube(frame2, 1f, 0f, 11f, 3f, 3f, 13f);
        cube(frame2, 4f, 0f, 6f, 9f, 5f, 9f);
        frame2.texture("txt", txt);
        frame2.texture("particle", txt);

        VariantBlockStateBuilder bld = p.getVariantBuilder(block.get());
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

    private static void cube(BlockModelBuilder builder, float fx, float fy, float fz, float tx, float ty, float tz) {
        builder.element()
                .from(fx, fy, fz)
                .to(tx, ty, tz)
                .allFaces((direction, faceBuilder) -> faceBuilder.texture("#txt"))
                .end();
    }



}
