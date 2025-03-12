package com.teamabnormals.woodworks.core.data.server;

import com.google.common.collect.Maps;
import com.teamabnormals.blueprint.core.api.conditions.BlueprintAndCondition;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksConditions;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

	public WoodworksRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
		super(Woodworks.MOD_ID, output, provider);
	}

	@Override
	public void buildRecipes(RecipeOutput output) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SAWMILL.get()).define('I', Tags.Items.INGOTS_IRON).define('#', ItemTags.PLANKS).define('S', ItemTags.WOODEN_SLABS).pattern("#I").pattern("# ").pattern("#S").unlockedBy("has_planks", has(ItemTags.PLANKS)).save(output.withConditions(SAWMILL_ENABLED));
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.BOOKSHELF).define('#', ItemTags.PLANKS).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)).save(output.withConditions(config(COMMON.woodenBookshelves, "wooden_bookshelves", true)));
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_BOOKSHELF).define('#', ItemTags.PLANKS).define('X', ItemTags.WOODEN_SLABS).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)).save(output.withConditions(config(COMMON.woodenBookshelves, "wooden_bookshelves", true)));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.LADDER, 3).define('#', Items.STICK).pattern("# #").pattern("###").pattern("# #").unlockedBy("has_stick", has(Items.STICK)).save(output.withConditions(config(COMMON.woodenLadders, "wooden_ladders", true)));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.BEEHIVE).define('P', ItemTags.PLANKS).define('H', Items.HONEYCOMB).pattern("PPP").pattern("HHH").pattern("PPP").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)).save(output.withConditions(config(COMMON.woodenBeehives, "wooden_beehives", true)));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Blocks.CHEST).define('#', ItemTags.PLANKS).pattern("###").pattern("# #").pattern("###").unlockedBy("has_lots_of_items", CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), new InventoryChangeTrigger.TriggerInstance.Slots(MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY), List.of()))).save(output.withConditions(config(COMMON.woodenChests, "wooden_chests", true)));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Blocks.TRAPPED_CHEST).requires(Tags.Items.CHESTS_WOODEN).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)).save(output.withConditions(config(COMMON.woodenChests, "wooden_chests", true)));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Blocks.TRAPPED_CHEST).requires(Blocks.CHEST).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)).save(output.withConditions(WOODEN_CHESTS), ResourceLocation.fromNamespaceAndPath(Woodworks.MOD_ID, "trapped_chest"));
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Blocks.LECTERN).define('S', ItemTags.WOODEN_SLABS).define('B', Tags.Items.BOOKSHELVES).pattern("SSS").pattern(" B ").pattern(" S ").unlockedBy("has_book", has(Items.BOOK)).save(output);

		baseRecipes(output, Blocks.OAK_PLANKS, Blocks.OAK_SLAB, OAK_BOARDS.get(), Blocks.BOOKSHELF, Blocks.CHISELED_BOOKSHELF, Blocks.LADDER, Blocks.BEEHIVE, OAK_CHEST.get(), TRAPPED_OAK_CHEST.get());
		baseRecipes(output, Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB, SPRUCE_BOARDS.get(), SPRUCE_BOOKSHELF.get(), CHISELED_SPRUCE_BOOKSHELF.get(), SPRUCE_LADDER.get(), SPRUCE_BEEHIVE.get(), SPRUCE_CHEST.get(), TRAPPED_SPRUCE_CHEST.get());
		baseRecipes(output, Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB, BIRCH_BOARDS.get(), BIRCH_BOOKSHELF.get(), CHISELED_BIRCH_BOOKSHELF.get(), BIRCH_LADDER.get(), BIRCH_BEEHIVE.get(), BIRCH_CHEST.get(), TRAPPED_BIRCH_CHEST.get());
		baseRecipes(output, Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB, JUNGLE_BOARDS.get(), JUNGLE_BOOKSHELF.get(), CHISELED_JUNGLE_BOOKSHELF.get(), JUNGLE_LADDER.get(), JUNGLE_BEEHIVE.get(), JUNGLE_CHEST.get(), TRAPPED_JUNGLE_CHEST.get());
		baseRecipes(output, Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB, ACACIA_BOARDS.get(), ACACIA_BOOKSHELF.get(), CHISELED_ACACIA_BOOKSHELF.get(), ACACIA_LADDER.get(), ACACIA_BEEHIVE.get(), ACACIA_CHEST.get(), TRAPPED_ACACIA_CHEST.get());
		baseRecipes(output, Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB, DARK_OAK_BOARDS.get(), DARK_OAK_BOOKSHELF.get(), CHISELED_DARK_OAK_BOOKSHELF.get(), DARK_OAK_LADDER.get(), DARK_OAK_BEEHIVE.get(), DARK_OAK_CHEST.get(), TRAPPED_DARK_OAK_CHEST.get());
		baseRecipes(output, Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB, MANGROVE_BOARDS.get(), MANGROVE_BOOKSHELF.get(), CHISELED_MANGROVE_BOOKSHELF.get(), MANGROVE_LADDER.get(), MANGROVE_BEEHIVE.get(), MANGROVE_CHEST.get(), TRAPPED_MANGROVE_CHEST.get());
		baseRecipes(output, Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB, CHERRY_BOARDS.get(), CHERRY_BOOKSHELF.get(), CHISELED_CHERRY_BOOKSHELF.get(), CHERRY_LADDER.get(), CHERRY_BEEHIVE.get(), CHERRY_CHEST.get(), TRAPPED_CHERRY_CHEST.get());
		baseRecipes(output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB, null, BAMBOO_BOOKSHELF.get(), CHISELED_BAMBOO_BOOKSHELF.get(), BAMBOO_LADDER.get(), BAMBOO_BEEHIVE.get(), BAMBOO_CLOSET.get(), TRAPPED_BAMBOO_CLOSET.get());
		baseRecipes(output, Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB, CRIMSON_BOARDS.get(), CRIMSON_BOOKSHELF.get(), CHISELED_CRIMSON_BOOKSHELF.get(), CRIMSON_LADDER.get(), CRIMSON_BEEHIVE.get(), CRIMSON_CHEST.get(), TRAPPED_CRIMSON_CHEST.get());
		baseRecipes(output, Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB, WARPED_BOARDS.get(), WARPED_BOOKSHELF.get(), CHISELED_WARPED_BOOKSHELF.get(), WARPED_LADDER.get(), WARPED_BEEHIVE.get(), WARPED_CHEST.get(), TRAPPED_WARPED_CHEST.get());

		alternateStickRecipes(output, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_FENCE, Blocks.BAMBOO_FENCE_GATE, BAMBOO_LADDER.get(), Items.BAMBOO);

		conditionalLeafPileRecipes(output, Blocks.OAK_LEAVES, OAK_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.SPRUCE_LEAVES, SPRUCE_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.BIRCH_LEAVES, BIRCH_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.JUNGLE_LEAVES, JUNGLE_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.ACACIA_LEAVES, ACACIA_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.DARK_OAK_LEAVES, DARK_OAK_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.MANGROVE_LEAVES, MANGROVE_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.CHERRY_LEAVES, CHERRY_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.AZALEA_LEAVES, AZALEA_LEAF_PILE.get());
		conditionalLeafPileRecipes(output, Blocks.FLOWERING_AZALEA_LEAVES, FLOWERING_AZALEA_LEAF_PILE.get());

		sawmillRecipes(output, BlockFamilies.OAK_PLANKS, ItemTags.OAK_LOGS, OAK_BOARDS.get(), Blocks.LADDER);
		sawmillRecipes(output, BlockFamilies.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, SPRUCE_BOARDS.get(), SPRUCE_LADDER.get());
		sawmillRecipes(output, BlockFamilies.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, BIRCH_BOARDS.get(), BIRCH_LADDER.get());
		sawmillRecipes(output, BlockFamilies.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, JUNGLE_BOARDS.get(), JUNGLE_LADDER.get());
		sawmillRecipes(output, BlockFamilies.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, ACACIA_BOARDS.get(), ACACIA_LADDER.get());
		sawmillRecipes(output, BlockFamilies.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, DARK_OAK_BOARDS.get(), DARK_OAK_LADDER.get());
		sawmillRecipes(output, BlockFamilies.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS, MANGROVE_BOARDS.get(), MANGROVE_LADDER.get());
		sawmillRecipes(output, BlockFamilies.CHERRY_PLANKS, ItemTags.CHERRY_LOGS, CHERRY_BOARDS.get(), CHERRY_LADDER.get());
		sawmillRecipes(output, BlockFamilies.BAMBOO_PLANKS, ItemTags.BAMBOO_BLOCKS, Blocks.BAMBOO_MOSAIC, BAMBOO_LADDER.get(), true);
		sawmillRecipes(output, BlockFamilies.BAMBOO_MOSAIC, null, null, null);
		sawmillRecipes(output, BlockFamilies.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, CRIMSON_BOARDS.get(), CRIMSON_LADDER.get());
		sawmillRecipes(output, BlockFamilies.WARPED_PLANKS, ItemTags.WARPED_STEMS, WARPED_BOARDS.get(), WARPED_LADDER.get());
	}

	public static void baseRecipes(RecipeOutput output, Block planks, Block slab, Block boards, Block bookshelf, Block chiseledBookshelf, Block ladder, Block beehive, Block chest, Block trappedChest) {
		baseRecipes(output, planks, slab, boards, bookshelf, chiseledBookshelf, ladder, beehive, chest, trappedChest, Woodworks.MOD_ID);
	}

	public static void baseRecipes(RecipeOutput output, Block planks, Block slab, Block boards, Block bookshelf, Block chiseledBookshelf, Block ladder, Block beehive, Block chest, Block trappedChest, String modid) {
		boolean compat = !modid.equals(Woodworks.MOD_ID);

		ICondition boardsCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_BOARDS) : WOODEN_BOARDS;
		ICondition bookshelfCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_BOOKSHELVES) : WOODEN_BOOKSHELVES;
		ICondition ladderCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_LADDERS) : WOODEN_LADDERS;
		ICondition beehiveCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_BEEHIVES) : WOODEN_BEEHIVES;
		ICondition chestCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, WOODEN_CHESTS) : WOODEN_CHESTS;

		String prefix = planks == Blocks.OAK_PLANKS ? "oak_" : "";
		if (boards != null) {
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, boards).define('#', slab).pattern("#").pattern("#").group("wooden_boards").unlockedBy(getHasName(slab), has(slab)).save(output.withConditions(boardsCondition));
		}
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf).define('#', planks).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").group("wooden_bookshelf").unlockedBy("has_book", has(Items.BOOK)).save(output.withConditions(bookshelfCondition), ResourceLocation.fromNamespaceAndPath(modid, prefix + BuiltInRegistries.BLOCK.getKey(bookshelf).getPath()));
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chiseledBookshelf).define('#', planks).define('X', slab).pattern("###").pattern("XXX").pattern("###").group("chiseled_wooden_bookshelf").unlockedBy("has_book", has(Items.BOOK)).save(output.withConditions(bookshelfCondition), ResourceLocation.fromNamespaceAndPath(modid, prefix + BuiltInRegistries.BLOCK.getKey(chiseledBookshelf).getPath()));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).define('#', planks).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").group("wooden_ladder").unlockedBy("has_stick", has(Items.STICK)).save(output.withConditions(ladderCondition), ResourceLocation.fromNamespaceAndPath(modid, prefix + BuiltInRegistries.BLOCK.getKey(ladder).getPath()));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, beehive).define('#', planks).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").group("wooden_beehive").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)).save(output.withConditions(beehiveCondition), ResourceLocation.fromNamespaceAndPath(modid, prefix + BuiltInRegistries.BLOCK.getKey(beehive).getPath()));
		if (chest != null) {
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, chest).define('#', planks).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), new InventoryChangeTrigger.TriggerInstance.Slots(MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY), List.of()))).save(output.withConditions(chestCondition));
		}
		if (trappedChest != null) {
			ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, trappedChest).requires(chest).requires(Blocks.TRIPWIRE_HOOK).group("wooden_trapped_chest").unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)).save(output.withConditions(chestCondition));
		}
	}

	private static void alternateStickRecipes(RecipeOutput output, Block planks, Block fence, Block fenceGate, Block ladder, Item stick) {
		alternateStickRecipes(output, planks, fence, fenceGate, ladder, stick, Woodworks.MOD_ID);
	}

	public static void alternateStickRecipes(RecipeOutput output, Block planks, Block fence, Block fenceGate, Block ladder, Item stick, String modid) {
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3).define('W', planks).define('#', stick).pattern("W#W").pattern("W#W").group("wooden_custom_fence").unlockedBy("has_planks", has(planks)).save(output, getModConversionRecipeName(modid, fence, stick));
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, fenceGate).define('#', stick).define('W', planks).pattern("#W#").pattern("#W#").group("wooden_custom_fence_gate").unlockedBy("has_planks", has(planks)).save(output, getModConversionRecipeName(modid, fenceGate, stick));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ladder, 4).define('#', planks).define('S', stick).pattern("S S").pattern("S#S").pattern("S S").group("wooden_ladder").unlockedBy("has_bamboo", has(Items.BAMBOO)).save(output.withConditions(WOODEN_LADDERS), getModConversionRecipeName(modid, ladder, stick));
	}

	public static void sawmillRecipes(RecipeOutput output, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder) {
		sawmillRecipes(output, family, logs, boards, ladder, false);
	}

	private static void sawmillRecipes(RecipeOutput output, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder, boolean half) {
		sawmillRecipes(output, family, logs, boards, ladder, Woodworks.MOD_ID, half);
	}

	public static void sawmillRecipes(RecipeOutput output, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder, String modid) {
		sawmillRecipes(output, family, logs, boards, ladder, modid, false);
	}

	public static void sawmillRecipes(RecipeOutput output, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder, String modid, boolean half) {
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
		sawmillRecipe(output, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, logs, planks, full ? 4 : 2, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.REDSTONE, planks, button, 1, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.REDSTONE, logs, button, full ? 4 : 2, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.REDSTONE, logs, door, full ? 2 : 1, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.DECORATIONS, planks, fence, 1, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.DECORATIONS, logs, fence, full ? 4 : 2, "", modid);
		if (full) sawmillRecipe(output, sawmillCondition, RecipeCategory.REDSTONE, logs, fenceGate, 1, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.REDSTONE, logs, pressurePlate, full ? 2 : 1, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.DECORATIONS, logs, sign, full ? 2 : 1, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, planks, slab, 2, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, logs, slab, full ? 8 : 4, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, planks, stairs, 1, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.BUILDING_BLOCKS, logs, stairs, full ? 4 : 2, "", modid);
		sawmillRecipe(output, sawmillCondition, RecipeCategory.REDSTONE, logs, trapdoor, full ? 2 : 1, "", modid);

		if (boards != null) {
			ICondition boardsCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, SAWMILL_ENABLED, WOODEN_BOARDS) : new BlueprintAndCondition(SAWMILL_ENABLED, WOODEN_BOARDS);
			sawmillRecipe(output, boardsCondition, RecipeCategory.BUILDING_BLOCKS, planks, boards, 1, "", modid);
			sawmillRecipe(output, boardsCondition, RecipeCategory.BUILDING_BLOCKS, logs, boards, full ? 4 : 2, "", modid);
		}

		if (ladder != null) {
			ICondition ladderCondition = compat ? new BlueprintAndCondition(WOODWORKS_LOADED, SAWMILL_ENABLED, WOODEN_LADDERS) : new BlueprintAndCondition(SAWMILL_ENABLED, WOODEN_LADDERS);
			String prefix = planks == Blocks.OAK_PLANKS ? "oak_" : "";
			sawmillRecipe(output, ladderCondition, RecipeCategory.DECORATIONS, planks, ladder, 1, prefix, modid);
			sawmillRecipe(output, ladderCondition, RecipeCategory.DECORATIONS, logs, ladder, full ? 4 : 2, prefix, modid);
		}
	}

	private static void conditionalLeafPileRecipes(RecipeOutput output, ItemLike leaves, ItemLike leafPile) {
		conditionalLeafPileRecipes(output, leaves, leafPile, Woodworks.MOD_ID);
	}

	public static void conditionalLeafPileRecipes(RecipeOutput output, ItemLike leaves, ItemLike leafPile, String modid) {
		ICondition condition = modid.equals(Woodworks.MOD_ID) ? LEAF_PILES : new BlueprintAndCondition(WOODWORKS_LOADED, LEAF_PILES);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, leafPile, 4).requires(leaves).group("leaf_pile").unlockedBy(getHasName(leaves), has(leaves)).save(output.withConditions(condition));
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, leaves).define('#', leafPile).pattern("##").pattern("##").group("leaves").unlockedBy(getHasName(leafPile), has(leafPile)).save(output.withConditions(condition), ResourceLocation.fromNamespaceAndPath(modid, getConversionRecipeName(leaves, leafPile)));
	}

	public static void sawmillRecipe(RecipeOutput output, ICondition condition, RecipeCategory recipeCategory, ItemLike input, ItemLike result, int count) {
		sawmillRecipe(output, condition, recipeCategory, input, result, count, "");
	}

	public static void sawmillRecipe(RecipeOutput output, ICondition condition, RecipeCategory recipeCategory, ItemLike input, ItemLike result, int count, String prefix) {
		sawmillRecipe(output, condition, recipeCategory, input, result, count, prefix, Woodworks.MOD_ID);
	}

	public static void sawmillRecipe(RecipeOutput output, ICondition condition, RecipeCategory recipeCategory, ItemLike input, ItemLike result, int count, String prefix, String modid) {
		if (input != null && result != null) {
			sawmillResultFromBase(recipeCategory, result, input, count).save(output.withConditions(condition), ResourceLocation.fromNamespaceAndPath(modid, prefix + getConversionRecipeName(result, input) + "_sawing"));
		}
	}

	public static void sawmillRecipe(RecipeOutput output, ICondition condition, RecipeCategory recipeCategory, TagKey<Item> input, ItemLike result, int count) {
		sawmillRecipe(output, condition, recipeCategory, input, result, count, "");
	}

	private static void sawmillRecipe(RecipeOutput output, ICondition condition, RecipeCategory recipeCategory, TagKey<Item> input, ItemLike result, int count, String prefix) {
		sawmillRecipe(output, condition, recipeCategory, input, result, count, prefix, Woodworks.MOD_ID);
	}

	public static void sawmillRecipe(RecipeOutput output, ICondition condition, RecipeCategory recipeCategory, TagKey<Item> input, ItemLike result, int count, String prefix, String modid) {
		if (input != null && result != null) {
			sawmillResultFromBase(recipeCategory, result, input, count).save(output.withConditions(condition), ResourceLocation.fromNamespaceAndPath(modid, prefix + getConversionRecipeName(result, input) + "_sawing"));
		}
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(WoodworksConditions.CONFIG.get(), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}

	public static SingleItemRecipeBuilder sawing(RecipeCategory recipeCategory, Ingredient ingredient, ItemLike result, int count) {
		return new SingleItemRecipeBuilder(recipeCategory, SawmillRecipe::new, ingredient, result, count);
	}

	protected static SingleItemRecipeBuilder sawmillResultFromBase(RecipeCategory recipeCategory, ItemLike result, ItemLike input, int count) {
		return sawing(recipeCategory, Ingredient.of(input), result, count).unlockedBy(getHasName(input), has(input));
	}

	protected static SingleItemRecipeBuilder sawmillResultFromBase(RecipeCategory recipeCategory, ItemLike result, TagKey<Item> input, int count) {
		return sawing(recipeCategory, Ingredient.of(input), result, count).unlockedBy("has_" + input.location().getPath(), has(input));
	}

	protected static String getConversionRecipeName(ItemLike result, TagKey<Item> input) {
		return getItemName(result) + "_from_" + input.location().getPath();
	}

	public static ResourceLocation getModConversionRecipeName(String modid, ItemLike result, ItemLike input) {
		return ResourceLocation.fromNamespaceAndPath(modid, getConversionRecipeName(result, input));
	}
}