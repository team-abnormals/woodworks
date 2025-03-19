package com.teamabnormals.woodworks.integration.jei;

import com.mojang.serialization.Codec;
import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public class SawingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<SawmillRecipe>> {
	public static final String TRANSLATION = "gui." + Woodworks.MOD_ID + ".category.sawmill";

	public SawingRecipeCategory(IGuiHelper guiHelper) {
		super(WoodworksPlugin.SAWING, Component.translatable(TRANSLATION), guiHelper.createDrawableItemLike(WoodworksBlocks.SAWMILL), 82, 34);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<SawmillRecipe> recipeHolder, IFocusGroup focuses) {
		SawmillRecipe recipe = recipeHolder.value();
		builder.addInputSlot(1, 9).setStandardSlotBackground().addIngredients(recipe.getIngredients().getFirst());
		builder.addOutputSlot(61, 9).setOutputSlotBackground().addItemStack(getResultItem(recipe));
	}

	public static ItemStack getResultItem(Recipe<?> recipe) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		if (level == null) {
			throw new NullPointerException("level must not be null.");
		}
		RegistryAccess registryAccess = level.registryAccess();
		return recipe.getResultItem(registryAccess);
	}

	@Override
	public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<SawmillRecipe> recipe, IFocusGroup focuses) {
		builder.addRecipeArrow().setPosition(26, 9);
	}

	@Override
	public boolean isHandled(RecipeHolder<SawmillRecipe> recipeHolder) {
		SawmillRecipe recipe = recipeHolder.value();
		return !recipe.isSpecial();
	}

	@Override
	public ResourceLocation getRegistryName(RecipeHolder<SawmillRecipe> recipe) {
		return recipe.id();
	}

	@Override
	public Codec<RecipeHolder<SawmillRecipe>> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {
		return codecHelper.getRecipeHolderCodec();
	}
}