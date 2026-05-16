package io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow;

import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import io.github.jason13official.more_bows_and_arrows.impl.common.entity.ModArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow.Pickup;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class ModArrowItem extends ArrowItem {

  private final ArrowType type;

  public ModArrowItem(ArrowType type, Properties properties) {
    super(properties);
    this.type = type;
  }

  public ArrowType getType() {
    return type;
  }

  @Override
  public boolean isEnabled(FeatureFlagSet enabledFeatures) {
    return !ModConfig.get().BANNED_ARROWS.contains(this);
  }

  public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity owner, @Nullable ItemStack firedFromWeapon) {
    return new ModArrow(this.type, level, owner, itemStack.copyWithCount(1), firedFromWeapon);
  }

  public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
    ModArrow arrow = new ModArrow(this.type, level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), (ItemStack)null);
    arrow.pickup = Pickup.ALLOWED;
    return arrow;
  }
}
