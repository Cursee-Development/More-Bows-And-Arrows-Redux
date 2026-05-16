package io.github.jason13official.more_bows_and_arrows;

import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import io.github.jason13official.more_bows_and_arrows.impl.common.network.packet.ConfigSyncS2CPacket;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModBlocks;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEnchantments;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEntities;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModItems;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModMenus;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModParticles;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModTabs;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModTiles;
import io.github.jason13official.more_bows_and_arrows.platform.Services;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.v1.DataResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.ResourceReloaderKeys;
import net.fabricmc.fabric.impl.resource.DataResourceLoaderImpl;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

public class MoreBowsAndArrowsFabric implements ModInitializer {

  @Override
  public void onInitialize() {

    bind(BuiltInRegistries.BLOCK, ModBlocks::register);
    bind(BuiltInRegistries.ENTITY_TYPE, ModEntities::register);
    bind(BuiltInRegistries.ITEM, ModItems::register);
    bind(BuiltInRegistries.PARTICLE_TYPE, ModParticles::register);
    bind(BuiltInRegistries.BLOCK_ENTITY_TYPE, ModTiles::register);
    bind(BuiltInRegistries.MENU, ModMenus::register);
    bind(BuiltInRegistries.CREATIVE_MODE_TAB, ModTabs::register);
    bind(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ModEnchantments::register);

    MoreBowsAndArrows.clientBoundPacketSender = ServerPlayNetworking::send;
    MoreBowsAndArrows.init();
    PayloadTypeRegistry.clientboundPlay().register(ConfigSyncS2CPacket.TYPE, ConfigSyncS2CPacket.STREAM_CODEC);

    DataResourceLoaderImpl.get(PackType.SERVER_DATA).registerReloadListener(MoreBowsAndArrows.identifier(Constants.MOD_ID), new ResourceReloadListener());
  }

  public <T> void bind(Registry<T> registry, Consumer<BiConsumer<T, Identifier>> source) {

    source.accept((t, rl) -> Registry.register(registry, rl, t));
  }

  public static class ResourceReloadListener extends SimplePreparableReloadListener<Void> {

    @Override
    public String getName() {
      return MoreBowsAndArrows.identifier(Constants.MOD_ID).toString();
    }

    @Override
    protected void apply(Void unused, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
      ModConfig.load(Services.PLATFORM.getConfigDirectory());
    }

    @Override
    protected Void prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
      return null;
    }
  }
}
