package com.mcjty.lostruins;

import com.mcjty.lostruins.compat.LostCitiesCompat;
import com.mcjty.lostruins.datagen.DataGenerators;
import com.mcjty.lostruins.setup.ClientSetup;
import com.mcjty.lostruins.setup.ModSetup;
import com.mcjty.lostruins.setup.Registration;
import mcjty.lib.datagen.DataGen;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Supplier;

@Mod(LostRuins.MODID)
public class LostRuins {

    public static final String MODID = "lostruins";

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();

    public static LostRuins instance;

    public LostRuins() {
        instance = this;
        Registration.register();
        LostCitiesCompat.setupLostCitiesPre();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(setup::init);
        bus.addListener(this::onDataGen);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            bus.addListener(ClientSetup::initClient);
        });
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
