package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.function.BiConsumer;

public class ModTabs {

    public static final DeferredRegistryObject<CreativeModeTab> MORE_BOWS_AND_ARROWS = Services.PLATFORM.register(BuiltInRegistries.CREATIVE_MODE_TAB, Constants.MOD_ID, () ->
            Services.PLATFORM.tabBuilder()
                    .icon(() -> new ItemStack(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(ArrowType.AMETHYST).get()))
                    .title(Component.translatable("itemGroup.moreBowsAndArrows"))
                    .displayItems((itemDisplayParameters, output) -> {

                        itemDisplayParameters.holders().lookup(Registries.ENCHANTMENT).ifPresent(enchantments -> {
                            enchantments.listElements().filter(reference -> {
                                        return reference.key().toString().contains("more_bows_and_arrows");
                                    })
                                    .map(reference -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(reference, reference.value().getMaxLevel())))
                                    .forEach(output::accept);
                        });

                        ModItems.ITEMS_FOR_TAB.forEach(item -> {
                            {
                                if (ModConfig.BANNED_BOWS != null) {
                                    if (ModConfig.BANNED_BOWS.contains(item.get().getDescriptionId().replace("item." + Constants.MOD_ID + ".", "")))
                                        return;
                                    if (ModConfig.BANNED_ARROWS.contains(item.get().getDescriptionId().replace("item." + Constants.MOD_ID + ".", "")))
                                        return;
                                }
                            }
                            output.accept(item.get());
                        });
                    })
                    .build());

    public static void loadClass() {}

//    public static final CreativeModeTab MORE_BOWS_AND_ARROWS = Services.PLATFORM.creativeModeTab(
//            () -> new ItemStack(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(ArrowType.AMETHYST)),
//            Component.translatable("itemGroup.moreBowsAndArrows"),
//            (itemDisplayParameters, output) -> {
////                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.ANTI_GRAVITY, 1)));
////                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.BONUS_SHOT, 1)));
////                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.DEFENSIVE_SHOT, 1)));
////                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.FLUID_MOVEMENT, 1)));
////                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.MONSTER_HUNTER, 1)));
////                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.QUICK_PULL, 1)));
////                output.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.TEMPO_THIEF, 1)));
//
//                itemDisplayParameters.holders().lookup(Registries.ENCHANTMENT).ifPresent(enchantments -> {
//                    enchantments.listElements().filter(reference -> {
//                                System.out.println(reference.key());
//                        return reference.key().toString().contains("more_bows_and_arrows");
//                            })
//                            .map(reference -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(reference, reference.value().getMaxLevel())))
//                            .forEach(output::accept);
//                });
//
//                ModItems.ITEMS_FOR_TAB.forEach(item -> {
//
//                    {
//                        if (ModConfig.BANNED_BOWS != null) {
//                            if (ModConfig.BANNED_BOWS.contains(item.getDescriptionId().replace("item." + Constants.MOD_ID + ".", "")))
//                                return;
//                            if (ModConfig.BANNED_ARROWS.contains(item.getDescriptionId().replace("item." + Constants.MOD_ID + ".", "")))
//                                return;
//                        }
//                    }
//
//                    output.accept(item);
//                });
//            });
//
//    public static void register(BiConsumer<CreativeModeTab, Identifier> consumer) {
//        consumer.accept(MORE_BOWS_AND_ARROWS, MoreBowsAndArrows.identifier(Constants.MOD_ID));
//    }
}
