package com.cursee.more_bows_and_arrows;

import com.cursee.monolib.platform.Services;
import com.cursee.more_bows_and_arrows.client.renderer.entity.ModArrowRenderer;
import com.cursee.more_bows_and_arrows.core.registry.ModEntities;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Consumer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class MoreBowsAndArrowsClientNeoForge {

    public MoreBowsAndArrowsClientNeoForge(final IEventBus modEventBus) {
        MoreBowsAndArrowsClient.init();

        modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> {
            MoreBowsAndArrowsClient.registerBowProperties();
            // MoreBowsAndArrowsClient.registerArrowRenderers();
        });

        modEventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> {
            ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.forEach((type, modArrowEntityType) -> {
                event.registerEntityRenderer(modArrowEntityType.get(), context -> new ModArrowRenderer(context, type));
            });
        });
    }
}
