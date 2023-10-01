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
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksLootConditions;
import com.teamabnormals.woodworks.core.registry.WoodworksMenuTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeSerializers;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import com.teamabnormals.woodworks.core.registry.helper.WoodworksBlockSubRegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

@Mod(Woodworks.MOD_ID)
public class Woodworks {
	public static final String MOD_ID = "woodworks";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.BLOCKS, new WoodworksBlockSubRegistryHelper(helper)));

	public Woodworks() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
		WoodworksLootConditions.LOOT_CONDITION_TYPES.register(bus);
		WoodworksMenuTypes.MENU_TYPES.register(bus);
		WoodworksRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
		WoodworksRecipeTypes.RECIPE_TYPES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			WoodworksBlocks.setupTabEditors();
		});

		context.registerConfig(ModConfig.Type.COMMON, WoodworksConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			WoodworksCompat.register();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			WoodworksClientCompat.register();
			WoodworksMenuTypes.registerScreens();
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		WoodworksBlockTagsProvider blockTags = new WoodworksBlockTagsProvider(packOutput, lookupProvider, fileHelper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new WoodworksItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), fileHelper));
		generator.addProvider(includeServer, new WoodworksLootTableProvider(packOutput));
		generator.addProvider(includeServer, new WoodworksRecipeProvider(packOutput));
//		generator.addProvider(includeServer, new WoodworksStructureRepaletterProvider(generator));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new WoodworksBlockStateProvider(packOutput, fileHelper));
		generator.addProvider(includeClient, new WoodworksLanguageProvider(packOutput));
	}
}