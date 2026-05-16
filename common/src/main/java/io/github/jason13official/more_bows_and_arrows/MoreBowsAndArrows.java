package io.github.jason13official.more_bows_and_arrows;

import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModItems;
import io.github.jason13official.more_bows_and_arrows.platform.Services;
import java.util.function.BiConsumer;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;

public class MoreBowsAndArrows {

  public static BiConsumer<ServerPlayer, CustomPacketPayload> clientBoundPacketSender;

  /// this should be called after game object registration
  /// such as wrapping with FMLCommonSetupEvent in NeoForge
  public static void init() {

    ModConfig.load(Services.PLATFORM.getConfigDirectory());

    ModItems.ARROWS.forEach((identifier, item) -> {
      DispenserBlock.registerProjectileBehavior(item);
    });
  }

  public static Identifier identifier(final String path) {
    return Identifier.fromNamespaceAndPath(Constants.MOD_ID, path);
  }
}