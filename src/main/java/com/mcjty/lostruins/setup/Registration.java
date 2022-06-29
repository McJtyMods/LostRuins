package com.mcjty.lostruins.setup;


import com.mcjty.lostruins.LostRuins;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.mcjty.lostruins.LostRuins.MODID;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static final BlockWithItem BRICKS1A = BlockWithItem.create("bricks1a");
    public static final BlockWithItem BRICKS1B = BlockWithItem.create("bricks1b");
    public static final BlockWithItem BRICKS1C = BlockWithItem.create("bricks1c");

    public static void register() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TILES.register(bus);
        CONTAINERS.register(bus);
    }

    public static Item.Properties createStandardProperties() {
        return new Item.Properties().tab(LostRuins.setup.getTab());
    }
}
