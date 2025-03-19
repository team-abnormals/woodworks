package com.teamabnormals.woodworks.integration.jei;

import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

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
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(WoodworksBlocks.SAWMILL.get()), SAWING);
	}
}