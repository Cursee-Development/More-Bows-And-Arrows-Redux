package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrowsNeoForge;
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
        bind(Registries.BLOCK, ModBlocks::register);
        bind(Registries.ITEM, ModItems::register);
//        bind(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ModEnchantmentEffects::register);
        ModEnchantmentEntityEffectsNeoForge.register(modEventBus);

        bind(Registries.CREATIVE_MODE_TAB, ModTabs::register);
        bind(Registries.ENTITY_TYPE, ModEntities::register);
    }

    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        MoreBowsAndArrowsNeoForge.EVENT_BUS.addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }
}
