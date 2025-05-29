package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.platform.Services;
import com.google.common.collect.Lists;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.function.BiConsumer;

public class ModTabs {

    public static final CreativeModeTab MORE_BOWS_AND_ARROWS = Services.PLATFORM.creativeModeTab(
            () -> new ItemStack(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(ArrowType.AMETHYST)),
            Component.translatable("itemGroup.moreBowsAndArrows"),
            (itemDisplayParameters, output) -> {
                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.ANTI_GRAVITY, ModEnchantments.ANTI_GRAVITY.getMaxLevel())));
                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.BONUS_SHOT, ModEnchantments.BONUS_SHOT.getMaxLevel())));
                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.FLUID_MOVEMENT, ModEnchantments.FLUID_MOVEMENT.getMaxLevel())));
                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.MONSTER_HUNTER, ModEnchantments.MONSTER_HUNTER.getMaxLevel())));
                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.QUICK_PULL, ModEnchantments.QUICK_PULL.getMaxLevel())));
                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.TEMPO_THIEF, ModEnchantments.TEMPO_THIEF.getMaxLevel())));
                ModItems.ITEMS_FOR_TAB.forEach(item -> {

                    if (ModConfig.BANNED_BOWS.contains(item.getDescriptionId().replace("item." + Constants.MOD_ID + ".", ""))) return;
                    if (ModConfig.BANNED_ARROWS.contains(item.getDescriptionId().replace("item." + Constants.MOD_ID + ".", ""))) return;

                    output.accept(item);
                });
            });

    public static void register(BiConsumer<CreativeModeTab, ResourceLocation> consumer) {
        consumer.accept(MORE_BOWS_AND_ARROWS, MoreBowsAndArrows.identifier(Constants.MOD_ID));
    }
}
