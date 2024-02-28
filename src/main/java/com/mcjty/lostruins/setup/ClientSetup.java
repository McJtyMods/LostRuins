package com.mcjty.lostruins.setup;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.neoforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void initClient(FMLClientSetupEvent event) {
        BlockWithItem.getGlassBlocks().forEach(entry -> {
            ItemBlockRenderTypes.setRenderLayer(entry.getKey().getBlock().get(), RenderType.translucent());
        });
        BlockWithItem.getPaneBlocks().forEach(entry -> {
            ItemBlockRenderTypes.setRenderLayer(entry.getKey().getBlock().get(), RenderType.translucent());
        });
    }
}
