package com.teamabnormals.woodworks.common.block;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.teamabnormals.woodworks.common.inventory.SawmillMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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
	public static final Component CONTAINER_TITLE = new TranslatableComponent("container.woodworks.sawmill");
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
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (worldIn.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			player.openMenu(state.getMenuProvider(worldIn, pos));
			player.awardStat(Stats.INTERACT_WITH_STONECUTTER);
			return InteractionResult.CONSUME;
		}
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> new SawmillMenu(p_57074_, p_57075_, ContainerLevelAccess.create(level, pos)), CONTAINER_TITLE);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get3DDataValue() - 2];
	}

	@Override
	public boolean useShapeForLightOcclusion(BlockState p_57109_) {
		return true;
	}

	@Override
	public RenderShape getRenderShape(BlockState p_57098_) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState rotate(BlockState p_57093_, Rotation p_57094_) {
		return p_57093_.setValue(FACING, p_57094_.rotate(p_57093_.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState p_57090_, Mirror p_57091_) {
		return p_57090_.rotate(p_57091_.getRotation(p_57090_.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_57096_) {
		p_57096_.add(FACING);
	}

	@Override
	public boolean isPathfindable(BlockState p_57078_, BlockGetter p_57079_, BlockPos p_57080_, PathComputationType p_57081_) {
		return false;
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), group, items);
	}
}