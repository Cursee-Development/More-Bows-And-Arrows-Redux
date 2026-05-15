package io.github.jason13official.more_bows_and_arrows.impl.common.network.packet;

import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import java.util.List;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ConfigSyncS2CPacket(List<Identifier> bowIdentifiers, List<Identifier> arrowIdentifiers) implements CustomPacketPayload {

  public static final Type<ConfigSyncS2CPacket> TYPE = new Type<>(MoreBowsAndArrows.identifier("config_sync"));

  public static final StreamCodec<RegistryFriendlyByteBuf, ConfigSyncS2CPacket> STREAM_CODEC =
      StreamCodec.composite(
          Identifier.STREAM_CODEC.apply(ByteBufCodecs.list()), ConfigSyncS2CPacket::bowIdentifiers,
          Identifier.STREAM_CODEC.apply(ByteBufCodecs.list()), ConfigSyncS2CPacket::arrowIdentifiers,
          ConfigSyncS2CPacket::new);

  @Override
  public Type<? extends CustomPacketPayload> type() {
    return TYPE;
  }
}
