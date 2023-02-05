package com.teamabnormals.woodworks.integration.jei;

import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SawingRecipeCategory implements IRecipeCategory<SawmillRecipe> {
	public static final ResourceLocation SAWING = new ResourceLocation(Woodworks.MOD_ID, "sawing");
	public static final MutableComponent TRANSLATION = Component.translatable("gui." + Woodworks.MOD_ID + ".category.sawmill");

	public static final int width = 82;
	public static final int height = 34;

	private final IDrawable background;
	private final IDrawable icon;
	private final Component localizedName;

	public SawingRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = WoodworksPlugin.RECIPE_GUI_VANILLA;
		background = guiHelper.createDrawable(location, 0, 220, width, height);
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(WoodworksBlocks.SAWMILL.get()));
		localizedName = TRANSLATION;
	}

	@Override
	public RecipeType<SawmillRecipe> getRecipeType() {
		return WoodworksPlugin.SAWING;
	}

	@Override
	public Component getTitle() {
		return localizedName;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, SawmillRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(recipe.getResultItem());
	}

	@Override
	public boolean isHandled(SawmillRecipe recipe) {
		return !recipe.isSpecial();
	}
}