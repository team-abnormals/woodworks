package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintLadderBlock;
import com.teamabnormals.blueprint.common.block.BookshelfBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.helper.WoodworksBlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = Woodworks.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class WoodworksBlocks {
	public static final WoodworksBlockSubRegistryHelper HELPER = Woodworks.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> OAK_BOARDS = HELPER.createFuelBlock("oak_boards", () -> new RotatedPillarBlock(WoodworksProperties.OAK_WOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> SPRUCE_BOARDS = HELPER.createFuelBlock("spruce_boards", () -> new RotatedPillarBlock(WoodworksProperties.SPRUCE_WOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BIRCH_BOARDS = HELPER.createFuelBlock("birch_boards", () -> new RotatedPillarBlock(WoodworksProperties.BIRCH_WOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> JUNGLE_BOARDS = HELPER.createFuelBlock("jungle_boards", () -> new RotatedPillarBlock(WoodworksProperties.JUNGLE_WOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ACACIA_BOARDS = HELPER.createFuelBlock("acacia_boards", () -> new RotatedPillarBlock(WoodworksProperties.ACACIA_WOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> DARK_OAK_BOARDS = HELPER.createFuelBlock("dark_oak_boards", () -> new RotatedPillarBlock(WoodworksProperties.DARK_OAK_WOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MANGROVE_BOARDS = HELPER.createFuelBlock("mangrove_boards", () -> new RotatedPillarBlock(WoodworksProperties.MANGROVE_WOOD.planks()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRIMSON_BOARDS = HELPER.createBlock("crimson_boards", () -> new RotatedPillarBlock(WoodworksProperties.CRIMSON_STEM.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> WARPED_BOARDS = HELPER.createBlock("warped_boards", () -> new RotatedPillarBlock(WoodworksProperties.WARPED_STEM.planks()), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> OAK_LEAF_PILE = HELPER.createBlock("oak_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.OAK_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> SPRUCE_LEAF_PILE = HELPER.createBlock("spruce_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.SPRUCE_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BIRCH_LEAF_PILE = HELPER.createBlock("birch_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.BIRCH_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> JUNGLE_LEAF_PILE = HELPER.createBlock("jungle_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.JUNGLE_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ACACIA_LEAF_PILE = HELPER.createBlock("acacia_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.ACACIA_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DARK_OAK_LEAF_PILE = HELPER.createBlock("dark_oak_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.DARK_OAK_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MANGROVE_LEAF_PILE = HELPER.createBlock("mangrove_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.MANGROVE_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> AZALEA_LEAF_PILE = HELPER.createBlock("azalea_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.AZALEA_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> FLOWERING_AZALEA_LEAF_PILE = HELPER.createBlock("flowering_azalea_leaf_pile", () -> new LeafPileBlock(WoodworksProperties.AZALEA_WOOD.leafPile()), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> SPRUCE_BOOKSHELF = HELPER.createFuelBlock("spruce_bookshelf", () -> new BookshelfBlock(WoodworksProperties.SPRUCE_WOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> BIRCH_BOOKSHELF = HELPER.createFuelBlock("birch_bookshelf", () -> new BookshelfBlock(WoodworksProperties.BIRCH_WOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> JUNGLE_BOOKSHELF = HELPER.createFuelBlock("jungle_bookshelf", () -> new BookshelfBlock(WoodworksProperties.JUNGLE_WOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> ACACIA_BOOKSHELF = HELPER.createFuelBlock("acacia_bookshelf", () -> new BookshelfBlock(WoodworksProperties.ACACIA_WOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> DARK_OAK_BOOKSHELF = HELPER.createFuelBlock("dark_oak_bookshelf", () -> new BookshelfBlock(WoodworksProperties.DARK_OAK_WOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> MANGROVE_BOOKSHELF = HELPER.createFuelBlock("mangrove_bookshelf", () -> new BookshelfBlock(WoodworksProperties.MANGROVE_WOOD.bookshelf()), 300, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> CRIMSON_BOOKSHELF = HELPER.createBlock("crimson_bookshelf", () -> new BookshelfBlock(WoodworksProperties.CRIMSON_STEM.bookshelf()), CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Block> WARPED_BOOKSHELF = HELPER.createBlock("warped_bookshelf", () -> new BookshelfBlock(WoodworksProperties.WARPED_STEM.bookshelf()), CreativeModeTab.TAB_BUILDING_BLOCKS);

	public static final RegistryObject<Block> SPRUCE_LADDER = HELPER.createFuelBlock("spruce_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.SPRUCE_WOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BIRCH_LADDER = HELPER.createFuelBlock("birch_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.BIRCH_WOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> JUNGLE_LADDER = HELPER.createFuelBlock("jungle_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.JUNGLE_WOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ACACIA_LADDER = HELPER.createFuelBlock("acacia_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.ACACIA_WOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DARK_OAK_LADDER = HELPER.createFuelBlock("dark_oak_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.DARK_OAK_WOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MANGROVE_LADDER = HELPER.createFuelBlock("mangrove_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.MANGROVE_WOOD.ladder()), 300, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CRIMSON_LADDER = HELPER.createBlock("crimson_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.CRIMSON_STEM.ladder()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> WARPED_LADDER = HELPER.createBlock("warped_ladder", () -> new BlueprintLadderBlock(WoodworksProperties.WARPED_STEM.ladder()), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<Block> SPRUCE_BEEHIVE = HELPER.createBlock("spruce_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.SPRUCE_WOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> BIRCH_BEEHIVE = HELPER.createBlock("birch_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.BIRCH_WOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> JUNGLE_BEEHIVE = HELPER.createBlock("jungle_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.JUNGLE_WOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> ACACIA_BEEHIVE = HELPER.createBlock("acacia_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.ACACIA_WOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> DARK_OAK_BEEHIVE = HELPER.createBlock("dark_oak_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.DARK_OAK_WOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> MANGROVE_BEEHIVE = HELPER.createBlock("mangrove_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.MANGROVE_WOOD.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> CRIMSON_BEEHIVE = HELPER.createBlock("crimson_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.CRIMSON_STEM.beehive()), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> WARPED_BEEHIVE = HELPER.createBlock("warped_beehive", () -> new BlueprintBeehiveBlock(WoodworksProperties.WARPED_STEM.beehive()), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<BlueprintChestBlock> OAK_CHEST = HELPER.createChestBlock("oak", WoodworksProperties.OAK_WOOD.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> SPRUCE_CHEST = HELPER.createChestBlock("spruce", WoodworksProperties.SPRUCE_WOOD.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> BIRCH_CHEST = HELPER.createChestBlock("birch", WoodworksProperties.BIRCH_WOOD.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> JUNGLE_CHEST = HELPER.createChestBlock("jungle", WoodworksProperties.JUNGLE_WOOD.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> ACACIA_CHEST = HELPER.createChestBlock("acacia", WoodworksProperties.ACACIA_WOOD.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> DARK_OAK_CHEST = HELPER.createChestBlock("dark_oak", WoodworksProperties.DARK_OAK_WOOD.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> MANGROVE_CHEST = HELPER.createChestBlock("mangrove", WoodworksProperties.MANGROVE_WOOD.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> CRIMSON_CHEST = HELPER.createNonFuelChestBlock("crimson", WoodworksProperties.CRIMSON_STEM.chest(), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<BlueprintChestBlock> WARPED_CHEST = HELPER.createNonFuelChestBlock("warped", WoodworksProperties.WARPED_STEM.chest(), CreativeModeTab.TAB_DECORATIONS);

	public static final RegistryObject<BlueprintTrappedChestBlock> OAK_TRAPPED_CHEST = HELPER.createTrappedChestBlock("oak", WoodworksProperties.OAK_WOOD.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> SPRUCE_TRAPPED_CHEST = HELPER.createTrappedChestBlock("spruce", WoodworksProperties.SPRUCE_WOOD.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> BIRCH_TRAPPED_CHEST = HELPER.createTrappedChestBlock("birch", WoodworksProperties.BIRCH_WOOD.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> JUNGLE_TRAPPED_CHEST = HELPER.createTrappedChestBlock("jungle", WoodworksProperties.JUNGLE_WOOD.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> ACACIA_TRAPPED_CHEST = HELPER.createTrappedChestBlock("acacia", WoodworksProperties.ACACIA_WOOD.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> DARK_OAK_TRAPPED_CHEST = HELPER.createTrappedChestBlock("dark_oak", WoodworksProperties.DARK_OAK_WOOD.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> MANGROVE_TRAPPED_CHEST = HELPER.createTrappedChestBlock("mangrove", WoodworksProperties.MANGROVE_WOOD.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> CRIMSON_TRAPPED_CHEST = HELPER.createNonFuelTrappedChestBlock("crimson", WoodworksProperties.CRIMSON_STEM.chest(), CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<BlueprintTrappedChestBlock> WARPED_TRAPPED_CHEST = HELPER.createNonFuelTrappedChestBlock("warped", WoodworksProperties.WARPED_STEM.chest(), CreativeModeTab.TAB_REDSTONE);

	public static final class WoodworksProperties {
		public static final WoodSetProperties OAK_WOOD = WoodSetProperties.builder(MaterialColor.WOOD).build();
		public static final WoodSetProperties SPRUCE_WOOD = WoodSetProperties.builder(MaterialColor.PODZOL).build();
		public static final WoodSetProperties BIRCH_WOOD = WoodSetProperties.builder(MaterialColor.SAND).build();
		public static final WoodSetProperties JUNGLE_WOOD = WoodSetProperties.builder(MaterialColor.DIRT).build();
		public static final WoodSetProperties ACACIA_WOOD = WoodSetProperties.builder(MaterialColor.COLOR_ORANGE).build();
		public static final WoodSetProperties DARK_OAK_WOOD = WoodSetProperties.builder(MaterialColor.COLOR_BROWN).build();
		public static final WoodSetProperties MANGROVE_WOOD = WoodSetProperties.builder(MaterialColor.COLOR_RED).build();
		public static final WoodSetProperties CRIMSON_STEM = WoodSetProperties.builder(MaterialColor.CRIMSON_STEM).material(Material.NETHER_WOOD).build();
		public static final WoodSetProperties WARPED_STEM = WoodSetProperties.builder(MaterialColor.WARPED_STEM).material(Material.NETHER_WOOD).build();
		public static final WoodSetProperties AZALEA_WOOD = WoodSetProperties.builder(MaterialColor.TERRACOTTA_PURPLE).leavesSound(SoundType.AZALEA_LEAVES).build();
	}
}
