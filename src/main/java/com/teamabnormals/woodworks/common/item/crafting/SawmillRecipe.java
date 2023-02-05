package com.teamabnormals.woodworks.common.item.crafting;

import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;

public class SawmillRecipe extends SingleItemRecipe {

	public SawmillRecipe(ResourceLocation p_44478_, String p_44479_, Ingredient p_44480_, ItemStack p_44481_) {
		super(WoodworksRecipeTypes.SAWING.get(), RecipeSerializer.STONECUTTER, p_44478_, p_44479_, p_44480_, p_44481_);
	}

	public boolean matches(Container container, Level level) {
		return this.ingredient.test(container.getItem(0));
	}

	public ItemStack getToastSymbol() {
		return new ItemStack(WoodworksBlocks.SAWMILL.get());
	}

	public boolean isSpecial() {
		return true;
	}
}