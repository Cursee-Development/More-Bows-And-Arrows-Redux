package com.cursee.more_bows_and_arrows.core.world.item.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record AntiGravityEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<AntiGravityEnchantmentEffect> CODEC = MapCodec.unit(AntiGravityEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}

//public class AntiGravityEnchantment extends Enchantment {
//
//    public AntiGravityEnchantment(Rarity rarity, EquipmentSlot[] applicableSlots) {
//        super(rarity, EnchantmentCategory.BOW, applicableSlots);
//    }
//
//    @Override
//    public int getMinCost(int enchantmentLevel) {
//        return 5;
//    }
//
//    @Override
//    public int getMaxCost(int enchantmentLevel) {
//        return 20;
//    }
//
//    @Override
//    public boolean checkCompatibility(Enchantment ench) {
//        return super.checkCompatibility(ench);
//    }
//}
