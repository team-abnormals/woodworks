package com.teamabnormals.woodworks.core.registry;

import com.google.common.collect.ImmutableSet;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class WoodworksVillagerProfessions {
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Woodworks.MOD_ID);
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Woodworks.MOD_ID);

	public static final RegistryObject<PoiType> SAWMILL = POI_TYPES.register("sawmill", () -> new PoiType("carpenter", PoiType.getBlockStates(WoodworksBlocks.SAWMILL.get()), 1, 1));

	public static final RegistryObject<VillagerProfession> CARPENTER = PROFESSIONS.register("carpenter", () -> new VillagerProfession("woodworks:carpenter", SAWMILL.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_BUTCHER));

	//TODO: Carpenter Villager gift
	public static void setupVillagerHouses() {
		PlainVillagePools.bootstrap();
		SnowyVillagePools.bootstrap();
		SavannaVillagePools.bootstrap();
		DesertVillagePools.bootstrap();
		TaigaVillagePools.bootstrap();

		addVillagerHouse("plains", "carpenter_1");
		addVillagerHouse("snowy", "carpenter_1");
		addVillagerHouse("savanna", "carpenter_1");
		addVillagerHouse("desert", "carpenter_1");
		addVillagerHouse("taiga", "carpenter_1");
		addVillagerHouse("taiga", "carpenter_2");
	}

	private static void addVillagerHouse(String biome, String name) {
		DataUtil.addToJigsawPattern(new ResourceLocation("village/" + biome + "/houses"), StructurePoolElement.legacy(Woodworks.MOD_ID + ":village/" + biome + "/houses/" + biome + "_" + name).apply(StructureTemplatePool.Projection.RIGID), 2);
	}
}