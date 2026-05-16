package io.github.jason13official.more_bows_and_arrows;

import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import io.github.jason13official.more_bows_and_arrows.impl.common.network.packet.ConfigSyncS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

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
  }
}
