package com.mcjty.lostruins.datagen;

import com.mcjty.lostruins.LostRuins;
import com.mcjty.lostruins.setup.BlockWithItem;
import com.mcjty.lostruins.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class RuinsBlockStates extends BlockStateProvider {

    public RuinsBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, LostRuins.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simple(Registration.BRICKS1A);
        simple(Registration.BRICKS1B);
        simple(Registration.BRICKS1C);
    }

    private void simple(BlockWithItem bwi) {
        simple(bwi.getBlock());
    }

    private void simple(RegistryObject<Block> block) {
        simpleBlock(block.get(), cubeAll(block.get()));
    }
}
