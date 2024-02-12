package com.teamabnormals.blueprint.core.util.registry;

import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiFunction;

/**
 * A basic {@link AbstractSubRegistryHelper} for entities.
 * <p>This contains some useful registering methods for entities.</p>
 *
 * @author SmellyModder (Luke Tonon)
 * @see AbstractSubRegistryHelper
 */
public class EntitySubRegistryHelper extends AbstractSubRegistryHelper<EntityType<?>> {

	public EntitySubRegistryHelper(RegistryHelper parent, DeferredRegister<EntityType<?>> deferredRegister) {
		super(parent, deferredRegister);
	}

	public EntitySubRegistryHelper(RegistryHelper parent) {
		super(parent, DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, parent.getModId()));
	}

	/**
	 * Creates and registers an {@link EntityType} with the type of a {@link LivingEntity}.
	 *
	 * @param name                 The entity's name.
	 * @param factory              The entity's factory.
	 * @param entityClassification The entity's classification.
	 * @param width                The width of the entity's bounding box.
	 * @param height               The height of the entity's bounding box.
	 * @return A {@link RegistryObject} containing the created {@link EntityType}.
	 */
	public <E extends LivingEntity> RegistryObject<EntityType<E>> createLivingEntity(String name, EntityType.EntityFactory<E> factory, MobCategory entityClassification, float width, float height) {
		return this.deferredRegister.register(name, () -> createLivingEntity(factory, entityClassification, name, width, height));
	}

	/**
	 * Creates and registers an {@link EntityType} with the type of {@link Entity}.
	 *
	 * @param name                 The entity's name.
	 * @param factory              The entity's factory.
	 * @param clientFactory        The entity's client factory.
	 * @param entityClassification The entity's classification.
	 * @param width                The width of the entity's bounding box.
	 * @param height               The height of the entity's bounding box.
	 * @return A {@link RegistryObject} containing the created {@link EntityType}.
	 */
	public <E extends Entity> RegistryObject<EntityType<E>> createEntity(String name, EntityType.EntityFactory<E> factory, BiFunction<PlayMessages.SpawnEntity, Level, E> clientFactory, MobCategory entityClassification, float width, float height) {
		return this.deferredRegister.register(name, () -> createEntity(factory, clientFactory, entityClassification, name, width, height));
	}

	/**
	 * Creates an {@link EntityType} with the type of {@link LivingEntity}.
	 *
	 * @param name                 The entity's name.
	 * @param factory              The entity's factory.
	 * @param entityClassification The entity's classification.
	 * @param width                The width of the entity's bounding box.
	 * @param height               The height of the entity's bounding box.
	 * @return The created {@link EntityType}.
	 */
	public <E extends LivingEntity> EntityType<E> createLivingEntity(EntityType.EntityFactory<E> factory, MobCategory entityClassification, String name, float width, float height) {
		ResourceLocation location = this.parent.prefix(name);
		return EntityType.Builder.of(factory, entityClassification)
				.sized(width, height)
				.setTrackingRange(64)
				.setShouldReceiveVelocityUpdates(true)
				.setUpdateInterval(3)
				.build(location.toString());
	}

	/**
	 * Creates an {@link EntityType} with the type of {@link Entity}.
	 *
	 * @param name                 The entity's name.
	 * @param factory              The entity's factory.
	 * @param clientFactory        The entity's client factory.
	 * @param entityClassification The entity's classification.
	 * @param width                The width of the entity's bounding box.
	 * @param height               The height of the entity's bounding box.
	 * @return The created {@link EntityType}.
	 */
	public <E extends Entity> EntityType<E> createEntity(EntityType.EntityFactory<E> factory, BiFunction<PlayMessages.SpawnEntity, Level, E> clientFactory, MobCategory entityClassification, String name, float width, float height) {
		ResourceLocation location = this.parent.prefix(name);
		return EntityType.Builder.of(factory, entityClassification)
				.sized(width, height)
				.setTrackingRange(64)
				.setShouldReceiveVelocityUpdates(true)
				.setUpdateInterval(3)
				.setCustomClientFactory(clientFactory)
				.build(location.toString());
	}

}
