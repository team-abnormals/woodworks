package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.*;

public class WoodworksStructureRepaletters {

	public static final ResourceKey<StructureRepaletterEntry> OAK_CHESTS_IN_VILLAGES = repaletterKey("oak_chests_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> SPRUCE_CHESTS_IN_VILLAGES = repaletterKey("spruce_chests_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> JUNGLE_CHESTS_IN_VILLAGES = repaletterKey("jungle_chests_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> ACACIA_CHESTS_IN_VILLAGES = repaletterKey("acacia_chests_in_villages");

	public static final ResourceKey<StructureRepaletterEntry> SPRUCE_BOOKSHELVES_IN_VILLAGES = repaletterKey("spruce_bookshelves_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> JUNGLE_BOOKSHELVES_IN_VILLAGES = repaletterKey("jungle_bookshelves_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> ACACIA_BOOKSHELVES_IN_VILLAGES = repaletterKey("acacia_bookshelves_in_villages");

	public static final ResourceKey<StructureRepaletterEntry> SPRUCE_LADDERS_IN_VILLAGES = repaletterKey("spruce_ladders_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> JUNGLE_LADDERS_IN_VILLAGES = repaletterKey("jungle_ladders_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> ACACIA_LADDERS_IN_VILLAGES = repaletterKey("acacia_ladders_in_villages");

	public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		basicRepaletter(context, structures, OAK_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.OAK_CHEST.get(), VILLAGE_PLAINS);
		basicRepaletter(context, structures, SPRUCE_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.SPRUCE_CHEST.get(), VILLAGE_TAIGA, VILLAGE_SNOWY);
		basicRepaletter(context, structures, JUNGLE_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.JUNGLE_CHEST.get(), VILLAGE_DESERT);
		basicRepaletter(context, structures, ACACIA_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.ACACIA_CHEST.get(), VILLAGE_SAVANNA);

		basicRepaletter(context, structures, SPRUCE_BOOKSHELVES_IN_VILLAGES, Blocks.BOOKSHELF, WoodworksBlocks.SPRUCE_BOOKSHELF.get(), VILLAGE_TAIGA, VILLAGE_SNOWY);
		basicRepaletter(context, structures, JUNGLE_BOOKSHELVES_IN_VILLAGES, Blocks.BOOKSHELF, WoodworksBlocks.JUNGLE_BOOKSHELF.get(), VILLAGE_DESERT);
		basicRepaletter(context, structures, ACACIA_BOOKSHELVES_IN_VILLAGES, Blocks.BOOKSHELF, WoodworksBlocks.ACACIA_BOOKSHELF.get(), VILLAGE_SAVANNA);

		basicRepaletter(context, structures, SPRUCE_LADDERS_IN_VILLAGES, Blocks.LADDER, WoodworksBlocks.SPRUCE_LADDER.get(), VILLAGE_TAIGA, VILLAGE_SNOWY);
		basicRepaletter(context, structures, JUNGLE_LADDERS_IN_VILLAGES, Blocks.LADDER, WoodworksBlocks.JUNGLE_LADDER.get(), VILLAGE_DESERT);
		basicRepaletter(context, structures, ACACIA_LADDERS_IN_VILLAGES, Blocks.LADDER, WoodworksBlocks.ACACIA_LADDER.get(), VILLAGE_SAVANNA);
	}

	@SafeVarargs
	private static void basicRepaletter(BootstrapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, ResourceKey<StructureRepaletterEntry> key, Block replacesBlock, Block replacesWith, ResourceKey<Structure>... selector) {
		context.register(key,
				new StructureRepaletterEntry(
						HolderSet.direct(Stream.of(selector).map(structures::getOrThrow).collect(Collectors.toList())),
						Optional.empty(),
						false,
						new SimpleStructureRepaletter(replacesBlock, replacesWith)
				)
		);
	}

	private static ResourceKey<StructureRepaletterEntry> repaletterKey(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, ResourceLocation.fromNamespaceAndPath(Woodworks.MOD_ID, name));
	}
}
