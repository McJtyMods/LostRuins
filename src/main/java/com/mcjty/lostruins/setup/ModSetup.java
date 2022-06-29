package com.mcjty.lostruins.setup;

import com.mcjty.lostruins.compat.LostCitiesCompat;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

    private CreativeModeTab creativeTab;

    public ModSetup() {
        creativeTab = new CreativeModeTab("lostruins") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(Registration.BRICKS1A.getBlock().get());
            }
        };
    }

    public void init(FMLCommonSetupEvent e) {
        LostCitiesCompat.setupLostCities();
    }

    public CreativeModeTab getTab() {
        return creativeTab;
    }

}
