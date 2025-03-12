package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.woodworks.common.inventory.SawmillMenu;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WoodworksMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Woodworks.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<SawmillMenu>> SAWMILL = MENU_TYPES.register("sawmill", () -> new MenuType<>(SawmillMenu::new, FeatureFlags.VANILLA_SET));
}