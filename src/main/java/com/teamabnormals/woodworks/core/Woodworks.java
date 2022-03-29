package com.teamabnormals.woodworks.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.woodworks.core.data.client.WoodworksBlockStateProvider;
import com.teamabnormals.woodworks.core.data.client.WoodworksLanguageProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksLootTableProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.tags.WoodworksBlockTagsProvider;
import com.teamabnormals.woodworks.core.data.server.tags.WoodworksItemTagsProvider;
import com.teamabnormals.woodworks.core.other.WoodworksClientCompat;
import com.teamabnormals.woodworks.core.other.WoodworksCompat;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(Woodworks.MOD_ID)
public class Woodworks {
	public static final String MOD_ID = "woodworks";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public Woodworks() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(WoodworksCompat::register);
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(WoodworksClientCompat::register);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			WoodworksBlockTagsProvider blockTags = new WoodworksBlockTagsProvider(generator, fileHelper);
			generator.addProvider(blockTags);
			generator.addProvider(new WoodworksItemTagsProvider(generator, blockTags, fileHelper));
			generator.addProvider(new WoodworksLootTableProvider(generator));
			generator.addProvider(new WoodworksRecipeProvider(generator));
		}

		if (event.includeClient()) {
			generator.addProvider(new WoodworksBlockStateProvider(generator, fileHelper));
			generator.addProvider(new WoodworksLanguageProvider(generator));
		}
	}
}