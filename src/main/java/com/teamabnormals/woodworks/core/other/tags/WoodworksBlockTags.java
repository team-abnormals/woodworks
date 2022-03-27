package com.teamabnormals.woodworks.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class WoodworksBlockTags {
	public static final TagKey<Block> LEAF_PILES = TagUtil.blockTag(Woodworks.MOD_ID, "leaf_piles");
}
