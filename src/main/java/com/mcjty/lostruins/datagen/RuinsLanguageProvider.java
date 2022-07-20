package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.setup.BlockWithItem;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class RuinsLanguageProvider extends LanguageProvider {

    public RuinsLanguageProvider(DataGenerator gen, String locale) {
        super(gen, LostRuins.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.lostruins", "Lost Ruins");
        BlockWithItem.getSimpleBlocks().forEach(entry -> add(entry.getKey().getBlock().get(), entry.getValue().translation()));
        BlockWithItem.getGlassBlocks().forEach(entry -> add(entry.getKey().getBlock().get(), entry.getValue().translation()));
        BlockWithItem.getPaneBlocks().forEach(entry -> add(entry.getKey().getBlock().get(), entry.getValue().translation()));
        BlockWithItem.getRubbleBlocks().forEach(entry -> add(entry.getKey().getBlock().get(), entry.getValue().translation()));
    }
}