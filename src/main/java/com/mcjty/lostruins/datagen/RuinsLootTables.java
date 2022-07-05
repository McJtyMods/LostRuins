package com.mcjty.lostruins.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.setup.BlockWithItem;
import com.mcjty.lostruins.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class RuinsLootTables extends LootTableProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final DataGenerator generator;

    public RuinsLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    @Override
    public void run(HashCache cache) {
        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        addSimpleTable(tables, Registration.BRICKS1A);
        addSimpleTable(tables, Registration.BRICKS1B);
        addSimpleTable(tables, Registration.BRICKS1C);
        addSimpleTable(tables, Registration.BRICKS1_RUBBLE);
        writeTables(cache, tables);
    }

    private void addSimpleTable(Map<ResourceLocation, LootTable> tables, BlockWithItem bwi) {
        addSimpleTable(tables, bwi.getBlock().get());
    }

    private void addSimpleTable(Map<ResourceLocation, LootTable> tables, Block block) {
        tables.put(block.getRegistryName(), createSimpleTable(block.getRegistryName().getPath(), block).build());
    }

    private LootTable.Builder createSimpleTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block));
        return LootTable.lootTable().withPool(builder);
    }


    private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path);
            } catch (IOException e) {
                LostRuins.logger.error("Couldn't write loot table {}", path, (Object) e);
            }
        });
    }

    @Override
    public String getName() {
        return "LostRuins LootTables";
    }
}
