package com.teamabnormals.woodworks.core;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class WoodworksConfig {

	public static class Common {
		@ConfigKey("sawmill")
		public final BooleanValue sawmill;
		@ConfigKey("wooden_boards")
		public final BooleanValue woodenBoards;
		@ConfigKey("wooden_bookshelves")
		public final BooleanValue woodenBookshelves;
		@ConfigKey("wooden_ladders")
		public final BooleanValue woodenLadders;
		@ConfigKey("wooden_beehives")
		public final BooleanValue woodenBeehives;
		@ConfigKey("wooden_chests")
		public final BooleanValue woodenChests;
		@ConfigKey("mixed_chest_crafting")
		public final BooleanValue mixedChestCrafting;
		@ConfigKey("leaf_piles")
		public final BooleanValue leafPiles;

		@ConfigKey("wooden_bookshelves_in_villages")
		public final BooleanValue woodenBookshelvesInVillages;
		@ConfigKey("wooden_ladders_in_villages")
		public final BooleanValue woodenLaddersInVillages;
		@ConfigKey("wooden_chests_in_villages")
		public final BooleanValue woodenChestsInVillages;

		public Common(ModConfigSpec.Builder builder) {
			builder.push("blocks");
			this.sawmill = builder.define("Sawmill", true);
			this.woodenBoards = builder.define("Wooden boards", true);
			this.woodenBookshelves = builder.define("Wooden bookshelves", true);
			this.woodenLadders = builder.define("Wooden ladders", true);
			this.woodenBeehives = builder.define("Wooden beehives", true);
			this.woodenChests = builder.define("Wooden chests", true);
			this.mixedChestCrafting = builder.comment("If the vanilla Chest can be crafted from mixing woods or woods without a chest variant").define("Mixed chest crafting", true);
			this.leafPiles = builder.define("Leaf piles", true);
			builder.pop();
			builder.push("generation");
			this.woodenBookshelvesInVillages = builder.comment("If variant bookshelves replace bookshelves in Villages").define("Wooden bookshelves in Villages", true);
			this.woodenLaddersInVillages = builder.comment("If variant ladders replace ladders in Villages").define("Wooden ladders in Villages", true);
			this.woodenChestsInVillages = builder.comment("If variant chests replace chests in Villages").define("Wooden chests in Villages", true);
			builder.pop();
		}
	}

	public static final ModConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = commonSpecPair.getRight();
		COMMON = commonSpecPair.getLeft();
	}
}