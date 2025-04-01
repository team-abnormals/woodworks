package com.teamabnormals.woodworks.core.data.client;

import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

import javax.annotation.Nullable;
import java.util.List;

import static com.teamabnormals.woodworks.core.registry.WoodworksSoundEvents.*;

public class WoodworksSoundDefinitionsProvider extends SoundDefinitionsProvider {

	public WoodworksSoundDefinitionsProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Woodworks.MOD_ID, helper);
	}

	@Override
	public void registerSounds() {
		this.add(CHERRY_WOOD_LADDER_BREAK.get(), vanilla("block/cherry_wood/break", 5, "subtitles.block.generic.break"));
		this.add(CHERRY_WOOD_LADDER_STEP.get(), define("block/cherry_wood_ladder/step", 5, "subtitles.block.generic.footsteps"));
		this.add(CHERRY_WOOD_LADDER_PLACE.get(), vanilla("block/cherry_wood/break", 5, "subtitles.block.generic.place"));
		this.add(CHERRY_WOOD_LADDER_HIT.get(), define("block/cherry_wood_ladder/step", 5, "subtitles.block.generic.hit"));
		this.add(CHERRY_WOOD_LADDER_FALL.get(), define("block/cherry_wood_ladder/step", 5));

		this.add(CHERRY_WOOD_CHISELED_BOOKSHELF_BREAK.get(), define("block/cherry_wood_chiseled_bookshelf/break", 6, "subtitles.block.generic.break"));
		this.add(CHERRY_WOOD_CHISELED_BOOKSHELF_STEP.get(), define("block/cherry_wood_chiseled_bookshelf/step", 5, "subtitles.block.generic.footsteps"));
		this.add(CHERRY_WOOD_CHISELED_BOOKSHELF_PLACE.get(), define("block/cherry_wood_chiseled_bookshelf/break", 6, "subtitles.block.generic.place"));
		this.add(CHERRY_WOOD_CHISELED_BOOKSHELF_HIT.get(), define("block/cherry_wood_chiseled_bookshelf/step", 5, "subtitles.block.generic.hit"));
		this.add(CHERRY_WOOD_CHISELED_BOOKSHELF_FALL.get(), define("block/cherry_wood_chiseled_bookshelf/step", 5));

		this.add(BAMBOO_WOOD_LADDER_BREAK.get(), vanilla("block/bamboo_wood/break", 5, "subtitles.block.generic.break", 0.9F));
		this.add(BAMBOO_WOOD_LADDER_STEP.get(), define("block/bamboo_wood_ladder/step", 5, "subtitles.block.generic.footsteps"));
		this.add(BAMBOO_WOOD_LADDER_PLACE.get(), vanilla("block/bamboo_wood/break", 5, "subtitles.block.generic.place", 0.9F));
		this.add(BAMBOO_WOOD_LADDER_HIT.get(), define("block/bamboo_wood_ladder/step", 5, "subtitles.block.generic.hit"));
		this.add(BAMBOO_WOOD_LADDER_FALL.get(), define("block/bamboo_wood_ladder/step", 5));

		this.add(BAMBOO_WOOD_CHISELED_BOOKSHELF_BREAK.get(), define("block/bamboo_wood_chiseled_bookshelf/break", 6, "subtitles.block.generic.break"));
		this.add(BAMBOO_WOOD_CHISELED_BOOKSHELF_STEP.get(), define("block/bamboo_wood_chiseled_bookshelf/step", 5, "subtitles.block.generic.footsteps"));
		this.add(BAMBOO_WOOD_CHISELED_BOOKSHELF_PLACE.get(), define("block/bamboo_wood_chiseled_bookshelf/break", 6, "subtitles.block.generic.place"));
		this.add(BAMBOO_WOOD_CHISELED_BOOKSHELF_HIT.get(), define("block/bamboo_wood_chiseled_bookshelf/step", 5, "subtitles.block.generic.hit"));
		this.add(BAMBOO_WOOD_CHISELED_BOOKSHELF_FALL.get(), define("block/bamboo_wood_chiseled_bookshelf/step", 5));

		this.add(BAMBOO_WOOD_CLOSET_OPEN.get(), define("block/bamboo_wood_closet/open", 1, "subtitles.woodworks.block.closet.open"));
		this.add(BAMBOO_WOOD_CLOSET_CLOSE.get(), define("block/bamboo_wood_closet/close", 1, "subtitles.woodworks.block.closet.close"));
		this.add(BAMBOO_WOOD_CLOSET_LOCKED.get(), vanilla(List.of("block/chest/close_locked", "block/chest/open_locked"), "subtitles.woodworks.block.closet.locked"));

		this.add(NETHER_WOOD_LADDER_BREAK.get(), vanilla("block/nether_wood/break", 4, "subtitles.block.generic.break"));
		this.add(NETHER_WOOD_LADDER_STEP.get(), define("block/nether_wood_ladder/step", 5, "subtitles.block.generic.footsteps"));
		this.add(NETHER_WOOD_LADDER_PLACE.get(), vanilla("block/nether_wood/break", 4, "subtitles.block.generic.place"));
		this.add(NETHER_WOOD_LADDER_HIT.get(), define("block/nether_wood_ladder/step", 5, "subtitles.block.generic.hit"));
		this.add(NETHER_WOOD_LADDER_FALL.get(), define("block/nether_wood_ladder/step", 5));

		this.add(NETHER_WOOD_CHISELED_BOOKSHELF_BREAK.get(), define("block/nether_wood_chiseled_bookshelf/break", 6, "subtitles.block.generic.break"));
		this.add(NETHER_WOOD_CHISELED_BOOKSHELF_PLACE.get(), define("block/nether_wood_chiseled_bookshelf/break", 6, "subtitles.block.generic.place"));
		this.add(NETHER_WOOD_CHISELED_BOOKSHELF_STEP.get(), define("block/nether_wood_chiseled_bookshelf/step", 5, "subtitles.block.generic.footsteps"));
		this.add(NETHER_WOOD_CHISELED_BOOKSHELF_HIT.get(), define("block/nether_wood_chiseled_bookshelf/step", 5, "subtitles.block.generic.hit"));
		this.add(NETHER_WOOD_CHISELED_BOOKSHELF_FALL.get(), define("block/nether_wood_chiseled_bookshelf/step", 5));
	}

	public static SoundDefinition vanilla(String path, int count, String subtitle) {
		return define("minecraft", path, count, subtitle, 1.0F);
	}

	public static SoundDefinition vanilla(String path, int count, String subtitle, float volume) {
		return define("minecraft", path, count, subtitle, volume);
	}

	public static SoundDefinition define(String path, int count) {
		return define(path, count, null);
	}

	public static SoundDefinition define(String path, int count, String subtitle) {
		return define(Woodworks.MOD_ID, path, count, subtitle, 1.0F);
	}

	public static SoundDefinition define(String modid, String path, int count, @Nullable String subtitle, float volume) {
		SoundDefinition definition = definition();
		if (subtitle != null) {
			definition.subtitle(subtitle);
		}

		for (int i = 1; i <= count; i++) {
			definition.with(sound(ResourceLocation.fromNamespaceAndPath(modid, path + i)).volume(volume));
		}

		return definition;
	}

	public static SoundDefinition vanilla(List<String> paths, String subtitle) {
		return define("minecraft", paths, subtitle, 1.0F);
	}

	public static SoundDefinition define(String modid, List<String> strings, @Nullable String subtitle, float volume) {
		SoundDefinition definition = definition();
		if (subtitle != null) {
			definition.subtitle(subtitle);
		}

		for (String path : strings) {
			definition.with(sound(ResourceLocation.fromNamespaceAndPath(modid, path)).volume(volume));
		}

		return definition;
	}
}