package com.teamabnormals.blueprint.core.registry;

import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.entity.*;
import com.teamabnormals.blueprint.core.BlueprintForge;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registry class for the built-in {@link BlockEntityType}s.
 */
@Mod.EventBusSubscriber(modid = BlueprintForge.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BlueprintBlockEntityTypes {
	public static final BlockEntitySubRegistryHelper HELPER = BlueprintForge.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final RegistryObject<BlockEntityType<BlueprintSignBlockEntity>> SIGN = HELPER.createBlockEntity("sign", BlueprintSignBlockEntity::new, () -> BlueprintSignBlockEntity.VALID_BLOCKS);
	public static final RegistryObject<BlockEntityType<BlueprintHangingSignBlockEntity>> HANGING_SIGN = HELPER.createBlockEntity("hanging_sign", BlueprintHangingSignBlockEntity::new, () -> BlueprintHangingSignBlockEntity.VALID_BLOCKS);
	public static final RegistryObject<BlockEntityType<BlueprintBeehiveBlockEntity>> BEEHIVE = HELPER.createBlockEntity("beehive", BlueprintBeehiveBlockEntity::new, BlueprintBeehiveBlock.class);
	public static final RegistryObject<BlockEntityType<BlueprintChiseledBookShelfBlockEntity>> CHISELED_BOOKSHELF = HELPER.createBlockEntity("chiseled_bookshelf", BlueprintChiseledBookShelfBlockEntity::new, BlueprintChiseledBookShelfBlock.class);
	public static final RegistryObject<BlockEntityType<BlueprintChestBlockEntity>> CHEST = HELPER.createBlockEntity("chest", BlueprintChestBlockEntity::new, BlueprintChestBlock.class);
	public static final RegistryObject<BlockEntityType<BlueprintTrappedChestBlockEntity>> TRAPPED_CHEST = HELPER.createBlockEntity("trapped_chest", BlueprintTrappedChestBlockEntity::new, BlueprintTrappedChestBlock.class);
}