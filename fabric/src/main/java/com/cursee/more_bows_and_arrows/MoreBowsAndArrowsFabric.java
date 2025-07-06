package com.cursee.more_bows_and_arrows;

import com.cursee.more_bows_and_arrows.core.registry.ModRegistryFabric;
import net.fabricmc.api.ModInitializer;

public class MoreBowsAndArrowsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        MoreBowsAndArrows.init();
        ModRegistryFabric.register();

        // MoreBowsAndArrows.afterRegistration();
        // ModDispenserBlockBehaviors.BEHAVIOR_FROM_ITEM_MAP.forEach(DispenserBlock::registerBehavior);

//        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
//            ServerConfig.onLoad();
//        });
    }
}
