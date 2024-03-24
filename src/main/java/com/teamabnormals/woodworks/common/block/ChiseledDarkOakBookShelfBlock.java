package com.teamabnormals.woodworks.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import net.minecraft.world.phys.Vec2;

public class ChiseledDarkOakBookShelfBlock extends BlueprintChiseledBookShelfBlock {

	public ChiseledDarkOakBookShelfBlock(Properties properties) {
		super(properties);
	}

	public int getHitSlot(Vec2 vec2) {
		int i = vec2.y >= 0.5F ? 0 : 1;
		int j = getSection(i, vec2.x);
		return j + i * 3;
	}

	public static int getSection(int i, float x) {
		if (i == 0) {
			return x < 0.5F ? 0 : x < 0.75F ? 1 : 2;
		} else {
			return x < 0.25F ? 0 : x < 0.5F ? 1 : 2;
		}
	}
}
