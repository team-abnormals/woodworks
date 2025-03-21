package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WoodworksRecipes {

	public static class WoodworksRecipeSerializers {
		public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Woodworks.MOD_ID);
		public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SawmillRecipe>> SAWMILL = RECIPE_SERIALIZERS.register("sawmill", () -> new SingleItemRecipe.Serializer<>(SawmillRecipe::new) {
		});
	}

	public static class WoodworksRecipeTypes {
		public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Woodworks.MOD_ID);
		public static final DeferredHolder<RecipeType<?>, RecipeType<SawmillRecipe>> SAWING = RECIPE_TYPES.register("sawing", () -> new RecipeType<>() {
			@Override
			public String toString() {
				return Woodworks.MOD_ID + ":sawing";
			}
		});
	}

	@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClayworksRecipeCategories {
		public static final RecipeBookCategories SAWMILL = RecipeBookCategories.valueOf("WOODWORKS_SAWMILL");

		@SubscribeEvent
		public static void registerCategories(RegisterRecipeBookCategoriesEvent event) {
			event.registerRecipeCategoryFinder(WoodworksRecipeTypes.SAWING.get(), recipe -> SAWMILL);
		}
	}
}
