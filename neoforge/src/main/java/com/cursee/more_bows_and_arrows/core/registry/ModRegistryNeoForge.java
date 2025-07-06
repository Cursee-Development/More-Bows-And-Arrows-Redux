package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrowsNeoForge;
import com.cursee.more_bows_and_arrows.core.util.NeoForgeRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModRegistryNeoForge {

    public static void register(final IEventBus modEventBus) {
        NeoForgeRegistryHelper.ENCHANTMENT_ENTITY_EFFECTS.register(modEventBus);
        NeoForgeRegistryHelper.ITEMS.register(modEventBus);
        NeoForgeRegistryHelper.TABS.register(modEventBus);
        NeoForgeRegistryHelper.ENTITIES.register(modEventBus);
    }
}
