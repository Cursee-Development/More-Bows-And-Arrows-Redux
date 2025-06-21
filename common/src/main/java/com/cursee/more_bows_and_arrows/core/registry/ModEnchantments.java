package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.world.item.enchantment.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.BiConsumer;

public class ModEnchantments {

    public static final Enchantment ANTI_GRAVITY = new AntiGravityEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment DEFENSIVE_SHOT = new DefensiveShotEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment BONUS_SHOT = new DefensiveShotEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment FLUID_MOVEMENT = new FluidMovementEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment MONSTER_HUNTER = new MonsterHunterEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment QUICK_PULL = new QuickPullEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment TEMPO_THIEF = new TempoThiefEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});

    public static void register(BiConsumer<Enchantment, ResourceLocation> consumer) {
        consumer.accept(ANTI_GRAVITY, MoreBowsAndArrows.identifier("anti_gravity"));
        consumer.accept(DEFENSIVE_SHOT, MoreBowsAndArrows.identifier("defensive_shot"));
        consumer.accept(BONUS_SHOT, MoreBowsAndArrows.identifier("bonus_shot"));
        consumer.accept(FLUID_MOVEMENT, MoreBowsAndArrows.identifier("fluid_movement"));
        consumer.accept(MONSTER_HUNTER, MoreBowsAndArrows.identifier("monster_hunter"));
        consumer.accept(QUICK_PULL, MoreBowsAndArrows.identifier("quick_pull"));
        consumer.accept(TEMPO_THIEF, MoreBowsAndArrows.identifier("tempo_thief"));
    }
}
