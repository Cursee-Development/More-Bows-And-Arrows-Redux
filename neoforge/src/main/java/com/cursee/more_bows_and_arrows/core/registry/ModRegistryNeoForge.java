package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.core.util.NeoForgeRegistryHelper;
import net.neoforged.bus.api.IEventBus;

public class ModRegistryNeoForge {

    public static void register(final IEventBus modEventBus) {
        NeoForgeRegistryHelper.ENCHANTMENT_ENTITY_EFFECTS.register(modEventBus);
        NeoForgeRegistryHelper.ITEMS.register(modEventBus);
        NeoForgeRegistryHelper.TABS.register(modEventBus);
        NeoForgeRegistryHelper.ENTITIES.register(modEventBus);
    }
}
