package com.teamabnormals.woodworks.core;

import com.teamabnormals.blueprint.client.screen.splash.SplashSerializers;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.woodworks.client.gui.screens.inventory.SawmillScreen;
import com.teamabnormals.woodworks.client.renderer.block.DrawerBlockEntityRenderer;
import com.teamabnormals.woodworks.client.splashes.ClayworksSplash;
import com.teamabnormals.woodworks.core.data.client.WoodworksBlockStateProvider;
import com.teamabnormals.woodworks.core.data.client.WoodworksLanguageProvider;
import com.teamabnormals.woodworks.core.data.client.WoodworksSoundDefinitionsProvider;
import com.teamabnormals.woodworks.core.data.client.WoodworksSplashProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksDatapackProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksLootTableProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.tags.WoodworksBlockTagsProvider;
import com.teamabnormals.woodworks.core.data.server.tags.WoodworksItemTagsProvider;
import com.teamabnormals.woodworks.core.other.WoodworksCompat;
import com.teamabnormals.woodworks.core.other.WoodworksDataProcessors;
import com.teamabnormals.woodworks.core.other.WoodworksModelLayers;
import com.teamabnormals.woodworks.core.registry.*;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeSerializers;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import com.teamabnormals.woodworks.core.registry.helper.WoodworksBlockSubRegistryHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(Woodworks.MOD_ID)
public class Woodworks {
	public static final String MOD_ID = "woodworks";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(Registries.BLOCK, new WoodworksBlockSubRegistryHelper(helper)));

	public Woodworks(IEventBus bus, ModContainer container) {
		WoodworksDataProcessors.registerTrackedData();

		WoodworksBlocks.BLOCKS.register(bus);
		WoodworksBlocks.ITEMS.register(bus);
		WoodworksBlockEntityTypes.BLOCK_ENTITY_TYPES.register(bus);
		WoodworksSoundEvents.SOUND_EVENTS.register(bus);
		WoodworksConditions.CONDITION_SERIALIZERS.register(bus);
		WoodworksMenuTypes.MENU_TYPES.register(bus);
		WoodworksRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
		WoodworksRecipeTypes.RECIPE_TYPES.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			WoodworksBlocks.setupTabEditors();
			bus.addListener(this::registerLayerDefinitions);
			bus.addListener(this::registerRenderers);
			bus.addListener(this::registerMenuScreens);
			bus.addListener(this::registerBlockColors);
			bus.addListener(this::registerItemColors);
			SplashSerializers.register(ResourceLocation.fromNamespaceAndPath(MOD_ID, "clayworks"), ClayworksSplash.CODEC);
		}

		container.registerConfig(ModConfig.Type.COMMON, WoodworksConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			WoodworksCompat.register();
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();
		WoodworksBlockTagsProvider blockTags = new WoodworksBlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new WoodworksItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new WoodworksLootTableProvider(output, provider));
		generator.addProvider(server, new WoodworksRecipeProvider(output, provider));
		generator.addProvider(server, new WoodworksDatapackProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new WoodworksSplashProvider(output));
		generator.addProvider(client, new WoodworksBlockStateProvider(output, helper));
		generator.addProvider(client, new WoodworksLanguageProvider(output));
		generator.addProvider(client, new WoodworksSoundDefinitionsProvider(output, helper));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_LEFT, () -> DrawerBlockEntityRenderer.createBodyLayer(false, false));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_RIGHT, () -> DrawerBlockEntityRenderer.createBodyLayer(false, true));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_TALL_LEFT, () -> DrawerBlockEntityRenderer.createBodyLayer(true, false));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_TALL_RIGHT, () -> DrawerBlockEntityRenderer.createBodyLayer(true, true));
	}

	@OnlyIn(Dist.CLIENT)
	private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(WoodworksBlockEntityTypes.CLOSET.get(), DrawerBlockEntityRenderer::new);
		event.registerBlockEntityRenderer(WoodworksBlockEntityTypes.TRAPPED_CLOSET.get(), DrawerBlockEntityRenderer::new);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerMenuScreens(RegisterMenuScreensEvent event) {
		event.register(WoodworksMenuTypes.SAWMILL.get(), SawmillScreen::new);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerItemColors(RegisterColorHandlersEvent.Item event) {
		BlockColors colors = Minecraft.getInstance().getBlockColors();
		event.register((stack, color) -> {
			BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
			return colors.getColor(blockstate, null, null, color);
		}, WoodworksBlocks.OAK_LEAF_PILE, WoodworksBlocks.SPRUCE_LEAF_PILE, WoodworksBlocks.BIRCH_LEAF_PILE, WoodworksBlocks.JUNGLE_LEAF_PILE, WoodworksBlocks.ACACIA_LEAF_PILE, WoodworksBlocks.DARK_OAK_LEAF_PILE, WoodworksBlocks.MANGROVE_LEAF_PILE);
	}

	@OnlyIn(Dist.CLIENT)
	private void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), WoodworksBlocks.OAK_LEAF_PILE.get(), WoodworksBlocks.JUNGLE_LEAF_PILE.get(), WoodworksBlocks.ACACIA_LEAF_PILE.get(), WoodworksBlocks.DARK_OAK_LEAF_PILE.get(), WoodworksBlocks.MANGROVE_LEAF_PILE.get());
		event.register((x, blockAndTintGetter, pos, u) -> FoliageColor.getEvergreenColor(), WoodworksBlocks.SPRUCE_LEAF_PILE.get());
		event.register((x, blockAndTintGetter, pos, u) -> FoliageColor.getBirchColor(), WoodworksBlocks.BIRCH_LEAF_PILE.get());
	}

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}