package com.teamabnormals.woodworks.core.data.server.tags;

import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags.*;
import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksBlockTagsProvider extends BlockTagsProvider {

	public WoodworksBlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, Woodworks.MOD_ID, helper);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void addTags(Provider provider) {
		this.tag(WOODEN_BOARDS).add(OAK_BOARDS.get(), SPRUCE_BOARDS.get(), BIRCH_BOARDS.get(), JUNGLE_BOARDS.get(), ACACIA_BOARDS.get(), DARK_OAK_BOARDS.get(), MANGROVE_BOARDS.get(), CHERRY_BOARDS.get(), CRIMSON_BOARDS.get(), WARPED_BOARDS.get());
		this.tag(WOODEN_CHESTS).add(OAK_CHEST.get(), SPRUCE_CHEST.get(), BIRCH_CHEST.get(), JUNGLE_CHEST.get(), ACACIA_CHEST.get(), DARK_OAK_CHEST.get(), MANGROVE_CHEST.get(), CHERRY_CHEST.get(), BAMBOO_CLOSET.get(), CRIMSON_CHEST.get(), WARPED_CHEST.get());
		this.tag(WOODEN_TRAPPED_CHESTS).add(TRAPPED_OAK_CHEST.get(), TRAPPED_SPRUCE_CHEST.get(), TRAPPED_BIRCH_CHEST.get(), TRAPPED_JUNGLE_CHEST.get(), TRAPPED_ACACIA_CHEST.get(), TRAPPED_DARK_OAK_CHEST.get(), TRAPPED_MANGROVE_CHEST.get(), TRAPPED_CHERRY_CHEST.get(), TRAPPED_BAMBOO_CLOSET.get(), TRAPPED_CRIMSON_CHEST.get(), TRAPPED_WARPED_CHEST.get());
		this.tag(WOODEN_BEEHIVES).add(SPRUCE_BEEHIVE.get(), BIRCH_BEEHIVE.get(), JUNGLE_BEEHIVE.get(), ACACIA_BEEHIVE.get(), DARK_OAK_BEEHIVE.get(), MANGROVE_BEEHIVE.get(), CHERRY_BEEHIVE.get(), BAMBOO_BEEHIVE.get(), CRIMSON_BEEHIVE.get(), WARPED_BEEHIVE.get());
		this.tag(WOODEN_LADDERS).add(SPRUCE_LADDER.get(), BIRCH_LADDER.get(), JUNGLE_LADDER.get(), ACACIA_LADDER.get(), DARK_OAK_LADDER.get(), MANGROVE_LADDER.get(), CHERRY_LADDER.get(), BAMBOO_LADDER.get(), CRIMSON_LADDER.get(), WARPED_LADDER.get());
		this.tag(WOODEN_BOOKSHELVES).add(SPRUCE_BOOKSHELF.get(), BIRCH_BOOKSHELF.get(), JUNGLE_BOOKSHELF.get(), ACACIA_BOOKSHELF.get(), DARK_OAK_BOOKSHELF.get(), MANGROVE_BOOKSHELF.get(), CHERRY_BOOKSHELF.get(), BAMBOO_BOOKSHELF.get(), CRIMSON_BOOKSHELF.get(), WARPED_BOOKSHELF.get());
		this.tag(WOODEN_CHISELED_BOOKSHELVES).add(CHISELED_SPRUCE_BOOKSHELF.get(), CHISELED_BIRCH_BOOKSHELF.get(), CHISELED_JUNGLE_BOOKSHELF.get(), CHISELED_ACACIA_BOOKSHELF.get(), CHISELED_DARK_OAK_BOOKSHELF.get(), CHISELED_MANGROVE_BOOKSHELF.get(), CHISELED_CHERRY_BOOKSHELF.get(), CHISELED_BAMBOO_BOOKSHELF.get(), CHISELED_CRIMSON_BOOKSHELF.get(), CHISELED_WARPED_BOOKSHELF.get());
		this.tag(LEAF_PILES).add(OAK_LEAF_PILE.get(), SPRUCE_LEAF_PILE.get(), BIRCH_LEAF_PILE.get(), JUNGLE_LEAF_PILE.get(), ACACIA_LEAF_PILE.get(), DARK_OAK_LEAF_PILE.get(), MANGROVE_LEAF_PILE.get(), CHERRY_LEAF_PILE.get(), AZALEA_LEAF_PILE.get(), FLOWERING_AZALEA_LEAF_PILE.get());

		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(SAWMILL.get());
		this.tag(BlockTags.FLOWERS).add(CHERRY_LEAF_PILE.get(), FLOWERING_AZALEA_LEAF_PILE.get());
	}
}