package com.teamabnormals.woodworks.core.mixin;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.woodworks.core.other.WoodworksDataProcessors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractChestedHorse.class)
public final class AbstractChestedHorseMixin extends AbstractHorse {

	private AbstractChestedHorseMixin(EntityType<? extends AbstractHorse> horse, Level level) {
		super(horse, level);
	}

	@Inject(at = @At("RETURN"), method = "getSlot", cancellable = true)
	private void getSlot(int slot, CallbackInfoReturnable<SlotAccess> cir) {
		if ((AbstractHorse) this instanceof AbstractChestedHorse horse) {
			if (slot == 499) {
				IDataManager dataManager = (IDataManager) horse;
				Item chest = BuiltInRegistries.ITEM.get(dataManager.getValue(WoodworksDataProcessors.CHEST_VARIANT));
				cir.setReturnValue(
						new SlotAccess() {
							@Override
							public ItemStack get() {
								return horse.hasChest() ? new ItemStack(chest) : ItemStack.EMPTY;
							}

							@Override
							public boolean set(ItemStack stack) {
								if (stack.isEmpty()) {
									if (horse.hasChest()) {
										horse.setChest(false);
										horse.createInventory();
									}

									return true;
								} else if (isValidChest(stack)) {
									if (!horse.hasChest()) {
										dataManager.setValue(WoodworksDataProcessors.CHEST_VARIANT, BuiltInRegistries.ITEM.getKey(stack.getItem()));
										horse.setChest(true);
										horse.createInventory();
									}

									return true;
								} else {
									return false;
								}
							}
						}
				);
			}
		}
	}


	@Inject(at = @At("HEAD"), method = "equipChest")
	private void equipChest(Player player, ItemStack stack, CallbackInfo ci) {
		((IDataManager) (AbstractHorse) this).setValue(WoodworksDataProcessors.CHEST_VARIANT, BuiltInRegistries.ITEM.getKey(stack.getItem()));
	}

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/AbstractChestedHorse;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"), method = "dropEquipment")
	private ItemEntity dropEquipment(AbstractChestedHorse horse, ItemLike item) {
		ResourceLocation chestID = ((IDataManager) (AbstractHorse) this).getValue(WoodworksDataProcessors.CHEST_VARIANT);
		Item chest = BuiltInRegistries.ITEM.get(chestID);
		return horse.spawnAtLocation(chest != null ? chest : item);
	}

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"), method = "mobInteract")
	private boolean mobInteract(ItemStack stack, Item item) {
		return isValidChest(stack);
	}

	@Unique
	private static boolean isValidChest(ItemStack stack) {
		return stack.is(Tags.Items.CHESTS_WOODEN) && !stack.is(Tags.Items.CHESTS_TRAPPED);
	}
}