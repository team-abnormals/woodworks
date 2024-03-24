package com.teamabnormals.woodworks.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.teamabnormals.blueprint.client.renderer.block.BlueprintChestBlockEntityRenderer;
import com.teamabnormals.woodworks.common.block.ClosetBlock;
import com.teamabnormals.woodworks.core.other.WoodworksModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;

import java.util.Calendar;

public class DrawerBlockEntityRenderer<T extends BlockEntity & LidBlockEntity> extends BlueprintChestBlockEntityRenderer<T> {
	public static Block itemBlock = null;

	private final ModelPart leftDoor;
	private final ModelPart leftBack;
	private final ModelPart tallLeftDoor;
	private final ModelPart tallLeftBack;

	private final ModelPart rightDoor;
	private final ModelPart rightBack;
	private final ModelPart tallRightDoor;
	private final ModelPart tallRightBack;

	public boolean isChristmas;

	public DrawerBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
			this.isChristmas = true;
		}

		ModelPart left = context.bakeLayer(WoodworksModelLayers.BAMBOO_CLOSET_LEFT);
		this.leftBack = left.getChild("back");
		this.leftDoor = left.getChild("door");

		ModelPart tallLeft = context.bakeLayer(WoodworksModelLayers.BAMBOO_CLOSET_TALL_LEFT);
		this.tallLeftBack = tallLeft.getChild("back");
		this.tallLeftDoor = tallLeft.getChild("door");

		ModelPart right = context.bakeLayer(WoodworksModelLayers.BAMBOO_CLOSET_RIGHT);
		this.rightBack = right.getChild("back");
		this.rightDoor = right.getChild("door");

		ModelPart tallRight = context.bakeLayer(WoodworksModelLayers.BAMBOO_CLOSET_TALL_RIGHT);
		this.tallRightBack = tallRight.getChild("back");
		this.tallRightDoor = tallRight.getChild("door");
	}

	public static LayerDefinition createBodyLayer(boolean tall, boolean mirror) {
		float height = tall ? 16.0F : 14.0F;
		float pivot = mirror ? 15.0F : 1.0F;

		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 1.0F, 14.0F, height, 11.0F, mirror), PartPose.ZERO);
		PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(0, tall ? 27 : 25).addBox(-pivot + 1.0F, 0.0F, 0.0F, 14.0F, height, 4.0F, mirror), PartPose.offset(pivot, 0.0F, 11.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void render(T tileEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Level level = tileEntityIn.getLevel();
		boolean flag = level != null;
		BlockState state = flag ? tileEntityIn.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		ChestType chesttype = state.hasProperty(ChestBlock.TYPE) ? state.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = state.getBlock();
		if (block instanceof AbstractChestBlock) {
			AbstractChestBlock<?> abstractchestblock = (AbstractChestBlock) block;
			boolean flag1 = chesttype != ChestType.SINGLE;
			poseStack.pushPose();

			Direction facing = state.getValue(ChestBlock.FACING);
			float f = facing.toYRot();
			boolean right = state.hasProperty(ClosetBlock.HINGE) && state.getValue(ClosetBlock.HINGE) == DoorHingeSide.RIGHT;

			BlockPos neighborPos = tileEntityIn.getBlockPos().relative(!right ? facing.getCounterClockWise() : facing.getClockWise());

			if (flag && level.getBlockState(neighborPos).is(state.getBlock())) {
				BlockState neighborState = level.getBlockState(neighborPos);
				DoorHingeSide hinge = state.getValue(ClosetBlock.HINGE);
				if (neighborState.getValue(ClosetBlock.TYPE) == chesttype && neighborState.getValue(ClosetBlock.FACING) == facing && neighborState.getValue(ClosetBlock.HINGE) != hinge) {
					poseStack.translate(facing.getNormal().getZ() * 0.0625D * (right ? -1.0D : 1.0D), 0.0D, -facing.getNormal().getX() * 0.0625D * (right ? -1.0D : 1.0D));
				}
			}

			poseStack.translate(0.5D, 0.5D, 0.5D);
			poseStack.mulPose(Axis.YP.rotationDegrees(-f));
			poseStack.translate(-0.5D, -0.5D, -0.5D);

			DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> icallbackwrapper;
			if (flag) {
				icallbackwrapper = abstractchestblock.combine(state, level, tileEntityIn.getBlockPos(), true);
			} else {
				icallbackwrapper = DoubleBlockCombiner.Combiner::acceptNone;
			}

			float f1 = icallbackwrapper.apply(ChestBlock.opennessCombiner(tileEntityIn)).get(partialTicks);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;

			if (right) {
				f1 *= -1.0F;
			}

			int i = icallbackwrapper.apply(new BrightnessCombiner<>()).applyAsInt(combinedLightIn);
			VertexConsumer ivertexbuilder = this.getChestMaterial(tileEntityIn, chesttype).buffer(bufferIn, RenderType::entityCutout);
			if (flag1 && chesttype == ChestType.RIGHT) {
				this.render(poseStack, ivertexbuilder, right ? this.tallRightDoor : this.tallLeftDoor, right ? this.tallRightBack : this.tallLeftBack, f1, i, combinedOverlayIn);
			} else {
				this.render(poseStack, ivertexbuilder, right ? this.rightDoor : this.leftDoor, right ? this.rightBack : this.leftBack, f1, i, combinedOverlayIn);
			}

			poseStack.popPose();
		}
	}

	public void render(PoseStack matrixStack, VertexConsumer builder, ModelPart closetDoor, ModelPart closetBack, float lidAngle, int combinedLightIn, int combinedOverlayIn) {
		closetDoor.yRot = -(lidAngle * ((float) Math.PI * 13F / 24F));
		closetDoor.render(matrixStack, builder, combinedLightIn, combinedOverlayIn);
		closetBack.render(matrixStack, builder, combinedLightIn, combinedOverlayIn);
	}
}