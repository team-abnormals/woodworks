package com.teamabnormals.woodworks.core.data.client;

import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.woodworks.common.block.SawmillBlock;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.integration.jei.SawingRecipeCategory;
import com.teamabnormals.woodworks.integration.jei.WoodworksPlugin;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class WoodworksLanguageProvider extends LanguageProvider {

	public WoodworksLanguageProvider(DataGenerator generator) {
		super(generator, Woodworks.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add("oak_bookshelf");
		this.add("oak_ladder");
		this.add("oak_beehive");

		WoodworksBlocks.HELPER.getDeferredRegister().getEntries().forEach(block -> {
			add(block.get(), block.get() instanceof LeafPileBlock ? "Pile of " + format(block.getId()).replace("Leaf Pile", "Leaves") : format(block.getId()));
		});

		this.add(SawmillBlock.CONTAINER_TITLE.getString(), "Sawmill");
		this.add(SawingRecipeCategory.TRANSLATION.getString(), "Sawing");
	}

	private void add(String block) {
		this.add(Util.makeDescriptionId("block", new ResourceLocation(Woodworks.MOD_ID, block)), format(block));
	}

	private String format(ResourceLocation registryName) {
		return format(registryName.getPath());
	}

	private String format(String name) {
		return WordUtils.capitalizeFully(name.replace("_", " "));
	}
}