package com.teamabnormals.woodworks.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WoodworksItemTagsProvider extends ItemTagsProvider {

	public WoodworksItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(generator, blockTags, Woodworks.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(ItemTags.NON_FLAMMABLE_WOOD).add(WoodworksBlocks.CRIMSON_BOARDS.get().asItem(), WoodworksBlocks.WARPED_BOARDS.get().asItem(), WoodworksBlocks.CRIMSON_BOOKSHELF.get().asItem(), WoodworksBlocks.WARPED_BOOKSHELF.get().asItem(), WoodworksBlocks.CRIMSON_LADDER.get().asItem(), WoodworksBlocks.WARPED_LADDER.get().asItem(), WoodworksBlocks.CRIMSON_BEEHIVE.get().asItem(), WoodworksBlocks.WARPED_BEEHIVE.get().asItem(), WoodworksBlocks.CRIMSON_CHEST.get().asItem(), WoodworksBlocks.WARPED_CHEST.get().asItem(), WoodworksBlocks.CRIMSON_TRAPPED_CHEST.get().asItem(), WoodworksBlocks.WARPED_TRAPPED_CHEST.get().asItem());

		this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
		this.copy(Tags.Blocks.CHESTS_TRAPPED, Tags.Items.CHESTS_TRAPPED);
		this.tag(Tags.Items.BOOKSHELVES).add(WoodworksBlocks.SPRUCE_BOOKSHELF.get().asItem(), WoodworksBlocks.BIRCH_BOOKSHELF.get().asItem(), WoodworksBlocks.JUNGLE_BOOKSHELF.get().asItem(), WoodworksBlocks.ACACIA_BOOKSHELF.get().asItem(), WoodworksBlocks.DARK_OAK_BOOKSHELF.get().asItem(), WoodworksBlocks.CRIMSON_BOOKSHELF.get().asItem(), WoodworksBlocks.WARPED_BOOKSHELF.get().asItem());

		this.tag(BlueprintItemTags.BOATABLE_CHESTS).add(WoodworksBlocks.OAK_CHEST.get().asItem(), WoodworksBlocks.SPRUCE_CHEST.get().asItem(), WoodworksBlocks.BIRCH_CHEST.get().asItem(), WoodworksBlocks.JUNGLE_CHEST.get().asItem(), WoodworksBlocks.ACACIA_CHEST.get().asItem(), WoodworksBlocks.DARK_OAK_CHEST.get().asItem(), WoodworksBlocks.CRIMSON_CHEST.get().asItem(), WoodworksBlocks.WARPED_CHEST.get().asItem());
		this.tag(BlueprintItemTags.REVERTABLE_CHESTS).add(WoodworksBlocks.OAK_CHEST.get().asItem(), WoodworksBlocks.SPRUCE_CHEST.get().asItem(), WoodworksBlocks.BIRCH_CHEST.get().asItem(), WoodworksBlocks.JUNGLE_CHEST.get().asItem(), WoodworksBlocks.ACACIA_CHEST.get().asItem(), WoodworksBlocks.DARK_OAK_CHEST.get().asItem(), WoodworksBlocks.CRIMSON_CHEST.get().asItem(), WoodworksBlocks.WARPED_CHEST.get().asItem());
		this.copy(BlueprintBlockTags.LADDERS, BlueprintItemTags.LADDERS);
	}
}