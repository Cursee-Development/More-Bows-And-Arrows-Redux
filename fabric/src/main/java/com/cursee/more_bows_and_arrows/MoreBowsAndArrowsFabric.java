package com.cursee.more_bows_and_arrows;

import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.DispenserBlock;

public class MoreBowsAndArrowsFabric implements ModInitializer {

  @Override
  public void onInitialize() {
    MoreBowsAndArrows.init();

    Registry.register(BuiltInRegistries.ITEM, MoreBowsAndArrows.identifier("test_bow"), new BowItem(new Properties()));
    Registry.register(BuiltInRegistries.ITEM, MoreBowsAndArrows.identifier("test2_bow"), new ModBowItem(BowType.ACACIA, new Properties()));

    // after game objects are registered
    ModItems.ARROW_ITEM_FROM_TYPE_MAP.forEach((arrowType, itemDeferredRegistryObject) -> {
      DispenserBlock.registerProjectileBehavior(itemDeferredRegistryObject.get());
    });
  }
}
