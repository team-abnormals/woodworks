package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Woodworks.MOD_ID)
public class WoodworksEvents {

	@SubscribeEvent
	public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
		if (event.getState().is(BlueprintBlockTags.LEAF_PILES))
			event.setNewSpeed(15.0F);
	}
}
