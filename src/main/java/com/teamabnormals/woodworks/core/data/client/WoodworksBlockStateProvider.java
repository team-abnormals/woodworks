package com.teamabnormals.woodworks.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksBlockStateProvider extends BlueprintBlockStateProvider {
	public static final String[] CHERRY_BOOKSHELF_POSITIONS = new String[]{"far_left", "mid_left", "top_mid", "bottom_mid", "mid_right", "far_right"};

	public WoodworksBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Woodworks.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.sawmillBlock(SAWMILL);

		this.boardsBlock(OAK_BOARDS);
		this.leafPileBlock(Blocks.OAK_LEAVES, OAK_LEAF_PILE);
		this.chestBlocks(Blocks.OAK_PLANKS, OAK_CHEST, TRAPPED_OAK_CHEST);

		this.leafPileBlock(Blocks.SPRUCE_LEAVES, SPRUCE_LEAF_PILE);
		this.woodworksBlocks(Blocks.SPRUCE_PLANKS, SPRUCE_BOARDS, SPRUCE_LADDER, SPRUCE_BOOKSHELF, SPRUCE_BEEHIVE, SPRUCE_CHEST, TRAPPED_SPRUCE_CHEST);
		this.chiseledBookshelfBlock(CHISELED_SPRUCE_BOOKSHELF, ALTERNATE_BOOKSHELF_POSITIONS);

		this.leafPileBlock(Blocks.BIRCH_LEAVES, BIRCH_LEAF_PILE);
		this.woodworksBlocks(Blocks.BIRCH_PLANKS, BIRCH_BOARDS, BIRCH_LADDER, BIRCH_BOOKSHELF, BIRCH_BEEHIVE, BIRCH_CHEST, TRAPPED_BIRCH_CHEST);
		this.chiseledBookshelfBlock(CHISELED_BIRCH_BOOKSHELF);

		this.leafPileBlock(Blocks.JUNGLE_LEAVES, JUNGLE_LEAF_PILE);
		this.woodworksBlocks(Blocks.JUNGLE_PLANKS, JUNGLE_BOARDS, JUNGLE_LADDER, JUNGLE_BOOKSHELF, JUNGLE_BEEHIVE, JUNGLE_CHEST, TRAPPED_JUNGLE_CHEST);
		this.chiseledBookshelfBlock(CHISELED_JUNGLE_BOOKSHELF);

		this.leafPileBlock(Blocks.ACACIA_LEAVES, ACACIA_LEAF_PILE);
		this.woodworksBlocks(Blocks.ACACIA_PLANKS, ACACIA_BOARDS, ACACIA_LADDER, ACACIA_BOOKSHELF, ACACIA_BEEHIVE, ACACIA_CHEST, TRAPPED_ACACIA_CHEST);
		this.chiseledBookshelfBlock(CHISELED_ACACIA_BOOKSHELF);

		this.leafPileBlock(Blocks.DARK_OAK_LEAVES, DARK_OAK_LEAF_PILE);
		this.woodworksBlocks(Blocks.DARK_OAK_PLANKS, DARK_OAK_BOARDS, DARK_OAK_LADDER, DARK_OAK_BOOKSHELF, DARK_OAK_BEEHIVE, DARK_OAK_CHEST, TRAPPED_DARK_OAK_CHEST);
		this.chiseledBookshelfBlock(CHISELED_DARK_OAK_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);

		this.leafPileBlock(Blocks.MANGROVE_LEAVES, MANGROVE_LEAF_PILE);
		this.woodworksBlocks(Blocks.MANGROVE_PLANKS, MANGROVE_BOARDS, MANGROVE_LADDER, MANGROVE_BOOKSHELF, MANGROVE_BEEHIVE, MANGROVE_CHEST, TRAPPED_MANGROVE_CHEST);
		this.chiseledBookshelfBlock(CHISELED_MANGROVE_BOOKSHELF, ALTERNATE_BOOKSHELF_POSITIONS);

		this.leafPileBlock(Blocks.CHERRY_LEAVES, CHERRY_LEAF_PILE);
		this.woodworksBlocks(Blocks.CHERRY_PLANKS, CHERRY_BOARDS, CHERRY_LADDER, CHERRY_BOOKSHELF, CHERRY_BEEHIVE, CHERRY_CHEST, TRAPPED_CHERRY_CHEST);
		this.chiseledBookshelfBlock(CHISELED_CHERRY_BOOKSHELF, CHERRY_BOOKSHELF_POSITIONS);

		this.ladderBlock(BAMBOO_LADDER);
		this.bookshelfBlock(Blocks.BAMBOO_PLANKS, BAMBOO_BOOKSHELF);
		this.beehiveBlock(BAMBOO_BEEHIVE);
		this.chestBlocks(Blocks.BAMBOO_PLANKS, BAMBOO_CLOSET, TRAPPED_BAMBOO_CLOSET);
		this.chiseledBookshelfBlock(CHISELED_BAMBOO_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);

		this.woodworksBlocks(Blocks.CRIMSON_PLANKS, CRIMSON_BOARDS, CRIMSON_LADDER, CRIMSON_BOOKSHELF, CRIMSON_BEEHIVE, CRIMSON_CHEST, TRAPPED_CRIMSON_CHEST);
		this.chiseledBookshelfBlock(CHISELED_CRIMSON_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);

		this.woodworksBlocks(Blocks.WARPED_PLANKS, WARPED_BOARDS, WARPED_LADDER, WARPED_BOOKSHELF, WARPED_BEEHIVE, WARPED_CHEST, TRAPPED_WARPED_CHEST);
		this.chiseledBookshelfBlock(CHISELED_WARPED_BOOKSHELF, ALTERNATE_BOOKSHELF_POSITIONS, blockTexture(CHISELED_SPRUCE_BOOKSHELF.get()));

		this.leafPileBlock(Blocks.AZALEA_LEAVES, AZALEA_LEAF_PILE, false);
		this.leafPileBlock(Blocks.FLOWERING_AZALEA_LEAVES, FLOWERING_AZALEA_LEAF_PILE, false);
	}

	public void sawmillBlock(DeferredBlock<Block> sawmill) {
		this.horizontalBlock(sawmill.get(), new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Woodworks.MOD_ID, "block/sawmill")));
		this.blockItem(sawmill);
	}
}