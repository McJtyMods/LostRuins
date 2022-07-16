package com.mcjty.lostruins.setup;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.mcjty.lostruins.setup.Registration.createStandardProperties;

public class BlockWithItem<T extends Block> {
    private final RegistryObject<T> block;

    private BlockWithItem(String name, Supplier<T> supplier) {
        block = Registration.BLOCKS.register(name, supplier);
        RegistryObject<Item> item = Registration.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), createStandardProperties()));
    }

    public RegistryObject<T> getBlock() {
        return block;
    }

    public static <T extends Block> BlockWithItem<T> create(String name, Supplier<T> supplier) {
        return new BlockWithItem<T>(name, supplier);
    }

    public static BlockWithItem<Block> create(String name) {
        return create(name, () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    }
}
