package com.teamabnormals.woodworks.core.data.server.tags;

import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WoodworksBlockTagsProvider extends BlockTagsProvider {

	public WoodworksBlockTagsProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, Woodworks.MOD_ID, fileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(BlockTags.BEEHIVES).add(WoodworksBlocks.BIRCH_BEEHIVE.get(), WoodworksBlocks.SPRUCE_BEEHIVE.get(), WoodworksBlocks.JUNGLE_BEEHIVE.get(), WoodworksBlocks.ACACIA_BEEHIVE.get(), WoodworksBlocks.DARK_OAK_BEEHIVE.get(), WoodworksBlocks.CRIMSON_BEEHIVE.get(), WoodworksBlocks.WARPED_BEEHIVE.get());
	}
}