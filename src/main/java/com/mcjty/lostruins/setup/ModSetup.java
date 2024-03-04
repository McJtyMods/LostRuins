package com.mcjty.lostruins.setup;

import com.mcjty.lostruins.compat.LostCitiesCompat;
import mcjty.lib.setup.DefaultModSetup;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup extends DefaultModSetup {

    @Override
    public void init(FMLCommonSetupEvent e) {
    }

    @Override
    protected void setupModCompat() {
        LostCitiesCompat.setupLostCities();
    }
}
