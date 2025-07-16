package com.cursee.more_bows_and_arrows;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Consumer;

public class MoreBowsAndArrowsClientNeoForge {

    public MoreBowsAndArrowsClientNeoForge(final IEventBus modEventBus) {
        MoreBowsAndArrowsClient.init();

        modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> {
            MoreBowsAndArrowsClient.registerBowProperties();
            MoreBowsAndArrowsClient.registerArrowRenderers();
        });
    }
}
