package com.teamabnormals.woodworks.integration.jei;

import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public class SawingRecipeCategory implements IRecipeCategory<SawmillRecipe> {
	public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png");
	public static final MutableComponent TRANSLATION = Component.translatable("gui." + Woodworks.MOD_ID + ".category.sawmill");

	public static final int WIDTH = 82;
	public static final int HEIGHT = 34;

	private final IDrawable background;
	private final IDrawable icon;
	private final Component localizedName;

	public SawingRecipeCategory(IGuiHelper guiHelper) {
		background = guiHelper.createDrawable(RECIPE_GUI_VANILLA, 0, 220, WIDTH, HEIGHT);
		icon = guiHelper.createDrawableItemStack(new ItemStack(WoodworksBlocks.SAWMILL.get()));
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
		builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(getResultItem(recipe));
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