package com.teamabnormals.blueprint.core.registry;

import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.stream.Stream;

public final class BlueprintPoiTypes {
	public static final ResourceKey<PoiType> BEEHIVE = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation("beehive"));

	public static void registerPois() {
		HashSet<BlockState> set = Stream.of(BlockEntitySubRegistryHelper.collectBlocks(BlueprintBeehiveBlock.class)).map(block -> block.getStateDefinition().getPossibleStates()).collect(HashSet::new, HashSet::addAll, HashSet::addAll);
		PointOfInterestHelper.register(BEEHIVE.location(),0, 1, set);
	}
}
