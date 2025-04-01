package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.woodworks.common.block.ClosetBlock;
import com.teamabnormals.woodworks.common.block.TrappedClosetBlock;
import com.teamabnormals.woodworks.common.block.entity.ClosetBlockEntity;
import com.teamabnormals.woodworks.common.block.entity.TrappedClosetBlockEntity;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class WoodworksBlockEntityTypes {
	public static final BlockEntitySubRegistryHelper BLOCK_ENTITY_TYPES = Woodworks.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ClosetBlockEntity>> CLOSET = BLOCK_ENTITY_TYPES.createBlockEntity("closet", ClosetBlockEntity::new, ClosetBlock.class);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrappedClosetBlockEntity>> TRAPPED_CLOSET = BLOCK_ENTITY_TYPES.createBlockEntity("trapped_closet", TrappedClosetBlockEntity::new, TrappedClosetBlock.class);
}