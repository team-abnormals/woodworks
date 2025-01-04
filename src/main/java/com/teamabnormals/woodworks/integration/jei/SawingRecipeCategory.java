package com.teamabnormals.woodworks.integration.jei;

import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public class SawingRecipeCategory implements IRecipeCategory<SawmillRecipe> {
	public static final String TRANSLATION = "gui." + Woodworks.MOD_ID + ".category.sawmill";

	private final IDrawable background;
	private final IDrawable icon;
	private final Component localizedName;

	public SawingRecipeCategory(IGuiHelper guiHelper) {
		this(guiHelper, TRANSLATION, 82, 34);
	}

	public SawingRecipeCategory(IGuiHelper guiHelper, String translationKey, int width, int height) {
		this.background = guiHelper.createBlankDrawable(width, height);
		this.icon = guiHelper.createDrawableItemStack(new ItemStack(WoodworksBlocks.SAWMILL.get()));
		this.localizedName = Component.translatable(translationKey);
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
		builder.addInputSlot(1, 9).setStandardSlotBackground().addIngredients(recipe.getIngredients().get(0));
		builder.addOutputSlot(61, 9).setOutputSlotBackground().addItemStack(getResultItem(recipe));
	}

	@Override
	public void createRecipeExtras(IRecipeExtrasBuilder builder, SawmillRecipe recipe, IFocusGroup focuses) {
		builder.addRecipeArrow().setPosition(26, 9);
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
	public boolean isHandled(SawmillRecipe recipe) {
		return true;
	}
}