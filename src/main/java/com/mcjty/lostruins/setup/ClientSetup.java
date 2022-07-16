package com.mcjty.lostruins.setup;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void initClient(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSBROKEN1.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSBROKEN2.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSBROKEN3.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSBROKEN4.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSBROKEN5.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSBROKENALL.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSBROKENFRAME.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSOLD.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_BROKEN1.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_BROKEN2.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_BROKEN3.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_BROKEN4.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_BROKEN5.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_BROKENALL.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_BROKENFRAME.getBlock().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.GLASSPANE_OLD.getBlock().get(), RenderType.translucent());
    }
}
