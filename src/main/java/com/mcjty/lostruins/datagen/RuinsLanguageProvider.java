package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.setup.BlockWithItem;
import mcjty.lib.datagen.DataGen;
import mcjty.lib.datagen.Dob;

public class RuinsLanguageProvider {

    public static void generate(DataGen dataGen) {
        dataGen.add(Dob.builder().message("itemGroup.lostruins", "Lost Ruins"));
        BlockWithItem.getSimpleBlocks().forEach(entry -> Dob.blockBuilder(entry.getKey().getBlock()).name(entry.getValue().translation()));
        BlockWithItem.getVariantBlocks().forEach(entry -> Dob.blockBuilder(entry.getKey().getBlock()).name(entry.getValue().translation()));
        BlockWithItem.getGlassBlocks().forEach(entry -> Dob.blockBuilder(entry.getKey().getBlock()).name(entry.getValue().translation()));
        BlockWithItem.getPaneBlocks().forEach(entry -> Dob.blockBuilder(entry.getKey().getBlock()).name(entry.getValue().translation()));
        BlockWithItem.getRubbleBlocks().forEach(entry -> Dob.blockBuilder(entry.getKey().getBlock()).name(entry.getValue().translation()));
    }
}