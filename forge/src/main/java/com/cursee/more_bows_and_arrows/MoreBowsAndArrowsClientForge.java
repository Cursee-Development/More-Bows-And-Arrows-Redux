package com.cursee.more_bows_and_arrows;

import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Consumer;

public class MoreBowsAndArrowsClientForge {

    public MoreBowsAndArrowsClientForge(final IEventBus modEventBus) {
        MoreBowsAndArrowsClient.init();

        modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> {
            MoreBowsAndArrowsClient.registerBowProperties();
            MoreBowsAndArrowsClient.registerArrowRenderers();
        });
    }
}
