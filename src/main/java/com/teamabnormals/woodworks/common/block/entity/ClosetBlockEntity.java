package com.teamabnormals.woodworks.common.block.entity;

import com.teamabnormals.woodworks.common.block.ClosetBlock;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.registry.WoodworksBlockEntityTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.LockCode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

public class ClosetBlockEntity extends ChestBlockEntity {
	public static final String CONTAINER_CLOSET = "container." + Woodworks.MOD_ID + ".closet";
	public static final String CONTAINER_CLOSET_DOUBLE = "container." + Woodworks.MOD_ID + ".closetDouble";

	private final ContainerOpenersCounter newOpenersCounter = new ContainerOpenersCounter() {
		protected void onOpen(Level level, BlockPos pos, BlockState state) {
			playSound(level, pos, state, WoodworksSoundEvents.BAMBOO_WOOD_CLOSET_OPEN.get());
		}

		protected void onClose(Level level, BlockPos pos, BlockState state) {
			playSound(level, pos, state, WoodworksSoundEvents.BAMBOO_WOOD_CLOSET_CLOSE.get());
		}

		@Override
		protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int num1, int num2) {
			ClosetBlockEntity.this.signalOpenCount(level, pos, state, num1, num2);
		}

		@Override
		protected boolean isOwnContainer(Player player) {
			if (!(player.containerMenu instanceof ChestMenu chestMenu)) {
				return false;
			} else {
				Container container = chestMenu.getContainer();
				return container == ClosetBlockEntity.this || container instanceof CompoundContainer compoundContainer && compoundContainer.contains(ClosetBlockEntity.this);
			}
		}
	};

	public static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent event) {
		ChestType chesttype = state.getValue(ChestBlock.TYPE);
		if (chesttype != ChestType.LEFT) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY() + 0.5D;
			double d2 = (double) pos.getZ() + 0.5D;
			if (chesttype == ChestType.RIGHT) {
				Direction direction = ClosetBlock.getConnectedDirection(state);
				d1 += (double) direction.getStepY() * 0.5D;
			}

			level.playSound(null, d0, d1, d2, event, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
		}
	}

	protected ClosetBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
		// this.openersCounter = this.newOpenersCounter;
	}

	public ClosetBlockEntity(BlockPos pos, BlockState state) {
		super(WoodworksBlockEntityTypes.CLOSET.get(), pos, state);
		// this.openersCounter = this.newOpenersCounter;
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable(CONTAINER_CLOSET);
	}

	@Override
	public boolean canOpen(Player player) {
		return canUnlock(player, this.lockKey, this.getDisplayName());
	}

	public static boolean canUnlock(Player player, LockCode code, Component component) {
		if (!player.isSpectator() && !code.unlocksWith(player.getMainHandItem())) {
			player.displayClientMessage(Component.translatable("container.isLocked", component), true);
			player.playNotifySound(WoodworksSoundEvents.BAMBOO_WOOD_CLOSET_LOCKED.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			return false;
		} else {
			return true;
		}
	}
}