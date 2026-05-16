package io.github.jason13official.more_bows_and_arrows;

import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import io.github.jason13official.more_bows_and_arrows.impl.common.network.packet.ConfigSyncS2CPacket;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

public class MoreBowsAndArrowsClientNeoForge {

  public MoreBowsAndArrowsClientNeoForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> {
      MoreBowsAndArrowsClient.serverBoundPacketSender = ClientPacketDistributor::sendToServer;
      MoreBowsAndArrowsClient.init();
    });

    modEventBus.addListener((Consumer<RegisterClientPayloadHandlersEvent>) event -> {
      event.register(ConfigSyncS2CPacket.TYPE, (payload, context) -> {
        ModConfig.get().sync(payload);
      });
    });

    NeoForge.EVENT_BUS.addListener((Consumer<EntityLeaveLevelEvent>) event -> {
      if (Minecraft.getInstance().player != null && event.getEntity() == Minecraft.getInstance().player) {
        ModConfig.unsync();
      }
    });

//    NeoForge.EVENT_BUS.addListener((Consumer<EntityRenderersEvent.RegisterLayerDefinitions>) event -> {
//
//      event.registerLayerDefinition(ModelLayers.ARROW, ArrowModel::createBodyLayer);
//    });

//    NeoForge.EVENT_BUS.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> {
//
//      // event.registerEntityRenderer(ModEntities.MOD_ARROW, TippableArrowRenderer::new);
//      for (ArrowType type : ArrowType.values()) {
//        event.registerEntityRenderer(ModEntities.ARROWS.get(type), context -> new ModArrowRenderer(context, type));
//      }
//    });

    bindEntityRenderers(MoreBowsAndArrowsClient::registerEntityRenderers);
  }

  private <T extends Entity> void bindEntityRenderers(Consumer<BiConsumer<EntityType<T>, EntityRendererProvider<T>>> source) {
    MoreBowsAndArrowsNeoForge.EVENT_BUS.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> {
      source.accept(event::registerEntityRenderer);
    });
  }
}
