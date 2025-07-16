package com.cursee.more_bows_and_arrows.core.util;

import com.cursee.more_bows_and_arrows.Constants;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeRegistryHelper {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Constants.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Constants.MOD_ID);
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE.key(), Constants.MOD_ID);

    @SuppressWarnings("unchecked")
    public static <T> DeferredRegister<T> deferredRegisterFor(Registry<T> objRegistry) {

        if (objRegistry.key().location() == BuiltInRegistries.ITEM.key().location()) return (DeferredRegister<T>) ITEMS;
        if (objRegistry.key().location() == BuiltInRegistries.CREATIVE_MODE_TAB.key().location()) return (DeferredRegister<T>) TABS;
        if (objRegistry.key().location() == BuiltInRegistries.ENTITY_TYPE.key().location()) return (DeferredRegister<T>) ENTITIES;
        if (objRegistry.key().location() == BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE.key().location()) return (DeferredRegister<T>) ENCHANTMENT_ENTITY_EFFECTS;

        throw new IllegalArgumentException("No registry linked in NeoForge module to register type: " + objRegistry.key());
        // return null; // throws an error if registering to undefined/unlinked NeoForge registry
    }
}
