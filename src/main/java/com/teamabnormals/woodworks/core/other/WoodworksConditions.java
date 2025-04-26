package com.teamabnormals.woodworks.core.other;

import com.google.common.collect.Maps;
import com.teamabnormals.blueprint.core.api.conditions.BlueprintAndCondition;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksConditionSerializers;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.AndCondition;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

import static com.teamabnormals.woodworks.core.WoodworksConfig.COMMON;

public class WoodworksConditions {
	public static final ModLoadedCondition WOODWORKS_LOADED = new ModLoadedCondition(Woodworks.MOD_ID);

	public static final ConfigValueCondition SAWMILL_ENABLED = config(COMMON.sawmill, "sawmill");
	public static final ConfigValueCondition WOODEN_BOOKSHELVES = config(COMMON.woodenBookshelves, "wooden_bookshelves");
	public static final ConfigValueCondition WOODEN_LADDERS = config(COMMON.woodenLadders, "wooden_ladders");
	public static final ConfigValueCondition WOODEN_BEEHIVES = config(COMMON.woodenBeehives, "wooden_beehives");
	public static final ConfigValueCondition WOODEN_CHESTS = config(COMMON.woodenChests, "wooden_chests");
	public static final ConfigValueCondition WOODEN_BOARDS = config(COMMON.woodenBoards, "wooden_boards");
	public static final ConfigValueCondition LEAF_PILES = config(COMMON.leafPiles, "leaf_piles");

	public static final AndCondition WOODEN_BOOKSHELVES_IN_VILLAGES = andConfig(COMMON.woodenBookshelves, "wooden_bookshelves", COMMON.woodenBookshelvesInVillages, "wooden_bookshelves_in_villages");
	public static final AndCondition WOODEN_LADDERS_IN_VILLAGES = andConfig(COMMON.woodenLadders, "wooden_ladders", COMMON.woodenLaddersInVillages, "wooden_ladders_in_villages");
	public static final AndCondition WOODEN_CHESTS_IN_VILLAGES = andConfig(COMMON.woodenChests, "wooden_chests", COMMON.woodenChestsInVillages, "wooden_chests_in_villages");

	private static AndCondition andConfig(ModConfigSpec.ConfigValue<?> value1, String key1, ModConfigSpec.ConfigValue<?> value2, String key2) {
		return new AndCondition(List.of(config(value1, key1), config(value2, key2)));
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(WoodworksConditionSerializers.CONFIG.get(), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}

	public static BlueprintAndCondition compat(ICondition... condition) {
		List<ICondition> list = Lists.newArrayList();
		list.add(WOODWORKS_LOADED);
		list.addAll(List.of(condition));
		return new BlueprintAndCondition(list);
	}
}