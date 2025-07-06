package com.cursee.more_bows_and_arrows.platform.services;

import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.world.item.enchantment.AntiGravityEnchantmentEffect;
import com.cursee.more_bows_and_arrows.core.world.item.enchantment.ModEnchantment;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the root directory of the minecraft instance.
     *
     * @return The String form of the minecraft instance's root folder.
     */
    String getGameDirectory();

    /**
     * Check if the mod is loaded in a client instance.
     *
     * @return True if loaded on a client, false otherwise.
     */
    boolean isClientSide();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    CreativeModeTab creativeModeTab(Supplier<ItemStack> icon, Component title, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator);

    <T extends Entity> EntityType.Builder<T> createEntityType(BiFunction<EntityType<T>, Level, T> constructor, MobCategory category);

    <T extends EnchantmentEntityEffect> MapCodec<T> createEnchantmentEffect(String name, MapCodec<T> codec);

    // MapCodec<? extends EnchantmentEntityEffect> getEnchantmentReference(ModEnchantment enchantment);

    <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier);

    CreativeModeTab.Builder tabBuilder();
}