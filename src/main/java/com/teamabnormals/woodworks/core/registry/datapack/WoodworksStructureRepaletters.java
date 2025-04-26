package com.teamabnormals.woodworks.core.registry.datapack;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamabnormals.woodworks.core.other.WoodworksConditions.*;
import static net.minecraft.world.level.levelgen.structure.BuiltinStructures.*;

public class WoodworksStructureRepaletters {
	public static final ResourceKey<StructureRepaletterEntry> OAK_CHESTS_IN_VILLAGES = create("oak_chests_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> SPRUCE_CHESTS_IN_VILLAGES = create("spruce_chests_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> JUNGLE_CHESTS_IN_VILLAGES = create("jungle_chests_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> ACACIA_CHESTS_IN_VILLAGES = create("acacia_chests_in_villages");

	public static final ResourceKey<StructureRepaletterEntry> SPRUCE_BOOKSHELVES_IN_VILLAGES = create("spruce_bookshelves_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> JUNGLE_BOOKSHELVES_IN_VILLAGES = create("jungle_bookshelves_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> ACACIA_BOOKSHELVES_IN_VILLAGES = create("acacia_bookshelves_in_villages");

	public static final ResourceKey<StructureRepaletterEntry> SPRUCE_LADDERS_IN_VILLAGES = create("spruce_ladders_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> JUNGLE_LADDERS_IN_VILLAGES = create("jungle_ladders_in_villages");
	public static final ResourceKey<StructureRepaletterEntry> ACACIA_LADDERS_IN_VILLAGES = create("acacia_ladders_in_villages");

	public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		register(context, structures, OAK_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.OAK_CHEST.get(), VILLAGE_PLAINS);
		register(context, structures, SPRUCE_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.SPRUCE_CHEST.get(), VILLAGE_TAIGA, VILLAGE_SNOWY);
		register(context, structures, JUNGLE_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.JUNGLE_CHEST.get(), VILLAGE_DESERT);
		register(context, structures, ACACIA_CHESTS_IN_VILLAGES, Blocks.CHEST, WoodworksBlocks.ACACIA_CHEST.get(), VILLAGE_SAVANNA);

		register(context, structures, SPRUCE_BOOKSHELVES_IN_VILLAGES, Blocks.BOOKSHELF, WoodworksBlocks.SPRUCE_BOOKSHELF.get(), VILLAGE_TAIGA, VILLAGE_SNOWY);
		register(context, structures, JUNGLE_BOOKSHELVES_IN_VILLAGES, Blocks.BOOKSHELF, WoodworksBlocks.JUNGLE_BOOKSHELF.get(), VILLAGE_DESERT);
		register(context, structures, ACACIA_BOOKSHELVES_IN_VILLAGES, Blocks.BOOKSHELF, WoodworksBlocks.ACACIA_BOOKSHELF.get(), VILLAGE_SAVANNA);

		register(context, structures, SPRUCE_LADDERS_IN_VILLAGES, Blocks.LADDER, WoodworksBlocks.SPRUCE_LADDER.get(), VILLAGE_TAIGA, VILLAGE_SNOWY);
		register(context, structures, JUNGLE_LADDERS_IN_VILLAGES, Blocks.LADDER, WoodworksBlocks.JUNGLE_LADDER.get(), VILLAGE_DESERT);
		register(context, structures, ACACIA_LADDERS_IN_VILLAGES, Blocks.LADDER, WoodworksBlocks.ACACIA_LADDER.get(), VILLAGE_SAVANNA);
	}

	public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> builder) {
		builder.accept(OAK_CHESTS_IN_VILLAGES, WOODEN_CHESTS_IN_VILLAGES);
		builder.accept(SPRUCE_CHESTS_IN_VILLAGES, WOODEN_CHESTS_IN_VILLAGES);
		builder.accept(JUNGLE_CHESTS_IN_VILLAGES, WOODEN_CHESTS_IN_VILLAGES);
		builder.accept(ACACIA_CHESTS_IN_VILLAGES, WOODEN_CHESTS_IN_VILLAGES);

		builder.accept(SPRUCE_BOOKSHELVES_IN_VILLAGES, WOODEN_BOOKSHELVES_IN_VILLAGES);
		builder.accept(JUNGLE_BOOKSHELVES_IN_VILLAGES, WOODEN_BOOKSHELVES_IN_VILLAGES);
		builder.accept(ACACIA_BOOKSHELVES_IN_VILLAGES, WOODEN_BOOKSHELVES_IN_VILLAGES);

		builder.accept(SPRUCE_LADDERS_IN_VILLAGES, WOODEN_LADDERS_IN_VILLAGES);
		builder.accept(JUNGLE_LADDERS_IN_VILLAGES, WOODEN_LADDERS_IN_VILLAGES);
		builder.accept(ACACIA_LADDERS_IN_VILLAGES, WOODEN_LADDERS_IN_VILLAGES);
	}

	@SafeVarargs
	private static void register(BootstrapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, ResourceKey<StructureRepaletterEntry> key, Block replacesBlock, Block replacesWith, ResourceKey<Structure>... keys) {
		context.register(key, StructureRepaletterEntry.repalette().repaletters(simple(replacesBlock, replacesWith)).select(holder(structures, keys)));
	}

	@SafeVarargs
	public static HolderSet<Structure> holder(HolderGetter<Structure> structures, ResourceKey<Structure>... keys) {
		return HolderSet.direct(Stream.of(keys).map(structures::getOrThrow).collect(Collectors.toList()));
	}

	public static SimpleStructureRepaletter simple(Block replaces, Block replacesWith) {
		return new SimpleStructureRepaletter(replaces, replacesWith);
	}

	private static ResourceKey<StructureRepaletterEntry> create(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Woodworks.location(name));
	}
}
