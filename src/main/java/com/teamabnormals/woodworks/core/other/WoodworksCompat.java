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
		DataUtil.registerFlammable(WoodworksBlocks.OAK_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.SPRUCE_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.BIRCH_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.JUNGLE_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.ACACIA_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.DARK_OAK_BOARDS.get(), 5, 20);

		DataUtil.registerFlammable(WoodworksBlocks.OAK_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(WoodworksBlocks.SPRUCE_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(WoodworksBlocks.BIRCH_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(WoodworksBlocks.JUNGLE_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(WoodworksBlocks.ACACIA_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(WoodworksBlocks.DARK_OAK_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(WoodworksBlocks.AZALEA_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(WoodworksBlocks.FLOWERING_AZALEA_LEAF_PILE.get(), 30, 60);

		DataUtil.registerFlammable(WoodworksBlocks.SPRUCE_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(WoodworksBlocks.BIRCH_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(WoodworksBlocks.JUNGLE_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(WoodworksBlocks.ACACIA_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(WoodworksBlocks.DARK_OAK_BOOKSHELF.get(), 30, 20);
		
		DataUtil.registerFlammable(WoodworksBlocks.SPRUCE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.BIRCH_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.JUNGLE_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.DARK_OAK_BEEHIVE.get(), 5, 20);
		DataUtil.registerFlammable(WoodworksBlocks.ACACIA_BEEHIVE.get(), 5, 20);
	}
}
