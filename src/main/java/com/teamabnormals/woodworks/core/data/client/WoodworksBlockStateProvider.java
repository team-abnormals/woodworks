package com.teamabnormals.woodworks.core.data.client;

import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;


public class WoodworksBlockStateProvider extends BlockStateProvider {

	public WoodworksBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Woodworks.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.boardsBlock(WoodworksBlocks.OAK_BOARDS.get());
		this.leafPileBlock(Blocks.OAK_LEAVES, WoodworksBlocks.OAK_LEAF_PILE.get());
		this.chestBlocks(Blocks.OAK_PLANKS, WoodworksBlocks.OAK_CHEST.get(), WoodworksBlocks.OAK_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.SPRUCE_BOARDS.get());
		this.leafPileBlock(Blocks.SPRUCE_LEAVES, WoodworksBlocks.SPRUCE_LEAF_PILE.get());
		this.bookshelfBlock(Blocks.SPRUCE_PLANKS, WoodworksBlocks.SPRUCE_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.SPRUCE_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.SPRUCE_BEEHIVE.get());
		this.chestBlocks(Blocks.SPRUCE_PLANKS, WoodworksBlocks.SPRUCE_CHEST.get(), WoodworksBlocks.SPRUCE_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.BIRCH_BOARDS.get());
		this.leafPileBlock(Blocks.BIRCH_LEAVES, WoodworksBlocks.BIRCH_LEAF_PILE.get());
		this.bookshelfBlock(Blocks.BIRCH_PLANKS, WoodworksBlocks.BIRCH_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.BIRCH_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.BIRCH_BEEHIVE.get());
		this.chestBlocks(Blocks.BIRCH_PLANKS, WoodworksBlocks.BIRCH_CHEST.get(), WoodworksBlocks.BIRCH_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.JUNGLE_BOARDS.get());
		this.leafPileBlock(Blocks.JUNGLE_LEAVES, WoodworksBlocks.JUNGLE_LEAF_PILE.get());
		this.bookshelfBlock(Blocks.JUNGLE_PLANKS, WoodworksBlocks.JUNGLE_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.JUNGLE_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.JUNGLE_BEEHIVE.get());
		this.chestBlocks(Blocks.JUNGLE_PLANKS, WoodworksBlocks.JUNGLE_CHEST.get(), WoodworksBlocks.JUNGLE_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.ACACIA_BOARDS.get());
		this.leafPileBlock(Blocks.ACACIA_LEAVES, WoodworksBlocks.ACACIA_LEAF_PILE.get());
		this.bookshelfBlock(Blocks.ACACIA_PLANKS, WoodworksBlocks.ACACIA_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.ACACIA_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.ACACIA_BEEHIVE.get());
		this.chestBlocks(Blocks.ACACIA_PLANKS, WoodworksBlocks.ACACIA_CHEST.get(), WoodworksBlocks.ACACIA_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.DARK_OAK_BOARDS.get());
		this.leafPileBlock(Blocks.DARK_OAK_LEAVES, WoodworksBlocks.DARK_OAK_LEAF_PILE.get());
		this.bookshelfBlock(Blocks.DARK_OAK_PLANKS, WoodworksBlocks.DARK_OAK_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.DARK_OAK_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.DARK_OAK_BEEHIVE.get());
		this.chestBlocks(Blocks.DARK_OAK_PLANKS, WoodworksBlocks.DARK_OAK_CHEST.get(), WoodworksBlocks.DARK_OAK_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.MANGROVE_BOARDS.get());
		this.leafPileBlock(Blocks.MANGROVE_LEAVES, WoodworksBlocks.MANGROVE_LEAF_PILE.get());
		this.bookshelfBlock(Blocks.MANGROVE_PLANKS, WoodworksBlocks.MANGROVE_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.MANGROVE_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.MANGROVE_BEEHIVE.get());
		this.chestBlocks(Blocks.MANGROVE_PLANKS, WoodworksBlocks.MANGROVE_CHEST.get(), WoodworksBlocks.MANGROVE_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.CRIMSON_BOARDS.get());
		this.bookshelfBlock(Blocks.CRIMSON_PLANKS, WoodworksBlocks.CRIMSON_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.CRIMSON_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.CRIMSON_BEEHIVE.get());
		this.chestBlocks(Blocks.CRIMSON_PLANKS, WoodworksBlocks.CRIMSON_CHEST.get(), WoodworksBlocks.CRIMSON_TRAPPED_CHEST.get());

		this.boardsBlock(WoodworksBlocks.WARPED_BOARDS.get());
		this.bookshelfBlock(Blocks.WARPED_PLANKS, WoodworksBlocks.WARPED_BOOKSHELF.get());
		this.ladderBlock(WoodworksBlocks.WARPED_LADDER.get());
		this.beehiveBlock(WoodworksBlocks.WARPED_BEEHIVE.get());
		this.chestBlocks(Blocks.WARPED_PLANKS, WoodworksBlocks.WARPED_CHEST.get(), WoodworksBlocks.WARPED_TRAPPED_CHEST.get());

