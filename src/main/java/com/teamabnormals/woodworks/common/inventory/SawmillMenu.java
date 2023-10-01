package com.teamabnormals.woodworks.common.inventory;

import com.google.common.collect.Lists;
import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import com.teamabnormals.woodworks.core.registry.WoodworksBlocks;
import com.teamabnormals.woodworks.core.registry.WoodworksMenuTypes;
import com.teamabnormals.woodworks.core.registry.WoodworksRecipes.WoodworksRecipeTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class SawmillMenu extends AbstractContainerMenu {
	private final ContainerLevelAccess access;
	private final DataSlot selectedRecipeIndex = DataSlot.standalone();
	private final Level level;
	private List<SawmillRecipe> recipes = Lists.newArrayList();
	private ItemStack input = ItemStack.EMPTY;
	long lastSoundTime;
	final Slot inputSlot;
	final Slot resultSlot;
	Runnable slotUpdateListener = () -> {
	};
	public final Container container = new SimpleContainer(1) {
		@Override
		public void setChanged() {
			super.setChanged();
			SawmillMenu.this.slotsChanged(this);
			SawmillMenu.this.slotUpdateListener.run();
		}
	};
	final ResultContainer resultContainer = new ResultContainer();

	public SawmillMenu(int id, Inventory inventory) {
		this(id, inventory, ContainerLevelAccess.NULL);
	}

	public SawmillMenu(int id, Inventory p_40298_, final ContainerLevelAccess access) {
		super(WoodworksMenuTypes.SAWMILL.get(), id);
		this.access = access;
		this.level = p_40298_.player.level();
		this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
		this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {

			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			@Override
			public void onTake(Player player, ItemStack stack) {
				stack.onCraftedBy(player.level(), player, stack.getCount());
				SawmillMenu.this.resultContainer.awardUsedRecipes(player, this.getRelevantItems());
				ItemStack input = SawmillMenu.this.inputSlot.remove(1);
				if (!input.isEmpty()) {
					SawmillMenu.this.setupResultSlot();
				}

				access.execute((level, pos) -> {
					long time = level.getGameTime();
					if (SawmillMenu.this.lastSoundTime != time) {
						level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
						SawmillMenu.this.lastSoundTime = time;
					}

				});
				super.onTake(player, stack);
			}

			private List<ItemStack> getRelevantItems() {
				return List.of(SawmillMenu.this.inputSlot.getItem());
			}
		});

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(p_40298_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(p_40298_, k, 8 + k * 18, 142));
		}

		this.addDataSlot(this.selectedRecipeIndex);
	}

	public int getSelectedRecipeIndex() {
		return this.selectedRecipeIndex.get();
	}

	public List<SawmillRecipe> getRecipes() {
		return this.recipes;
	}

	public int getNumRecipes() {
		return this.recipes.size();
	}

	public boolean hasInputItem() {
		return this.inputSlot.hasItem() && !this.recipes.isEmpty();
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.access, player, WoodworksBlocks.SAWMILL.get());
	}

	@Override
	public boolean clickMenuButton(Player player, int index) {
		if (this.isValidRecipeIndex(index)) {
			this.selectedRecipeIndex.set(index);
			this.setupResultSlot();
		}

		return true;
	}

	private boolean isValidRecipeIndex(int p_40335_) {
		return p_40335_ >= 0 && p_40335_ < this.recipes.size();
	}

	@Override
	public void slotsChanged(Container container) {
		ItemStack itemstack = this.inputSlot.getItem();
		if (!itemstack.is(this.input.getItem())) {
			this.input = itemstack.copy();
			this.setupRecipeList(container, itemstack);
		}
	}

	private void setupRecipeList(Container container, ItemStack stack) {
		this.recipes.clear();
		this.selectedRecipeIndex.set(-1);
		this.resultSlot.set(ItemStack.EMPTY);
		if (!stack.isEmpty()) {
			this.recipes = this.level.getRecipeManager().getRecipesFor(WoodworksRecipeTypes.SAWING.get(), container, this.level);
		}
	}

	void setupResultSlot() {
		if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
			SawmillRecipe recipe = this.recipes.get(this.selectedRecipeIndex.get());
			ItemStack stack = recipe.assemble(this.container, this.level.registryAccess());
			if (stack.isItemEnabled(this.level.enabledFeatures())) {
				this.resultContainer.setRecipeUsed(recipe);
				this.resultSlot.set(stack);
			} else {
				this.resultSlot.set(ItemStack.EMPTY);
			}
		} else {
			this.resultSlot.set(ItemStack.EMPTY);
		}

		this.broadcastChanges();
	}

	@Override
	public MenuType<?> getType() {
		return WoodworksMenuTypes.SAWMILL.get();
	}

	public void registerUpdateListener(Runnable listener) {
		this.slotUpdateListener = listener;
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != this.resultContainer && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack input = slot.getItem();
			Item item = input.getItem();
			stack = input.copy();
			if (index == 1) {
				item.onCraftedBy(input, player.level(), player);
				if (!this.moveItemStackTo(input, 2, 38, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(input, stack);
			} else if (index == 0) {
				if (!this.moveItemStackTo(input, 2, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (this.level.getRecipeManager().getRecipeFor(WoodworksRecipeTypes.SAWING.get(), new SimpleContainer(input), this.level).isPresent()) {
				if (!this.moveItemStackTo(input, 0, 1, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 2 && index < 29) {
				if (!this.moveItemStackTo(input, 29, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 29 && index < 38 && !this.moveItemStackTo(input, 2, 29, false)) {
				return ItemStack.EMPTY;
			}

			if (input.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}

			slot.setChanged();
			if (input.getCount() == stack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, input);
			this.broadcastChanges();
		}

		return stack;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.resultContainer.removeItemNoUpdate(1);
		this.access.execute((p_40313_, p_40314_) -> {
			this.clearContainer(player, this.container);
		});
	}
}