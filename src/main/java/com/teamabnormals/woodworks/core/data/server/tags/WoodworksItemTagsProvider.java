package com.teamabnormals.woodworks.core.data.server.tags;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags.LEAF_PILES;
import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksItemTagsProvider extends BlueprintItemTagsProvider {

	public WoodworksItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup, ExistingFileHelper helper) {
		super(Woodworks.MOD_ID, output, provider, tagLookup, helper);
	}

	public static final TagKey<Item> WOODWORKS_CHESTS = TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath(Woodworks.MOD_ID, "woodworks_chests"));

	@Override
	protected void addTags(Provider provider) {
		this.copyWoodworksTags();
		this.copy(BlueprintBlockTags.LEAF_PILES, LEAF_PILES);
		this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
		this.tag(ItemTags.NON_FLAMMABLE_WOOD).add(CRIMSON_BOARDS.get().asItem(), WARPED_BOARDS.get().asItem(), CRIMSON_BOOKSHELF.get().asItem(), WARPED_BOOKSHELF.get().asItem(), CHISELED_CRIMSON_BOOKSHELF.get().asItem(), CHISELED_WARPED_BOOKSHELF.get().asItem(), CRIMSON_LADDER.get().asItem(), WARPED_LADDER.get().asItem(), CRIMSON_BEEHIVE.get().asItem(), WARPED_BEEHIVE.get().asItem(), CRIMSON_CHEST.get().asItem(), WARPED_CHEST.get().asItem(), TRAPPED_CRIMSON_CHEST.get().asItem(), TRAPPED_WARPED_CHEST.get().asItem());
	
		/* All chests specifically added by Woodworks */
		this.tag(WOODWORKS_CHESTS)
			.add(
				OAK_CHEST.asItem(),
				SPRUCE_CHEST.asItem(),
				BIRCH_CHEST.asItem(),
				JUNGLE_CHEST.asItem(),
				ACACIA_CHEST.asItem(),
				DARK_OAK_CHEST.asItem(),
				MANGROVE_CHEST.asItem(),
				CHERRY_CHEST.asItem(),
				BAMBOO_CLOSET.asItem(),
				CRIMSON_CHEST.asItem(),
				WARPED_CHEST.asItem()
			);
	}
}
