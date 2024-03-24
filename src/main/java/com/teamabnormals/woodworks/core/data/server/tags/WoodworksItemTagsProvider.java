package com.teamabnormals.woodworks.core.data.server.tags;

import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksItemTagsProvider extends ItemTagsProvider {

	public WoodworksItemTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup, ExistingFileHelper fileHelper) {
		super(output, lookupProvider, tagLookup, Woodworks.MOD_ID, fileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
		this.tag(ItemTags.NON_FLAMMABLE_WOOD).add(CRIMSON_BOARDS.get().asItem(), WARPED_BOARDS.get().asItem(), CRIMSON_BOOKSHELF.get().asItem(), WARPED_BOOKSHELF.get().asItem(), CHISELED_CRIMSON_BOOKSHELF.get().asItem(), CHISELED_WARPED_BOOKSHELF.get().asItem(), CRIMSON_LADDER.get().asItem(), WARPED_LADDER.get().asItem(), CRIMSON_BEEHIVE.get().asItem(), WARPED_BEEHIVE.get().asItem(), CRIMSON_CHEST.get().asItem(), WARPED_CHEST.get().asItem(), TRAPPED_CRIMSON_CHEST.get().asItem(), TRAPPED_WARPED_CHEST.get().asItem());

		this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
		this.copy(Tags.Blocks.CHESTS_TRAPPED, Tags.Items.CHESTS_TRAPPED);
		this.copy(Tags.Blocks.BOOKSHELVES, Tags.Items.BOOKSHELVES);
	}
}