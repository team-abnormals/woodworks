package com.teamabnormals.woodworks.common.block;

import net.minecraft.world.phys.Vec2;

public class ChiseledBambooBookShelfBlock extends WoodworksChiseledBookShelfBlock {

	public ChiseledBambooBookShelfBlock(Properties properties) {
		super(properties);
	}

	public int getHitSlot(Vec2 vec2) {
		int i = vec2.y >= (vec2.x < 0.625F ? 0.5625F : 0.375F) ? 0 : 1;
		int j = getSection(i, vec2.x);
		return j + i * 3;
	}

	private static int getSection(int i, float x) {
		if (i == 0) {
			return x < 0.3125F ? 0 : x < 0.625F ? 1 : 2;
		} else {
			return x < 0.375F ? 0 : x < 0.625F ? 1 : 2;
		}
	}
}
