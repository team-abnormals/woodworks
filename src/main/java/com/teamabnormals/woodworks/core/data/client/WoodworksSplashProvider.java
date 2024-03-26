package com.teamabnormals.woodworks.core.data.client;

import com.teamabnormals.blueprint.client.screen.splash.SplashProvider;
import com.teamabnormals.woodworks.client.splashes.ClayworksSplash;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.data.PackOutput;

public final class WoodworksSplashProvider extends SplashProvider {

	public WoodworksSplashProvider(PackOutput packOutput) {
		super(Woodworks.MOD_ID, packOutput);
	}

	@Override
	protected void registerSplashes() {
		this.add("The wood works!");
		this.add(ClayworksSplash.INSTANCE);
	}
}