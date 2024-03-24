package com.teamabnormals.woodworks.common.block;

import com.teamabnormals.blueprint.core.api.IChestBlock;
import com.teamabnormals.woodworks.common.block.entity.ClosetBlockEntity;
import com.teamabnormals.woodworks.core.registry.WoodworksBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class ClosetBlock extends ChestBlock implements IChestBlock {
	public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;

	protected static final VoxelShape NORTH_AABB = Block.box(2.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
	protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 1.0D, 14.0D, 14.0D, 15.0D);
	protected static final VoxelShape NORTH_AABB_TALL = Block.box(2.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D);
	protected static final VoxelShape SOUTH_AABB_TALL = Block.box(0.0D, 0.0D, 1.0D, 14.0D, 16.0D, 15.0D);

	protected static final VoxelShape WEST_AABB = Block.box(1.0D, 0.0D, 2.0D, 15.0D, 14.0D, 16.0D);
	protected static final VoxelShape EAST_AABB = Block.box(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 14.0D);
	protected static final VoxelShape WEST_AABB_TALL = Block.box(1.0D, 0.0D, 2.0D, 15.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_AABB_TALL = Block.box(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 14.0D);

	protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape AABB_TALL = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public final String type;

	private static final DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {
		public Optional<MenuProvider> acceptDouble(final ChestBlockEntity chest1, final ChestBlockEntity chest2) {
			final Container container = new CompoundContainer(chest2, chest1);
			return Optional.of(new MenuProvider() {
				@Nullable
				public AbstractContainerMenu createMenu(int num, Inventory inventory, Player player) {
					if (chest1.canOpen(player) && chest2.canOpen(player)) {
						chest1.unpackLootTable(inventory.player);
						chest2.unpackLootTable(inventory.player);
						return ChestMenu.sixRows(num, inventory, container);
					} else {
						return null;
					}
				}

				public Component getDisplayName() {
					if (chest1.hasCustomName()) {
						return chest1.getDisplayName();
					} else {
						return chest2.hasCustomName() ? chest2.getDisplayName() : Component.translatable(ClosetBlockEntity.CONTAINER_CLOSET_DOUBLE);
					}
				}
			});
		}

		public Optional<MenuProvider> acceptSingle(ChestBlockEntity p_51602_) {
			return Optional.of(p_51602_);
		}

		public Optional<MenuProvider> acceptNone() {
			return Optional.empty();
		}
	};

	public ClosetBlock(String type, Properties props, Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityType) {
		super(props, blockEntityType);
		this.type = type;
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HINGE, DoorHingeSide.LEFT).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, false));
	}

	public ClosetBlock(String type, Properties props) {
		this(type, props, WoodworksBlockEntityTypes.CLOSET::get);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ClosetBlockEntity(pos, state);
	}

	@Override
	public String getChestMaterialsName() {
		return type;
	}

	@Nullable
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return this.combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
	}

	@Override
	public DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine(BlockState state, Level level, BlockPos pos, boolean p_51547_) {
		BiPredicate<LevelAccessor, BlockPos> bipredicate;
		if (p_51547_) {
			bipredicate = (p_51578_, p_51579_) -> false;
		} else {
			bipredicate = ClosetBlock::isChestBlockedAt;
		}

		return DoubleBlockCombiner.combineWithNeigbour(this.blockEntityType.get(), ChestBlock::getBlockType, ClosetBlock::getConnectedDirection, FACING, state, level, pos, bipredicate);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		boolean tall = state.getValue(TYPE) == ChestType.RIGHT;
		boolean left = state.getValue(HINGE) == DoorHingeSide.LEFT;

		if (shouldTranslateCloset(state, level, pos)) {
			VoxelShape north = tall ? NORTH_AABB_TALL : NORTH_AABB;
			VoxelShape south = tall ? SOUTH_AABB_TALL : SOUTH_AABB;
			VoxelShape east = tall ? EAST_AABB_TALL : EAST_AABB;
			VoxelShape west = tall ? WEST_AABB_TALL : WEST_AABB;

			switch (state.getValue(FACING)) {
				default:
				case NORTH:
					return !left ? north : south;
				case SOUTH:
					return !left ? south : north;
				case WEST:
					return left ? west : east;
				case EAST:
					return left ? east : west;
			}
		} else if (tall) {
			return AABB_TALL;
		}

		return AABB;
	}

	public static boolean shouldTranslateCloset(BlockState state, BlockGetter level, BlockPos pos) {
		boolean right = state.hasProperty(ClosetBlock.HINGE) && state.getValue(ClosetBlock.HINGE) == DoorHingeSide.RIGHT;
		Direction facing = state.getValue(ChestBlock.FACING);
		BlockPos neighborPos = pos.relative(!right ? facing.getCounterClockWise() : facing.getClockWise());
		ChestType chesttype = state.hasProperty(ChestBlock.TYPE) ? state.getValue(ChestBlock.TYPE) : ChestType.SINGLE;

		if (level.getBlockState(neighborPos).is(state.getBlock())) {
			BlockState neighborState = level.getBlockState(neighborPos);
			DoorHingeSide hinge = state.getValue(ClosetBlock.HINGE);
			return neighborState.getValue(ClosetBlock.TYPE) == chesttype && neighborState.getValue(ClosetBlock.FACING) == facing && neighborState.getValue(ClosetBlock.HINGE) != hinge;
		}

		return false;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState otherState, LevelAccessor level, BlockPos pos, BlockPos otherPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		if (otherState.is(this) && direction.getAxis().isVertical()) {
			ChestType chesttype = otherState.getValue(TYPE);
			if (state.getValue(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && state.getValue(FACING) == otherState.getValue(FACING) && getConnectedDirection(otherState) == direction.getOpposite()) {
				return state.setValue(TYPE, chesttype.getOpposite());
			}
		} else if (getConnectedDirection(state) == direction) {
			return state.setValue(TYPE, ChestType.SINGLE);
		}

		return state;
	}

	public static boolean isChestBlockedAt(LevelAccessor level, BlockPos pos) {
		Direction facing = level.getBlockState(pos).getValue(FACING);
		BlockPos offsetPos = pos.relative(facing);
		return level.getBlockState(offsetPos).isRedstoneConductor(level, offsetPos);
	}

	public static Direction getConnectedDirection(BlockState state) {
		return state.getValue(TYPE) == ChestType.LEFT ? Direction.DOWN : Direction.UP;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		ChestType chestType = ChestType.SINGLE;
		Direction facingDirection = context.getHorizontalDirection().getOpposite();
		FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
		boolean isSneaking = context.isSecondaryUseActive();
		Direction direction1 = context.getClickedFace();
		DoorHingeSide hinge = this.getHinge(context);

		if (direction1.getAxis().isVertical() && isSneaking) {
			Direction direction2 = this.candidatePartnerFacing(context, direction1.getOpposite());
			if (direction2 != null && direction2 == facingDirection && direction2.getAxis() != direction1.getAxis()) {
				chestType = direction1 == Direction.DOWN ? ChestType.RIGHT : ChestType.LEFT;
				DoorHingeSide hinge2 = this.candidatePartnerHinge(context, direction1.getOpposite());
				if (hinge2 != null) {
					hinge = hinge2;
				}
			}
		}

		if (direction1.getAxis().isHorizontal() && isSneaking) {
			DoorHingeSide hinge2 = this.candidatePartnerHinge(context, direction1.getOpposite());
			if (hinge2 != null) {
				hinge = hinge2 == DoorHingeSide.LEFT ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
			}
		}

		if (chestType == ChestType.SINGLE && !isSneaking) {
			if (facingDirection == this.candidatePartnerFacing(context, Direction.DOWN)) {
				chestType = ChestType.LEFT;
				DoorHingeSide hinge2 = this.candidatePartnerHinge(context, Direction.DOWN);
				if (hinge2 != null) {
					hinge = hinge2;
				}
			} else if (facingDirection == this.candidatePartnerFacing(context, Direction.UP)) {
				chestType = ChestType.RIGHT;
				DoorHingeSide hinge2 = this.candidatePartnerHinge(context, Direction.UP);
				if (hinge2 != null) {
					hinge = hinge2;
				}
			}
		}

		return this.defaultBlockState().setValue(FACING, facingDirection).setValue(HINGE, hinge).setValue(TYPE, chestType).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	private DoorHingeSide getHinge(BlockPlaceContext context) {
		BlockGetter level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockPos abovePos = pos.above();
		Direction dir = context.getHorizontalDirection();

		Direction dirCCW = dir.getCounterClockWise();
		BlockPos posCCW = pos.relative(dirCCW);
		BlockState stateCCW = level.getBlockState(posCCW);
		BlockPos abovePosCCW = abovePos.relative(dirCCW);
		BlockState aboveStateCCW = level.getBlockState(abovePosCCW);

		Direction dirCW = dir.getClockWise();
		BlockPos posCW = pos.relative(dirCW);
		BlockState stateCW = level.getBlockState(posCW);
		BlockPos abovePosCW = abovePos.relative(dirCW);
		BlockState aboveStateCW = level.getBlockState(abovePosCW);

		int i = (stateCCW.isCollisionShapeFullBlock(level, posCCW) ? -1 : 0) + (aboveStateCCW.isCollisionShapeFullBlock(level, abovePosCCW) ? -1 : 0) + (stateCW.isCollisionShapeFullBlock(level, posCW) ? 1 : 0) + (aboveStateCW.isCollisionShapeFullBlock(level, abovePosCW) ? 1 : 0);
		boolean flag = stateCCW.is(this);
		boolean flag1 = stateCW.is(this);
		if ((!flag || flag1) && i <= 0) {
			if ((!flag1 || flag) && i >= 0) {
				int j = dir.getStepX();
				int k = dir.getStepZ();
				Vec3 vec3 = context.getClickLocation();
				double d0 = vec3.x - (double) pos.getX();
				double d1 = vec3.z - (double) pos.getZ();
				return (j >= 0 || !(d1 < 0.5D)) && (j <= 0 || !(d1 > 0.5D)) && (k >= 0 || !(d0 > 0.5D)) && (k <= 0 || !(d0 < 0.5D)) ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
			} else {
				return DoorHingeSide.LEFT;
			}
		} else {
			return DoorHingeSide.RIGHT;
		}
	}

	@Nullable
	private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direction) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
		return state.is(this) && state.getValue(TYPE) == ChestType.SINGLE ? state.getValue(FACING) : null;
	}

	@Nullable
	private DoorHingeSide candidatePartnerHinge(BlockPlaceContext context, Direction direction) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
		return state.is(this) && state.getValue(TYPE) == ChestType.SINGLE ? state.getValue(HINGE) : null;
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return super.mirror(state, mirror).cycle(HINGE);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HINGE);
	}
}