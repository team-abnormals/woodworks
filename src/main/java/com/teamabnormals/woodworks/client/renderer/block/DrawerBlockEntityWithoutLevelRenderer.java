package com.teamabnormals.woodworks.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.blueprint.client.renderer.block.TypedBlockEntityWithoutLevelRenderer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrawerBlockEntityWithoutLevelRenderer<C extends BlockEntity> extends TypedBlockEntityWithoutLevelRenderer<C> {

	public DrawerBlockEntityWithoutLevelRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet, C be) {
		super(dispatcher, modelSet, be);
	}

	@Override
	public void renderByItem(ItemStack itemStackIn, ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		BlockItem blockItem = (BlockItem) itemStackIn.getItem();
		DrawerBlockEntityRenderer.itemBlock = blockItem.getBlock();
		super.renderByItem(itemStackIn, itemDisplayContext, poseStack, buffer, combinedLight, combinedOverlay);
		DrawerBlockEntityRenderer.itemBlock = null;
	}
}