package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.world.item.enchantment.*;
import com.cursee.more_bows_and_arrows.platform.Services;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

import java.util.function.BiConsumer;

public class ModEnchantmentEntityEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> ANTI_GRAVITY = Services.PLATFORM.createEnchantmentEffect("anti_gravity", AntiGravityEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> BONUS_SHOT = Services.PLATFORM.createEnchantmentEffect("bonus_shot", BonusShotEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> DEFENSIVE_SHOT = Services.PLATFORM.createEnchantmentEffect("defensive_shot", DefensiveShotEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FLUID_MOVEMENT = Services.PLATFORM.createEnchantmentEffect("fluid_movement", FluidMovementEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> MONSTER_HUNTER = Services.PLATFORM.createEnchantmentEffect("monster_hunter", MonsterHunterEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> QUICK_PULL = Services.PLATFORM.createEnchantmentEffect("quick_pull", QuickPullEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> TEMPO_THIEF = Services.PLATFORM.createEnchantmentEffect("tempo_thief", TempoThiefEnchantmentEffect.CODEC);

    public static void register(BiConsumer<MapCodec<? extends EnchantmentEntityEffect>, ResourceLocation> consumer) {
        if (!Services.PLATFORM.getPlatformName().equalsIgnoreCase("neoforge")) {
            consumer.accept(ANTI_GRAVITY, MoreBowsAndArrows.identifier("anti_gravity"));
            consumer.accept(BONUS_SHOT, MoreBowsAndArrows.identifier("bonus_shot"));
            consumer.accept(DEFENSIVE_SHOT, MoreBowsAndArrows.identifier("defensive_shot"));
            consumer.accept(FLUID_MOVEMENT, MoreBowsAndArrows.identifier("fluid_movement"));
            consumer.accept(MONSTER_HUNTER, MoreBowsAndArrows.identifier("monster_hunter"));
            consumer.accept(QUICK_PULL, MoreBowsAndArrows.identifier("quick_pull"));
            consumer.accept(TEMPO_THIEF, MoreBowsAndArrows.identifier("tempo_thief"));
        }
    }
}
