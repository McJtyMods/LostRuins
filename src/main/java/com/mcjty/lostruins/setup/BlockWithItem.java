package com.mcjty.lostruins.setup;

import com.mcjty.lostruins.blocks.RubbleBlock;
import com.mcjty.lostruins.blocks.VariantGlassBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.mcjty.lostruins.setup.Registration.createStandardProperties;

public class BlockWithItem<T extends Block> {
    private final RegistryObject<T> block;

    public static record BlockInfo(String translation, String texture) {}
    public static record BlockInfoMulti(String translation, List<String> textures) {}

    private static final Map<BlockWithItem<?>, BlockInfoMulti> GLASS_BLOCKS = new HashMap<>();
    private static final Map<BlockWithItem<IronBarsBlock>, BlockInfoMulti> PANE_BLOCKS = new HashMap<>();
    private static final Map<BlockWithItem<?>, BlockInfo> SIMPLE_BLOCKS = new HashMap<>();
    private static final Map<BlockWithItem<?>, BlockInfoMulti> VARIANT_BLOCKS = new HashMap<>();
    private static final Map<BlockWithItem<RubbleBlock>, BlockInfo> RUBBLE_BLOCKS = new HashMap<>();

    private BlockWithItem(String name, Supplier<T> supplier) {
        block = Registration.BLOCKS.register(name, supplier);
        RegistryObject<Item> item = Registration.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), createStandardProperties()));
    }

    public RegistryObject<T> getBlock() {
        return block;
    }

    public static BlockWithItem<Block> variant(String name, String translation, String... textures) {
        BlockWithItem<Block> bi = create(name);
        VARIANT_BLOCKS.put(bi, new BlockInfoMulti(translation, List.of(textures)));
        return bi;
    }

    public static BlockWithItem<Block> simple(String name, String translation, String texture) {
        BlockWithItem<Block> bi = create(name);
        SIMPLE_BLOCKS.put(bi, new BlockInfo(translation, texture));
        return bi;
    }

    public static BlockWithItem<VariantGlassBlock> glass(String name, String translation, List<String> textures) {
        return glass(name, translation, () -> new VariantGlassBlock(Registration.createGlassProperties()), textures);
    }

    public static <T extends Block> BlockWithItem<T> glass(String name, String translation, Supplier<T> supplier, List<String> textures) {
        BlockWithItem<T> bi = new BlockWithItem<>(name, supplier);
        GLASS_BLOCKS.put(bi, new BlockInfoMulti(translation, textures));
        return bi;
    }

    public static BlockWithItem<IronBarsBlock> pane(String name, String translation, List<String> textures) {
        return pane(name, translation, () -> new IronBarsBlock(Registration.createGlassProperties()), textures);
    }

    public static BlockWithItem<IronBarsBlock> pane(String name, String translation, Supplier<IronBarsBlock> supplier, List<String> textures) {
        BlockWithItem<IronBarsBlock> bi = new BlockWithItem<>(name, supplier);
        PANE_BLOCKS.put(bi, new BlockInfoMulti(translation, textures));
        return bi;
    }

    public static <T extends Block> BlockWithItem<T> create(String name, Supplier<T> supplier) {
        return new BlockWithItem<T>(name, supplier);
    }

    public static BlockWithItem<RubbleBlock> rubble(String name, String translation, String texture) {
        BlockWithItem<RubbleBlock> bi = BlockWithItem.create(name, RubbleBlock::new);
        RUBBLE_BLOCKS.put(bi, new BlockInfo(translation, texture));
        return bi;
    }

    public static BlockWithItem<Block> create(String name) {
        return create(name, () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    }

    public static Stream<Map.Entry<BlockWithItem<?>, BlockInfoMulti>> getGlassBlocks() {
        return GLASS_BLOCKS.entrySet().stream();
    }

    public static Stream<Map.Entry<BlockWithItem<IronBarsBlock>, BlockInfoMulti>> getPaneBlocks() {
        return PANE_BLOCKS.entrySet().stream();
    }

    public static Stream<Map.Entry<BlockWithItem<?>, BlockInfo>> getSimpleBlocks() {
        return SIMPLE_BLOCKS.entrySet().stream();
    }

    public static Stream<Map.Entry<BlockWithItem<?>, BlockInfoMulti>> getVariantBlocks() {
        return VARIANT_BLOCKS.entrySet().stream();
    }

    public static Stream<Map.Entry<BlockWithItem<RubbleBlock>, BlockInfo>> getRubbleBlocks() {
        return RUBBLE_BLOCKS.entrySet().stream();
    }
}
