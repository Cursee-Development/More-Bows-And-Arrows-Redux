package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class ModEntities {

    public static final LinkedHashMap<ArrowType, DeferredRegistryObject<EntityType<ModArrow>>> ENTITY_TYPE_FROM_TYPE_MAP = new LinkedHashMap<>();

    private static boolean forge() {
        return Services.PLATFORM.getEnvironmentName().equalsIgnoreCase("forge");
    }

    public static void loadClass() {
        initializeEntities();
    }

    private static void initializeEntities() {
        for (ArrowType type : ArrowType.values()) {

            // float size = type == ArrowType.REALLY_BIG ? 5.0F : 0.5f;
            float size = 0.5f;

            DeferredRegistryObject<EntityType<ModArrow>> arrow = Services.PLATFORM.register(BuiltInRegistries.ENTITY_TYPE, type.name().toLowerCase() + "_arrow", () -> Services.PLATFORM.<ModArrow>createEntityType((modArrowEntityType, level) -> new ModArrow(modArrowEntityType, level, type), MobCategory.MISC)
                    .sized(size, size)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(forge() ? null : MoreBowsAndArrows.identifier(type.name().toLowerCase() + "_arrow").toString()));

            ENTITY_TYPE_FROM_TYPE_MAP.put(type, arrow);
        }
    }
}
