package com.teamabnormals.woodworks.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WoodworksItemTagsProvider extends ItemTagsProvider {

	public WoodworksItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper fileHelper) {
		super(generator, blockTags, Woodworks.MOD_ID, fileHelper);
	}

	@Override
	protected void addTags() {
		this.copy(BlockTags.PLANKS, ItemTags.PLANKS);

		this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
		this.copy(Tags.Blocks.CHESTS_TRAPPED, Tags.Items.CHESTS_TRAPPED);
		this.tag(Tags.Items.BOOKSHELVES).add(WoodworksBlocks.SPRUCE_BEEHIVE.get().asItem(), WoodworksBlocks.BIRCH_BEEHIVE.get().asItem(), WoodworksBlocks.JUNGLE_BEEHIVE.get().asItem(), WoodworksBlocks.ACACIA_BEEHIVE.get().asItem(), WoodworksBlocks.DARK_OAK_BEEHIVE.get().asItem(), WoodworksBlocks.CRIMSON_BEEHIVE.get().asItem(), WoodworksBlocks.WARPED_BEEHIVE.get().asItem());

		this.copy(BlueprintBlockTags.LADDERS, BlueprintItemTags.LADDERS);
	}
}