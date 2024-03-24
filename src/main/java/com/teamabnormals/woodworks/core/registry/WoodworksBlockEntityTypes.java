package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.woodworks.common.block.ClosetBlock;
import com.teamabnormals.woodworks.common.block.TrappedClosetBlock;
import com.teamabnormals.woodworks.common.block.entity.ClosetBlockEntity;
import com.teamabnormals.woodworks.common.block.entity.TrappedClosetBlockEntity;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class WoodworksBlockEntityTypes {
	public static final BlockEntitySubRegistryHelper HELPER = Woodworks.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final RegistryObject<BlockEntityType<ClosetBlockEntity>> CLOSET = HELPER.createBlockEntity("closet", ClosetBlockEntity::new, ClosetBlock.class);
	public static final RegistryObject<BlockEntityType<TrappedClosetBlockEntity>> TRAPPED_CLOSET = HELPER.createBlockEntity("trapped_closet", TrappedClosetBlockEntity::new, TrappedClosetBlock.class);
}