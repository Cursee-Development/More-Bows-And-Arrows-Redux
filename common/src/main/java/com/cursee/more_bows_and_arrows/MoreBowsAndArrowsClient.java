package com.cursee.more_bows_and_arrows;

import com.cursee.monolib.platform.Services;
import com.cursee.more_bows_and_arrows.client.renderer.entity.ModArrowRenderer;
import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.cursee.more_bows_and_arrows.core.registry.ModEntities;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.List;

public class MoreBowsAndArrowsClient {

    public static void init() {}

    public static void registerArrowRenderers() {
        ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.forEach((type, modArrowEntityType) -> {
            Services.REGISTER.registerEntityRenderer(modArrowEntityType.get(), context -> new ModArrowRenderer(context, type));
        });
    }

    public static void registerBowProperties() {
        ModItems.BOW_ITEM_FROM_TYPE_MAP.forEach((type, item) -> registerBowProperties(item.get()));
    }

    private static <T extends Item> void registerBowProperties(T item) {

//        ItemProperties.register(item, new ResourceLocation("pull"), ((itemStack, clientLevel, livingEntity, i) -> {
//            if (livingEntity == null || livingEntity.getUseItem() != itemStack) return 0.0f;
//            return (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
//        }));
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"), ((itemStack, clientLevel, livingEntity, i) -> {
            if (livingEntity == null || livingEntity.getUseItem() != itemStack) return 0.0f;
            float duration = 20.0f;

            List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(itemStack).entrySet().stream().toList();

            for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
                if (entry.getKey().is(ModEnchantments.QUICK_PULL)) {
                    duration = 10.0f;
                    break;
                }
            }

            return (float) (itemStack.getUseDuration(livingEntity) - livingEntity.getUseItemRemainingTicks()) / duration;
        }));

        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"), ((itemStack, clientLevel, livingEntity, i) -> {
            if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack) return 1.0f;
            return 0.0f;
        }));
    }
}
