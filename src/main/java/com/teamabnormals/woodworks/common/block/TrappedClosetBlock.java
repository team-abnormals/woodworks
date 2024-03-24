package com.teamabnormals.woodworks.common.block;

import com.teamabnormals.blueprint.core.api.IChestBlock;
import com.teamabnormals.woodworks.common.block.entity.TrappedClosetBlockEntity;
import com.teamabnormals.woodworks.core.registry.WoodworksBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TrappedClosetBlock extends ClosetBlock implements IChestBlock {

	public TrappedClosetBlock(String type, Properties props) {
		super(type, props, WoodworksBlockEntityTypes.TRAPPED_CLOSET::get);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TrappedClosetBlockEntity(pos, state);
	}

	@Override
	protected Stat<ResourceLocation> getOpenChestStat() {
		return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction face) {
		return Mth.clamp(ChestBlockEntity.getOpenCount(getter, pos), 0, 15);
	}

	@Override
	public int getDirectSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction face) {
		return face == Direction.UP ? state.getSignal(getter, pos, face) : 0;
	}
}
