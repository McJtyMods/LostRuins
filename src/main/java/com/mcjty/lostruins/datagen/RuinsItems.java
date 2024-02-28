package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.setup.BlockWithItem;
import mcjty.lib.datagen.BaseItemModelProvider;
import mcjty.lib.datagen.DataGen;
import mcjty.lib.datagen.Dob;
import mcjty.lib.setup.DeferredBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.RegistryObject;

public class RuinsItems {

    public static void generate(DataGen dataGen) {
        BlockWithItem.getSimpleBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                            .itemModel(p -> parented(p, entry.getKey().getBlock())));
        });
        BlockWithItem.getVariantBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .itemModel(p -> parented(p, entry.getKey().getBlock())));
        });
        BlockWithItem.getGlassBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .itemModel(p -> parented(p, entry.getKey().getBlock())));
        });
        BlockWithItem.getPaneBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                            .itemModel(p -> generated(p, entry.getKey(), entry.getValue().textures().get(0))));
        });
        BlockWithItem.getRubbleBlocks().forEach(entry -> {
            dataGen.add(Dob.blockBuilder(entry.getKey().getBlock())
                    .itemModel(p -> parented(p, entry.getKey().getBlock())));
        });
    }

    private static void parented(BaseItemModelProvider p, BlockWithItem<? extends Block> bwi) {
        parented(p, bwi.getBlock());
    }

    private static void parented(BaseItemModelProvider p, DeferredBlock<? extends Block> block) {
        p.getBuilder(block.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile(p.modLoc("block/" + block.getId().getPath())));
    }

    private static void generated(BaseItemModelProvider p, BlockWithItem<? extends Block> bwi, String texture) {
        p.getBuilder(bwi.getBlock().getId().getPath()).parent(p.getExistingFile(p.mcLoc("item/generated")))
                .texture("layer0", new ResourceLocation(texture));
    }
}
