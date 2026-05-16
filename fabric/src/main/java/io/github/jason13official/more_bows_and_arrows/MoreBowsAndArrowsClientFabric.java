package io.github.jason13official.more_bows_and_arrows;

import io.github.jason13official.more_bows_and_arrows.impl.client.renderer.ModArrowRenderer;
import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.network.packet.ConfigSyncS2CPacket;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.projectile.ArrowModel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;

public class MoreBowsAndArrowsClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    MoreBowsAndArrowsClient.serverBoundPacketSender = ClientPlayNetworking::send;
    MoreBowsAndArrowsClient.init();

    // NETWORKING

    ClientPlayNetworking.registerGlobalReceiver(ConfigSyncS2CPacket.TYPE, (payload, context) -> {
      ModConfig.get().sync(payload);
    });

    ClientEntityEvents.ENTITY_UNLOAD.register((entity, level) -> {
      if (Minecraft.getInstance().player != null && entity == Minecraft.getInstance().player) {
        ModConfig.unsync();
      }
    });

    // ModelLayerRegistry.registerModelLayer(ModelLayers.ARROW, ArrowModel::createBodyLayer);

    // EntityRenderers.register(ModEntities.MOD_ARROW, TippableArrowRenderer::new);
    for (ArrowType type : ArrowType.values()) {
      EntityRenderers.register(ModEntities.ARROWS.get(type), context -> new ModArrowRenderer(context, type));
    }
  }
}
