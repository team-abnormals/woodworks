package com.teamabnormals.woodworks.core.other;

import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Woodworks.MOD_ID)
public class WoodworksEvents {

	//TODO: Carpenter Trades
	@SubscribeEvent
	public static void onVillagerTrades(VillagerTradesEvent event) {

	}
}
