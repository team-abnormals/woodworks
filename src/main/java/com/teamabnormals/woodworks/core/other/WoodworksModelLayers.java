package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.woodworks.client.renderer.block.DrawerBlockEntityRenderer;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlockEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;

@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WoodworksModelLayers {
	public static final ModelLayerLocation BAMBOO_CLOSET_LEFT = register("bamboo_closet_left");
	public static final ModelLayerLocation BAMBOO_CLOSET_TALL_LEFT = register("bamboo_closet_tall_left");
	public static final ModelLayerLocation BAMBOO_CLOSET_RIGHT = register("bamboo_closet_right");
	public static final ModelLayerLocation BAMBOO_CLOSET_TALL_RIGHT = register("bamboo_closet_tall_right");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Woodworks.MOD_ID, name), layer);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_LEFT, () -> DrawerBlockEntityRenderer.createBodyLayer(false, false));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_RIGHT, () -> DrawerBlockEntityRenderer.createBodyLayer(false, true));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_TALL_LEFT, () -> DrawerBlockEntityRenderer.createBodyLayer(true, false));
		event.registerLayerDefinition(WoodworksModelLayers.BAMBOO_CLOSET_TALL_RIGHT, () -> DrawerBlockEntityRenderer.createBodyLayer(true, true));
	}

	@SubscribeEvent
	public static void registerRenderers(RegisterRenderers event) {
		event.registerBlockEntityRenderer(WoodworksBlockEntityTypes.CLOSET.get(), DrawerBlockEntityRenderer::new);
		event.registerBlockEntityRenderer(WoodworksBlockEntityTypes.TRAPPED_CLOSET.get(), DrawerBlockEntityRenderer::new);
	}
}
