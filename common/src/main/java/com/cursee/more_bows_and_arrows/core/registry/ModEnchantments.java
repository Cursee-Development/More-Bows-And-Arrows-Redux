package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {

    public static final ResourceKey<Enchantment> ANTI_GRAVITY = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("anti_gravity"));
    public static final ResourceKey<Enchantment> BONUS_SHOT = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("bonus_shot"));
    public static final ResourceKey<Enchantment> DEFENSIVE_SHOT = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("defensive_shot"));
    public static final ResourceKey<Enchantment> FLUID_MOVEMENT = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("fluid_movement"));
    public static final ResourceKey<Enchantment> MONSTER_HUNTER = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("monster_hunter"));
    public static final ResourceKey<Enchantment> QUICK_PULL = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("quick_pull"));
    public static final ResourceKey<Enchantment> TEMPO_THIEF = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("tempo_thief"));

    public static void createResourceKeys() {}
}
