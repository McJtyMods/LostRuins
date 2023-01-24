package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostRuins.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new RuinsRecipes(generator));
        generator.addProvider(event.includeServer(), new RuinsLootTables(generator));
        generator.addProvider(event.includeClient(), new RuinsBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new RuinsItems(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new RuinsLanguageProvider(generator, "en_us"));
    }
}
