package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.woodworks.common.block.*;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.helper.WoodworksBlockSubRegistryHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class WoodworksBlocks {
	public static final WoodworksBlockSubRegistryHelper HELPER = Woodworks.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> SAWMILL = HELPER.createBlock("sawmill", () -> new SawmillBlock(WoodworksProperties.SAWMILL));

	public static final RegistryObject<Block> OAK_BOARDS = HELPER.createFuelBlock("oak_boards", () -> new RotatedPillarBlock(WoodworksProperties.OAK_WOOD.planks()), 300);
	public static final RegistryObject<Block> SPRUCE_BOARDS = HELPER.createFuelBlock("spruce_boards", () -> new RotatedPillarBlock(WoodworksProperties.SPRUCE_WOOD.planks()), 300);
	public static final RegistryObject<Block> BIRCH_BOARDS = HELPER.createFuelBlock("birch_boards", () -> new RotatedPillarBlock(WoodworksProperties.BIRCH_WOOD.planks()), 300);
	public static final RegistryObject<Block> JUNGLE_BOARDS = HELPER.createFuelBlock("jungle_boards", () -> new RotatedPillarBlock(WoodworksProperties.JUNGLE_WOOD.planks()), 300);
	public static final RegistryObject<Block> ACACIA_BOARDS = HELPER.createFuelBlock("acacia_boards", () -> new RotatedPillarBlock(WoodworksProperties.ACACIA_WOOD.planks()), 300);
	public static final RegistryObject<Block> DARK_OAK_BOARDS = HELPER.createFuelBlock("dark_oak_boards", () -> new RotatedPillarBlock(WoodworksProperties.DARK_OAK_WOOD.planks()), 300);
	public static final RegistryObject<Block> MANGROVE_BOARDS = HELPER.createFuelBlock("mangrove_boards", () -> new RotatedPillarBlock(WoodworksProperties.MANGROVE_WOOD.planks()), 300);
	public static final RegistryObject<Block> CHERRY_BOARDS = HELPER.createFuelBlock("cherry_boards", () -> new RotatedPillarBlock(WoodworksProperties.CHERRY_WOOD.planks()), 300);
	public static final RegistryObject<Block> CRIMSON_BOARDS = HELPER.createBlock("crimson_boards", () -> new RotatedPillarBlock(WoodworksProperties.CRIMSON_STEM.planks()));
	public static final RegistryObject<Block> WARPED_BOARDS = HELPER.createBlock("warped_boards", () -> new RotatedPillarBlock(WoodworksProperties.WARPED_STEM.planks()));

	public static final RegistryObject<Block> OAK_LEAF_PILE = HELPER.createBlock("oak_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.OAK_WOOD.leafPile()));
	public static final RegistryObject<Block> SPRUCE_LEAF_PILE = HELPER.createBlock("spruce_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.SPRUCE_WOOD.leafPile()));
	public static final RegistryObject<Block> BIRCH_LEAF_PILE = HELPER.createBlock("birch_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.BIRCH_WOOD.leafPile()));
	public static final RegistryObject<Block> JUNGLE_LEAF_PILE = HELPER.createBlock("jungle_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.JUNGLE_WOOD.leafPile()));
	public static final RegistryObject<Block> ACACIA_LEAF_PILE = HELPER.createBlock("acacia_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.ACACIA_WOOD.leafPile()));
	public static final RegistryObject<Block> DARK_OAK_LEAF_PILE = HELPER.createBlock("dark_oak_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.DARK_OAK_WOOD.leafPile()));
	public static final RegistryObject<Block> MANGROVE_LEAF_PILE = HELPER.createBlock("mangrove_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.MANGROVE_WOOD.leafPile()));
	public static final RegistryObject<Block> CHERRY_LEAF_PILE = HELPER.createBlock("cherry_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.CHERRY_WOOD.leafPile()));
	public static final RegistryObject<Block> AZALEA_LEAF_PILE = HELPER.createBlock("azalea_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.AZALEA_WOOD.leafPile()));
	public static final RegistryObject<Block> FLOWERING_AZALEA_LEAF_PILE = HELPER.createBlock("flowering_azalea_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.AZALEA_WOOD.leafPile()));

	public static final RegistryObject<Block> SPRUCE_BOOKSHELF = HELPER.createFuelBlock("spruce_bookshelf", () -> new Block(WoodworksProperties.SPRUCE_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> BIRCH_BOOKSHELF = HELPER.createFuelBlock("birch_bookshelf", () -> new Block(WoodworksProperties.BIRCH_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> JUNGLE_BOOKSHELF = HELPER.createFuelBlock("jungle_bookshelf", () -> new Block(WoodworksProperties.JUNGLE_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> ACACIA_BOOKSHELF = HELPER.createFuelBlock("acacia_bookshelf", () -> new Block(WoodworksProperties.ACACIA_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> DARK_OAK_BOOKSHELF = HELPER.createFuelBlock("dark_oak_bookshelf", () -> new Block(WoodworksProperties.DARK_OAK_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> MANGROVE_BOOKSHELF = HELPER.createFuelBlock("mangrove_bookshelf", () -> new Block(WoodworksProperties.MANGROVE_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHERRY_BOOKSHELF = HELPER.createFuelBlock("cherry_bookshelf", () -> new Block(WoodworksProperties.CHERRY_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> BAMBOO_BOOKSHELF = HELPER.createFuelBlock("bamboo_bookshelf", () -> new Block(WoodworksProperties.BAMBOO_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CRIMSON_BOOKSHELF = HELPER.createBlock("crimson_bookshelf", () -> new Block(WoodworksProperties.CRIMSON_STEM.bookshelf()));
	public static final RegistryObject<Block> WARPED_BOOKSHELF = HELPER.createBlock("warped_bookshelf", () -> new Block(WoodworksProperties.WARPED_STEM.bookshelf()));

	public static final RegistryObject<Block> CHISELED_SPRUCE_BOOKSHELF = HELPER.createFuelBlock("chiseled_spruce_bookshelf", () -> new ChiseledSpruceBookShelfBlock(WoodworksProperties.SPRUCE_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_BIRCH_BOOKSHELF = HELPER.createFuelBlock("chiseled_birch_bookshelf", () -> new ChiseledBookShelfBlock(WoodworksProperties.BIRCH_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_JUNGLE_BOOKSHELF = HELPER.createFuelBlock("chiseled_jungle_bookshelf", () -> new ChiseledBookShelfBlock(WoodworksProperties.JUNGLE_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_ACACIA_BOOKSHELF = HELPER.createFuelBlock("chiseled_acacia_bookshelf", () -> new ChiseledBookShelfBlock(WoodworksProperties.ACACIA_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_DARK_OAK_BOOKSHELF = HELPER.createFuelBlock("chiseled_dark_oak_bookshelf", () -> new ChiseledDarkOakBookShelfBlock(WoodworksProperties.DARK_OAK_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_MANGROVE_BOOKSHELF = HELPER.createFuelBlock("chiseled_mangrove_bookshelf", () -> new ChiseledMangroveBookShelfBlock(WoodworksProperties.MANGROVE_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_CHERRY_BOOKSHELF = HELPER.createFuelBlock("chiseled_cherry_bookshelf", () -> new ChiseledCherryBookShelfBlock(WoodworksProperties.CHERRY_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_BAMBOO_BOOKSHELF = HELPER.createFuelBlock("chiseled_bamboo_bookshelf", () -> new ChiseledBambooBookShelfBlock(WoodworksProperties.BAMBOO_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_CRIMSON_BOOKSHELF = HELPER.createBlock("chiseled_crimson_bookshelf", () -> new ChiseledBookShelfBlock(WoodworksProperties.CRIMSON_STEM.bookshelf()));
	public static final RegistryObject<Block> CHISELED_WARPED_BOOKSHELF = HELPER.createBlock("chiseled_warped_bookshelf", () -> new ChiseledBookShelfBlock(WoodworksProperties.WARPED_STEM.bookshelf()));

	public static final RegistryObject<Block> SPRUCE_LADDER = HELPER.createFuelBlock("spruce_ladder", () -> new LadderBlock(WoodworksProperties.SPRUCE_WOOD.ladder()), 300);
	public static final RegistryObject<Block> BIRCH_LADDER = HELPER.createFuelBlock("birch_ladder", () -> new LadderBlock(WoodworksProperties.BIRCH_WOOD.ladder()), 300);
	public static final RegistryObject<Block> JUNGLE_LADDER = HELPER.createFuelBlock("jungle_ladder", () -> new LadderBlock(WoodworksProperties.JUNGLE_WOOD.ladder()), 300);
	public static final RegistryObject<Block> ACACIA_LADDER = HELPER.createFuelBlock("acacia_ladder", () -> new LadderBlock(WoodworksProperties.ACACIA_WOOD.ladder()), 300);
	public static final RegistryObject<Block> DARK_OAK_LADDER = HELPER.createFuelBlock("dark_oak_ladder", () -> new LadderBlock(WoodworksProperties.DARK_OAK_WOOD.ladder()), 300);
	public static final RegistryObject<Block> MANGROVE_LADDER = HELPER.createFuelBlock("mangrove_ladder", () -> new LadderBlock(WoodworksProperties.MANGROVE_WOOD.ladder()), 300);
	public static final RegistryObject<Block> CHERRY_LADDER = HELPER.createFuelBlock("cherry_ladder", () -> new LadderBlock(WoodworksProperties.CHERRY_WOOD.ladder()), 300);
	public static final RegistryObject<Block> BAMBOO_LADDER = HELPER.createFuelBlock("bamboo_ladder", () -> new LadderBlock(WoodworksProperties.BAMBOO_WOOD.ladder()), 300);
	public static final RegistryObject<Block> CRIMSON_LADDER = HELPER.createBlock("crimson_ladder", () -> new LadderBlock(WoodworksProperties.CRIMSON_STEM.ladder()));
	public static final RegistryObject<Block> WARPED_LADDER = HELPER.createBlock("warped_ladder", () -> new LadderBlock(WoodworksProperties.WARPED_STEM.ladder()));

	public static final RegistryObject<Block> SPRUCE_BEEHIVE = HELPER.createBlock("spruce_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.SPRUCE_WOOD.beehive()));
	public static final RegistryObject<Block> BIRCH_BEEHIVE = HELPER.createBlock("birch_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.BIRCH_WOOD.beehive()));
	public static final RegistryObject<Block> JUNGLE_BEEHIVE = HELPER.createBlock("jungle_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.JUNGLE_WOOD.beehive()));
	public static final RegistryObject<Block> ACACIA_BEEHIVE = HELPER.createBlock("acacia_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.ACACIA_WOOD.beehive()));
	public static final RegistryObject<Block> DARK_OAK_BEEHIVE = HELPER.createBlock("dark_oak_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.DARK_OAK_WOOD.beehive()));
	public static final RegistryObject<Block> MANGROVE_BEEHIVE = HELPER.createBlock("mangrove_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.MANGROVE_WOOD.beehive()));
	public static final RegistryObject<Block> CHERRY_BEEHIVE = HELPER.createBlock("cherry_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.CHERRY_WOOD.beehive()));
	public static final RegistryObject<Block> BAMBOO_BEEHIVE = HELPER.createBlock("bamboo_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.BAMBOO_WOOD.beehive()));
	public static final RegistryObject<Block> CRIMSON_BEEHIVE = HELPER.createBlock("crimson_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.CRIMSON_STEM.beehive()));
	public static final RegistryObject<Block> WARPED_BEEHIVE = HELPER.createBlock("warped_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.WARPED_STEM.beehive()));

	public static final RegistryObject<BlueprintChestBlock> OAK_CHEST = HELPER.createChestBlock("oak", WoodworksProperties.OAK_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> SPRUCE_CHEST = HELPER.createChestBlock("spruce", WoodworksProperties.SPRUCE_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> BIRCH_CHEST = HELPER.createChestBlock("birch", WoodworksProperties.BIRCH_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> JUNGLE_CHEST = HELPER.createChestBlock("jungle", WoodworksProperties.JUNGLE_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> ACACIA_CHEST = HELPER.createChestBlock("acacia", WoodworksProperties.ACACIA_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> DARK_OAK_CHEST = HELPER.createChestBlock("dark_oak", WoodworksProperties.DARK_OAK_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> MANGROVE_CHEST = HELPER.createChestBlock("mangrove", WoodworksProperties.MANGROVE_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> CHERRY_CHEST = HELPER.createChestBlock("cherry", WoodworksProperties.CHERRY_WOOD.chest());
	public static final RegistryObject<BlueprintChestBlock> CRIMSON_CHEST = HELPER.createNonFuelChestBlock("crimson", WoodworksProperties.CRIMSON_STEM.chest());
	public static final RegistryObject<BlueprintChestBlock> WARPED_CHEST = HELPER.createNonFuelChestBlock("warped", WoodworksProperties.WARPED_STEM.chest());

	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_OAK_CHEST = HELPER.createTrappedChestBlock("oak", WoodworksProperties.OAK_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_SPRUCE_CHEST = HELPER.createTrappedChestBlock("spruce", WoodworksProperties.SPRUCE_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_BIRCH_CHEST = HELPER.createTrappedChestBlock("birch", WoodworksProperties.BIRCH_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_JUNGLE_CHEST = HELPER.createTrappedChestBlock("jungle", WoodworksProperties.JUNGLE_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_ACACIA_CHEST = HELPER.createTrappedChestBlock("acacia", WoodworksProperties.ACACIA_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_DARK_OAK_CHEST = HELPER.createTrappedChestBlock("dark_oak", WoodworksProperties.DARK_OAK_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_MANGROVE_CHEST = HELPER.createTrappedChestBlock("mangrove", WoodworksProperties.MANGROVE_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_CHERRY_CHEST = HELPER.createTrappedChestBlock("cherry", WoodworksProperties.CHERRY_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_CRIMSON_CHEST = HELPER.createNonFuelTrappedChestBlock("crimson", WoodworksProperties.CRIMSON_STEM.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_WARPED_CHEST = HELPER.createNonFuelTrappedChestBlock("warped", WoodworksProperties.WARPED_STEM.chest());

	public static final class WoodworksProperties {
		public static final BlockBehaviour.Properties SAWMILL = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.5F);

		public static final WoodSetProperties OAK_WOOD = WoodSetProperties.builder(MapColor.WOOD).build();
		public static final WoodSetProperties SPRUCE_WOOD = WoodSetProperties.builder(MapColor.PODZOL).build();
		public static final WoodSetProperties BIRCH_WOOD = WoodSetProperties.builder(MapColor.SAND).build();
		public static final WoodSetProperties JUNGLE_WOOD = WoodSetProperties.builder(MapColor.DIRT).build();
		public static final WoodSetProperties ACACIA_WOOD = WoodSetProperties.builder(MapColor.COLOR_ORANGE).build();
		public static final WoodSetProperties DARK_OAK_WOOD = WoodSetProperties.builder(MapColor.COLOR_BROWN).build();
		public static final WoodSetProperties MANGROVE_WOOD = WoodSetProperties.builder(MapColor.COLOR_RED).build();
		public static final WoodSetProperties CHERRY_WOOD = WoodSetProperties.builder(MapColor.TERRACOTTA_WHITE).sound(SoundType.CHERRY_WOOD).logSound(SoundType.CHERRY_WOOD).leavesColor(MapColor.COLOR_PINK).leavesSound(SoundType.CHERRY_LEAVES).build();
		public static final WoodSetProperties BAMBOO_WOOD = WoodSetProperties.builder(MapColor.COLOR_YELLOW).sound(SoundType.BAMBOO_WOOD).build();
		public static final WoodSetProperties CRIMSON_STEM = WoodSetProperties.builder(MapColor.CRIMSON_STEM).build();
		public static final WoodSetProperties WARPED_STEM = WoodSetProperties.builder(MapColor.WARPED_STEM).build();
		public static final WoodSetProperties AZALEA_WOOD = WoodSetProperties.builder(MapColor.TERRACOTTA_PURPLE).leavesSound(SoundType.AZALEA_LEAVES).build();
	}

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(Woodworks.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItemsAfter(of(Items.OAK_PLANKS), OAK_BOARDS)
				.addItemsAfter(of(Items.SPRUCE_PLANKS), SPRUCE_BOARDS)
				.addItemsAfter(of(Items.BIRCH_PLANKS), BIRCH_BOARDS)
				.addItemsAfter(of(Items.JUNGLE_PLANKS), JUNGLE_BOARDS)
				.addItemsAfter(of(Items.ACACIA_PLANKS), ACACIA_BOARDS)
				.addItemsAfter(of(Items.DARK_OAK_PLANKS), DARK_OAK_BOARDS)
				.addItemsAfter(of(Items.MANGROVE_PLANKS), MANGROVE_BOARDS)
				.addItemsAfter(of(Items.CHERRY_PLANKS), CHERRY_BOARDS)
				.addItemsAfter(of(Items.CRIMSON_PLANKS), CRIMSON_BOARDS)
				.addItemsAfter(of(Items.WARPED_PLANKS), WARPED_BOARDS)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Items.OAK_LEAVES), OAK_LEAF_PILE)
				.addItemsAfter(of(Items.SPRUCE_LEAVES), SPRUCE_LEAF_PILE)
				.addItemsAfter(of(Items.BIRCH_LEAVES), BIRCH_LEAF_PILE)
				.addItemsAfter(of(Items.JUNGLE_LEAVES), JUNGLE_LEAF_PILE)
				.addItemsAfter(of(Items.ACACIA_LEAVES), ACACIA_LEAF_PILE)
				.addItemsAfter(of(Items.DARK_OAK_LEAVES), DARK_OAK_LEAF_PILE)
				.addItemsAfter(of(Items.MANGROVE_LEAVES), MANGROVE_LEAF_PILE)
				.addItemsAfter(of(Items.CHERRY_LEAVES), CHERRY_LEAF_PILE)
				.addItemsAfter(of(Items.AZALEA_LEAVES), AZALEA_LEAF_PILE)
				.addItemsAfter(of(Items.FLOWERING_AZALEA_LEAVES), FLOWERING_AZALEA_LEAF_PILE)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsAfter(of(Items.STONECUTTER), SAWMILL)
				.addItemsAfter(of(Items.LADDER), SPRUCE_LADDER, BIRCH_LADDER, JUNGLE_LADDER, ACACIA_LADDER, DARK_OAK_LADDER, MANGROVE_LADDER, CHERRY_LADDER, BAMBOO_LADDER, CRIMSON_LADDER, WARPED_LADDER)
				.addItemsAfter(of(Items.BEEHIVE), SPRUCE_BEEHIVE, BIRCH_BEEHIVE, JUNGLE_BEEHIVE, ACACIA_BEEHIVE, DARK_OAK_BEEHIVE, MANGROVE_BEEHIVE, CHERRY_BEEHIVE, BAMBOO_BEEHIVE, CRIMSON_BEEHIVE, WARPED_BEEHIVE)
				.addItemsAfter(of(Items.CHISELED_BOOKSHELF), SPRUCE_BOOKSHELF, CHISELED_SPRUCE_BOOKSHELF, BIRCH_BOOKSHELF, CHISELED_BIRCH_BOOKSHELF, JUNGLE_BOOKSHELF, CHISELED_JUNGLE_BOOKSHELF, ACACIA_BOOKSHELF, CHISELED_ACACIA_BOOKSHELF, DARK_OAK_BOOKSHELF, CHISELED_DARK_OAK_BOOKSHELF, MANGROVE_BOOKSHELF, CHISELED_MANGROVE_BOOKSHELF, CHERRY_BOOKSHELF, CHISELED_CHERRY_BOOKSHELF, BAMBOO_BOOKSHELF, CHISELED_BAMBOO_BOOKSHELF, CRIMSON_BOOKSHELF, CHISELED_CRIMSON_BOOKSHELF, WARPED_BOOKSHELF, CHISELED_WARPED_BOOKSHELF)
				.addItemsAfter(of(Items.CHEST), OAK_CHEST, SPRUCE_CHEST, BIRCH_CHEST, JUNGLE_CHEST, ACACIA_CHEST, DARK_OAK_CHEST, MANGROVE_CHEST, CHERRY_CHEST, CRIMSON_CHEST, WARPED_CHEST)
				.tab(REDSTONE_BLOCKS)
				.addItemsAfter(of(Items.TRAPPED_CHEST), TRAPPED_OAK_CHEST, TRAPPED_SPRUCE_CHEST, TRAPPED_BIRCH_CHEST, TRAPPED_JUNGLE_CHEST, TRAPPED_ACACIA_CHEST, TRAPPED_DARK_OAK_CHEST, TRAPPED_MANGROVE_CHEST, TRAPPED_CHERRY_CHEST, TRAPPED_CRIMSON_CHEST, TRAPPED_WARPED_CHEST);
	}
}
