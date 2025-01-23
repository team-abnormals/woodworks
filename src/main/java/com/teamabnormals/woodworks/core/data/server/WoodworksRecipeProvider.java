package com.teamabnormals.woodworks.core.data.server;

import com.google.common.collect.Maps;
import com.teamabnormals.blueprint.core.api.conditions.BlueprintAndCondition;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
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
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.teamabnormals.woodworks.core.WoodworksConfig.COMMON;
import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksRecipeProvider extends BlueprintRecipeProvider implements IConditionBuilder {
	public static final ModLoadedCondition WOODWORKS_LOADED = new ModLoadedCondition(Woodworks.MOD_ID);

	public static final ConfigValueCondition SAWMILL_ENABLED = config(COMMON.sawmill, "sawmill");
	public static final ConfigValueCondition WOODEN_BOOKSHELVES = config(COMMON.woodenBookshelves, "wooden_bookshelves");
	public static final ConfigValueCondition WOODEN_LADDERS = config(COMMON.woodenLadders, "wooden_ladders");
	public static final ConfigValueCondition WOODEN_BEEHIVES = config(COMMON.woodenBeehives, "wooden_beehives");
	public static final ConfigValueCondition WOODEN_CHESTS = config(COMMON.woodenChests, "wooden_chests");
	public static final ConfigValueCondition WOODEN_BOARDS = config(COMMON.woodenBoards, "wooden_boards");
	public static final ConfigValueCondition LEAF_PILES = config(COMMON.leafPiles, "leaf_piles");

	public WoodworksRecipeProvider(PackOutput output) {
		super(Woodworks.MOD_ID, output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		conditionalRecipe(consumer, SAWMILL_ENABLED, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SAWMILL.get()).define('I', Tags.Items.INGOTS_IRON).define('#', ItemTags.PLANKS).define('S', ItemTags.WOODEN_SLABS).pattern("#I").pattern("# ").pattern("#S").unlockedBy("has_planks", has(ItemTags.PLANKS)));
		conditionalRecipe(consumer, config(COMMON.woodenBookshelves, "wooden_bookshelves", true), RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.BOOKSHELF).define('#', ItemTags.PLANKS).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)));
		conditionalRecipe(consumer, config(COMMON.woodenBookshelves, "wooden_bookshelves", true), RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_BOOKSHELF).define('#', ItemTags.PLANKS).define('X', ItemTags.WOODEN_SLABS).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)));
		conditionalRecipe(consumer, config(COMMON.woodenLadders, "wooden_ladders", true), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.LADDER, 3).define('#', Items.STICK).pattern("# #").pattern("###").pattern("# #").unlockedBy("has_stick", has(Items.STICK)));
		conditionalRecipe(consumer, config(COMMON.woodenBeehives, "wooden_beehives", true), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.BEEHIVE).define('P', ItemTags.PLANKS).define('H', Items.HONEYCOMB).pattern("PPP").pattern("HHH").pattern("PPP").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)));
		conditionalRecipe(consumer, config(COMMON.woodenChests, "wooden_chests", true), RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.CHEST).define('#', ItemTags.PLANKS).pattern("###").pattern("# #").pattern("###").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])));
		conditionalRecipe(consumer, config(COMMON.woodenChests, "wooden_chests", true), RecipeCategory.REDSTONE, ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Blocks.TRAPPED_CHEST).requires(Tags.Items.CHESTS_WOODEN).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)));
		conditionalRecipe(consumer, WOODEN_CHESTS, RecipeCategory.REDSTONE, ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Blocks.TRAPPED_CHEST).requires(Blocks.CHEST).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)), new ResourceLocation(Woodworks.MOD_ID, "trapped_chest"));
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Blocks.LECTERN).define('S', ItemTags.WOODEN_SLABS).define('B', Tags.Items.BOOKSHELVES).pattern("SSS").pattern(" B ").pattern(" S ").unlockedBy("has_book", has(Items.BOOK)).save(consumer);

		baseRecipes(consumer, Blocks.OAK_PLANKS, Blocks.OAK_SLAB, OAK_BOARDS.get(), Blocks.BOOKSHELF, Blocks.CHISELED_BOOKSHELF, Blocks.LADDER, Blocks.BEEHIVE, OAK_CHEST.get(), TRAPPED_OAK_CHEST.get());
		baseRecipes(consumer, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB, SPRUCE_BOARDS.get(), SPRUCE_BOOKSHELF.get(), CHISELED_SPRUCE_BOOKSHELF.get(), SPRUCE_LADDER.get(), SPRUCE_BEEHIVE.get(), SPRUCE_CHEST.get(), TRAPPED_SPRUCE_CHEST.get());
		baseRecipes(consumer, Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB, BIRCH_BOARDS.get(), BIRCH_BOOKSHELF.get(), CHISELED_BIRCH_BOOKSHELF.get(), BIRCH_LADDER.get(), BIRCH_BEEHIVE.get(), BIRCH_CHEST.get(), TRAPPED_BIRCH_CHEST.get());
		baseRecipes(consumer, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB, JUNGLE_BOARDS.get(), JUNGLE_BOOKSHELF.get(), CHISELED_JUNGLE_BOOKSHELF.get(), JUNGLE_LADDER.get(), JUNGLE_BEEHIVE.get(), JUNGLE_CHEST.get(), TRAPPED_JUNGLE_CHEST.get());
		baseRecipes(consumer, Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB, ACACIA_BOARDS.get(), ACACIA_BOOKSHELF.get(), CHISELED_ACACIA_BOOKSHELF.get(), ACACIA_LADDER.get(), ACACIA_BEEHIVE.get(), ACACIA_CHEST.get(), TRAPPED_ACACIA_CHEST.get());
		baseRecipes(consumer, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB, DARK_OAK_BOARDS.get(), DARK_OAK_BOOKSHELF.get(), CHISELED_DARK_OAK_BOOKSHELF.get(), DARK_OAK_LADDER.get(), DARK_OAK_BEEHIVE.get(), DARK_OAK_CHEST.get(), TRAPPED_DARK_OAK_CHEST.get());
		baseRecipes(consumer, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB, MANGROVE_BOARDS.get(), MANGROVE_BOOKSHELF.get(), CHISELED_MANGROVE_BOOKSHELF.get(), MANGROVE_LADDER.get(), MANGROVE_BEEHIVE.get(), MANGROVE_CHEST.get(), TRAPPED_MANGROVE_CHEST.get());
		baseRecipes(consumer, Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB, CHERRY_BOARDS.get(), CHERRY_BOOKSHELF.get(), CHISELED_CHERRY_BOOKSHELF.get(), CHERRY_LADDER.get(), CHERRY_BEEHIVE.get(), CHERRY_CHEST.get(), TRAPPED_CHERRY_CHEST.get());
		baseRecipes(consumer, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB, null, BAMBOO_BOOKSHELF.get(), CHISELED_BAMBOO_BOOKSHELF.get(), BAMBOO_LADDER.get(), BAMBOO_BEEHIVE.get(), BAMBOO_CLOSET.get(), TRAPPED_BAMBOO_CLOSET.get());
		baseRecipes(consumer, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB, CRIMSON_BOARDS.get(), CRIMSON_BOOKSHELF.get(), CHISELED_CRIMSON_BOOKSHELF.get(), CRIMSON_LADDER.get(), CRIMSON_BEEHIVE.get(), CRIMSON_CHEST.get(), TRAPPED_CRIMSON_CHEST.get());
		baseRecipes(consumer, Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB, WARPED_BOARDS.get(), WARPED_BOOKSHELF.get(), CHISELED_WARPED_BOOKSHELF.get(), WARPED_LADDER.get(), WARPED_BEEHIVE.get(), WARPED_CHEST.get(), TRAPPED_WARPED_CHEST.get());

		alternateStickRecipes(consumer, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_FENCE, Blocks.BAMBOO_FENCE_GATE, BAMBOO_LADDER.get(), Items.BAMBOO);

		leafPileRecipes(consumer, Blocks.OAK_LEAVES, OAK_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.SPRUCE_LEAVES, SPRUCE_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.BIRCH_LEAVES, BIRCH_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.JUNGLE_LEAVES, JUNGLE_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.ACACIA_LEAVES, ACACIA_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.DARK_OAK_LEAVES, DARK_OAK_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.MANGROVE_LEAVES, MANGROVE_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.CHERRY_LEAVES, CHERRY_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.AZALEA_LEAVES, AZALEA_LEAF_PILE.get());
		leafPileRecipes(consumer, Blocks.FLOWERING_AZALEA_LEAVES, FLOWERING_AZALEA_LEAF_PILE.get());

		sawmillRecipes(consumer, BlockFamilies.OAK_PLANKS, ItemTags.OAK_LOGS, OAK_BOARDS.get(), Blocks.LADDER);
		sawmillRecipes(consumer, BlockFamilies.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, SPRUCE_BOARDS.get(), SPRUCE_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, BIRCH_BOARDS.get(), BIRCH_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, JUNGLE_BOARDS.get(), JUNGLE_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, ACACIA_BOARDS.get(), ACACIA_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, DARK_OAK_BOARDS.get(), DARK_OAK_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS, MANGROVE_BOARDS.get(), MANGROVE_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.CHERRY_PLANKS, ItemTags.CHERRY_LOGS, CHERRY_BOARDS.get(), CHERRY_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.BAMBOO_PLANKS, ItemTags.BAMBOO_BLOCKS, Blocks.BAMBOO_MOSAIC, BAMBOO_LADDER.get(), true);
		sawmillRecipes(consumer, BlockFamilies.BAMBOO_MOSAIC, null, null, null);
		sawmillRecipes(consumer, BlockFamilies.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, CRIMSON_BOARDS.get(), CRIMSON_LADDER.get());
		sawmillRecipes(consumer, BlockFamilies.WARPED_PLANKS, ItemTags.WARPED_STEMS, WARPED_BOARDS.get(), WARPED_LADDER.get());
	}

	public static void baseRecipes(Consumer<FinishedRecipe> consumer, Block planks, Block slab, Block boards, Block bookshelf, Block chiseledBookshelf, Block ladder, Block beehive, Block chest, Block trappedChest) {
		baseRecipes(consumer, planks, slab, boards, bookshelf, chiseledBookshelf, ladder, beehive, chest, trappedChest, Woodworks.MOD_ID);
	}

	public static void baseRecipes(Consumer<FinishedRecipe> consumer, Block planks, Block slab, Block boards, Block bookshelf, Block chiseledBookshelf, Block ladder, Block beehive, Block chest, Block trappedChest, String modid) {
		boolean compat = !modid.equals(Woodworks.MOD_ID);

		ICondition boardsCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_BOARDS) : WOODEN_BOARDS;
		ICondition bookshelfCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_BOOKSHELVES) : WOODEN_BOOKSHELVES;
		ICondition ladderCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_LADDERS) : WOODEN_LADDERS;
		ICondition beehiveCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_BEEHIVES) : WOODEN_BEEHIVES;
		ICondition chestCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_CHESTS) : WOODEN_CHESTS;

		String prefix = planks == Blocks.OAK_PLANKS ? "oak_" : "";
		if (boards != null) {
			conditionalRecipe(consumer, boardsCondition, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, boards).define('#', slab).pattern("#").pattern("#").group("wooden_boards").unlockedBy(getHasName(slab), has(slab)));
		}
		conditionalRecipe(consumer, bookshelfCondition, RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf).define('#', planks).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").group("wooden_bookshelf").unlockedBy("has_book", has(Items.BOOK)), new ResourceLocation(modid, prefix + ForgeRegistries.BLOCKS.getKey(bookshelf).getPath()));
		conditionalRecipe(consumer, bookshelfCondition, RecipeCategory.BUILDING_BLOCKS, ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chiseledBookshelf).define('#', planks).define('X', slab).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)), new ResourceLocation(modid, prefix + ForgeRegistries.BLOCKS.getKey(chiseledBookshelf).getPath()));
		conditionalRecipe(consumer, ladderCondition, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).define('#', planks).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").group("wooden_ladder").unlockedBy("has_stick", has(Items.STICK)), new ResourceLocation(modid, prefix + ForgeRegistries.BLOCKS.getKey(ladder).getPath()));
		conditionalRecipe(consumer, beehiveCondition, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, beehive).define('#', planks).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").group("wooden_beehive").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)), new ResourceLocation(modid, prefix + ForgeRegistries.BLOCKS.getKey(beehive).getPath()));
		if (chest != null) {
			conditionalRecipe(consumer, chestCondition, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, chest).define('#', planks).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])));
		}
		if (trappedChest != null) {
			conditionalRecipe(consumer, chestCondition, RecipeCategory.REDSTONE, ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, trappedChest).requires(chest).requires(Blocks.TRIPWIRE_HOOK).group("wooden_trapped_chest").unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)));
		}
	}

	public static void alternateStickRecipes(Consumer<FinishedRecipe> consumer, Block planks, Block fence, Block fenceGate, Block ladder, Item stick) {
		alternateStickRecipes(consumer, planks, fence, fenceGate, ladder, stick, Woodworks.MOD_ID);
	}

	public static void alternateStickRecipes(Consumer<FinishedRecipe> consumer, Block planks, Block fence, Block fenceGate, Block ladder, Item stick, String modid) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3).define('W', planks).define('#', stick).pattern("W#W").pattern("W#W").group("wooden_custom_fence").unlockedBy("has_planks", has(planks)).save(consumer, getModConversionRecipeName(modid, fence, stick));
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, fenceGate).define('#', stick).define('W', planks).pattern("#W#").pattern("#W#").group("wooden_custom_fence_gate").unlockedBy("has_planks", has(planks)).save(consumer, getModConversionRecipeName(modid, fenceGate, stick));
		conditionalRecipe(consumer, WOODEN_LADDERS, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).define('#', planks).define('S', stick).pattern("S S").pattern("S#S").pattern("S S").group("wooden_ladder").unlockedBy("has_bamboo", has(Items.BAMBOO)), getModConversionRecipeName(modid, ladder, stick));
	}

	public static void sawmillRecipes(Consumer<FinishedRecipe> consumer, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder) {
		sawmillRecipes(consumer, family, logs, boards, ladder, false);
	}

	public static void sawmillRecipes(Consumer<FinishedRecipe> consumer, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder, boolean half) {
		sawmillRecipes(consumer, family, logs, boards, ladder, Woodworks.MOD_ID, half);
	}

	public static void sawmillRecipes(Consumer<FinishedRecipe> consumer, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder, String modid, boolean half) {
		boolean compat = !modid.equals(Woodworks.MOD_ID);
		boolean full = !half;

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

		ICondition sawmillCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, SAWMILL_ENABLED) : SAWMILL_ENABLED;
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, logs, planks, full ? 4 : 2, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.REDSTONE, planks, button, 1, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.REDSTONE, logs, button, full ? 4 : 2, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.REDSTONE, logs, door, full ? 2 : 1, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.DECORATIONS, planks, fence, 1, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.DECORATIONS, logs, fence, full ? 4 : 2, "", modid);
		if (full) sawmillRecipe(consumer, sawmillCondition, RecipeCategory.REDSTONE, logs, fenceGate, 1, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.REDSTONE, logs, pressurePlate, full ? 2 : 1, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.DECORATIONS, logs, sign, full ? 2 : 1, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, planks, slab, 2, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, logs, slab, full ? 8 : 4, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, planks, stairs, 1, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, logs, stairs, full ? 4 : 2, "", modid);
		sawmillRecipe(consumer, sawmillCondition, RecipeCategory.REDSTONE, logs, trapdoor, full ? 2 : 1, "", modid);

		if (boards != null) {
			ICondition boardsCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, SAWMILL_ENABLED, WOODEN_BOARDS) : new BlueprintAndCondition(SAWMILL_ENABLED, WOODEN_BOARDS);
			sawmillRecipe(consumer, boardsCondition, RecipeCategory.BUILDING_BLOCKS, planks, boards, 1, "", modid);
			sawmillRecipe(consumer, boardsCondition, RecipeCategory.BUILDING_BLOCKS, logs, boards, full ? 4 : 2, "", modid);
		}

		if (ladder != null) {
			ICondition ladderCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, SAWMILL_ENABLED, WOODEN_LADDERS) : new BlueprintAndCondition(SAWMILL_ENABLED, WOODEN_LADDERS);
			String prefix = planks == Blocks.OAK_PLANKS ? "oak_" : "";
			sawmillRecipe(consumer, ladderCondition, RecipeCategory.DECORATIONS, planks, ladder, 1, prefix, modid);
			sawmillRecipe(consumer, ladderCondition, RecipeCategory.DECORATIONS, logs, ladder, full ? 4 : 2, prefix, modid);
		}
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, RecipeBuilder recipe) {
		conditionalRecipe(consumer, condition, recipeCategory, recipe, RecipeBuilder.getDefaultRecipeId(recipe.getResult()));
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, RecipeBuilder recipe, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public static void sawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, ItemLike input, ItemLike output, int count) {
		sawmillRecipe(consumer, condition, recipeCategory, input, output, count, "");
	}

	public static void sawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, ItemLike input, ItemLike output, int count, String prefix) {
		sawmillRecipe(consumer, condition, recipeCategory, input, output, count, prefix, Woodworks.MOD_ID);
	}

	public static void sawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, ItemLike input, ItemLike output, int count, String prefix, String modid) {
		if (input != null && output != null) {
			ResourceLocation id = new ResourceLocation(modid, prefix + getConversionRecipeName(output, input) + "_sawing");
			RecipeBuilder recipe = sawmillResultFromBase(recipeCategory, output, input, count);
			ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
		}
	}

	public static void sawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, TagKey<Item> input, ItemLike output, int count) {
		sawmillRecipe(consumer, condition, recipeCategory, input, output, count, "");
	}

	public static void sawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, TagKey<Item> input, ItemLike output, int count, String prefix) {
		sawmillRecipe(consumer, condition, recipeCategory, input, output, count, prefix, Woodworks.MOD_ID);
	}

	public static void sawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory recipeCategory, TagKey<Item> input, ItemLike output, int count, String prefix, String modid) {
		if (input != null && output != null) {
			ResourceLocation id = new ResourceLocation(modid, prefix + getConversionRecipeName(output, input) + "_sawing");
			RecipeBuilder recipe = sawmillResultFromBase(recipeCategory, output, input, count);
			ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
		}
	}

	public static void leafPileRecipes(Consumer<FinishedRecipe> consumer, Block leaves, Block leafPile) {
		leafPileRecipes(consumer, leaves, leafPile, Woodworks.MOD_ID);
	}

	public static void leafPileRecipes(Consumer<FinishedRecipe> consumer, Block leaves, Block leafPile, String modid) {
		boolean compat = !modid.equals(Woodworks.MOD_ID);
		ICondition condition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, LEAF_PILES) : LEAF_PILES;
		conditionalRecipe(consumer, condition, RecipeCategory.DECORATIONS, ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, leafPile, 4).requires(leaves, 1).group("leaf_pile").unlockedBy(getHasName(leaves), has(leaves)));
		conditionalRecipe(consumer, condition, RecipeCategory.DECORATIONS, ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, leaves, 1).define('#', leafPile).pattern("##").pattern("##").group("leaves").unlockedBy(getHasName(leafPile), has(leafPile)), new ResourceLocation(modid, ForgeRegistries.BLOCKS.getKey(leaves).getPath() + "_from_leaf_piles"));
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

	public static ResourceLocation getModConversionRecipeName(String modid, ItemLike output, ItemLike input) {
		return new ResourceLocation(modid, getConversionRecipeName(output, input));
	}
}