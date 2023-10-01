package com.teamabnormals.woodworks.core.data.client;

import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;


public class WoodworksBlockStateProvider extends BlockStateProvider {
	public static final String[] DEFAULT_BOOKSHELF_POSITIONS = new String[]{"top_left", "top_mid", "top_right", "bottom_left", "bottom_mid", "bottom_right"};
	public static final String[] ALTERNATE_BOOKSHELF_POSITIONS = new String[]{"top_left", "top_right", "mid_left", "mid_right", "bottom_left", "bottom_right"};
	public static final String[] CHERRY_BOOKSHELF_POSITIONS = new String[]{"far_left", "mid_left", "top_mid", "bottom_mid", "mid_right", "far_right"};

	public WoodworksBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, Woodworks.MOD_ID, fileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.sawmillBlock(SAWMILL.get());

		this.boardsBlock(OAK_BOARDS.get());
		this.leafPileBlock(Blocks.OAK_LEAVES, OAK_LEAF_PILE.get());
		this.chestBlocks(Blocks.OAK_PLANKS, OAK_CHEST.get(), TRAPPED_OAK_CHEST.get());

		this.boardsBlock(SPRUCE_BOARDS.get());
		this.leafPileBlock(Blocks.SPRUCE_LEAVES, SPRUCE_LEAF_PILE.get());
		this.bookshelfBlocks(Blocks.SPRUCE_PLANKS, SPRUCE_BOOKSHELF.get(), CHISELED_SPRUCE_BOOKSHELF.get(), ALTERNATE_BOOKSHELF_POSITIONS, Woodworks.MOD_ID + ":block/chiseled_spruce");
		this.ladderBlock(SPRUCE_LADDER.get());
		this.beehiveBlock(SPRUCE_BEEHIVE.get());
		this.chestBlocks(Blocks.SPRUCE_PLANKS, SPRUCE_CHEST.get(), TRAPPED_SPRUCE_CHEST.get());

		this.boardsBlock(BIRCH_BOARDS.get());
		this.leafPileBlock(Blocks.BIRCH_LEAVES, BIRCH_LEAF_PILE.get());
		this.bookshelfBlocks(Blocks.BIRCH_PLANKS, BIRCH_BOOKSHELF.get(), CHISELED_BIRCH_BOOKSHELF.get());
		this.ladderBlock(BIRCH_LADDER.get());
		this.beehiveBlock(BIRCH_BEEHIVE.get());
		this.chestBlocks(Blocks.BIRCH_PLANKS, BIRCH_CHEST.get(), TRAPPED_BIRCH_CHEST.get());

		this.boardsBlock(JUNGLE_BOARDS.get());
		this.leafPileBlock(Blocks.JUNGLE_LEAVES, JUNGLE_LEAF_PILE.get());
		this.bookshelfBlocks(Blocks.JUNGLE_PLANKS, JUNGLE_BOOKSHELF.get(), CHISELED_JUNGLE_BOOKSHELF.get());
		this.ladderBlock(JUNGLE_LADDER.get());
		this.beehiveBlock(JUNGLE_BEEHIVE.get());
		this.chestBlocks(Blocks.JUNGLE_PLANKS, JUNGLE_CHEST.get(), TRAPPED_JUNGLE_CHEST.get());

		this.boardsBlock(ACACIA_BOARDS.get());
		this.leafPileBlock(Blocks.ACACIA_LEAVES, ACACIA_LEAF_PILE.get());
		this.bookshelfBlocks(Blocks.ACACIA_PLANKS, ACACIA_BOOKSHELF.get(), CHISELED_ACACIA_BOOKSHELF.get());
		this.ladderBlock(ACACIA_LADDER.get());
		this.beehiveBlock(ACACIA_BEEHIVE.get());
		this.chestBlocks(Blocks.ACACIA_PLANKS, ACACIA_CHEST.get(), TRAPPED_ACACIA_CHEST.get());

		this.boardsBlock(DARK_OAK_BOARDS.get());
		this.leafPileBlock(Blocks.DARK_OAK_LEAVES, DARK_OAK_LEAF_PILE.get());
		this.bookshelfBlocks(Blocks.DARK_OAK_PLANKS, DARK_OAK_BOOKSHELF.get(), CHISELED_DARK_OAK_BOOKSHELF.get(), DEFAULT_BOOKSHELF_POSITIONS, Woodworks.MOD_ID + ":block/chiseled_dark_oak");
		this.ladderBlock(DARK_OAK_LADDER.get());
		this.beehiveBlock(DARK_OAK_BEEHIVE.get());
		this.chestBlocks(Blocks.DARK_OAK_PLANKS, DARK_OAK_CHEST.get(), TRAPPED_DARK_OAK_CHEST.get());

		this.boardsBlock(MANGROVE_BOARDS.get());
		this.leafPileBlock(Blocks.MANGROVE_LEAVES, MANGROVE_LEAF_PILE.get());
		this.bookshelfBlocks(Blocks.MANGROVE_PLANKS, MANGROVE_BOOKSHELF.get(), CHISELED_MANGROVE_BOOKSHELF.get(), ALTERNATE_BOOKSHELF_POSITIONS, Woodworks.MOD_ID + ":block/chiseled_mangrove");
		this.ladderBlock(MANGROVE_LADDER.get());
		this.beehiveBlock(MANGROVE_BEEHIVE.get());
		this.chestBlocks(Blocks.MANGROVE_PLANKS, MANGROVE_CHEST.get(), TRAPPED_MANGROVE_CHEST.get());

