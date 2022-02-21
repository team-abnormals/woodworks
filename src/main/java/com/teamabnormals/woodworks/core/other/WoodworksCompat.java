package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.world.level.block.Blocks;

public class WoodworksCompat {

	public static void register() {
		registerFlammables();
		changeLocalizationKeys();
	}

	private static void changeLocalizationKeys() {
		DataUtil.changeBlockLocalization(Blocks.BOOKSHELF, Woodworks.MOD_ID, "oak_bookshelf");
		DataUtil.changeBlockLocalization(Blocks.LADDER, Woodworks.MOD_ID, "oak_ladder");
		DataUtil.changeBlockLocalization(Blocks.BEEHIVE, Woodworks.MOD_ID, "oak_beehive");
	}

	private static void registerFlammables() {
		DataUtil.registerFlammable(WoodworksBlocks.SPRUCE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.BIRCH_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.JUNGLE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.DARK_OAK_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.ACACIA_BEEHIVE.get(), 5, 20);
	}
}
