package com.teamabnormals.woodworks.common.block;

import net.minecraft.world.phys.Vec2;

public class ChiseledCrimsonBookShelfBlock extends WoodworksChiseledBookShelfBlock {

	public ChiseledCrimsonBookShelfBlock(Properties properties) {
		super(properties);
	}

	public int getHitSlot(Vec2 vec2) {
		int i = vec2.y >= 0.5F ? 0 : 1;
		int j = getSection(vec2.x);
		return j + i * 3;
	}

	private static int getSection(float x) {
		return x < 0.375F ? 0 : x < 0.625F ? 1 : 2;
	}
}
