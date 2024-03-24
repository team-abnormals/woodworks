package com.teamabnormals.woodworks.common.block.entity;

import com.teamabnormals.woodworks.core.registry.WoodworksBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TrappedClosetBlockEntity extends ClosetBlockEntity {

	public TrappedClosetBlockEntity(BlockPos pos, BlockState state) {
		super(WoodworksBlockEntityTypes.TRAPPED_CLOSET.get(), pos, state);
	}

	@Override
	protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int oldOpenCount, int openCount) {
		super.signalOpenCount(level, pos, state, oldOpenCount, openCount);
		if (oldOpenCount != openCount) {
			Block block = state.getBlock();
			level.updateNeighborsAt(pos, block);
			level.updateNeighborsAt(pos.below(), block);
		}
	}
}