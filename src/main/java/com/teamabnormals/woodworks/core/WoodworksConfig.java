package com.teamabnormals.woodworks.core;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
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
		@ConfigKey("leaf_piles")
		public final BooleanValue leafPiles;

		@ConfigKey("wooden_bookshelves_in_structures")
		public final BooleanValue woodenBookshelvesInStructures;
		@ConfigKey("wooden_ladders_in_structures")
		public final BooleanValue woodenLaddersInStructures;
		@ConfigKey("wooden_chests_in_structures")
		public final BooleanValue woodenChestsInStructures;

		public Common(ForgeConfigSpec.Builder builder) {
			builder.push("blocks");
			this.sawmill = builder.define("Sawmill", true);
			this.woodenBoards = builder.define("Wooden boards", true);
			this.woodenBookshelves = builder.define("Wooden bookshelves", true);
			this.woodenLadders = builder.define("Wooden ladders", true);
			this.woodenBeehives = builder.define("Wooden beehives", true);
			this.woodenChests = builder.define("Wooden chests", true);
			this.leafPiles = builder.define("Leaf piles", true);
			builder.pop();
			builder.push("generation");
			this.woodenBookshelvesInStructures = builder.comment("If variant bookshelves replace bookshelves in certain structures").define("Wooden bookshelves in structures", true);
			this.woodenLaddersInStructures = builder.comment("If variant ladders replace ladders in certain structures").define("Wooden ladders in structures", true);
			this.woodenChestsInStructures = builder.comment("If variant chests replace chests in certain structures").define("Wooden chests in structres", true);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = commonSpecPair.getRight();
		COMMON = commonSpecPair.getLeft();
	}
}