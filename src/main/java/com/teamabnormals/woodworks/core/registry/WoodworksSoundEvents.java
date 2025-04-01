package com.teamabnormals.woodworks.core.registry;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import com.teamabnormals.woodworks.core.Woodworks;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class WoodworksSoundEvents {
	public static final SoundSubRegistryHelper SOUND_EVENTS = Woodworks.REGISTRY_HELPER.getSoundSubHelper();

	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_LADDER_BREAK = SOUND_EVENTS.createSoundEvent("block.cherry_wood_ladder.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_LADDER_STEP = SOUND_EVENTS.createSoundEvent("block.cherry_wood_ladder.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_LADDER_PLACE = SOUND_EVENTS.createSoundEvent("block.cherry_wood_ladder.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_LADDER_HIT = SOUND_EVENTS.createSoundEvent("block.cherry_wood_ladder.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_LADDER_FALL = SOUND_EVENTS.createSoundEvent("block.cherry_wood_ladder.fall");

	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_CHISELED_BOOKSHELF_BREAK = SOUND_EVENTS.createSoundEvent("block.cherry_wood_chiseled_bookshelf.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_CHISELED_BOOKSHELF_STEP = SOUND_EVENTS.createSoundEvent("block.cherry_wood_chiseled_bookshelf.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_CHISELED_BOOKSHELF_PLACE = SOUND_EVENTS.createSoundEvent("block.cherry_wood_chiseled_bookshelf.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_CHISELED_BOOKSHELF_HIT = SOUND_EVENTS.createSoundEvent("block.cherry_wood_chiseled_bookshelf.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> CHERRY_WOOD_CHISELED_BOOKSHELF_FALL = SOUND_EVENTS.createSoundEvent("block.cherry_wood_chiseled_bookshelf.fall");

	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CLOSET_OPEN = SOUND_EVENTS.createSoundEvent("block.closet.open");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CLOSET_CLOSE = SOUND_EVENTS.createSoundEvent("block.closet.close");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CLOSET_LOCKED = SOUND_EVENTS.createSoundEvent("block.closet.locked");

	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_LADDER_BREAK = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_ladder.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_LADDER_STEP = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_ladder.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_LADDER_PLACE = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_ladder.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_LADDER_HIT = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_ladder.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_LADDER_FALL = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_ladder.fall");

	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CHISELED_BOOKSHELF_BREAK = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_chiseled_bookshelf.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CHISELED_BOOKSHELF_STEP = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_chiseled_bookshelf.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CHISELED_BOOKSHELF_PLACE = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_chiseled_bookshelf.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CHISELED_BOOKSHELF_HIT = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_chiseled_bookshelf.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> BAMBOO_WOOD_CHISELED_BOOKSHELF_FALL = SOUND_EVENTS.createSoundEvent("block.bamboo_wood_chiseled_bookshelf.fall");

	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_LADDER_BREAK = SOUND_EVENTS.createSoundEvent("block.nether_wood_ladder.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_LADDER_STEP = SOUND_EVENTS.createSoundEvent("block.nether_wood_ladder.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_LADDER_PLACE = SOUND_EVENTS.createSoundEvent("block.nether_wood_ladder.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_LADDER_HIT = SOUND_EVENTS.createSoundEvent("block.nether_wood_ladder.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_LADDER_FALL = SOUND_EVENTS.createSoundEvent("block.nether_wood_ladder.fall");

	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_CHISELED_BOOKSHELF_BREAK = SOUND_EVENTS.createSoundEvent("block.nether_wood_chiseled_bookshelf.break");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_CHISELED_BOOKSHELF_STEP = SOUND_EVENTS.createSoundEvent("block.nether_wood_chiseled_bookshelf.step");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_CHISELED_BOOKSHELF_PLACE = SOUND_EVENTS.createSoundEvent("block.nether_wood_chiseled_bookshelf.place");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_CHISELED_BOOKSHELF_HIT = SOUND_EVENTS.createSoundEvent("block.nether_wood_chiseled_bookshelf.hit");
	public static final DeferredHolder<SoundEvent, SoundEvent> NETHER_WOOD_CHISELED_BOOKSHELF_FALL = SOUND_EVENTS.createSoundEvent("block.nether_wood_chiseled_bookshelf.fall");

	public static class WoodworksSoundTypes {
		public static final DeferredSoundType CHERRY_WOOD_CHISELED_BOOKSHELF = new DeferredSoundType(1.0F, 1.0F, CHERRY_WOOD_CHISELED_BOOKSHELF_BREAK, CHERRY_WOOD_CHISELED_BOOKSHELF_STEP, CHERRY_WOOD_CHISELED_BOOKSHELF_PLACE, CHERRY_WOOD_CHISELED_BOOKSHELF_HIT, CHERRY_WOOD_CHISELED_BOOKSHELF_FALL);
		public static final DeferredSoundType CHERRY_WOOD_LADDER = new DeferredSoundType(1.0F, 1.0F, CHERRY_WOOD_LADDER_BREAK, CHERRY_WOOD_LADDER_STEP, CHERRY_WOOD_LADDER_PLACE, CHERRY_WOOD_LADDER_HIT, CHERRY_WOOD_LADDER_FALL);

		public static final DeferredSoundType BAMBOO_WOOD_CHISELED_BOOKSHELF = new DeferredSoundType(1.0F, 1.0F, BAMBOO_WOOD_CHISELED_BOOKSHELF_BREAK, BAMBOO_WOOD_CHISELED_BOOKSHELF_STEP, BAMBOO_WOOD_CHISELED_BOOKSHELF_PLACE, BAMBOO_WOOD_CHISELED_BOOKSHELF_HIT, BAMBOO_WOOD_CHISELED_BOOKSHELF_FALL);
		public static final DeferredSoundType BAMBOO_WOOD_LADDER = new DeferredSoundType(1.0F, 1.0F, BAMBOO_WOOD_LADDER_BREAK, BAMBOO_WOOD_LADDER_STEP, BAMBOO_WOOD_LADDER_PLACE, BAMBOO_WOOD_LADDER_HIT, BAMBOO_WOOD_LADDER_FALL);

		public static final DeferredSoundType NETHER_WOOD_CHISELED_BOOKSHELF = new DeferredSoundType(1.0F, 1.0F, NETHER_WOOD_CHISELED_BOOKSHELF_BREAK, NETHER_WOOD_CHISELED_BOOKSHELF_STEP, NETHER_WOOD_CHISELED_BOOKSHELF_PLACE, NETHER_WOOD_CHISELED_BOOKSHELF_HIT, NETHER_WOOD_CHISELED_BOOKSHELF_FALL);
		public static final DeferredSoundType NETHER_WOOD_LADDER = new DeferredSoundType(1.0F, 1.0F, NETHER_WOOD_LADDER_BREAK, NETHER_WOOD_LADDER_STEP, NETHER_WOOD_LADDER_PLACE, NETHER_WOOD_LADDER_HIT, NETHER_WOOD_LADDER_FALL);
	}
}
