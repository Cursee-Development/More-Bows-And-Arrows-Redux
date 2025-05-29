package com.cursee.more_bows_and_arrows;

import com.cursee.monolib.platform.Services;
import com.cursee.more_bows_and_arrows.client.renderer.entity.ModArrowRenderer;
import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.cursee.more_bows_and_arrows.core.registry.ModEntities;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class MoreBowsAndArrowsClient {

    public static void init() {}

    public static void registerArrowRenderers() {
        ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.forEach((type, modArrowEntityType) -> {
            Services.REGISTER.registerEntityRenderer(modArrowEntityType, context -> new ModArrowRenderer(context, type));
        });
    }

    public static void registerBowProperties() {
        ModItems.BOW_ITEM_FROM_TYPE_MAP.forEach((type, item) -> registerBowProperties(item));
    }

    private static <T extends Item> void registerBowProperties(T item) {

//        ItemProperties.register(item, new ResourceLocation("pull"), ((itemStack, clientLevel, livingEntity, i) -> {
//            if (livingEntity == null || livingEntity.getUseItem() != itemStack) return 0.0f;
//            return (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
//        }));
        ItemProperties.register(item, new ResourceLocation("pull"), ((itemStack, clientLevel, livingEntity, i) -> {
            if (livingEntity == null || livingEntity.getUseItem() != itemStack) return 0.0f;
            float duration = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BONUS_SHOT, itemStack) > 0 ? 10.0f : 20.0f;
            return (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / duration;
        }));

        ItemProperties.register(item, new ResourceLocation("pulling"), ((itemStack, clientLevel, livingEntity, i) -> {
            if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) return 1.0f;
            return 0.0f;
        }));
    }
}
