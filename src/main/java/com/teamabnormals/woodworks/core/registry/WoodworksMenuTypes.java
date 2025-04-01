package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.woodworks.client.gui.screens.inventory.SawmillScreen;
import com.teamabnormals.woodworks.common.inventory.SawmillMenu;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WoodworksMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Woodworks.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<SawmillMenu>> SAWMILL = MENU_TYPES.register("sawmill", () -> new MenuType<>(SawmillMenu::new, FeatureFlags.VANILLA_SET));

	@SubscribeEvent
	public static void registerMenuScreens(RegisterMenuScreensEvent event) {
		event.register(WoodworksMenuTypes.SAWMILL.get(), SawmillScreen::new);
	}
}