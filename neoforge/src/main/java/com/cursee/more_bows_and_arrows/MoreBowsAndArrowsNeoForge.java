package com.cursee.more_bows_and_arrows;


import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.registry.ModRegistryNeoForge;
import java.util.function.Consumer;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class MoreBowsAndArrowsNeoForge {

  public static IEventBus EVENT_BUS;

  public MoreBowsAndArrowsNeoForge(final FMLModContainer container) {
    MoreBowsAndArrows.init();
    EVENT_BUS = container.getEventBus();
    ModRegistryNeoForge.register(EVENT_BUS);

    EVENT_BUS.addListener((Consumer<FMLCommonSetupEvent>) event -> {
      // after game objects are registered

      ModItems.ARROW_ITEM_FROM_TYPE_MAP.forEach((arrowType, itemDeferredRegistryObject) -> {
        DispenserBlock.registerProjectileBehavior(itemDeferredRegistryObject.get());
      });
    });

      if (FMLEnvironment.dist == Dist.CLIENT) {
          new MoreBowsAndArrowsClientNeoForge(EVENT_BUS);
      }
  }
}