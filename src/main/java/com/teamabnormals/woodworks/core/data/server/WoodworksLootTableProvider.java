package com.teamabnormals.woodworks.core.data.server;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.teamabnormals.woodworks.core.registry.WoodworksBlocks.*;

public class WoodworksLootTableProvider extends LootTableProvider {
	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> tables = ImmutableList.of(Pair.of(WoodworksBlockLoot::new, LootContextParamSets.BLOCK));

	public WoodworksLootTableProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
		return tables;
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {
	}

	private static class WoodworksBlockLoot extends BlockLoot {

		@Override
		public void addTables() {
			this.dropSelf(OAK_BOARDS.get());
			this.add(OAK_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
			this.add(OAK_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(OAK_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.dropSelf(SPRUCE_BOARDS.get());
			this.add(SPRUCE_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
			this.add(SPRUCE_BOOKSHELF.get(), WoodworksBlockLoot::createBookshelfDrops);
			this.dropSelf(SPRUCE_LADDER.get());
			this.add(SPRUCE_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(SPRUCE_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(SPRUCE_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.dropSelf(BIRCH_BOARDS.get());
			this.add(BIRCH_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
			this.add(BIRCH_BOOKSHELF.get(), WoodworksBlockLoot::createBookshelfDrops);
			this.dropSelf(BIRCH_LADDER.get());
			this.add(BIRCH_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(BIRCH_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(BIRCH_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.dropSelf(JUNGLE_BOARDS.get());
			this.add(JUNGLE_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
			this.add(JUNGLE_BOOKSHELF.get(), WoodworksBlockLoot::createBookshelfDrops);
			this.dropSelf(JUNGLE_LADDER.get());
			this.add(JUNGLE_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(JUNGLE_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(JUNGLE_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.dropSelf(DARK_OAK_BOARDS.get());
			this.add(DARK_OAK_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
			this.add(DARK_OAK_BOOKSHELF.get(), WoodworksBlockLoot::createBookshelfDrops);
			this.dropSelf(DARK_OAK_LADDER.get());
			this.add(DARK_OAK_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(DARK_OAK_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(DARK_OAK_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.dropSelf(ACACIA_BOARDS.get());
			this.add(ACACIA_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
			this.add(ACACIA_BOOKSHELF.get(), WoodworksBlockLoot::createBookshelfDrops);
			this.dropSelf(ACACIA_LADDER.get());
			this.add(ACACIA_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(ACACIA_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(ACACIA_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.dropSelf(CRIMSON_BOARDS.get());
			this.add(CRIMSON_BOOKSHELF.get(), WoodworksBlockLoot::createBookshelfDrops);
			this.dropSelf(CRIMSON_LADDER.get());
			this.add(CRIMSON_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(CRIMSON_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(CRIMSON_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.dropSelf(WARPED_BOARDS.get());
			this.add(WARPED_BOOKSHELF.get(), WoodworksBlockLoot::createBookshelfDrops);
			this.dropSelf(WARPED_LADDER.get());
			this.add(WARPED_BEEHIVE.get(), BlockLoot::createBeeHiveDrop);
			this.add(WARPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);
			this.add(WARPED_TRAPPED_CHEST.get(), BlockLoot::createNameableBlockEntityTable);

			this.add(AZALEA_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
			this.add(FLOWERING_AZALEA_LEAF_PILE.get(), WoodworksBlockLoot::createLeafPileDrops);
		}

		protected static LootTable.Builder createLeafPileDrops(Block block) {
			return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS)));
		}

		protected static LootTable.Builder createBookshelfDrops(Block block) {
			return createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F));
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> ForgeRegistries.BLOCKS.getKey(block).getNamespace().equals(Woodworks.MOD_ID)).collect(Collectors.toSet());
		}
	}
}