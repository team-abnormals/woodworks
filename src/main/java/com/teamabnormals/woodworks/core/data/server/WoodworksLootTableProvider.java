package com.teamabnormals.woodworks.core.data.server;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksLootTableProvider extends LootTableProvider {

	public WoodworksLootTableProvider(PackOutput output) {
		super(output, BuiltInLootTables.all(), ImmutableList.of(new LootTableProvider.SubProviderEntry(WoodworksBlockLoot::new, LootContextParamSets.BLOCK)));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
	}

	private static class WoodworksBlockLoot extends BlockLootSubProvider {
		private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

		protected WoodworksBlockLoot() {
			super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags());
		}

		@Override
		public void generate() {
			this.dropSelf(SAWMILL.get());

			this.dropSelf(OAK_BOARDS.get());
			this.add(OAK_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(OAK_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_OAK_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(SPRUCE_BOARDS.get());
			this.add(SPRUCE_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(SPRUCE_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_SPRUCE_BOOKSHELF.get());
			this.dropSelf(SPRUCE_LADDER.get());
			this.add(SPRUCE_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(SPRUCE_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_SPRUCE_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(BIRCH_BOARDS.get());
			this.add(BIRCH_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(BIRCH_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_BIRCH_BOOKSHELF.get());
			this.dropSelf(BIRCH_LADDER.get());
			this.add(BIRCH_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(BIRCH_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_BIRCH_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(JUNGLE_BOARDS.get());
			this.add(JUNGLE_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(JUNGLE_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_JUNGLE_BOOKSHELF.get());
			this.dropSelf(JUNGLE_LADDER.get());
			this.add(JUNGLE_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(JUNGLE_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_JUNGLE_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(ACACIA_BOARDS.get());
			this.add(ACACIA_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(ACACIA_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_ACACIA_BOOKSHELF.get());
			this.dropSelf(ACACIA_LADDER.get());
			this.add(ACACIA_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(ACACIA_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_ACACIA_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(DARK_OAK_BOARDS.get());
			this.add(DARK_OAK_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(DARK_OAK_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_DARK_OAK_BOOKSHELF.get());
			this.dropSelf(DARK_OAK_LADDER.get());
			this.add(DARK_OAK_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(DARK_OAK_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_DARK_OAK_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(MANGROVE_BOARDS.get());
			this.add(MANGROVE_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(MANGROVE_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_MANGROVE_BOOKSHELF.get());
			this.dropSelf(MANGROVE_LADDER.get());
			this.add(MANGROVE_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(MANGROVE_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_MANGROVE_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(CHERRY_BOARDS.get());
			this.add(CHERRY_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(CHERRY_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_CHERRY_BOOKSHELF.get());
			this.dropSelf(CHERRY_LADDER.get());
			this.add(CHERRY_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(CHERRY_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_CHERRY_CHEST.get(), this::createNameableBlockEntityTable);

			this.add(BAMBOO_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_BAMBOO_BOOKSHELF.get());
			this.dropSelf(BAMBOO_LADDER.get());
			this.add(BAMBOO_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(BAMBOO_CLOSET.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_BAMBOO_CLOSET.get(), this::createNameableBlockEntityTable);

			this.dropSelf(CRIMSON_BOARDS.get());
			this.add(CRIMSON_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_CRIMSON_BOOKSHELF.get());
			this.dropSelf(CRIMSON_LADDER.get());
			this.add(CRIMSON_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(CRIMSON_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_CRIMSON_CHEST.get(), this::createNameableBlockEntityTable);

			this.dropSelf(WARPED_BOARDS.get());
			this.add(WARPED_BOOKSHELF.get(), this::createBookshelfDrops);
			this.dropWhenSilkTouch(CHISELED_WARPED_BOOKSHELF.get());
			this.dropSelf(WARPED_LADDER.get());
			this.add(WARPED_BEEHIVE.get(), WoodworksBlockLoot::createBeeHiveDrop);
			this.add(WARPED_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_WARPED_CHEST.get(), this::createNameableBlockEntityTable);

			this.add(AZALEA_LEAF_PILE.get(), this::createLeafPileDrops);
			this.add(FLOWERING_AZALEA_LEAF_PILE.get(), this::createLeafPileDrops);
		}

		protected LootTable.Builder createLeafPileDrops(Block block) {
			return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS)));
		}

		protected LootTable.Builder createBookshelfDrops(Block block) {
			return createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F));
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(Woodworks.MOD_ID)).collect(Collectors.toSet());
		}
	}
}