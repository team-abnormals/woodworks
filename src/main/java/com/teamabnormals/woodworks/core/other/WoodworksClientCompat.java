package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WoodworksClientCompat {

	public static void register() {
		WoodworksBlocks.setupTabEditors();
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
