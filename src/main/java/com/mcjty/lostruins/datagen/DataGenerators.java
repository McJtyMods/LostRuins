package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = LostRuins.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new RuinsRecipes(generator));
            generator.addProvider(new RuinsLootTables(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new RuinsBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new RuinsItems(generator, event.getExistingFileHelper()));
        }
    }
}
