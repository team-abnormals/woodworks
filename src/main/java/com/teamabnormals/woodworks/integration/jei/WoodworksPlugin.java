package com.teamabnormals.woodworks.integration.jei;

import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.WoodworksConfig;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;

import java.util.List;
import java.util.Optional;

@JeiPlugin
public class WoodworksPlugin implements IModPlugin {
	public static final RecipeType<RecipeHolder<SawmillRecipe>> SAWING = RecipeType.createRecipeHolderType(Woodworks.location("sawing"));

	@Override
	public ResourceLocation getPluginUid() {
		return Woodworks.location(Woodworks.MOD_ID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new SawingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(SAWING, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(WoodworksRecipeTypes.SAWING.get()).stream().toList());

		if (WoodworksConfig.COMMON.mixedChestCrafting.get()) {
			Ingredient planksIngredient = Ingredient.of(ItemTags.PLANKS);
			NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY,
					planksIngredient, planksIngredient, planksIngredient,
					planksIngredient, Ingredient.EMPTY, planksIngredient,
					planksIngredient, planksIngredient, planksIngredient
			);

			CraftingRecipe recipe = new ShapedRecipe("minecraft.chest", CraftingBookCategory.MISC, new ShapedRecipePattern(3, 3, inputs, Optional.empty()), new ItemStack(Items.CHEST));
			List<RecipeHolder<CraftingRecipe>> recipeHolders = List.of(new RecipeHolder<>(ResourceLocation.withDefaultNamespace("minecraft.chest"), recipe));
			registration.addRecipes(RecipeTypes.CRAFTING, recipeHolders);
		}
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(WoodworksBlocks.SAWMILL.get()), SAWING);
	}
}