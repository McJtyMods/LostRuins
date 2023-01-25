package com.mcjty.lostruins.setup;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.compat.LostCitiesCompat;
import mcjty.lib.setup.DefaultModSetup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup extends DefaultModSetup {

    public ModSetup() {
        createTab(LostRuins.MODID, "lostruins", () -> new ItemStack(Blocks.STONE_BRICKS));
    }

    @Override
    public void init(FMLCommonSetupEvent e) {
    }

    @Override
    protected void setupModCompat() {
        LostCitiesCompat.setupLostCities();
    }
}
