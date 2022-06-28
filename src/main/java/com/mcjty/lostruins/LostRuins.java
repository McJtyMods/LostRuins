package com.mcjty.lostruins;

import com.mcjty.lostruins.compat.LostCitiesCompat;
import com.mcjty.lostruins.setup.ModSetup;
import com.mcjty.lostruins.setup.Registration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(LostRuins.MODID)
public class LostRuins {

    public static final String MODID = "lostruins";
    public static Logger logger = LogManager.getLogger(LostRuins.MODID);

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();

    public LostRuins() {
        Registration.register();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        LostCitiesCompat.setupLostCitiesPre();
        bus.addListener(setup::init);
    }


}
