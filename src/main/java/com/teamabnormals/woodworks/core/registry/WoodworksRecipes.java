package com.teamabnormals.woodworks.core.registry;

import com.google.common.base.Suppliers;
import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.core.Registry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class WoodworksRecipes {
	public static final Supplier<RecipeBookCategories> SAWING = Suppliers.memoize(() -> RecipeBookCategories.create("SAWING", new ItemStack(WoodworksBlocks.OAK_BOARDS.get())));

	public static class WoodworksRecipeSerializers {
		public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Woodworks.MOD_ID);
		public static final RegistryObject<RecipeSerializer<SawmillRecipe>> SAWMILL = RECIPE_SERIALIZERS.register("sawmill", () -> new SingleItemRecipe.Serializer<>(SawmillRecipe::new) {
		});
	}

	public static class WoodworksRecipeTypes {
		public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, Woodworks.MOD_ID);
		public static final RegistryObject<RecipeType<SawmillRecipe>> SAWING = RECIPE_TYPES.register("sawing", () -> new RecipeType<>() {
			@Override
			public String toString() {
				return Woodworks.MOD_ID + ":sawing";
			}
		});
	}
}
