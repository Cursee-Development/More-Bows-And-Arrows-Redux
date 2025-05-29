package com.cursee.more_bows_and_arrows.core.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FluidMovementEnchantment extends Enchantment {

    public FluidMovementEnchantment(Rarity rarity, EquipmentSlot[] applicableSlots) {
        super(rarity, EnchantmentCategory.BOW, applicableSlots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 5;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return 20;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench);
    }
}
