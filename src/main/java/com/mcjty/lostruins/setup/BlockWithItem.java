package com.mcjty.lostruins.setup;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.mcjty.lostruins.setup.Registration.createStandardProperties;

public class BlockWithItem {
    private final RegistryObject<Block> block;

    private BlockWithItem(String name, Supplier<Block> supplier) {
        block = Registration.BLOCKS.register(name, supplier);
        RegistryObject<Item> item = Registration.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), createStandardProperties()));
    }

    public RegistryObject<Block> getBlock() {
        return block;
    }

    public static BlockWithItem create(String name, Supplier<Block> supplier) {
        return new BlockWithItem(name, supplier);
    }

    public static BlockWithItem create(String name) {
        return create(name, () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    }
}
