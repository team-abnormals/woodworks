package com.teamabnormals.woodworks.common.item.crafting;

import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeSerializers;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public class SawmillRecipe extends SingleItemRecipe {

	public SawmillRecipe(String group, Ingredient ingredient, ItemStack result) {
		super(WoodworksRecipeTypes.SAWING.get(), WoodworksRecipeSerializers.SAWMILL.get(), group, ingredient, result);
	}

	@Override
	public boolean matches(SingleRecipeInput input, Level level) {
		return this.ingredient.test(input.item());
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(WoodworksBlocks.SAWMILL.get());
	}
}