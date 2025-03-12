package com.teamabnormals.woodworks.core.data.server;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.other.WoodworksStructureRepaletters;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.woodworks.core.WoodworksConfig.COMMON;
import static com.teamabnormals.woodworks.core.other.WoodworksStructureRepaletters.*;

public final class WoodworksDatapackBuiltinEntriesProvider extends net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider {

	private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, WoodworksStructureRepaletters::bootstrap);

	public WoodworksDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
		super(output, lookupProvider, BUILDER, conditionconsumer -> {
			conditionconsumer.accept(OAK_CHESTS_IN_VILLAGES, chestConfig());
			conditionconsumer.accept(SPRUCE_CHESTS_IN_VILLAGES, chestConfig());
			conditionconsumer.accept(JUNGLE_CHESTS_IN_VILLAGES, chestConfig());
			conditionconsumer.accept(ACACIA_CHESTS_IN_VILLAGES, chestConfig());
			conditionconsumer.accept(SPRUCE_BOOKSHELVES_IN_VILLAGES, bookshelfConfig());
			conditionconsumer.accept(JUNGLE_BOOKSHELVES_IN_VILLAGES, bookshelfConfig());
			conditionconsumer.accept(ACACIA_BOOKSHELVES_IN_VILLAGES, bookshelfConfig());
			conditionconsumer.accept(SPRUCE_LADDERS_IN_VILLAGES, ladderConfig());
			conditionconsumer.accept(JUNGLE_LADDERS_IN_VILLAGES, ladderConfig());
			conditionconsumer.accept(ACACIA_LADDERS_IN_VILLAGES, ladderConfig());
				}, Set.of(Woodworks.MOD_ID));
	}

	private static ICondition bookshelfConfig() {
		return configCondition(COMMON.woodenBookshelves, "wooden_bookshelves", COMMON.woodenBookshelvesInVillages, "wooden_bookshelves_in_villages");
	}

	private static ICondition ladderConfig() {
		return configCondition(COMMON.woodenLadders, "wooden_ladders", COMMON.woodenLaddersInVillages, "wooden_ladders_in_villages");
	}

	private static ICondition chestConfig() {
		return configCondition(COMMON.woodenChests, "wooden_chests", COMMON.woodenChestsInVillages, "wooden_chests_in_villages");
	}

	private static ICondition configCondition(ModConfigSpec.ConfigValue<?> value1, String key1, ModConfigSpec.ConfigValue<?> value2, String key2) {
		return new AndCondition(List.of(WoodworksRecipeProvider.config(value1, key1), WoodworksRecipeProvider.config(value2, key2)));
	}
}