package com.teamabnormals.woodworks.integration.jei;

import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@JeiPlugin
public class WoodworksPlugin implements IModPlugin {
	public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation("jei", "textures/gui/gui_vanilla.png");
	public static final RecipeType<SawmillRecipe> SAWING = RecipeType.create(Woodworks.MOD_ID, "sawing", SawmillRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(Woodworks.MOD_ID, Woodworks.MOD_ID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		registration.addRecipeCategories(new SawingRecipeCategory(guiHelper));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		List<SawmillRecipe> sawingRecipes = new ArrayList<>();
		sawingRecipes.addAll(getRecipes(Minecraft.getInstance().level.getRecipeManager(), WoodworksRecipeTypes.SAWING.get()));
		registration.addRecipes(SAWING, sawingRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(WoodworksBlocks.SAWMILL.get()), SAWING);
	}

	@SuppressWarnings("unchecked")
	private static <C extends Container, T extends Recipe<C>> Collection<T> getRecipes(RecipeManager recipeManager, net.minecraft.world.item.crafting.RecipeType<T> recipeType) {
		Map<ResourceLocation, Recipe<C>> recipesMap = recipeManager.byType(recipeType);
		return (Collection<T>) recipesMap.values();
	}
}