package com.teamabnormals.woodworks.core.data.server;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger.TriggerInstance;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.AdvancementProvider.AdvancementGenerator;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class WoodworksAdvancementProvider implements AdvancementGenerator {

	public static AdvancementProvider create(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		return new AdvancementProvider(output, provider, helper, List.of(new WoodworksAdvancementProvider()));
	}

	@Override
	public void generate(Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
		Advancement.Builder.advancement()
				.parent(ResourceLocation.withDefaultNamespace("adventure/root"))
				.display(
						Items.CHISELED_BOOKSHELF,
						Component.translatable("advancements.adventure.read_power_from_chiseled_bookshelf.title"),
						Component.translatable("advancements.adventure.read_power_from_chiseled_bookshelf.description"),
						null, AdvancementType.TASK, true, true, false
				)
				.requirements(AdvancementRequirements.Strategy.OR)
				.addCriterion("chiseled_bookshelf", placedBlockReadByComparator(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES))
				.addCriterion("comparator", placedComparatorReadingBlock(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES))
				.save(consumer, "adventure/read_power_of_chiseled_bookshelf");
	}

	private static Criterion<TriggerInstance> placedBlockReadByComparator(TagKey<Block> block) {
		LootItemCondition.Builder[] condition = ComparatorBlock.FACING.getPossibleValues().stream()
				.map(direction -> {
					StatePropertiesPredicate.Builder statePredicate = StatePropertiesPredicate.Builder.properties().hasProperty(ComparatorBlock.FACING, direction);
					BlockPredicate.Builder comparatorPredicate = BlockPredicate.Builder.block().of(Blocks.COMPARATOR).setProperties(statePredicate);
					return LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(comparatorPredicate), new BlockPos(direction.getOpposite().getNormal()));
				}).toArray(LootItemCondition.Builder[]::new);
		return ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block))), AnyOfCondition.anyOf(condition));
	}

	private static Criterion<ItemUsedOnLocationTrigger.TriggerInstance> placedComparatorReadingBlock(TagKey<Block> block) {
		LootItemCondition.Builder[] condition = ComparatorBlock.FACING.getPossibleValues().stream()
				.map(direction -> {
					StatePropertiesPredicate.Builder statePredicate = StatePropertiesPredicate.Builder.properties().hasProperty(ComparatorBlock.FACING, direction);
					LootItemBlockStatePropertyCondition.Builder comparatorPredicate = new LootItemBlockStatePropertyCondition.Builder(Blocks.COMPARATOR).setProperties(statePredicate);
					LootItemCondition.Builder locationPredicate = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block)), new BlockPos(direction.getNormal()));
					return AllOfCondition.allOf(comparatorPredicate, locationPredicate);
				}).toArray(LootItemCondition.Builder[]::new);
		return ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(AnyOfCondition.anyOf(condition));
	}
}