		this.boardsBlock(CHERRY_BOARDS.get());
		this.leafPileBlock(Blocks.CHERRY_LEAVES, CHERRY_LEAF_PILE.get());
		this.bookshelfBlocks(Blocks.CHERRY_PLANKS, CHERRY_BOOKSHELF.get(), CHISELED_CHERRY_BOOKSHELF.get(), CHERRY_BOOKSHELF_POSITIONS, Woodworks.MOD_ID + ":block/chiseled_cherry");
		this.ladderBlock(CHERRY_LADDER.get());
		this.beehiveBlock(CHERRY_BEEHIVE.get());
		this.chestBlocks(Blocks.CHERRY_PLANKS, CHERRY_CHEST.get(), TRAPPED_CHERRY_CHEST.get());

		this.bookshelfBlocks(Blocks.BAMBOO_PLANKS, BAMBOO_BOOKSHELF.get(), CHISELED_BAMBOO_BOOKSHELF.get(), DEFAULT_BOOKSHELF_POSITIONS, Woodworks.MOD_ID + ":block/chiseled_bamboo");
		this.ladderBlock(BAMBOO_LADDER.get());
		this.beehiveBlock(BAMBOO_BEEHIVE.get());

		this.boardsBlock(CRIMSON_BOARDS.get());
		this.bookshelfBlocks(Blocks.CRIMSON_PLANKS, CRIMSON_BOOKSHELF.get(), CHISELED_CRIMSON_BOOKSHELF.get());
		this.ladderBlock(CRIMSON_LADDER.get());
		this.beehiveBlock(CRIMSON_BEEHIVE.get());
		this.chestBlocks(Blocks.CRIMSON_PLANKS, CRIMSON_CHEST.get(), TRAPPED_CRIMSON_CHEST.get());

		this.boardsBlock(WARPED_BOARDS.get());
		this.bookshelfBlocks(Blocks.WARPED_PLANKS, WARPED_BOOKSHELF.get(), CHISELED_WARPED_BOOKSHELF.get());
		this.ladderBlock(WARPED_LADDER.get());
		this.beehiveBlock(WARPED_BEEHIVE.get());
		this.chestBlocks(Blocks.WARPED_PLANKS, WARPED_CHEST.get(), TRAPPED_WARPED_CHEST.get());

		this.leafPileBlock(Blocks.AZALEA_LEAVES, AZALEA_LEAF_PILE.get(), false);
		this.leafPileBlock(Blocks.FLOWERING_AZALEA_LEAVES, FLOWERING_AZALEA_LEAF_PILE.get(), false);
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

	public void sawmillBlock(Block sawmill) {
		this.horizontalBlock(sawmill, new UncheckedModelFile(new ResourceLocation(Woodworks.MOD_ID, "block/sawmill")));
		this.simpleBlockItem(sawmill);
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

	public void bookshelfBlocks(Block planks, Block bookshelf, Block chiseledBookshelf) {
		this.bookshelfBlocks(planks, bookshelf, chiseledBookshelf, DEFAULT_BOOKSHELF_POSITIONS, "block/template_chiseled");
	}

	public void bookshelfBlocks(Block planks, Block bookshelf, Block chiseledBookshelf, String[] parts, String parent) {
		this.simpleBlock(bookshelf, this.models().cubeColumn(name(bookshelf), blockTexture(bookshelf), blockTexture(planks)));
		this.simpleBlockItem(bookshelf);

		BlockModelBuilder model = this.models().withExistingParent(name(chiseledBookshelf), "block/chiseled_bookshelf").texture("top", blockTexture(chiseledBookshelf) + "_top").texture("side", blockTexture(chiseledBookshelf) + "_side");
		MultiPartBlockStateBuilder builder = getMultipartBuilder(chiseledBookshelf);
		for (Direction direction : Direction.Plane.HORIZONTAL) {
			int rotation = (int) (direction.toYRot() + 180) % 360;
			builder.part().modelFile(model).rotationY(rotation).uvLock(true).addModel().condition(HorizontalDirectionalBlock.FACING, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[0], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_0_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[1], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_1_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[2], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_2_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[3], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_3_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[4], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_4_OCCUPIED, direction);
			chiseledBookshelfParts(builder, chiseledBookshelf, parts[5], parent, BlockStateProperties.CHISELED_BOOKSHELF_SLOT_5_OCCUPIED, direction);
		}

		this.simpleBlockItem(chiseledBookshelf, this.models()
				.withExistingParent(name(chiseledBookshelf) + "_inventory", "block/chiseled_bookshelf_inventory")
				.texture("top", blockTexture(chiseledBookshelf) + "_top")
				.texture("side", blockTexture(chiseledBookshelf) + "_side")
				.texture("front", blockTexture(chiseledBookshelf) + "_empty"));
	}

	public void chiseledBookshelfParts(MultiPartBlockStateBuilder builder, Block chiseledBookshelf, String part, String parent, BooleanProperty property, Direction direction) {
		int rotation = (int) (direction.toYRot() + 180) % 360;
		builder.part().modelFile(this.models()
						.withExistingParent(name(chiseledBookshelf) + "_occupied_slot_" + part, parent + "_bookshelf_slot_" + part)
						.texture("texture", blockTexture(chiseledBookshelf) + "_occupied"))
				.rotationY(rotation).uvLock(true)
				.addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(property, true);
		builder.part().modelFile(this.models()
						.withExistingParent(name(chiseledBookshelf) + "_empty_slot_" + part, parent + "_bookshelf_slot_" + part)
						.texture("texture", blockTexture(chiseledBookshelf) + "_empty"))
				.rotationY(rotation).uvLock(true)
				.addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(property, false);
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