package com.teamabnormals.blueprint.core.data.server.tags;

import com.teamabnormals.blueprint.core.registry.BlueprintPoiTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;

import java.util.concurrent.CompletableFuture;

public class BlueprintPoiTypeTagsProvider extends PoiTypeTagsProvider {

	public BlueprintPoiTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(PoiTypeTags.BEE_HOME).add(BlueprintPoiTypes.BEEHIVE);
	}

}
