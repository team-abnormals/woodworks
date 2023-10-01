package com.teamabnormals.woodworks.common.block;

import net.minecraft.world.phys.Vec2;

public class ChiseledMangroveBookShelfBlock extends WoodworksChiseledBookShelfBlock {

	public ChiseledMangroveBookShelfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public int getHitSlot(Vec2 vec2) {
		int i = vec2.x <= 0.5F ? 0 : 1;
		int j = getSection(vec2.y);
		return j * 2 + i;
	}

	private static int getSection(float y) {
		if (y < 0.3125F) {
			return 2;
		} else {
			return y < 0.5F ? 1 : 0;
		}
	}
}
