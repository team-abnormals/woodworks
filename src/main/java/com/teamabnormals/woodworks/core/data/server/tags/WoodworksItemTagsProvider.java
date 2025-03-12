package com.teamabnormals.woodworks.core.data.server.tags;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags.LEAF_PILES;
import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksItemTagsProvider extends BlueprintItemTagsProvider {

	public WoodworksItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup, ExistingFileHelper helper) {
		super(Woodworks.MOD_ID, output, provider, tagLookup, helper);
	}

	@Override
	protected void addTags(Provider provider) {
		this.copyWoodworksTags();
		this.copy(BlueprintBlockTags.LEAF_PILES, LEAF_PILES);
		this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
		this.tag(ItemTags.NON_FLAMMABLE_WOOD).add(CRIMSON_BOARDS.get().asItem(), WARPED_BOARDS.get().asItem(), CRIMSON_BOOKSHELF.get().asItem(), WARPED_BOOKSHELF.get().asItem(), CHISELED_CRIMSON_BOOKSHELF.get().asItem(), CHISELED_WARPED_BOOKSHELF.get().asItem(), CRIMSON_LADDER.get().asItem(), WARPED_LADDER.get().asItem(), CRIMSON_BEEHIVE.get().asItem(), WARPED_BEEHIVE.get().asItem(), CRIMSON_CHEST.get().asItem(), WARPED_CHEST.get().asItem(), TRAPPED_CRIMSON_CHEST.get().asItem(), TRAPPED_WARPED_CHEST.get().asItem());
	}
}