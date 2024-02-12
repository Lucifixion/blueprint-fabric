package com.teamabnormals.blueprint.core.mixin.client;

import com.teamabnormals.blueprint.client.screen.splash.BlueprintSplashManager;
import com.teamabnormals.blueprint.client.screen.splash.Splash;
import com.teamabnormals.blueprint.client.screen.splash.SplashManagerAccessor;
import net.minecraft.client.User;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SplashManager.class)
public abstract class SplashManagerMixin implements SplashManagerAccessor {
	@Shadow
	@Final
	private static RandomSource RANDOM;
	@Shadow
	@Final
	private List<String> splashes;
	@Shadow
	@Final
	private User user;

	@Shadow @Nullable public abstract SplashRenderer getSplash();

	@Inject(method = "getSplash", at = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/SplashManager;user:Lnet/minecraft/client/User;", shift = At.Shift.AFTER), cancellable = true)
	private void handleBlueprintEventSplashes(CallbackInfoReturnable<SplashRenderer> info) {
		String randomEventSplash = BlueprintSplashManager.getRandomEventSplash(this.user, RANDOM);
		if (randomEventSplash != null) info.setReturnValue(new SplashRenderer(randomEventSplash));
	}

	@Inject(method = "getSplash", at = @At(value = "RETURN", ordinal = 4), cancellable = true)
	private void handleBlueprintRandomSplashes(CallbackInfoReturnable<SplashRenderer> info) {
		SplashRenderer splashRenderer = info.getReturnValue();
		String splash = splashRenderer.splash;
		Splash identifiedSplash = BlueprintSplashManager.getSplashForIdentifier(splash);
		if (identifiedSplash != null) {
			splash = identifiedSplash.getText(this.user, RANDOM);
			// If the identified splash wants to get skipped then we rerun the method.
			// Not an amazing solution, but needed in case other mods add custom splashes.
			info.setReturnValue(splash != null ? new SplashRenderer(splash) : this.getSplash());
		}
	}

	@Override
	public List<String> getSplashes() {
		return this.splashes;
	}
}
