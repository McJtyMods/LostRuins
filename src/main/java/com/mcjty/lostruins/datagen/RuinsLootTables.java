package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.setup.BlockWithItem;
import mcjty.lib.datagen.DataGen;
import mcjty.lib.datagen.Dob;

public class RuinsLootTables {

    public static void generate(DataGen dataGen) {
        BlockWithItem.getSimpleBlocks().forEach(entry -> dataGen.add(Dob.blockBuilder(entry.getKey().getBlock()).simpleLoot()));
        BlockWithItem.getVariantBlocks().forEach(entry -> dataGen.add(Dob.blockBuilder(entry.getKey().getBlock()).simpleLoot()));
        BlockWithItem.getGlassBlocks().forEach(entry -> dataGen.add(Dob.blockBuilder(entry.getKey().getBlock()).simpleLoot()));
        BlockWithItem.getPaneBlocks().forEach(entry -> dataGen.add(Dob.blockBuilder(entry.getKey().getBlock()).simpleLoot()));
        BlockWithItem.getRubbleBlocks().forEach(entry -> dataGen.add(Dob.blockBuilder(entry.getKey().getBlock()).simpleLoot()));
    }
}
