package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.core.util.ForgeRegistryHelper;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModRegistryForge {

    public static void register(final IEventBus modEventBus) {
        ForgeRegistryHelper.ENCHANTMENT_ENTITY_EFFECTS.register(modEventBus);
        ForgeRegistryHelper.ITEMS.register(modEventBus);
        ForgeRegistryHelper.TABS.register(modEventBus);
        ForgeRegistryHelper.ENTITIES.register(modEventBus);
    }
}
