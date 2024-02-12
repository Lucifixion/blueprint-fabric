package com.teamabnormals.blueprint.core.events;

import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModificationManager;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

/**
 * An event fired for when an {@link Advancement} is being deserialized and built.
 *
 * @author SmellyModder (Luke Tonon)
 */
public interface AdvancementBuildingEvent {

	Event<AdvancementBuildingEvent> EVENT = EventFactory.createArrayBacked(AdvancementBuildingEvent.class,
			(listeners) -> (builder, location) -> {
				for (AdvancementBuildingEvent listener : listeners) {
					AdvancementBuildingEventInstance instance = listener.instance(builder, location);
					if (AdvancementModificationManager.getInstance() != null) AdvancementModificationManager.getInstance().applyModifiers(priority, instance.location(), instance.builder());
				}
				return null;
			});

	AdvancementBuildingEventInstance instance(Advancement.Builder builder, ResourceLocation location);

	record AdvancementBuildingEventInstance(Advancement.Builder builder, ResourceLocation location) {

		/**
		 * Gets the {@link Advancement.Builder} of this event.
		 *
		 * @return The {@link Advancement.Builder} of this event.
		 */
		@Override
		public Advancement.Builder builder() {
			return this.builder;
		}

		/**
		 * Gets the {@link ResourceLocation} name of the {@link Advancement} for this event.
		 *
		 * @return The {@link ResourceLocation} name of the {@link Advancement} for this event.
		 */
		@Override
		public ResourceLocation location() {
			return this.location;
		}
	}


}
