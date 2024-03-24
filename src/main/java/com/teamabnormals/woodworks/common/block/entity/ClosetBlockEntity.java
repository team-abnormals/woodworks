package com.teamabnormals.woodworks.common.block.entity;

import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class ClosetBlockEntity extends ChestBlockEntity {
	public static final String CONTAINER_CLOSET = "container." + Woodworks.MOD_ID + ".closet";
	public static final String CONTAINER_CLOSET_DOUBLE = "container." + Woodworks.MOD_ID + ".closetDouble";

	protected ClosetBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
	}

	public ClosetBlockEntity(BlockPos pos, BlockState state) {
		super(WoodworksBlockEntityTypes.CLOSET.get(), pos, state);
	}

	@Override
	public AABB getRenderBoundingBox() {
		BlockPos worldPos = this.worldPosition;
		int x = worldPos.getX();
		int y = worldPos.getY();
		int z = worldPos.getZ();
		return new AABB(x - 1, y, z - 1, x + 2, y + 2, z + 2);
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable(CONTAINER_CLOSET);
	}
}