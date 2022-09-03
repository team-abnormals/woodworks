package com.teamabnormals.woodworks.core.registry.helper;

import com.teamabnormals.blueprint.client.ChestManager;
import com.teamabnormals.blueprint.client.renderer.block.ChestBlockEntityWithoutLevelRenderer;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class WoodworksBlockSubRegistryHelper extends BlockSubRegistryHelper {

	public WoodworksBlockSubRegistryHelper(RegistryHelper parent) {
		super(parent);
	}

	public RegistryObject<BlueprintChestBlock> createNonFuelChestBlock(String name, Block.Properties properties, @Nullable CreativeModeTab group) {
		String modId = this.parent.getModId();
		RegistryObject<BlueprintChestBlock> block = this.deferredRegister.register(name + "_chest", () -> new BlueprintChestBlock(modId + ":" + name, properties));
		ChestManager.putChestInfo(modId, name, false);
		this.itemRegister.register(name + "_chest", () -> new BEWLRBlockItem(block.get(), new Item.Properties().tab(group), () -> () -> chestBEWLR(false)));
		return block;
	}

	public RegistryObject<BlueprintTrappedChestBlock> createNonFuelTrappedChestBlock(String name, Block.Properties properties, @Nullable CreativeModeTab group) {
		String modId = this.parent.getModId();
		RegistryObject<BlueprintTrappedChestBlock> block = this.deferredRegister.register(name + "_trapped_chest", () -> new BlueprintTrappedChestBlock(modId + ":" + name + "_trapped", properties));
		ChestManager.putChestInfo(modId, name, true);
		this.itemRegister.register(name + "_trapped_chest", () -> new BEWLRBlockItem(block.get(), new Item.Properties().tab(group), () -> () -> chestBEWLR(true)));
		return block;
	}

	@OnlyIn(Dist.CLIENT)
	private static BEWLRBlockItem.LazyBEWLR chestBEWLR(boolean trapped) {
		return trapped ? new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> {
			return new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintTrappedChestBlockEntity(BlockPos.ZERO, Blocks.TRAPPED_CHEST.defaultBlockState()));
		}) : new BEWLRBlockItem.LazyBEWLR((dispatcher, entityModelSet) -> {
			return new ChestBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new BlueprintChestBlockEntity(BlockPos.ZERO, Blocks.CHEST.defaultBlockState()));
		});
	}
}
