package com.teamabnormals.woodworks.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import net.minecraft.world.phys.Vec2;

import java.util.OptionalInt;

public class ChiseledMangroveBookShelfBlock extends BlueprintChiseledBookShelfBlock {

	public ChiseledMangroveBookShelfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public OptionalInt getHitSlot(Vec2 vec2) {
		int i = vec2.x <= 0.5F ? 0 : 1;
		int j = getSection(vec2.y);
		return OptionalInt.of(j * 2 + i);
	}

	public static int getSection(float y) {
		if (y < 0.3125F) {
			return 2;
		} else {
			return y < 0.5F ? 1 : 0;
		}
	}
}
