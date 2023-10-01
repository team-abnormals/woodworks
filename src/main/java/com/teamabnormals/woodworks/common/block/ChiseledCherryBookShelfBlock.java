package com.teamabnormals.woodworks.common.block;

import net.minecraft.world.phys.Vec2;

public class ChiseledCherryBookShelfBlock extends WoodworksChiseledBookShelfBlock {

	public ChiseledCherryBookShelfBlock(Properties properties) {
		super(properties);
	}

	public int getHitSlot(Vec2 vec2) {
		return vec2.x < 0.1875F ? 0 : vec2.x < 0.375F ? 1 : vec2.x < 0.625F ? (vec2.y >= 0.5F ? 2 : 3) : vec2.x < 0.8125F ? 4 : 5;
	}
}
