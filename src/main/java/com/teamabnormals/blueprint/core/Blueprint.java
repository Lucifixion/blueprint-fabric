package com.teamabnormals.blueprint.core;

import com.teamabnormals.blueprint.client.BlueprintShaders;
import com.teamabnormals.blueprint.client.RewardHandler;
import com.teamabnormals.blueprint.client.screen.splash.BlueprintSplashManager;
import com.teamabnormals.blueprint.core.api.conditions.BlueprintAndCondition;
import com.teamabnormals.blueprint.core.api.conditions.config.*;
import com.teamabnormals.blueprint.core.registry.*;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.fml.config.ModConfig;

public class Blueprint implements ModInitializer {
    @Override
    public void onInitialize() {
        this.registerMessages();

        CraftingHelper.register(new BlueprintAndCondition.Serializer());
        DataUtil.registerConfigPredicate(new EqualsPredicate.Serializer());
        DataUtil.registerConfigPredicate(new GreaterThanOrEqualPredicate.Serializer());
        DataUtil.registerConfigPredicate(new GreaterThanPredicate.Serializer());
        DataUtil.registerConfigPredicate(new LessThanOrEqualPredicate.Serializer());
        DataUtil.registerConfigPredicate(new LessThanPredicate.Serializer());
        DataUtil.registerConfigPredicate(new ContainsPredicate.Serializer());
        DataUtil.registerConfigPredicate(new MatchesPredicate.Serializer());

        REGISTRY_HELPER.getEntitySubHelper().register(bus);
        REGISTRY_HELPER.getBlockEntitySubHelper().register(bus);
        BlueprintHolderSets.HOLDER_SET_TYPES.register(bus);
        BlueprintPoiTypes.registerPois(); // BlueprintPoiTypes.POI_TYPES.register(bus);
        BlueprintSurfaceRules.RULE_SOURCES.register(bus);
        BlueprintLootConditions.LOOT_CONDITION_TYPES.register(bus);

        bus.addListener((ModConfigEvent event) -> {
            final ModConfig config = event.getConfig();
            if (config.getSpec() == BlueprintConfig.CLIENT_SPEC) {
                BlueprintConfig.CLIENT.load();
            }
        });

        BlueprintDataPackRegistries.registerRegistries(); // bus.addListener(BlueprintDataPackRegistries::registerRegistries);
        bus.addListener(this::registerOnEvent);
        bus.addListener(EventPriority.LOWEST, this::commonSetup);
        bus.addListener(EventPriority.LOWEST, this::postLoadingSetup);
        bus.addListener(this::dataSetup);
        bus.addListener(this::registerCapabilities);
        context.registerConfig(ModConfig.Type.CLIENT, BlueprintConfig.CLIENT_SPEC);
        context.registerConfig(ModConfig.Type.COMMON, BlueprintConfig.COMMON_SPEC);
    }
}
