package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.woodworks.client.gui.screens.inventory.SawmillScreen;
import com.teamabnormals.woodworks.client.renderer.block.DrawerBlockEntityRenderer;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlockEntityTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WoodworksClientCompat {

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_LEFT, () -> DrawerBlockEntityRenderer.createBodyLayer(false, false));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_RIGHT, () -> DrawerBlockEntityRenderer.createBodyLayer(false, true));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_TALL_LEFT, () -> DrawerBlockEntityRenderer.createBodyLayer(true, false));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_TALL_RIGHT, () -> DrawerBlockEntityRenderer.createBodyLayer(true, true));
	}

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(WoodworksBlockEntityTypes.CLOSET.get(), DrawerBlockEntityRenderer::new);
		event.registerBlockEntityRenderer(WoodworksBlockEntityTypes.TRAPPED_CLOSET.get(), DrawerBlockEntityRenderer::new);
	}

	@SubscribeEvent
	public static void registerMenuScreens(RegisterMenuScreensEvent event) {
		event.register(WoodworksMenuTypes.SAWMILL.get(), SawmillScreen::new);
	}

	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		BlockColors colors = Minecraft.getInstance().getBlockColors();
		event.register((stack, color) -> {
			BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
			return colors.getColor(blockstate, null, null, color);
		}, WoodworksBlocks.OAK_LEAF_PILE, WoodworksBlocks.SPRUCE_LEAF_PILE, WoodworksBlocks.BIRCH_LEAF_PILE, WoodworksBlocks.JUNGLE_LEAF_PILE, WoodworksBlocks.ACACIA_LEAF_PILE, WoodworksBlocks.DARK_OAK_LEAF_PILE, WoodworksBlocks.MANGROVE_LEAF_PILE);
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), WoodworksBlocks.OAK_LEAF_PILE.get(), WoodworksBlocks.JUNGLE_LEAF_PILE.get(), WoodworksBlocks.ACACIA_LEAF_PILE.get(), WoodworksBlocks.DARK_OAK_LEAF_PILE.get(), WoodworksBlocks.MANGROVE_LEAF_PILE.get());
		event.register((x, blockAndTintGetter, pos, u) -> FoliageColor.getEvergreenColor(), WoodworksBlocks.SPRUCE_LEAF_PILE.get());
		event.register((x, blockAndTintGetter, pos, u) -> FoliageColor.getBirchColor(), WoodworksBlocks.BIRCH_LEAF_PILE.get());
	}
}
