package com.teamabnormals.woodworks.core.data.server;

import com.google.common.collect.Maps;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeSerializers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.DataGenerator;
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

import java.util.function.Consumer;

import static com.teamabnormals.woodworks.core.WoodworksConfig.COMMON;

public class WoodworksRecipeProvider extends RecipeProvider implements IConditionBuilder {

	public WoodworksRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		this.conditionalRecipe(consumer, config(COMMON.sawmill, "sawmill"), ShapedRecipeBuilder.shaped(WoodworksBlocks.SAWMILL.get()).define('I', Tags.Items.INGOTS_IRON).define('#', ItemTags.PLANKS).define('S', ItemTags.WOODEN_SLABS).pattern("#I").pattern("# ").pattern("#S").unlockedBy("has_planks", has(ItemTags.PLANKS)));
		this.conditionalRecipe(consumer, config(COMMON.woodenBookshelves, "wooden_bookshelves", true), ShapedRecipeBuilder.shaped(Blocks.BOOKSHELF).define('#', ItemTags.PLANKS).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").unlockedBy("has_book", has(Items.BOOK)));
		this.conditionalRecipe(consumer, config(COMMON.woodenLadders, "wooden_ladders", true), ShapedRecipeBuilder.shaped(Blocks.LADDER, 3).define('#', Items.STICK).pattern("# #").pattern("###").pattern("# #").unlockedBy("has_stick", has(Items.STICK)));
		this.conditionalRecipe(consumer, config(COMMON.woodenBeehives, "wooden_beehives", true), ShapedRecipeBuilder.shaped(Blocks.BEEHIVE).define('P', ItemTags.PLANKS).define('H', Items.HONEYCOMB).pattern("PPP").pattern("HHH").pattern("PPP").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)));
		this.conditionalRecipe(consumer, config(COMMON.woodenChests, "wooden_chests", true), ShapedRecipeBuilder.shaped(Blocks.CHEST).define('#', ItemTags.PLANKS).pattern("###").pattern("# #").pattern("###").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])));
		this.conditionalRecipe(consumer, config(COMMON.woodenChests, "wooden_chests", true), ShapelessRecipeBuilder.shapeless(Blocks.TRAPPED_CHEST).requires(Tags.Items.CHESTS_WOODEN).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)));
		this.conditionalRecipe(consumer, config(COMMON.woodenChests, "wooden_chests", false), ShapelessRecipeBuilder.shapeless(Blocks.TRAPPED_CHEST).requires(Blocks.CHEST).requires(Blocks.TRIPWIRE_HOOK).unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)), new ResourceLocation(Woodworks.MOD_ID, "trapped_chest"));
		ShapedRecipeBuilder.shaped(Blocks.LECTERN).define('S', ItemTags.WOODEN_SLABS).define('B', Tags.Items.BOOKSHELVES).pattern("SSS").pattern(" B ").pattern(" S ").unlockedBy("has_book", has(Items.BOOK)).save(consumer);

		this.baseRecipes(consumer, Blocks.OAK_PLANKS, WoodworksBlocks.OAK_BOARDS.get(), Blocks.BOOKSHELF, Blocks.LADDER, Blocks.BEEHIVE, WoodworksBlocks.OAK_CHEST.get(), WoodworksBlocks.OAK_TRAPPED_CHEST.get());
		this.baseRecipes(consumer, Blocks.SPRUCE_PLANKS, WoodworksBlocks.SPRUCE_BOARDS.get(), WoodworksBlocks.SPRUCE_BOOKSHELF.get(), WoodworksBlocks.SPRUCE_LADDER.get(), WoodworksBlocks.SPRUCE_BEEHIVE.get(), WoodworksBlocks.SPRUCE_CHEST.get(), WoodworksBlocks.SPRUCE_TRAPPED_CHEST.get());
		this.baseRecipes(consumer, Blocks.BIRCH_PLANKS, WoodworksBlocks.BIRCH_BOARDS.get(), WoodworksBlocks.BIRCH_BOOKSHELF.get(), WoodworksBlocks.BIRCH_LADDER.get(), WoodworksBlocks.BIRCH_BEEHIVE.get(), WoodworksBlocks.BIRCH_CHEST.get(), WoodworksBlocks.BIRCH_TRAPPED_CHEST.get());
		this.baseRecipes(consumer, Blocks.JUNGLE_PLANKS, WoodworksBlocks.JUNGLE_BOARDS.get(), WoodworksBlocks.JUNGLE_BOOKSHELF.get(), WoodworksBlocks.JUNGLE_LADDER.get(), WoodworksBlocks.JUNGLE_BEEHIVE.get(), WoodworksBlocks.JUNGLE_CHEST.get(), WoodworksBlocks.JUNGLE_TRAPPED_CHEST.get());
		this.baseRecipes(consumer, Blocks.ACACIA_PLANKS, WoodworksBlocks.ACACIA_BOARDS.get(), WoodworksBlocks.ACACIA_BOOKSHELF.get(), WoodworksBlocks.ACACIA_LADDER.get(), WoodworksBlocks.ACACIA_BEEHIVE.get(), WoodworksBlocks.ACACIA_CHEST.get(), WoodworksBlocks.ACACIA_TRAPPED_CHEST.get());
		this.baseRecipes(consumer, Blocks.DARK_OAK_PLANKS, WoodworksBlocks.DARK_OAK_BOARDS.get(), WoodworksBlocks.DARK_OAK_BOOKSHELF.get(), WoodworksBlocks.DARK_OAK_LADDER.get(), WoodworksBlocks.DARK_OAK_BEEHIVE.get(), WoodworksBlocks.DARK_OAK_CHEST.get(), WoodworksBlocks.DARK_OAK_TRAPPED_CHEST.get());
		this.baseRecipes(consumer, Blocks.CRIMSON_PLANKS, WoodworksBlocks.CRIMSON_BOARDS.get(), WoodworksBlocks.CRIMSON_BOOKSHELF.get(), WoodworksBlocks.CRIMSON_LADDER.get(), WoodworksBlocks.CRIMSON_BEEHIVE.get(), WoodworksBlocks.CRIMSON_CHEST.get(), WoodworksBlocks.CRIMSON_TRAPPED_CHEST.get());
		this.baseRecipes(consumer, Blocks.WARPED_PLANKS, WoodworksBlocks.WARPED_BOARDS.get(), WoodworksBlocks.WARPED_BOOKSHELF.get(), WoodworksBlocks.WARPED_LADDER.get(), WoodworksBlocks.WARPED_BEEHIVE.get(), WoodworksBlocks.WARPED_CHEST.get(), WoodworksBlocks.WARPED_TRAPPED_CHEST.get());

		this.leafPile(consumer, Blocks.OAK_LEAVES, WoodworksBlocks.OAK_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.SPRUCE_LEAVES, WoodworksBlocks.SPRUCE_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.BIRCH_LEAVES, WoodworksBlocks.BIRCH_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.JUNGLE_LEAVES, WoodworksBlocks.JUNGLE_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.ACACIA_LEAVES, WoodworksBlocks.ACACIA_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.DARK_OAK_LEAVES, WoodworksBlocks.DARK_OAK_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.AZALEA_LEAVES, WoodworksBlocks.AZALEA_LEAF_PILE.get());
		this.leafPile(consumer, Blocks.FLOWERING_AZALEA_LEAVES, WoodworksBlocks.FLOWERING_AZALEA_LEAF_PILE.get());

		this.sawmillRecipes(consumer, BlockFamilies.OAK_PLANKS, ItemTags.OAK_LOGS, WoodworksBlocks.OAK_BOARDS.get(), Blocks.LADDER);
		this.sawmillRecipes(consumer, BlockFamilies.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS, WoodworksBlocks.SPRUCE_BOARDS.get(), WoodworksBlocks.SPRUCE_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.BIRCH_PLANKS, ItemTags.BIRCH_LOGS, WoodworksBlocks.BIRCH_BOARDS.get(), WoodworksBlocks.BIRCH_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS, WoodworksBlocks.JUNGLE_BOARDS.get(), WoodworksBlocks.JUNGLE_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.ACACIA_PLANKS, ItemTags.ACACIA_LOGS, WoodworksBlocks.ACACIA_BOARDS.get(), WoodworksBlocks.ACACIA_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS, WoodworksBlocks.DARK_OAK_BOARDS.get(), WoodworksBlocks.DARK_OAK_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS, WoodworksBlocks.MANGROVE_BOARDS.get(), WoodworksBlocks.MANGROVE_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS, WoodworksBlocks.CRIMSON_BOARDS.get(), WoodworksBlocks.CRIMSON_LADDER.get());
		this.sawmillRecipes(consumer, BlockFamilies.WARPED_PLANKS, ItemTags.WARPED_STEMS, WoodworksBlocks.WARPED_BOARDS.get(), WoodworksBlocks.WARPED_LADDER.get());
	}

	public void baseRecipes(Consumer<FinishedRecipe> consumer, Block planks, Block boards, Block bookshelf, Block ladder, Block beehive, Block chest, Block trappedChest) {
		String prefix = planks == Blocks.OAK_PLANKS ? "oak_" : "";
		this.conditionalRecipe(consumer, config(COMMON.woodenBoards, "wooden_boards"), ShapedRecipeBuilder.shaped(boards, 3).define('#', planks).pattern("#").pattern("#").pattern("#").group("wooden_boards").unlockedBy(getHasName(planks), has(planks)));
		this.conditionalRecipe(consumer, config(COMMON.woodenBookshelves, "wooden_bookshelves"), ShapedRecipeBuilder.shaped(bookshelf).define('#', planks).define('X', Items.BOOK).pattern("###").pattern("XXX").pattern("###").group("wooden_bookshelf").unlockedBy("has_book", has(Items.BOOK)), new ResourceLocation(Woodworks.MOD_ID, prefix + bookshelf.getRegistryName().getPath()));
		this.conditionalRecipe(consumer, config(COMMON.woodenLadders, "wooden_ladders"), ShapedRecipeBuilder.shaped(ladder, 4).define('#', planks).define('S', Items.STICK).pattern("S S").pattern("S#S").pattern("S S").group("wooden_ladder").unlockedBy("has_stick", has(Items.STICK)), new ResourceLocation(Woodworks.MOD_ID, prefix + ladder.getRegistryName().getPath()));
		this.conditionalRecipe(consumer, config(COMMON.woodenBeehives, "wooden_beehives"), ShapedRecipeBuilder.shaped(beehive).define('#', planks).define('H', Items.HONEYCOMB).pattern("###").pattern("HHH").pattern("###").group("wooden_beehive").unlockedBy("has_honeycomb", has(Items.HONEYCOMB)), new ResourceLocation(Woodworks.MOD_ID, prefix + beehive.getRegistryName().getPath()));
		this.conditionalRecipe(consumer, config(COMMON.woodenChests, "wooden_chests"), ShapedRecipeBuilder.shaped(chest).define('#', planks).pattern("###").pattern("# #").pattern("###").group("wooden_chest").unlockedBy("has_lots_of_items", new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.atLeast(10), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, new ItemPredicate[0])));
		this.conditionalRecipe(consumer, config(COMMON.woodenChests, "wooden_chests"), ShapelessRecipeBuilder.shapeless(trappedChest).requires(chest).requires(Blocks.TRIPWIRE_HOOK).group("wooden_trapped_chest").unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK)));
	}

	public void sawmillRecipes(Consumer<FinishedRecipe> consumer, BlockFamily family, TagKey<Item> logs, Block boards, Block ladder) {
		Block planks = family.getBaseBlock();
		Block button = family.get(BlockFamily.Variant.BUTTON);
		Block door = family.get(BlockFamily.Variant.DOOR);
		Block fence = family.get(BlockFamily.Variant.FENCE);
		Block fenceGate = family.get(BlockFamily.Variant.FENCE_GATE);
		Block pressurePlate = family.get(BlockFamily.Variant.PRESSURE_PLATE);
		Block sign = family.get(BlockFamily.Variant.SIGN);
		Block slab = family.get(BlockFamily.Variant.SLAB);
		Block stairs = family.get(BlockFamily.Variant.STAIRS);
		Block trapdoor = family.get(BlockFamily.Variant.TRAPDOOR);

		this.sawmillRecipe(consumer, logs, planks, 4);
		this.sawmillRecipe(consumer, planks, button, 1);
		this.sawmillRecipe(consumer, logs, button, 4);
		this.sawmillRecipe(consumer, logs, door, 2);
		this.sawmillRecipe(consumer, planks, fence, 1);
		this.sawmillRecipe(consumer, logs, fence, 4);
		this.sawmillRecipe(consumer, logs, fenceGate, 1);
		this.sawmillRecipe(consumer, logs, pressurePlate, 2);
		this.sawmillRecipe(consumer, logs, sign, 2);
		this.sawmillRecipe(consumer, planks, slab, 2);
		this.sawmillRecipe(consumer, logs, slab, 8);
		this.sawmillRecipe(consumer, planks, stairs, 1);
		this.sawmillRecipe(consumer, logs, stairs, 4);
		this.sawmillRecipe(consumer, logs, trapdoor, 2);

		this.conditionalSawmillRecipe(consumer, config(COMMON.woodenBoards, "wooden_boards"), planks, boards);
		this.conditionalSawmillRecipe(consumer, config(COMMON.woodenBoards, "wooden_boards"), logs, boards, 4);
		this.conditionalSawmillRecipe(consumer, config(COMMON.woodenBoards, "wooden_ladders"), planks, ladder, 1, planks == Blocks.OAK_PLANKS);
		this.conditionalSawmillRecipe(consumer, config(COMMON.woodenBoards, "wooden_ladders"), logs, ladder, 4, planks == Blocks.OAK_PLANKS);
	}

	public void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeBuilder recipe) {
		this.conditionalRecipe(consumer, condition, recipe, RecipeBuilder.getDefaultRecipeId(recipe.getResult()));
	}

	public void conditionalRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeBuilder recipe, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, ItemLike input, ItemLike output) {
		conditionalSawmillRecipe(consumer, condition, input, output, 1);
	}

	public void sawmillRecipe(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike output, int count) {
		ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, getConversionRecipeName(output, input) + "_sawing");
		RecipeBuilder recipe = sawmillResultFromBase(output, input, count);
		ConditionalRecipe.builder().addCondition(config(COMMON.sawmill, "sawmill")).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public void sawmillRecipe(Consumer<FinishedRecipe> consumer, TagKey<Item> input, ItemLike output, int count) {
		ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, getConversionRecipeName(output, input) + "_sawing");
		RecipeBuilder recipe = sawmillResultFromBase(output, input, count);
		ConditionalRecipe.builder().addCondition(config(COMMON.sawmill, "sawmill")).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, ItemLike input, ItemLike output, int count) {
		conditionalSawmillRecipe(consumer, condition, input, output, count, false);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, ItemLike input, ItemLike output, int count, boolean doPrefix) {
		String prefix = doPrefix ? "oak_" : "";
		ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, prefix + getConversionRecipeName(output, input) + "_sawing");
		RecipeBuilder recipe = sawmillResultFromBase(output, input, count);
		ConditionalRecipe.builder().addCondition(new AndCondition(config(COMMON.sawmill, "sawmill"), condition)).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, TagKey<Item> input, ItemLike output, int count) {
		conditionalSawmillRecipe(consumer, condition, input, output, count, false);
	}

	public void conditionalSawmillRecipe(Consumer<FinishedRecipe> consumer, ICondition condition, TagKey<Item> input, ItemLike output, int count, boolean doPrefix) {
		String prefix = doPrefix ? "oak_" : "";
		ResourceLocation id = new ResourceLocation(Woodworks.MOD_ID, prefix + getConversionRecipeName(output, input) + "_sawing");
		RecipeBuilder recipe = sawmillResultFromBase(output, input, count);
		ConditionalRecipe.builder().addCondition(new AndCondition(config(COMMON.sawmill, "sawmill"), condition)).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipe.getResult().getItemCategory().getRecipeFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public void leafPile(Consumer<FinishedRecipe> consumer, Block leaves, Block leafPile) {
		this.conditionalRecipe(consumer, config(COMMON.leafPiles, "leaf_piles"), ShapelessRecipeBuilder.shapeless(leafPile, 4).requires(leaves, 1).group("leaf_pile").unlockedBy(getHasName(leaves), has(leaves)));
		this.conditionalRecipe(consumer, config(COMMON.leafPiles, "leaf_piles"), ShapedRecipeBuilder.shaped(leaves, 1).define('#', leafPile).pattern("##").pattern("##").group("leaves").unlockedBy(getHasName(leafPile), has(leafPile)), new ResourceLocation(Woodworks.MOD_ID, leaves.getRegistryName().getPath() + "_from_leaf_piles"));
	}

	public static ConfigValueCondition config(ForgeConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(new ResourceLocation(Woodworks.MOD_ID, "config"), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ForgeConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}

	public static SingleItemRecipeBuilder sawing(Ingredient ingredient, ItemLike item, int count) {
		return new SingleItemRecipeBuilder(WoodworksRecipeSerializers.SAWMILL.get(), ingredient, item, count);
	}

	protected static SingleItemRecipeBuilder sawmillResultFromBase(ItemLike output, ItemLike input, int count) {
		return sawing(Ingredient.of(input), output, count).unlockedBy(getHasName(input), has(input));
	}

	protected static SingleItemRecipeBuilder sawmillResultFromBase(ItemLike output, TagKey<Item> input, int count) {
		return sawing(Ingredient.of(input), output, count).unlockedBy("has_" + input.location().getPath(), has(input));
	}

	protected static String getConversionRecipeName(ItemLike output, TagKey<Item> input) {
		return getItemName(output) + "_from_" + input.location().getPath();
	}
}