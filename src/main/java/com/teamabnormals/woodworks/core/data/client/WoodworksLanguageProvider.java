package com.teamabnormals.woodworks.core.data.client;

import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.core.data.client.BlueprintLanguageProvider;
import com.teamabnormals.woodworks.common.block.SawmillBlock;
import com.teamabnormals.woodworks.common.block.entity.ClosetBlockEntity;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.integration.jei.SawingRecipeCategory;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

public class WoodworksLanguageProvider extends BlueprintLanguageProvider {

	public WoodworksLanguageProvider(PackOutput output) {
		super(output, Woodworks.MOD_ID);
	}

	@Override
	protected void addTranslations() {
		this.add("oak_bookshelf");
		this.add("chiseled_oak_bookshelf");
		this.add("oak_ladder");
		this.add("oak_beehive");

		WoodworksBlocks.HELPER.getDeferredRegister().getEntries().forEach(block -> {
			add(block.get(), block.get() instanceof LeafPileBlock ? "Pile of " + format(block.getId()).replace("Leaf Pile", "Leaves") : format(block.getId()));
		});

		this.add(SawmillBlock.CONTAINER_TITLE.getString(), "Sawmill");
		this.add(SawingRecipeCategory.TRANSLATION, "Sawing");

		this.add(ClosetBlockEntity.CONTAINER_CLOSET, "Closet");
		this.add(ClosetBlockEntity.CONTAINER_CLOSET_DOUBLE, "Large Closet");
		this.subtitle("block.closet.open", "Closet opens");
		this.subtitle("block.closet.close", "Closet closes");
		this.subtitle("block.closet.locked", "Closet locked");
	}

	private void add(String block) {
		this.add(Util.makeDescriptionId("block", ResourceLocation.fromNamespaceAndPath(Woodworks.MOD_ID, block)), format(block));
	}

	private void subtitle(String key, String subtitle) {
		this.add(Util.makeDescriptionId("subtitles", ResourceLocation.fromNamespaceAndPath(Woodworks.MOD_ID, key)), subtitle);
	}
}