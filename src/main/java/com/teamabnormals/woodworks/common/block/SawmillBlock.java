package com.teamabnormals.woodworks.common.block;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.teamabnormals.woodworks.common.inventory.SawmillMenu;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SawmillBlock extends Block {
	public static final Component CONTAINER_TITLE = Component.translatable("container." + Woodworks.MOD_ID + ".sawmill");
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.STONECUTTER);
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Shapes.or(box(0.0D, 0.0D, 9.0D, 16.0D, 16.0D, 16.0D), box(7.0D, 0.0D, 0.0D, 9.0D, 2.0D, 9.0D)),
			Shapes.or(box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 7.0D), box(7.0D, 0.0D, 7.0D, 9.0D, 2.0D, 16.0D)),
			Shapes.or(box(9.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), box(0.0D, 0.0D, 7.0D, 9.0D, 2.0D, 9.0D)),
			Shapes.or(box(0.0D, 0.0D, 0.0D, 7.0D, 16.0D, 16.0D), box(7.0D, 0.0D, 7.0D, 16.0D, 2.0D, 9.0D))
	};

	public SawmillBlock(BlockBehaviour.Properties propertiesIn) {
		super(propertiesIn);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			player.openMenu(state.getMenuProvider(level, pos));
			player.awardStat(Stats.INTERACT_WITH_STONECUTTER);
			return InteractionResult.CONSUME;
		}
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((id, inventory, player) -> new SawmillMenu(id, inventory, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get3DDataValue() - 2];
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
		return false;
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), tab, items);
	}
}