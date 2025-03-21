package com.teamabnormals.woodworks.core.other;

import com.google.common.base.Supplier;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.List;

public class WoodworksEnums {
	public static final EnumProxy<RecipeBookCategories> SAWMILL = new EnumProxy<>(RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(WoodworksBlocks.OAK_BOARDS)));
}