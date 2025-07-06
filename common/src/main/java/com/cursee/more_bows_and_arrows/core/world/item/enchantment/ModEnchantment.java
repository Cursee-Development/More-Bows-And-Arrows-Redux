package com.cursee.more_bows_and_arrows.core.world.item.enchantment;

public enum ModEnchantment {

    ANTI_GRAVITY("anti_gravity"),
    BONUS_SHOT("bonus_shot"),
    DEFENSIVE_SHOT("defensive_shot"),
    FLUID_MOVEMENT("fluid_movement"),
    MONSTER_HUNTER("monster_hunter"),
    QUICK_PULL("quick_pull"),
    TEMPO_THIEF("tempo_thief");

    public final String name;

    ModEnchantment(String name) {
        this.name = name;
    }
}
