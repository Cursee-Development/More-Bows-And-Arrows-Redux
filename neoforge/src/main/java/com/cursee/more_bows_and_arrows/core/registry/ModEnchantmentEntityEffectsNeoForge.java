package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.core.world.item.enchantment.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEntityEffectsNeoForge {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Constants.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ANTI_GRAVITY = registerEnchantmentEffect("anti_gravity", AntiGravityEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BONUS_SHOT = registerEnchantmentEffect("bonus_shot", BonusShotEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> DEFENSIVE_SHOT = registerEnchantmentEffect("defensive_shot", DefensiveShotEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> FLUID_MOVEMENT = registerEnchantmentEffect("fluid_movement", FluidMovementEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> MONSTER_HUNTER = registerEnchantmentEffect("monster_hunter", MonsterHunterEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> QUICK_PULL = registerEnchantmentEffect("quick_pull", QuickPullEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> TEMPO_THIEF = registerEnchantmentEffect("tempo_thief", TempoThiefEnchantmentEffect.CODEC);

    private static Supplier<MapCodec<? extends EnchantmentEntityEffect>> registerEnchantmentEffect(String name,
                                                                                                   MapCodec<? extends EnchantmentEntityEffect> codec) {
        return ENTITY_ENCHANTMENT_EFFECTS.register(name, () -> codec);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
