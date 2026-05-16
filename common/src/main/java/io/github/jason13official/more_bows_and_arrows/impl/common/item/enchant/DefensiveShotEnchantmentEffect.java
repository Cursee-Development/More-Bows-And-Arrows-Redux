package io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record DefensiveShotEnchantmentEffect() implements EnchantmentEntityEffect {

  public static final MapCodec<DefensiveShotEnchantmentEffect> CODEC = MapCodec.unit(DefensiveShotEnchantmentEffect::new);

  @Override
  public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {

  }

  @Override
  public MapCodec<? extends EnchantmentEntityEffect> codec() {
    return CODEC;
  }
}
