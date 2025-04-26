package com.teamabnormals.woodworks.core.data.server;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.datapack.WoodworksStructureRepaletters;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public final class WoodworksDatapackProvider extends DatapackBuiltinEntriesProvider {

	private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, WoodworksStructureRepaletters::bootstrap);

	public WoodworksDatapackProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, WoodworksStructureRepaletters::applyConditions, Set.of(Woodworks.MOD_ID));
	}
}