package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class WoodworksClientCompat {

	public static void register() {
		registerBlockColors();
	}

	private static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		List<RegistryObject<Block>> foliageBlocks = Arrays.asList(WoodworksBlocks.OAK_LEAF_PILE, WoodworksBlocks.JUNGLE_LEAF_PILE, WoodworksBlocks.ACACIA_LEAF_PILE, WoodworksBlocks.DARK_OAK_LEAF_PILE, WoodworksBlocks.MANGROVE_LEAF_PILE);

		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), foliageBlocks);
		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> FoliageColor.getEvergreenColor(), Arrays.asList(WoodworksBlocks.SPRUCE_LEAF_PILE));
		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> FoliageColor.getBirchColor(), Arrays.asList(WoodworksBlocks.BIRCH_LEAF_PILE));

		foliageBlocks = Arrays.asList(WoodworksBlocks.OAK_LEAF_PILE, WoodworksBlocks.SPRUCE_LEAF_PILE, WoodworksBlocks.BIRCH_LEAF_PILE, WoodworksBlocks.JUNGLE_LEAF_PILE, WoodworksBlocks.ACACIA_LEAF_PILE, WoodworksBlocks.DARK_OAK_LEAF_PILE, WoodworksBlocks.MANGROVE_LEAF_PILE);
		DataUtil.registerBlockItemColor(Minecraft.getInstance().getItemColors(), (stack, i) -> blockColors.getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, i), foliageBlocks);
	}
}