		this.leafPileBlock(Blocks.AZALEA_LEAVES, WoodworksBlocks.AZALEA_LEAF_PILE.get(), false);
		this.leafPileBlock(Blocks.FLOWERING_AZALEA_LEAVES, WoodworksBlocks.FLOWERING_AZALEA_LEAF_PILE.get(), false);
	}

	public void simpleBlockItem(Block block) {
		this.simpleBlockItem(block, new ExistingModelFile(blockTexture(block), this.models().existingFileHelper));
	}

	private void generatedItem(ItemLike item, String type) {
		generatedItem(item, item, type);
	}

	private void generatedItem(ItemLike item, ItemLike texture, String type) {
		itemModels().withExistingParent(ForgeRegistries.ITEMS.getKey(item.asItem()).getPath(), "item/generated").texture("layer0", new ResourceLocation(ForgeRegistries.ITEMS.getKey(texture.asItem()).getNamespace(), type + "/" + ForgeRegistries.ITEMS.getKey(texture.asItem()).getPath()));
	}

	public void boardsBlock(Block boards) {
		ModelFile boardsModel = models().getBuilder(name(boards)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards"))).texture("all", blockTexture(boards));
		ModelFile boardsHorizontalModel = models().getBuilder(name(boards) + "_horizontal").parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards_horizontal"))).texture("all", blockTexture(boards));
		this.getVariantBuilder(boards).partialState().with(RotatedPillarBlock.AXIS, Axis.Y).modelForState().modelFile(boardsModel).addModel().partialState().with(RotatedPillarBlock.AXIS, Axis.Z).modelForState().modelFile(boardsHorizontalModel).addModel().partialState().with(RotatedPillarBlock.AXIS, Axis.X).modelForState().modelFile(boardsHorizontalModel).rotationY(270).addModel();
		this.simpleBlockItem(boards);
	}

	public void leafPileBlock(Block leaves, Block leafPile) {
		this.leafPileBlock(leaves, leafPile, true);
	}

	public void leafPileBlock(Block leaves, Block leafPile, boolean tinted) {
		ModelFile leafPileModel = models().getBuilder(name(leafPile)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/" + (tinted ? "tinted_" : "") + "leaf_pile"))).renderType("cutout").texture("all", blockTexture(leaves));
		MultiPartBlockStateBuilder builder = getMultipartBuilder(leafPile);
		builder.part().modelFile(leafPileModel).rotationX(270).uvLock(true).addModel().condition(BlockStateProperties.UP, true);
		builder.part().modelFile(leafPileModel).rotationX(270).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).addModel().condition(BlockStateProperties.NORTH, true);
		builder.part().modelFile(leafPileModel).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.WEST, true);
		builder.part().modelFile(leafPileModel).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.SOUTH, true);
		builder.part().modelFile(leafPileModel).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.EAST, true);
		builder.part().modelFile(leafPileModel).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationX(90).uvLock(true).addModel().condition(BlockStateProperties.DOWN, true);
		builder.part().modelFile(leafPileModel).rotationX(90).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		this.generatedItem(leafPile, leaves, "block");
	}

	public void bookshelfBlock(Block planks, Block bookshelf) {
		this.simpleBlock(bookshelf, this.models().cubeColumn(name(bookshelf), blockTexture(bookshelf), blockTexture(planks)));
		this.simpleBlockItem(bookshelf);
	}

	public void ladderBlock(Block ladder) {
		this.horizontalBlock(ladder, models().withExistingParent(name(ladder), "block/ladder").texture("particle", blockTexture(ladder)).renderType("cutout").texture("texture", blockTexture(ladder)));
		this.generatedItem(ladder, "block");
	}

	public void beehiveBlock(Block block) {
		ModelFile beehive = models().orientableWithBottom(name(block), suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_front"), suffix(blockTexture(block), "_end"), suffix(blockTexture(block), "_end")).texture("particle", suffix(blockTexture(block), "_side"));
		ModelFile beehiveHoney = models().orientableWithBottom(name(block) + "_honey", suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_front_honey"), suffix(blockTexture(block), "_end"), suffix(blockTexture(block), "_end")).texture("particle", suffix(blockTexture(block), "_side"));
		this.horizontalBlock(block, (state -> state.getValue(BlockStateProperties.LEVEL_HONEY) == 5 ? beehiveHoney : beehive));
		this.simpleBlockItem(block);
	}

	public void chestBlocks(Block planks, BlueprintChestBlock chest, BlueprintTrappedChestBlock trappedChest) {
		this.simpleBlock(chest, particle(chest, blockTexture(planks)));
		this.simpleBlock(trappedChest, particle(chest, blockTexture(planks)));
		this.simpleBlockItem(chest, new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "item/template_chest")));
		this.simpleBlockItem(trappedChest, new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "item/template_chest")));
	}

	public ModelFile particle(Block block, ResourceLocation texture) {
		return this.models().getBuilder(name(block)).texture("particle", texture);
	}

	private String name(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	private ResourceLocation prefix(String prefix, ResourceLocation rl) {
		return new ResourceLocation(rl.getNamespace(), prefix + rl.getPath());
	}

	private ResourceLocation suffix(ResourceLocation rl, String suffix) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
	}
}