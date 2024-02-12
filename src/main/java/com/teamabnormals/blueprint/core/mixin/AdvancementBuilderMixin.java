package com.teamabnormals.blueprint.core.mixin;

import com.google.gson.JsonObject;
import com.teamabnormals.blueprint.core.events.AdvancementBuildingEvent;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.DeserializationContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Advancement.Builder.class)
public final class AdvancementBuilderMixin {

	@Inject(at = @At("RETURN"), method = "fromJson", cancellable = true, remap = false)
	private static void modifyBuilder(JsonObject jsonObject, DeserializationContext conditionParser, CallbackInfoReturnable<Advancement.Builder> info) {
		Advancement.Builder builder = info.getReturnValue();
//		AdvancementBuildingEvent.onBuildingAdvancement(builder, conditionParser.getAdvancementId());
		AdvancementBuildingEvent.EVENT.invoker().instance(builder, conditionParser.getAdvancementId());
		info.setReturnValue(builder);
	}

}
