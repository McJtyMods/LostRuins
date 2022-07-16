package com.mcjty.lostruins.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class VariantGlassBlock extends AbstractGlassBlock {

    public VariantGlassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacent, Direction side) {
        return adjacent.getBlock() instanceof VariantGlassBlock;
    }
}
