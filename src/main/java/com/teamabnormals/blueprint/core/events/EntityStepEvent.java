package com.teamabnormals.blueprint.core.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * This event is fired when an {@link Entity} steps on a block.
 * <p>Cancelling this event will prevent {@link net.minecraft.world.level.block.Block#stepOn} in the block's class from being called.</p>
 *
 * @author GoopSwagger
 */
public interface EntityStepEvent {

	Event<EntityStepEvent> EVENT = EventFactory.createArrayBacked(EntityStepEvent.class,
			(listeners) -> (level, pos, state, entity) -> {
				for (EntityStepEvent listener : listeners) {
					boolean cancel = listener.instance(level, pos, state, entity);
					if (cancel)
						return false;
				}
				return true;
			});

	boolean instance(Level level, BlockPos pos, BlockState state, Entity entity);
}
