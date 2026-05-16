package io.github.jason13official.more_bows_and_arrows.impl.common.registry;

import com.mojang.serialization.MapCodec;
import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant.AntiGravityEnchantmentEffect;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant.BonusShotEnchantmentEffect;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant.DefensiveShotEnchantmentEffect;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant.FluidMovementEnchantmentEffect;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant.MonsterHunterEnchantmentEffect;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant.QuickPullEnchantmentEffect;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.enchant.TempoThiefEnchantmentEffect;
import java.util.function.BiConsumer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class ModEnchantments {

  public static final ResourceKey<Enchantment> ANTI_GRAVITY = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("anti_gravity"));
  public static final ResourceKey<Enchantment> BONUS_SHOT = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("bonus_shot"));
  public static final ResourceKey<Enchantment> DEFENSIVE_SHOT = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("defensive_shot"));
  public static final ResourceKey<Enchantment> FLUID_MOVEMENT = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("fluid_movement"));
  public static final ResourceKey<Enchantment> MONSTER_HUNTER = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("monster_hunter"));
  public static final ResourceKey<Enchantment> QUICK_PULL = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("quick_pull"));
  public static final ResourceKey<Enchantment> TEMPO_THIEF = ResourceKey.create(Registries.ENCHANTMENT, MoreBowsAndArrows.identifier("tempo_thief"));

  public static void register(BiConsumer<MapCodec<? extends EnchantmentEntityEffect>, Identifier> consumer) {
    consumer.accept(AntiGravityEnchantmentEffect.CODEC, MoreBowsAndArrows.identifier("anti_gravity"));
    consumer.accept(BonusShotEnchantmentEffect.CODEC, MoreBowsAndArrows.identifier("bonus_shot"));
    consumer.accept(DefensiveShotEnchantmentEffect.CODEC, MoreBowsAndArrows.identifier("defensive_shot"));
    consumer.accept(FluidMovementEnchantmentEffect.CODEC, MoreBowsAndArrows.identifier("fluid_movement"));
    consumer.accept(MonsterHunterEnchantmentEffect.CODEC, MoreBowsAndArrows.identifier("monster_hunter"));
    consumer.accept(QuickPullEnchantmentEffect.CODEC, MoreBowsAndArrows.identifier("quick_pull"));
    consumer.accept(TempoThiefEnchantmentEffect.CODEC, MoreBowsAndArrows.identifier("tempo_thief"));
  }
}
