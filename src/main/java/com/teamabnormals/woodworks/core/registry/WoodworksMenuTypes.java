package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.woodworks.client.gui.screens.inventory.SawmillScreen;
import com.teamabnormals.woodworks.common.inventory.SawmillMenu;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WoodworksMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Woodworks.MOD_ID);

	public static final RegistryObject<MenuType<SawmillMenu>> SAWMILL = MENU_TYPES.register("sawmill", () -> new MenuType<>(SawmillMenu::new));

	public static void registerScreens() {
		MenuScreens.register(WoodworksMenuTypes.SAWMILL.get(), SawmillScreen::new);
	}
}
