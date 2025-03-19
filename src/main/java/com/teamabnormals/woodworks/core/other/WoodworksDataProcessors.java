package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;


public class WoodworksDataProcessors {
	public static final TrackedData<ResourceLocation> CHEST_VARIANT = TrackedData.Builder.create(ResourceLocation.STREAM_CODEC, () -> BuiltInRegistries.BLOCK.getKey(Blocks.CHEST)).enableSaving(ResourceLocation.CODEC.fieldOf("value")).build();

	public static void registerTrackedData() {
		TrackedDataManager.INSTANCE.registerData(Woodworks.location("chest_variant"), CHEST_VARIANT);
	}
}