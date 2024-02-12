package com.teamabnormals.blueprint.common.capability.chunkloading;

import com.teamabnormals.blueprint.core.BlueprintForge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * The event class for internal listeners related to the chunk loading capability.
 *
 * @author SmellyModder(Luke Tonon)
 */
public class ChunkLoaderEvents {

	@SubscribeEvent
	public void attachChunkLoaderCap(AttachCapabilitiesEvent<Level> event) {
		Level level = event.getObject();
		if (level instanceof ServerLevel) {
			event.addCapability(new ResourceLocation(BlueprintForge.MOD_ID, "chunk_loader"), new ChunkLoaderCapability.Provider((ServerLevel) level));
		}
	}

	@SubscribeEvent
	public void tickChunkLoader(TickEvent.LevelTickEvent event) {
		Level level = event.level;
		if (!level.isClientSide && event.phase == Phase.START) {
			level.getCapability(ChunkLoaderCapability.CHUNK_LOAD_CAP).ifPresent(IChunkLoader::tick);
		}
	}

}