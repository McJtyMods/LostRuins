package com.mcjty.lostruins.setup;


import com.google.common.collect.Lists;
import com.mcjty.lostruins.LostRuins;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mcjty.lostruins.LostRuins.MODID;
import static com.mcjty.lostruins.setup.BlockWithItem.*;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    private static String lrTxt(String txt) {
        return "lostruins:block/" + txt;
    }

    private static String mcTxt(String txt) {
        return "minecraft:block/" + txt;
    }

    static {
        simple("bricks1a", "Bricks 1", lrTxt("bricks1/bricks1a"));
        simple("bricks1b", "Bricks 1", lrTxt("bricks1/bricks1b"));
        simple("bricks1c", "Bricks 1", lrTxt("bricks1/bricks1c"));

        variant("concrete1", "Concrete 1", lrTxt("concrete1/concrete1a"), lrTxt("concrete1/concrete1b"), lrTxt("concrete1/concrete1c"));
        variant("concrete1_bricks", "Concrete 1 Bricks", lrTxt("concrete1/concrete1a_bricks"), lrTxt("concrete1/concrete1a_bricks"), lrTxt("concrete1/concrete1a_bricks"));
        variant("concrete1_mossy", "Concrete 1 Mossy", lrTxt("concrete1/concrete1a_mossy"), lrTxt("concrete1/concrete1b_mossy"), lrTxt("concrete1/concrete1c_mossy"));
        variant("stone1", "Stone 1", lrTxt("stone1/stone1"), lrTxt("stone1/stone1_mirrored"), lrTxt("stone1/stone1_rotated"));
        variant("stone1_cracked", "Stone 1 (Cracked)", lrTxt("stone1/stone1_cracked"), lrTxt("stone1/stone1_cracked2"));
        variant("stone1_mossy", "Stone 1 (Mossy)", lrTxt("stone1/stone1_mossy"));

        rubble("bricks1_rubble", "Brick rubble", lrTxt("bricks1/bricks1a"));
        rubble("stone_rubble", "Stone rubble", mcTxt("stone"));
        rubble("stone1_rubble", "Stone 1 rubble", lrTxt("stone1/stone1"));
        rubble("concrete1_rubble", "Concrete 1 rubble", lrTxt("concrete1/concrete1a_bricks"));
        rubble("stonebricks_rubble", "Stone brick rubble", mcTxt("stone_bricks"));
        rubble("blackstone_rubble", "Blackstone rubble", mcTxt("blackstone"));
        rubble("blackstonebricks_rubble", "Blackstone brick rubble", mcTxt("polished_blackstone_bricks"));

        for (SuffixWithTextures s : Lists.newArrayList(
                SuffixWithTextures.create("complete", "", "complete"),
                SuffixWithTextures.create("mossy", " (mossy)", "complete_mossy", "complete_mossy_vines", "broken_all_mossy"),
                SuffixWithTextures.create("broken", " (broken)", "broken1", "broken2", "broken3", "broken4", "broken5", "broken_all", "broken_frame"),
                SuffixWithTextures.create("broken_mossy", " (broken, mossy)", "broken_frame_mossy", "broken_mossy"),
                SuffixWithTextures.create("vines", " (vines)", "broken_frame_vines", "broken_frame_mossy_vines","broken_mossy_vines"))) {
            List<String> txt = Arrays.stream(s.textures()).map(t -> lrTxt("glassgray3x2/" + t)).collect(Collectors.toList());
            glass("glassgray3x2_" + s.suffix(), "Old glass" + s.languageSuffix(), txt);
            pane("glassgray3x2_pane_" + s.suffix(), "Old glass pane" + s.languageSuffix(), txt);
        }
    }

    private static record SuffixWithTextures(String suffix, String languageSuffix, String... textures) {
        public static SuffixWithTextures create(String suffix, String languageSuffix, String... textures) {
            return new SuffixWithTextures(suffix, languageSuffix, textures);
        }
    }

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
    public static BlockBehaviour.Properties createGlassProperties() {
        return BlockBehaviour.Properties.of(Material.GLASS).strength(0.3F).sound(SoundType.GLASS).noOcclusion();
    }
}
