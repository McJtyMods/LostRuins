package com.mcjty.lostruins.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RubbleBlock extends Block {

    private static final VoxelShape RUBBLE_SHAPE = Shapes.box(.1, 0, .1, .9, .35, .9);

    public RubbleBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.STONE).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return RUBBLE_SHAPE;
    }
}
