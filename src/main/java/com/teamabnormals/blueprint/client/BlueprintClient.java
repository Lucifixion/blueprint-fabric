package com.teamabnormals.blueprint.client;

import com.teamabnormals.blueprint.client.screen.splash.BlueprintSplashManager;
import com.teamabnormals.blueprint.core.BlueprintForge;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;

public class BlueprintClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        bus.addListener(EventPriority.NORMAL, false, RegisterColorHandlersEvent.Block.class, event -> {
            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
            if (resourceManager instanceof ReloadableResourceManager) {
                ((ReloadableResourceManager) resourceManager).registerReloadListener(ENDIMATION_LOADER);
            }
        });
        bus.addListener(EventPriority.NORMAL, false, ModConfigEvent.Reloading.class, event -> {
            if (event.getConfig().getModId().equals(BlueprintForge.MOD_ID))
                NetworkUtil.updateSlabfish(RewardHandler.SlabfishSetting.getConfig());
        });
        bus.addListener(this::clientSetup);
        bus.addListener(this::modelSetup);
        bus.addListener(this::registerLayerDefinitions);
        bus.addListener(this::rendererSetup);
        bus.addListener(CreativeModeTabContentsPopulator::onBuildCreativeModeTabContents);
        bus.addListener(BlueprintSplashManager::onRegisterClientReloadListeners);
        bus.addListener(RewardHandler::clientSetup);
        bus.addListener(RewardHandler::addLayers);

        BlueprintShaders.registerShaders();
    }
}
