package com.teamabnormals.woodworks.common.item.crafting;

import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeSerializers;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;

public class SawmillRecipe extends SingleItemRecipe {

	public SawmillRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
		super(WoodworksRecipeTypes.SAWING.get(), WoodworksRecipeSerializers.SAWMILL.get(), id, group, ingredient, result);
	}

	@Override
	public boolean matches(Container container, Level level) {
		return this.ingredient.test(container.getItem(0));
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(WoodworksBlocks.SAWMILL.get());
	}

	@Override
	public boolean isSpecial() {
		return true;
	}
}