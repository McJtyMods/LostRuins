package com.mcjty.lostruins;

import com.mcjty.lostruins.compat.LostCitiesCompat;
import com.mcjty.lostruins.datagen.DataGenerators;
import com.mcjty.lostruins.setup.ClientSetup;
import com.mcjty.lostruins.setup.ModSetup;
import com.mcjty.lostruins.setup.Registration;
import mcjty.lib.datagen.DataGen;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.api.distmarker.Dist;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fml.common.Mod;
import net.neoforged.neoforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.fml.loading.FMLEnvironment;

import java.util.function.Supplier;

@Mod(LostRuins.MODID)
public class LostRuins {

    public static final String MODID = "lostruins";

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();

    public static LostRuins instance;

    public LostRuins() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Dist dist = FMLEnvironment.dist;

        instance = this;
        Registration.register(bus);
        LostCitiesCompat.setupLostCitiesPre();
        bus.addListener(setup::init);
        bus.addListener(this::onDataGen);

        if (dist.isClient()) {
            bus.addListener(ClientSetup::initClient);
        }
    }

    public static <T extends Item> Supplier<T> tab(Supplier<T> supplier) {
        return instance.setup.tab(supplier);
    }

    private void onDataGen(GatherDataEvent event) {
        DataGen datagen = new DataGen(MODID, event);
        DataGenerators.gatherData(datagen);
//        modules.datagen(datagen);
        datagen.generate();
    }
}
