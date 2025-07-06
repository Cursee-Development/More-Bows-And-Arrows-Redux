package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.core.world.item.ModArrowItem;
import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import com.cursee.more_bows_and_arrows.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.function.BiConsumer;

public class ModItems {

    public static final LinkedHashMap<ArrowType, DeferredRegistryObject<Item>> ARROW_ITEM_FROM_TYPE_MAP = new LinkedHashMap<>();
    public static final LinkedHashMap<BowType, DeferredRegistryObject<Item>> BOW_ITEM_FROM_TYPE_MAP = new LinkedHashMap<>();

    public static final LinkedList<DeferredRegistryObject<Item>> ITEMS_FOR_TAB = new LinkedList<>();

    public static void loadClass() {
        initializeItems();
    }

    private static void initializeItems() {
        if (!ModConfig.BANNED_BOWS.isEmpty()) {
            Constants.LOG.info("Some bows were banned: ");
            ModConfig.BANNED_BOWS.forEach(System.out::println);
        }

        for (BowType type : BowType.values()) {
            DeferredRegistryObject<Item> bow = Services.PLATFORM.register(BuiltInRegistries.ITEM, type.name().toLowerCase() + "_bow", () -> new ModBowItem(type, new Item.Properties()));
            if (type != BowType.NORMAL) ITEMS_FOR_TAB.add(bow);
            BOW_ITEM_FROM_TYPE_MAP.put(type, bow);
        }

        if (ModConfig.BANNED_ARROWS != null && !ModConfig.BANNED_ARROWS.isEmpty()) {
            Constants.LOG.info("Some arrows were banned: ");
            ModConfig.BANNED_ARROWS.forEach(System.out::println);
        }

        for (ArrowType type : ArrowType.values()) {
            DeferredRegistryObject<Item> arrow = Services.PLATFORM.register(BuiltInRegistries.ITEM, type.name().toLowerCase() + "_arrow", () -> new ModArrowItem(type, new Item.Properties()));
            if (type != ArrowType.NORMAL) ITEMS_FOR_TAB.add(arrow);
            ARROW_ITEM_FROM_TYPE_MAP.put(type, arrow);
        }
    }
}
