package com.teamabnormals.woodworks.common;

import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class WoodenChestRecipe extends CustomRecipe {

	public WoodenChestRecipe(CraftingBookCategory category) {
		super(category);
	}

	@Override
	public boolean matches(CraftingInput input, Level level) {
		if (input.width() == 3 && input.height() == 3) {
			for (int i = 0; i < input.height(); i++) {
				for (int j = 0; j < input.width(); j++) {
					ItemStack stack = input.getItem(j, i);
					if (j == 1 && i == 1) {
						if (!stack.isEmpty()) {
							return false;
						}
					} else if (!stack.is(ItemTags.PLANKS)) {
						return false;
					}
				}
			}

			return level.getRecipeManager().getRecipes().stream()
					.map(RecipeHolder::value).filter(recipe -> recipe.getType() == RecipeType.CRAFTING && !recipe.isSpecial())
					.map(recipe -> (CraftingRecipe) recipe).filter(recipe -> recipe.matches(input, level))
					.toList().isEmpty();
		} else {
			return false;
		}
	}

	@Override
	public ItemStack assemble(CraftingInput container, HolderLookup.Provider registries) {
		return new ItemStack(Items.CHEST);
	}

	@Override
	public boolean canCraftInDimensions(int x, int y) {
		return x == 3 && y == 3;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return WoodworksRecipeSerializers.WOODEN_CHEST.get();
	}
}