package com.mcjty.lostruins.setup;

import com.mcjty.lostruins.blocks.RubbleBlock;
import com.mcjty.lostruins.blocks.VariantGlassBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.mcjty.lostruins.setup.Registration.createStandardProperties;

public class BlockWithItem<T extends Block> {
    private final RegistryObject<T> block;

    public static record BlockInfo(String texture, String translation) {}

    private static final Map<BlockWithItem<?>, BlockInfo> GLASS_BLOCKS = new HashMap<>();
    private static final Map<BlockWithItem<IronBarsBlock>, BlockInfo> PANE_BLOCKS = new HashMap<>();
    private static final Map<BlockWithItem<?>, BlockInfo> SIMPLE_BLOCKS = new HashMap<>();
    private static final Map<BlockWithItem<RubbleBlock>, BlockInfo> RUBBLE_BLOCKS = new HashMap<>();

    private BlockWithItem(String name, Supplier<T> supplier) {
        block = Registration.BLOCKS.register(name, supplier);
        RegistryObject<Item> item = Registration.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), createStandardProperties()));
    }

    public RegistryObject<T> getBlock() {
        return block;
    }

    public static BlockWithItem<Block> simple(String name, String texture, String translation) {
        BlockWithItem<Block> bi = create(name);
        SIMPLE_BLOCKS.put(bi, new BlockInfo(texture, translation));
        return bi;
    }

    public static BlockWithItem<VariantGlassBlock> glass(String name, String texture, String translation) {
        return glass(name, texture, translation, () -> new VariantGlassBlock(Registration.createGlassProperties()));
    }

    public static <T extends Block> BlockWithItem<T> glass(String name, String texture, String translation, Supplier<T> supplier) {
        BlockWithItem<T> bi = new BlockWithItem<>(name, supplier);
        GLASS_BLOCKS.put(bi, new BlockInfo(texture, translation));
        return bi;
    }

    public static BlockWithItem<IronBarsBlock> pane(String name, String texture, String translation) {
        return pane(name, texture, translation, () -> new IronBarsBlock(Registration.createGlassProperties()));
    }

    public static BlockWithItem<IronBarsBlock> pane(String name, String texture, String translation, Supplier<IronBarsBlock> supplier) {
        BlockWithItem<IronBarsBlock> bi = new BlockWithItem<>(name, supplier);
        PANE_BLOCKS.put(bi, new BlockInfo(texture, translation));
        return bi;
    }

    public static <T extends Block> BlockWithItem<T> create(String name, Supplier<T> supplier) {
        return new BlockWithItem<T>(name, supplier);
    }

    public static BlockWithItem<RubbleBlock> rubble(String name, String texture, String translation) {
        BlockWithItem<RubbleBlock> bi = BlockWithItem.create(name, RubbleBlock::new);
        RUBBLE_BLOCKS.put(bi, new BlockInfo(texture, translation));
        return bi;
    }

    public static BlockWithItem<Block> create(String name) {
        return create(name, () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    }

    public static Stream<Map.Entry<BlockWithItem<?>, BlockInfo>> getGlassBlocks() {
        return GLASS_BLOCKS.entrySet().stream();
    }

    public static Stream<Map.Entry<BlockWithItem<IronBarsBlock>, BlockInfo>> getPaneBlocks() {
        return PANE_BLOCKS.entrySet().stream();
    }

    public static Stream<Map.Entry<BlockWithItem<?>, BlockInfo>> getSimpleBlocks() {
        return SIMPLE_BLOCKS.entrySet().stream();
    }

    public static Stream<Map.Entry<BlockWithItem<RubbleBlock>, BlockInfo>> getRubbleBlocks() {
        return RUBBLE_BLOCKS.entrySet().stream();
    }
}
