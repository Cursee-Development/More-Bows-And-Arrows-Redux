package io.github.jason13official.more_bows_and_arrows.impl.common.item.bow;

import io.github.jason13official.more_bows_and_arrows.impl.common.entity.ModArrow;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;

public interface IModBow {

  BowType getBowType();

  default void hurtLivingEntity(AbstractArrow abstractArrow, LivingEntity owner, LivingEntity hitEntity) {

    if (abstractArrow == null || owner == null || hitEntity == null) {
      return;
    }

    if (owner instanceof Player player) {
      hitEntity.invulnerableTime = 0;
      hitEntity.setInvulnerable(false);
      hitEntity.hurtDuration = 0;
      hitEntity.hurtTime = 0;
      hitEntity.hurtMarked = false;
      // hitEntity.handleDamageEvent(owner.level().damageSources().generic());
      owner.setLastHurtMob(null);
      hitEntity.setLastHurtByMob(null);
      hitEntity.setLastHurtByPlayer((Player) null, 0);

      hitEntity.hurt(owner.level().damageSources().generic(), this.getBowType().getAttackDamageBonus());
    }

    if (!(abstractArrow instanceof ModArrow modArrow)) {
      return;
    }

    switch (this.getBowType()) {

      case AMETHYST -> {}
      case BLAZE -> setOnFire(hitEntity);
      case BONE -> hitEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 2, true, true));
      case COAL, IRON -> {
        if ((modArrow.getArrowType() == ArrowType.FLINT || modArrow.getArrowType() == ArrowType.FLINT_AND_STEEL)) setOnFire(hitEntity);
      }
      case COPPER -> {}
      case DIAMOND -> {}
      case EMERALD -> {}
      case GOLD -> {}
      case LAPIS -> {}
      case MOSS -> {}
      case NETHERITE -> {}
      case OBSIDIAN -> {}
      case PAPER -> {}

//            case ACACIA -> {}
//            case BAMBOO -> {}
//            case BIRCH -> {}
//            case CHERRY -> {}
//            case CRIMSON_STEM -> {}
//            case DARK_OAK -> {}
//            case JUNGLE -> {}
//            case MANGROVE -> {}
//            case OAK -> {}
//            case SPRUCE -> {}
//            case WARPED_STEM -> {}
//
//            case STRIPPED_ACACIA -> {}
//            case STRIPPED_BAMBOO -> {}
//            case STRIPPED_BIRCH -> {}
//            case STRIPPED_CHERRY -> {}
//            case STRIPPED_CRIMSON_STEM -> {}
//            case STRIPPED_DARK_OAK -> {}
//            case STRIPPED_JUNGLE -> {}
//            case STRIPPED_MANGROVE -> {}
//            case STRIPPED_OAK -> {}
//            case STRIPPED_SPRUCE -> {}
//            case STRIPPED_WARPED_STEM -> {}
      default -> {}
    }
  }

  private void setOnFire(LivingEntity hitEntity) {
    hitEntity.setRemainingFireTicks(2 * 20);
  }
}
