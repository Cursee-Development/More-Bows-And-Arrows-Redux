package com.cursee.more_bows_and_arrows;

import com.cursee.more_bows_and_arrows.client.renderer.entity.ModArrowRenderer;
import com.cursee.more_bows_and_arrows.core.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MoreBowsAndArrowsClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MoreBowsAndArrowsClient.init();
        MoreBowsAndArrowsClient.registerBowProperties();

        ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.forEach((type, modArrowEntityType) -> {
            EntityRendererRegistry.register(modArrowEntityType.get(), context -> new ModArrowRenderer(context, type));
        });
    }
}
