package com.teamabnormals.woodworks.core.registry.helper;

import com.teamabnormals.blueprint.client.BlueprintChestMaterials;
import com.teamabnormals.blueprint.client.MemoizedBEWLR;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.woodworks.client.renderer.block.DrawerBlockEntityWithoutLevelRenderer;
import com.teamabnormals.woodworks.common.block.ClosetBlock;
import com.teamabnormals.woodworks.common.block.TrappedClosetBlock;
import com.teamabnormals.woodworks.common.block.entity.ClosetBlockEntity;
import com.teamabnormals.woodworks.common.block.entity.TrappedClosetBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public class WoodworksBlockSubRegistryHelper extends BlockSubRegistryHelper {

	public WoodworksBlockSubRegistryHelper(RegistryHelper parent) {
		super(parent);
	}

	public DeferredBlock<ClosetBlock> createClosetBlock(String name, Block.Properties properties) {
		String modId = this.parent.getModId();
		String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, name, false);
		DeferredBlock<ClosetBlock> block = this.deferredRegister.register(name + "_closet", () -> new ClosetBlock(chestMaterialsName, properties));
		var item = this.itemRegister.register(name + "_closet", () -> new BlockItem(block.get(), new Item.Properties()));
		if (FMLEnvironment.dist == Dist.CLIENT) {
			this.clientItemExtensions.put(item, closetBEWLRItemExtensions(block, false));
		}
		return block;
	}

	public DeferredBlock<TrappedClosetBlock> createTrappedClosetBlock(String name, Block.Properties properties) {
		String modId = this.parent.getModId();
		DeferredBlock<TrappedClosetBlock> block = this.deferredRegister.register("trapped_" + name + "_closet", () -> new TrappedClosetBlock(modId + ":" + name + "_trapped", properties));
		String chestMaterialsName = BlueprintChestMaterials.registerMaterials(modId, name, true);
		var item = this.itemRegister.register("trapped_" + name + "_closet", () -> new BlockItem(block.get(), new Item.Properties()));
		if (FMLEnvironment.dist == Dist.CLIENT) {
			this.clientItemExtensions.put(item, closetBEWLRItemExtensions(block, true));
		}
		return block;
	}

	@OnlyIn(Dist.CLIENT)
	private static IClientItemExtensions closetBEWLRItemExtensions(Supplier<? extends Block> block, boolean trapped) {
		return MemoizedBEWLR.asCustomItemRenderer(trapped ? (dispatcher, entityModelSet) -> {
			return new DrawerBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new TrappedClosetBlockEntity(BlockPos.ZERO, block.get().defaultBlockState()));
		} : (dispatcher, entityModelSet) -> {
			return new DrawerBlockEntityWithoutLevelRenderer<>(dispatcher, entityModelSet, new ClosetBlockEntity(BlockPos.ZERO, block.get().defaultBlockState()));
		});
	}
}
