package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.world.item.enchantment.*;
import com.cursee.more_bows_and_arrows.platform.Services;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class ModEnchantmentEntityEffects {

    public static final DeferredRegistryObject<MapCodec<? extends EnchantmentEntityEffect>> ANTI_GRAVITY = Services.PLATFORM.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "anti_gravity", () -> AntiGravityEnchantmentEffect.CODEC);
    public static final DeferredRegistryObject<MapCodec<? extends EnchantmentEntityEffect>> BONUS_SHOT = Services.PLATFORM.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "bonus_shot", () -> BonusShotEnchantmentEffect.CODEC);
    public static final DeferredRegistryObject<MapCodec<? extends EnchantmentEntityEffect>> DEFENSIVE_SHOT = Services.PLATFORM.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "defensive_shot", () -> DefensiveShotEnchantmentEffect.CODEC);
    public static final DeferredRegistryObject<MapCodec<? extends EnchantmentEntityEffect>> FLUID_MOVEMENT = Services.PLATFORM.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "fluid_movement", () -> FluidMovementEnchantmentEffect.CODEC);
    public static final DeferredRegistryObject<MapCodec<? extends EnchantmentEntityEffect>> MONSTER_HUNTER = Services.PLATFORM.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "monster_hunter", () -> MonsterHunterEnchantmentEffect.CODEC);
    public static final DeferredRegistryObject<MapCodec<? extends EnchantmentEntityEffect>> QUICK_PULL = Services.PLATFORM.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "quick_pull", () -> QuickPullEnchantmentEffect.CODEC);
    public static final DeferredRegistryObject<MapCodec<? extends EnchantmentEntityEffect>> TEMPO_THIEF = Services.PLATFORM.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "tempo_thief", () -> TempoThiefEnchantmentEffect.CODEC);

//    public static void register(BiConsumer<MapCodec<? extends EnchantmentEntityEffect>, ResourceLocation> consumer) {
//        if (!Services.PLATFORM.getPlatformName().equalsIgnoreCase("neoforge")) {
//            consumer.accept(ANTI_GRAVITY, MoreBowsAndArrows.identifier("anti_gravity"));
//            consumer.accept(BONUS_SHOT, MoreBowsAndArrows.identifier("bonus_shot"));
//            consumer.accept(DEFENSIVE_SHOT, MoreBowsAndArrows.identifier("defensive_shot"));
//            consumer.accept(FLUID_MOVEMENT, MoreBowsAndArrows.identifier("fluid_movement"));
//            consumer.accept(MONSTER_HUNTER, MoreBowsAndArrows.identifier("monster_hunter"));
//            consumer.accept(QUICK_PULL, MoreBowsAndArrows.identifier("quick_pull"));
//            consumer.accept(TEMPO_THIEF, MoreBowsAndArrows.identifier("tempo_thief"));
//        }
//    }

    public static void loadClass() {
        initializeEnchantmentEntityEffects();
    }

    private static void initializeEnchantmentEntityEffects() {}
}
