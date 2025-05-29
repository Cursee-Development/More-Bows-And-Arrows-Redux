package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.core.world.item.ModArrowItem;
import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.function.BiConsumer;

public class ModItems {

    public static final LinkedHashMap<ArrowType, Item> ARROW_ITEM_FROM_TYPE_MAP = new LinkedHashMap<>();
    public static final LinkedHashMap<BowType, Item> BOW_ITEM_FROM_TYPE_MAP = new LinkedHashMap<>();

    public static final LinkedList<Item> ITEMS_FOR_TAB = new LinkedList<>();

    public static void register(BiConsumer<Item, ResourceLocation> consumer) {


        if (!ModConfig.BANNED_BOWS.isEmpty()) {
            Constants.LOG.info("Some bows were banned: ");
            ModConfig.BANNED_BOWS.forEach(System.out::println);
        }
        for (BowType type : BowType.values()) {
            // if (!ModConfig.BANNED_BOWS.contains(type.name().toLowerCase() + "_bow")) {
            if (true) {
                Item bow = new ModBowItem(type, new Item.Properties());
                if (type != BowType.NORMAL) ITEMS_FOR_TAB.add(bow);
                BOW_ITEM_FROM_TYPE_MAP.put(type, bow);
                consumer.accept(bow, MoreBowsAndArrows.identifier(type.name().toLowerCase() + "_bow"));
            }
        }

        if (!ModConfig.BANNED_ARROWS.isEmpty()) {
            Constants.LOG.info("Some arrows were banned: ");
            ModConfig.BANNED_ARROWS.forEach(System.out::println);
        }
        for (ArrowType type : ArrowType.values()) {
            // if (!ModConfig.BANNED_ARROWS.contains(type.name().toLowerCase() + "_arrow")) {
            if (true) {
                Item arrow = new ModArrowItem(type, new Item.Properties());
                if (type != ArrowType.NORMAL) ITEMS_FOR_TAB.add(arrow);
                ARROW_ITEM_FROM_TYPE_MAP.put(type, arrow);
                consumer.accept(arrow, MoreBowsAndArrows.identifier(type.name().toLowerCase() + "_arrow"));
            }
        }
    }
}
