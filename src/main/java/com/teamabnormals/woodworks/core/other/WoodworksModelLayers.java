package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class WoodworksModelLayers {
	public static final ModelLayerLocation BAMBOO_CLOSET_LEFT = register("bamboo_closet_left");
	public static final ModelLayerLocation BAMBOO_CLOSET_TALL_LEFT = register("bamboo_closet_tall_left");
	public static final ModelLayerLocation BAMBOO_CLOSET_RIGHT = register("bamboo_closet_right");
	public static final ModelLayerLocation BAMBOO_CLOSET_TALL_RIGHT = register("bamboo_closet_tall_right");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(new ResourceLocation(Woodworks.MOD_ID, name), layer);
	}
}
