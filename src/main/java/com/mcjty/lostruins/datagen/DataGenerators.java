package com.mcjty.lostruins.datagen;

import mcjty.lib.datagen.DataGen;

public class DataGenerators {

    public static void gatherData(DataGen datagen) {
        RuinsBlockStates.generate(datagen);
        RuinsItems.generate(datagen);
        RuinsLanguageProvider.generate(datagen);
        RuinsLootTables.generate(datagen);
        RuinsRecipes.generate(datagen);
    }
}
