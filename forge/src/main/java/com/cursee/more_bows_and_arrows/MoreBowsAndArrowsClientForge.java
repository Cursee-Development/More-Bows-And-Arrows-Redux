package com.cursee.more_bows_and_arrows;

import com.cursee.more_bows_and_arrows.client.renderer.entity.ModArrowRenderer;
import com.cursee.more_bows_and_arrows.core.registry.ModEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Consumer;

public class MoreBowsAndArrowsClientForge {

    public MoreBowsAndArrowsClientForge(final IEventBus modEventBus) {
        MoreBowsAndArrowsClient.init();

        modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> {
            MoreBowsAndArrowsClient.registerBowProperties();
        });

        modEventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> {
            ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.forEach((type, modArrowEntityType) -> {
                event.registerEntityRenderer(modArrowEntityType.get(), context -> new ModArrowRenderer(context, type));
            });
        });
    }
}
