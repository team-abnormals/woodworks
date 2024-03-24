package com.teamabnormals.woodworks.core.data.server;

import com.google.common.collect.Maps;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeSerializers;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.teamabnormals.woodworks.core.WoodworksConfig.COMMON;
import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksRecipeProvider extends RecipeProvider implements IConditionBuilder {

	public WoodworksRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.sawmill, "sawmill"), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SAWMILL.get()).define('I', Tags.Items.INGOTS_IRON).define('#', ItemTags.PLANKS).define('S', ItemTags.WOODEN_SLABS).pattern("#I").pattern("# ").pattern("#S").unlockedBy("has_planks", has(ItemTags.PLANKS)));
		this.conditionalRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, config(COMMON.woodenBookshelves, "wooden_bookshelves", true), ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.BOOKSHELF).define('#', ItemTags.PLANKS).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)));
		this.conditionalRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, config(COMMON.woodenBookshelves, "wooden_bookshelves", true), ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_BOOKSHELF).define('#', ItemTags.PLANKS).define('X', ItemTags.WOODEN_SLABS).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)));
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenLadders, "wooden_ladders", true), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.LADDER, 3).define('#', Items.STICK).pattern("# #").pattern("###").pattern("# #").unlockedBy("has_stick", has(Items.STICK)));
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenBeehives, "wooden_beehives", true), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.BEEHIVE).define('P', ItemTags.PLANKS).define('H', Items.HONEYCOMB).pattern("PPP").pattern("HHH").pattern("PPP").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)));
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenChests, "wooden_chests", true), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.CHEST).define('#', ItemTags.PLANKS).pattern("###").pattern("# #").pattern("###").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])));
		this.conditionalRecipe(consumer, RecipeCategory.REDSTONE, config(COMMON.woodenChests, "wooden_chests", true), ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Blocks.TRAPPED_CHEST).requires(Tags.Items.CHESTS_WOODEN).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)));
		this.conditionalRecipe(consumer, RecipeCategory.REDSTONE, config(COMMON.woodenChests, "wooden_chests"), ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Blocks.TRAPPED_CHEST).requires(Blocks.CHEST).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)), new ResourceLocation(Woodworks.MOD_ID, "trapped_chest"));
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Blocks.LECTERN).define('S', ItemTags.WOODEN_SLABS).define('B', Tags.Items.BOOKSHELVES).pattern("SSS").pattern(" B ").pattern(" S ").unlockedBy("has_book", has(Items.BOOK)).save(consumer);

		this.baseRecipes(consumer, Blocks.OAK_PLANKS, Blocks.OAK_SLAB, OAK_BOARDS.get(), Blocks.BOOKSHELF, Blocks.CHISELED_BOOKSHELF, Blocks.LADDER, Blocks.BEEHIVE, OAK_CHEST.get(), TRAPPED_OAK_CHEST.get());
		this.baseRecipes(consumer, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB, SPRUCE_BOARDS.get(), SPRUCE_BOOKSHELF.get(), CHISELED_SPRUCE_BOOKSHELF.get(), SPRUCE_LADDER.get(), SPRUCE_BEEHIVE.get(), SPRUCE_CHEST.get(), TRAPPED_SPRUCE_CHEST.get());
		this.baseRecipes(consumer, Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB, BIRCH_BOARDS.get(), BIRCH_BOOKSHELF.get(), CHISELED_BIRCH_BOOKSHELF.get(), BIRCH_LADDER.get(), BIRCH_BEEHIVE.get(), BIRCH_CHEST.get(), TRAPPED_BIRCH_CHEST.get());
		this.baseRecipes(consumer, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB, JUNGLE_BOARDS.get(), JUNGLE_BOOKSHELF.get(), CHISELED_JUNGLE_BOOKSHELF.get(), JUNGLE_LADDER.get(), JUNGLE_BEEHIVE.get(), JUNGLE_CHEST.get(), TRAPPED_JUNGLE_CHEST.get());
		this.baseRecipes(consumer, Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB, ACACIA_BOARDS.get(), ACACIA_BOOKSHELF.get(), CHISELED_ACACIA_BOOKSHELF.get(), ACACIA_LADDER.get(), ACACIA_BEEHIVE.get(), ACACIA_CHEST.get(), TRAPPED_ACACIA_CHEST.get());
		this.baseRecipes(consumer, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB, DARK_OAK_BOARDS.get(), DARK_OAK_BOOKSHELF.get(), CHISELED_DARK_OAK_BOOKSHELF.get(), DARK_OAK_LADDER.get(), DARK_OAK_BEEHIVE.get(), DARK_OAK_CHEST.get(), TRAPPED_DARK_OAK_CHEST.get());
		this.baseRecipes(consumer, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB, MANGROVE_BOARDS.get(), MANGROVE_BOOKSHELF.get(), CHISELED_MANGROVE_BOOKSHELF.get(), MANGROVE_LADDER.get(), MANGROVE_BEEHIVE.get(), MANGROVE_CHEST.get(), TRAPPED_MANGROVE_CHEST.get());
		this.baseRecipes(consumer, Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB, CHERRY_BOARDS.get(), CHERRY_BOOKSHELF.get(), CHISELED_CHERRY_BOOKSHELF.get(), CHERRY_LADDER.get(), CHERRY_BEEHIVE.get(), CHERRY_CHEST.get(), TRAPPED_CHERRY_CHEST.get());
		this.baseRecipes(consumer, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB, null, BAMBOO_BOOKSHELF.get(), CHISELED_BAMBOO_BOOKSHELF.get(), BAMBOO_LADDER.get(), BAMBOO_BEEHIVE.get(), BAMBOO_CLOSET.get(), TRAPPED_BAMBOO_CLOSET.get());
		this.baseRecipes(consumer, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB, CRIMSON_BOARDS.get(), CRIMSON_BOOKSHELF.get(), CHISELED_CRIMSON_BOOKSHELF.get(), CRIMSON_LADDER.get(), CRIMSON_BEEHIVE.get(), CRIMSON_CHEST.get(), TRAPPED_CRIMSON_CHEST.get());
		this.baseRecipes(consumer, Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB, WARPED_BOARDS.get(), WARPED_BOOKSHELF.get(), CHISELED_WARPED_BOOKSHELF.get(), WARPED_LADDER.get(), WARPED_BEEHIVE.get(), WARPED_CHEST.get(), TRAPPED_WARPED_CHEST.get());

		this.leafPile(consumer, Blocks.OAK_LEAVES, OAK_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.SPRUCE_LEAVES, SPRUCE_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.BIRCH_LEAVES, BIRCH_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.JUNGLE_LEAVES, JUNGLE_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.ACACIA_LEAVES, ACACIA_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.DARK_OAK_LEAVES, DARK_OAK_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.MANGROVE_LEAVES, MANGROVE_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.CHERRY_LEAVES, CHERRY_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.AZALEA_LEAVES, AZALEA_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.FLOWERING_AZALEA_LEAVES, FLOWERING_AZALEA_LEAF_PILE.get());

		this.sawmillRecipes(consumer, BlockFamilies.OAK_PLANKS, ItemTags.OAK_LOGS, OAK_BOARDS.get(), Blocks.LADDER);
		this.sawmillRecipes(consumer, BlockFamilies.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, SPRUCE_BOARDS.get(), SPRUCE_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, BIRCH_BOARDS.get(), BIRCH_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, JUNGLE_BOARDS.get(), JUNGLE_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, ACACIA_BOARDS.get(), ACACIA_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, DARK_OAK_BOARDS.get(), DARK_OAK_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS, MANGROVE_BOARDS.get(), MANGROVE_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.CHERRY_PLANKS, ItemTags.CHERRY_LOGS, CHERRY_BOARDS.get(), CHERRY_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.BAMBOO_PLANKS, null, null, BAMBOO_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, CRIMSON_BOARDS.get(), CRIMSON_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.WARPED_PLANKS, ItemTags.WARPED_STEMS, WARPED_BOARDS.get(), WARPED_LADDER.get());
	}

	public void baseRecipes(Consumer<FinishedRecipe> consumer, Block planks, Block slab, Block boards, Block bookshelf, Block chiseledBookshelf, Block ladder, Block beehive, Block chest, Block trappedChest) {
		String prefix = planks == Blocks.OAK_PLANKS ? "oak_" : "";
		if (boards != null) {
			this.conditionalRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, config(COMMON.woodenBoards, "wooden_boards"), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, boards).define('#', slab).pattern("#").pattern("#").group("wooden_boards").unlockedBy(getHasName(slab), has(slab)));
		}
		this.conditionalRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, config(COMMON.woodenBookshelves, "wooden_bookshelves"), ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf).define('#', planks).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").group("wooden_bookshelf").unlockedBy("has_book", has(Items.BOOK)), new ResourceLocation(Woodworks.MOD_ID, prefix + ForgeRegistries.BLOCKS.getKey(bookshelf).getPath()));
		this.conditionalRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, config(COMMON.woodenBookshelves, "wooden_bookshelves"), ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chiseledBookshelf).define('#', planks).define('X', slab).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)), new ResourceLocation(Woodworks.MOD_ID, prefix + ForgeRegistries.BLOCKS.getKey(chiseledBookshelf).getPath()));
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenLadders, "wooden_ladders"), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).define('#', planks).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").group("wooden_ladder").unlockedBy("has_stick", has(Items.STICK)), new ResourceLocation(Woodworks.MOD_ID, prefix + ForgeRegistries.BLOCKS.getKey(ladder).getPath()));
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenBeehives, "wooden_beehives"), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, beehive).define('#', planks).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").group("wooden_beehive").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)), new ResourceLocation(Woodworks.MOD_ID, prefix + ForgeRegistries.BLOCKS.getKey(beehive).getPath()));
		if (chest != null) {
			this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenChests, "wooden_chests"), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, chest).define('#', planks).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])));
		}
		if (trappedChest != null) {
			this.conditionalRecipe(consumer, RecipeCategory.REDSTONE, config(COMMON.woodenChests, "wooden_chests"), ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, trappedChest).requires(chest).requires(Blocks.TRIPWIRE_HOOK).group("wooden_trapped_chest").unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)));
		}
	}

	public void sawmillRecipes(Consumer<FinishedRecipe> consumer, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder) {
		Block planks = family.getBaseBlock();
		Block button = family.get(BlockFamily.Variant.BUTTON);
		Block door = family.get(BlockFamily.Variant.DOOR);
		Block fence = family.get(BlockFamily.Variant.FENCE);
		if (fence == null) fence = family.get(BlockFamily.Variant.CUSTOM_FENCE);
		Block fenceGate = family.get(BlockFamily.Variant.FENCE_GATE);
		if (fenceGate == null) fenceGate = family.get(BlockFamily.Variant.CUSTOM_FENCE_GATE);
		Block pressurePlate = family.get(BlockFamily.Variant.PRESSURE_PLATE);
		Block sign = family.get(BlockFamily.Variant.SIGN);
		Block slab = family.get(BlockFamily.Variant.SLAB);
		Block stairs = family.get(BlockFamily.Variant.STAIRS);
		Block trapdoor = family.get(BlockFamily.Variant.TRAPDOOR);

		this.sawmillRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, logs, planks, 4);
		this.sawmillRecipe(consumer, RecipeCategory.REDSTONE, planks, button, 1);
		this.sawmillRecipe(consumer, RecipeCategory.REDSTONE, logs, button, 4);
		this.sawmillRecipe(consumer, RecipeCategory.REDSTONE, logs, door, 2);
		this.sawmillRecipe(consumer, RecipeCategory.DECORATIONS, planks, fence, 1);
		this.sawmillRecipe(consumer, RecipeCategory.DECORATIONS, logs, fence, 4);
		this.sawmillRecipe(consumer, RecipeCategory.REDSTONE, logs, fenceGate, 1);
		this.sawmillRecipe(consumer, RecipeCategory.REDSTONE, logs, pressurePlate, 2);
		this.sawmillRecipe(consumer, RecipeCategory.DECORATIONS, logs, sign, 2);
		this.sawmillRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, planks, slab, 2);
		this.sawmillRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, logs, slab, 8);
		this.sawmillRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, planks, stairs, 1);
		this.sawmillRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, logs, stairs, 4);
		this.sawmillRecipe(consumer, RecipeCategory.REDSTONE, logs, trapdoor, 2);

		if (boards != null) {
			this.conditionalSawmillRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, config(COMMON.woodenBoards, "wooden_boards"), planks, boards);
			this.conditionalSawmillRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, config(COMMON.woodenBoards, "wooden_boards"), logs, boards, 4);
		}
		this.conditionalSawmillRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenLadders, "wooden_ladders"), planks, ladder, 1, planks == Blocks.OAK_PLANKS);
		this.conditionalSawmillRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.woodenLadders, "wooden_ladders"), logs, ladder, 4, planks == Blocks.OAK_PLANKS);
	}

	public void conditionalRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, RecipeBuilder recipe) {
		this.conditionalRecipe(consumer, recipeCategory, condition, recipe, RecipeBuilder.getDefaultRecipeId(recipe.getResult()));
	}

	public void conditionalRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, RecipeBuilder recipe, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, ItemLike input, ItemLike output) {
		conditionalSawmillRecipe(consumer, recipeCategory, condition, input, output, 1);
	}

	public void sawmillRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ItemLike input, ItemLike output, int count) {
		if (input != null) {
			ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, getConversionRecipeName(output, input) + "_sawing");
			RecipeBuilder recipe = sawmillResultFromBase(recipeCategory, output, input, count);
			ConditionalRecipe.builder().addCondition(config(COMMON.sawmill, "sawmill")).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
		}
	}

	public void sawmillRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, TagKey<Item> input, ItemLike output, int count) {
		if (input != null) {
			ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, getConversionRecipeName(output, input) + "_sawing");
			RecipeBuilder recipe = sawmillResultFromBase(recipeCategory, output, input, count);
			ConditionalRecipe.builder().addCondition(config(COMMON.sawmill, "sawmill")).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
		}
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, ItemLike input, ItemLike output, int count) {
		conditionalSawmillRecipe(consumer, recipeCategory, condition, input, output, count, false);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, ItemLike input, ItemLike output, int count, boolean doPrefix) {
		String prefix = doPrefix ? "oak_" : "";
		ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, prefix + getConversionRecipeName(output, input) + "_sawing");
		RecipeBuilder recipe = sawmillResultFromBase(recipeCategory, output, input, count);
		ConditionalRecipe.builder().addCondition(new AndCondition(config(COMMON.sawmill, "sawmill"), condition)).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, TagKey<Item> input, ItemLike output, int count) {
		conditionalSawmillRecipe(consumer, recipeCategory, condition, input, output, count, false);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, TagKey<Item> input, ItemLike output, int count, boolean doPrefix) {
		if (input != null) {
			String prefix = doPrefix ? "oak_" : "";
			ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, prefix + getConversionRecipeName(output, input) + "_sawing");
			RecipeBuilder recipe = sawmillResultFromBase(recipeCategory, output, input, count);
			ConditionalRecipe.builder().addCondition(new AndCondition(config(COMMON.sawmill, "sawmill"), condition)).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
		}
	}

	public void leafPile(Consumer<FinishedRecipe> consumer, Block leaves, Block leafPile) {
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.leafPiles, "leaf_piles"), ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, leafPile, 4).requires(leaves, 1).group("leaf_pile").unlockedBy(getHasName(leaves), has(leaves)));
		this.conditionalRecipe(consumer, RecipeCategory.DECORATIONS, config(COMMON.leafPiles, "leaf_piles"), ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, leaves, 1).define('#', leafPile).pattern("##").pattern("##").group("leaves").unlockedBy(getHasName(leafPile), has(leafPile)), new ResourceLocation(Woodworks.MOD_ID, ForgeRegistries.BLOCKS.getKey(leaves).getPath() + "_from_leaf_piles"));
	}

	public static ConfigValueCondition config(ForgeConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(new ResourceLocation(Woodworks.MOD_ID, "config"), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ForgeConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}

	public static SingleItemRecipeBuilder sawing(RecipeCategory recipeCategory, Ingredient ingredient, ItemLike item, int count) {
		return new SingleItemRecipeBuilder(recipeCategory, WoodworksRecipeSerializers.SAWMILL.get(), ingredient, item, count);
	}

	protected static SingleItemRecipeBuilder sawmillResultFromBase(RecipeCategory recipeCategory, ItemLike output, ItemLike input, int count) {
		return sawing(recipeCategory, Ingredient.of(input), output, count).unlockedBy(getHasName(input), has(input));
	}

	protected static SingleItemRecipeBuilder sawmillResultFromBase(RecipeCategory recipeCategory, ItemLike output, TagKey<Item> input, int count) {
		return sawing(recipeCategory, Ingredient.of(input), output, count).unlockedBy("has_" + input.location().getPath(), has(input));
	}

	protected static String getConversionRecipeName(ItemLike output, TagKey<Item> input) {
		return getItemName(output) + "_from_" + input.location().getPath();
	}
}