package io.github.jason13official.more_bows_and_arrows;

import io.github.jason13official.more_bows_and_arrows.impl.client.renderer.ModArrowRenderer;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEntities;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class MoreBowsAndArrowsClient {

  public static Consumer<CustomPacketPayload> serverBoundPacketSender;

  public static void init() {
  }

  @SuppressWarnings("unchecked")
  public static <T extends Entity> void registerEntityRenderers(BiConsumer<EntityType<T>, EntityRendererProvider<T>> consumer) {
    for (ArrowType type : ArrowType.values()) {
      consumer.<T, Object>accept((EntityType<T>) ModEntities.ARROWS.get(type), context -> (EntityRenderer<T, ArrowRenderState>) new ModArrowRenderer(context, type));
    }
  }
}