package io.github.jason13official.more_bows_and_arrows.impl.common.registry;

import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ModArrowItem;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.BowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.ModBowItem;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public class ModItems {

  public static LinkedHashMap<BowType, Item> BOWS = new LinkedHashMap<>();
  public static LinkedHashMap<ArrowType, Item> ARROWS = new LinkedHashMap<>();

  public static void register(BiConsumer<Item, Identifier> consumer) {

    BOWS.clear();
    ARROWS.clear();

    for (BowType type : BowType.values()) {
      BOWS.put(
          type,
          new ModBowItem(type, new Properties().durability(type.getUses()).enchantable(1).setId(bowKey(type))));
    }

    for (ArrowType type : ArrowType.values()) {
      ARROWS.put(
          type,
          new ModArrowItem(type, new Properties().setId(arrowKey(type))));
    }

    BOWS.forEach((type, item) -> consumer.accept(item, bowId(type)));
    ARROWS.forEach((type, item) -> consumer.accept(item, arrowId(type)));
  }

  private static ResourceKey<Item> bowKey(BowType type) {
    return ResourceKey.create(Registries.ITEM, bowId(type));
  }

  private static Identifier bowId(BowType type) {
    return MoreBowsAndArrows.identifier(type.name().toLowerCase() + "_bow");
  }

  private static ResourceKey<Item> arrowKey(ArrowType type) {
    return ResourceKey.create(Registries.ITEM, arrowId(type));
  }

  private static Identifier arrowId(ArrowType type) {
    return MoreBowsAndArrows.identifier(type.name().toLowerCase() + "_arrow");
  }
}
