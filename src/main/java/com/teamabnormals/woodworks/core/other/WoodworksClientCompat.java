package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class WoodworksClientCompat {

	public static void register() {
		registerRenderLayers();
		registerBlockColors();
	}

	private static void registerRenderLayers() {
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.OAK_LEAF_PILE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.SPRUCE_LEAF_PILE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.BIRCH_LEAF_PILE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.JUNGLE_LEAF_PILE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.ACACIA_LEAF_PILE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.DARK_OAK_LEAF_PILE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.AZALEA_LEAF_PILE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.FLOWERING_AZALEA_LEAF_PILE.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.SPRUCE_LADDER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.BIRCH_LADDER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.JUNGLE_LADDER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.ACACIA_LADDER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.DARK_OAK_LADDER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.CRIMSON_LADDER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WoodworksBlocks.WARPED_LADDER.get(), RenderType.cutout());
	}

	private static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		List<RegistryObject<Block>> foliageBlocks = Arrays.asList(WoodworksBlocks.OAK_LEAF_PILE, WoodworksBlocks.JUNGLE_LEAF_PILE, WoodworksBlocks.DARK_OAK_LEAF_PILE, WoodworksBlocks.ACACIA_LEAF_PILE);

		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), foliageBlocks);
		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> FoliageColor.getEvergreenColor(), Arrays.asList(WoodworksBlocks.SPRUCE_LEAF_PILE));
		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> FoliageColor.getBirchColor(), Arrays.asList(WoodworksBlocks.BIRCH_LEAF_PILE));

		foliageBlocks = Arrays.asList(WoodworksBlocks.OAK_LEAF_PILE, WoodworksBlocks.SPRUCE_LEAF_PILE, WoodworksBlocks.BIRCH_LEAF_PILE, WoodworksBlocks.JUNGLE_LEAF_PILE, WoodworksBlocks.DARK_OAK_LEAF_PILE, WoodworksBlocks.ACACIA_LEAF_PILE);
		DataUtil.registerBlockItemColor(Minecraft.getInstance().getItemColors(), (stack, i) -> blockColors.getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, i), foliageBlocks);
	}
}
