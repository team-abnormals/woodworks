package com.teamabnormals.woodworks.core.registry.helper;

import com.teamabnormals.blueprint.client.BlueprintChestMaterials;
import com.teamabnormals.blueprint.client.renderer.block.ChestBlockEntityWithoutLevelRenderer;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.BlueprintChestBlockEntity;
import com.teamabnormals.blueprint.common.block.entity.BlueprintTrappedChestBlockEntity;
import com.teamabnormals.blueprint.common.item.BEWLRBlockItem;
import com.teamabnormals.blueprint.common.item.BEWLRFuelBlockItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

public class WoodworksBlockSubRegistryHelper extends BlockSubRegistryHelper {

	public WoodworksBlockSubRegistryHelper(RegistryHelper parent) {
		super(parent);
	}

	public RegistryObject<BlueprintChestBlock> createNonFuelChestBlock(String name, Block.Properties properties) {
		String modId = this.parent.getModId();
		String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, name, false);
		RegistryObject<BlueprintChestBlock> block = this.deferredRegister.register(name + "_chest", () -> new BlueprintChestBlock(chestMaterialsName, properties));
		this.itemRegister.register(name + "_chest", () -> new BEWLRBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(false)));
		return block;
	}

	@Override
	public RegistryObject<BlueprintTrappedChestBlock> createTrappedChestBlock(String name, Block.Properties properties) {
		String modId = this.parent.getModId();
		RegistryObject<BlueprintTrappedChestBlock> block = this.deferredRegister.register("trapped_" + name + "_chest", () -> new BlueprintTrappedChestBlock(modId + ":" + name + "_trapped", properties));
		String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, name, true);
		this.itemRegister.register("trapped_" + name + "_chest", () -> new BEWLRFuelBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(true), 300));
		return block;
	}

	public RegistryObject<BlueprintTrappedChestBlock> createNonFuelTrappedChestBlock(String name, Block.Properties properties) {
		String modId = this.parent.getModId();
		RegistryObject<BlueprintTrappedChestBlock> block = this.deferredRegister.register("trapped_" + name + "_chest", () -> new BlueprintTrappedChestBlock(modId + ":" + name + "_trapped", properties));
		String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, name, true);
		this.itemRegister.register("trapped_" + name + "_chest", () -> new BEWLRBlockItem(block.get(), new Item.Properties(), () -> () -> chestBEWLR(true)));
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
