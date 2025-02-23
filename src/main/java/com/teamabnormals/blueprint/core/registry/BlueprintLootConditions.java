package com.teamabnormals.blueprint.core.registry;

import com.teamabnormals.blueprint.core.BlueprintForge;
import com.teamabnormals.blueprint.core.BlueprintConfig;
import com.teamabnormals.blueprint.core.api.conditions.loot.RaidCheckCondition.RaidCheckSerializer;
import com.teamabnormals.blueprint.core.api.conditions.loot.RandomDifficultyChanceCondition.RandomDifficultyChanceSerializer;
import com.teamabnormals.blueprint.core.util.DataUtil;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registry class for Blueprint's built-in loot conditions.
 *
 * <p>These conditions can be used by mods and datapacks.</p>
 *
 * @author abigailfails
 */
public final class BlueprintLootConditions {
	public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, BlueprintForge.MOD_ID);

	public static final RegistryObject<LootItemConditionType> RANDOM_DIFFICULTY_CHANCE = LOOT_CONDITION_TYPES.register("random_difficulty_chance", () -> new LootItemConditionType(new RandomDifficultyChanceSerializer()));
	public static final RegistryObject<LootItemConditionType> RAID_CHECK = LOOT_CONDITION_TYPES.register("raid_check", () -> new LootItemConditionType(new RaidCheckSerializer()));
	public static final RegistryObject<LootItemConditionType> CONFIG = LOOT_CONDITION_TYPES.register("config", () -> DataUtil.registerConfigCondition(BlueprintForge.MOD_ID, BlueprintConfig.CLIENT, BlueprintConfig.CLIENT.slabfishSettings));
}
