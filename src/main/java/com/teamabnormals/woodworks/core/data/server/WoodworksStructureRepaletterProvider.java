package com.teamabnormals.woodworks.core.data.server;

import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.woodworks.core.WoodworksConfig.COMMON;
import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.*;

public final class WoodworksStructureRepaletterProvider extends DatapackBuiltinEntriesProvider {
	private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, WoodworksStructureRepaletterProvider::bootstrapStructureRepaletters);

	public WoodworksStructureRepaletterProvider(PackOutput output, CompletableFuture<Provider> registries) {
		super(output, registries, BUILDER, Set.of(Woodworks.MOD_ID));
	}

	private static void bootstrapStructureRepaletters(BootstapContext<StructureRepaletterEntry> context) {
		var structures = context.lookup(Registries.STRUCTURE);
		var pieces = context.lookup(Registries.STRUCTURE_PIECE);

		basicRepaletter("oak_chests_in_structures", chestSelector(VILLAGE_PLAINS.location()), Blocks.CHEST, WoodworksBlocks.OAK_CHEST.get());
		basicRepaletter("spruce_chests_in_structures", chestSelector(VILLAGE_TAIGA.location(), VILLAGE_SNOWY.location(), IGLOO.location()), Blocks.CHEST, WoodworksBlocks.SPRUCE_CHEST.get());
		basicRepaletter("jungle_chests_in_structures", chestSelector(VILLAGE_DESERT.location(), JUNGLE_TEMPLE.location(), DESERT_PYRAMID.location()), Blocks.CHEST, WoodworksBlocks.JUNGLE_CHEST.get());
		basicRepaletter("acacia_chests_in_structures", chestSelector(VILLAGE_SAVANNA.location()), Blocks.CHEST, WoodworksBlocks.ACACIA_CHEST.get());
		basicRepaletter("dark_oak_chests_in_structures", chestSelector(PILLAGER_OUTPOST.location(), WOODLAND_MANSION.location()), Blocks.CHEST, WoodworksBlocks.DARK_OAK_CHEST.get());

		basicRepaletter("spruce_bookshelves_in_structures", bookshelfSelector(VILLAGE_TAIGA.location(), VILLAGE_SNOWY.location()), Blocks.BOOKSHELF, WoodworksBlocks.SPRUCE_BOOKSHELF.get());
		basicRepaletter("jungle_bookshelves_in_structures", bookshelfSelector(VILLAGE_DESERT.location()), Blocks.BOOKSHELF, WoodworksBlocks.JUNGLE_BOOKSHELF.get());
		basicRepaletter("acacia_bookshelves_in_structures", bookshelfSelector(VILLAGE_SAVANNA.location()), Blocks.BOOKSHELF, WoodworksBlocks.ACACIA_BOOKSHELF.get());

		basicRepaletter("spruce_ladders_in_structures", ladderSelector(VILLAGE_TAIGA.location(), VILLAGE_SNOWY.location(), IGLOO.location()), Blocks.LADDER, WoodworksBlocks.SPRUCE_LADDER.get());
		basicRepaletter("jungle_ladders_in_structures", ladderSelector(VILLAGE_DESERT.location()), Blocks.LADDER, WoodworksBlocks.JUNGLE_LADDER.get());
		basicRepaletter("acacia_ladders_in_structures", ladderSelector(VILLAGE_SAVANNA.location()), Blocks.LADDER, WoodworksBlocks.ACACIA_LADDER.get());
		basicRepaletter("dark_oak_ladders_in_structures", ladderSelector(ANCIENT_CITY.location()), Blocks.LADDER, WoodworksBlocks.DARK_OAK_LADDER.get());
	}


	private static void basicRepaletter(String name, ConditionedResourceSelector selector, Block replacesBlock, Block replacesWith) {
		//registerRepaletter(name, selector, EventPriority.NORMAL, new SimpleStructureRepaletter(replacesBlock, replacesWith));
	}

	private static ConditionedResourceSelector bookshelfSelector(ResourceLocation... structures) {
		return selector(COMMON.woodenBookshelves, "wooden_bookshelves", COMMON.woodenBookshelvesInStructures, "wooden_bookshelves_in_structures", structures);
	}

	private static ConditionedResourceSelector ladderSelector(ResourceLocation... structures) {
		return selector(COMMON.woodenLadders, "wooden_ladders", COMMON.woodenLaddersInStructures, "wooden_ladders_in_structures", structures);
	}

	private static ConditionedResourceSelector chestSelector(ResourceLocation... structures) {
		return selector(COMMON.woodenChests, "wooden_chests", COMMON.woodenChestsInStructures, "wooden_chests_in_structures", structures);
	}

	private static ConditionedResourceSelector selector(ForgeConfigSpec.ConfigValue<?> value1, String key1, ForgeConfigSpec.ConfigValue<?> value2, String key2, ResourceLocation... structures) {
		return new ConditionedResourceSelector(new NamesResourceSelector(structures), new AndCondition(WoodworksRecipeProvider.config(value1, key1), WoodworksRecipeProvider.config(value2, key2)));
	}

	private static ResourceKey<StructureRepaletterEntry> repaletterKey(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, new ResourceLocation(Woodworks.MOD_ID, name));
	}
}