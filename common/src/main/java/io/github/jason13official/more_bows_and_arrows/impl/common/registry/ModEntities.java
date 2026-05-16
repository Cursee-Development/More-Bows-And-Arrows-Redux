package io.github.jason13official.more_bows_and_arrows.impl.common.registry;

import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import io.github.jason13official.more_bows_and_arrows.impl.common.entity.ModArrow;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.Item;

public class ModEntities {


  public static LinkedHashMap<ArrowType, EntityType<ModArrow>> ARROWS = new LinkedHashMap<>();

  public static void register(BiConsumer<EntityType<?>, Identifier> consumer) {

    for (ArrowType type : ArrowType.values()) {
      EntityType<ModArrow> modArrow = EntityType.Builder.<ModArrow>of((entityType, level) -> new ModArrow(entityType, level, type), MobCategory.MISC)
          .noLootTable()
          .sized(0.5F, 0.5F)
          .eyeHeight(0.13F)
          .clientTrackingRange(4)
          .updateInterval(20)
          .build(ResourceKey.create(Registries.ENTITY_TYPE, MoreBowsAndArrows.identifier(type.name().toLowerCase() + "_arrow")));

      ARROWS.put(type, modArrow);
      consumer.accept(modArrow, MoreBowsAndArrows.identifier(type.name().toLowerCase() + "_arrow"));
    }
  }
}
