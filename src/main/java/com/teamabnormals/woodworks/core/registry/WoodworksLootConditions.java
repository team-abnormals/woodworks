package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.woodworks.core.Woodworks;
import com.teamabnormals.woodworks.core.WoodworksConfig;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WoodworksLootConditions {
	public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registry.LOOT_ITEM_REGISTRY, Woodworks.MOD_ID);

	public static final RegistryObject<LootItemConditionType> CONFIG = LOOT_CONDITION_TYPES.register("config", () -> DataUtil.registerConfigCondition(Woodworks.MOD_ID, WoodworksConfig.COMMON));
}