package com.mcjty.lostruins.setup;


import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.blocks.RubbleBlock;
import com.mcjty.lostruins.blocks.VariantGlassBlock;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static com.mcjty.lostruins.LostRuins.MODID;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static final BlockWithItem<Block> BRICKS1A = BlockWithItem.create("bricks1a");
    public static final BlockWithItem<Block> BRICKS1B = BlockWithItem.create("bricks1b");
    public static final BlockWithItem<Block> BRICKS1C = BlockWithItem.create("bricks1c");

    public static final BlockWithItem<Block> BRICKS1_RUBBLE = BlockWithItem.create("bricks1_rubble", RubbleBlock::new);
    public static final BlockWithItem<Block> STONE_RUBBLE = BlockWithItem.create("stone_rubble", RubbleBlock::new);
    public static final BlockWithItem<Block> STONEBRICKS_RUBBLE = BlockWithItem.create("stonebricks_rubble", RubbleBlock::new);

    public static final BlockWithItem<VariantGlassBlock> GLASSOLD = BlockWithItem.create("glassold", () -> new VariantGlassBlock(createGlassProperties()));
    public static final BlockWithItem<VariantGlassBlock> GLASSBROKEN1 = BlockWithItem.create("glassbroken1", () -> new VariantGlassBlock(createGlassProperties()));
    public static final BlockWithItem<VariantGlassBlock> GLASSBROKEN2 = BlockWithItem.create("glassbroken2", () -> new VariantGlassBlock(createGlassProperties()));
    public static final BlockWithItem<VariantGlassBlock> GLASSBROKEN3 = BlockWithItem.create("glassbroken3", () -> new VariantGlassBlock(createGlassProperties()));
    public static final BlockWithItem<VariantGlassBlock> GLASSBROKEN4 = BlockWithItem.create("glassbroken4", () -> new VariantGlassBlock(createGlassProperties()));
    public static final BlockWithItem<VariantGlassBlock> GLASSBROKEN5 = BlockWithItem.create("glassbroken5", () -> new VariantGlassBlock(createGlassProperties()));
    public static final BlockWithItem<VariantGlassBlock> GLASSBROKENALL = BlockWithItem.create("glassbrokenall", () -> new VariantGlassBlock(createGlassProperties()));
    public static final BlockWithItem<VariantGlassBlock> GLASSBROKENFRAME = BlockWithItem.create("glassbrokenframe", () -> new VariantGlassBlock(createGlassProperties()));

    public static final BlockWithItem<IronBarsBlock> GLASSPANE_OLD = BlockWithItem.create("glasspane_old", () -> new IronBarsBlock(createGlassProperties()));
    public static final BlockWithItem<IronBarsBlock> GLASSPANE_BROKEN1 = BlockWithItem.create("glasspane_broken1", () -> new IronBarsBlock(createGlassProperties()));
    public static final BlockWithItem<IronBarsBlock> GLASSPANE_BROKEN2 = BlockWithItem.create("glasspane_broken2", () -> new IronBarsBlock(createGlassProperties()));
    public static final BlockWithItem<IronBarsBlock> GLASSPANE_BROKEN3 = BlockWithItem.create("glasspane_broken3", () -> new IronBarsBlock(createGlassProperties()));
    public static final BlockWithItem<IronBarsBlock> GLASSPANE_BROKEN4 = BlockWithItem.create("glasspane_broken4", () -> new IronBarsBlock(createGlassProperties()));
    public static final BlockWithItem<IronBarsBlock> GLASSPANE_BROKEN5 = BlockWithItem.create("glasspane_broken5", () -> new IronBarsBlock(createGlassProperties()));
    public static final BlockWithItem<IronBarsBlock> GLASSPANE_BROKENALL = BlockWithItem.create("glasspane_brokenall", () -> new IronBarsBlock(createGlassProperties()));
    public static final BlockWithItem<IronBarsBlock> GLASSPANE_BROKENFRAME = BlockWithItem.create("glasspane_brokenframe", () -> new IronBarsBlock(createGlassProperties()));

    public static void register() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TILES.register(bus);
        CONTAINERS.register(bus);
    }

    @NotNull
    public static Item.Properties createStandardProperties() {
        return new Item.Properties().tab(LostRuins.setup.getTab());
    }

    @NotNull
    private static BlockBehaviour.Properties createGlassProperties() {
        return BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion();
    }
}
