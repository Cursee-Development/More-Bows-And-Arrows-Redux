package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrowsForge;
import com.cursee.more_bows_and_arrows.core.util.ForgeRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModRegistryForge {

    public static void register(final IEventBus modEventBus) {
        ForgeRegistryHelper.ENCHANTMENT_ENTITY_EFFECTS.register(modEventBus);
        ForgeRegistryHelper.ITEMS.register(modEventBus);
        ForgeRegistryHelper.TABS.register(modEventBus);
        ForgeRegistryHelper.ENTITIES.register(modEventBus);
    }
}
