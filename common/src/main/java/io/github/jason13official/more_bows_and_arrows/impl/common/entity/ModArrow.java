package io.github.jason13official.more_bows_and_arrows.impl.common.entity;

import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEntities;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class ModArrow extends AbstractArrow {

  private final ArrowType type;

  public ModArrow(EntityType<? extends ModArrow> entityType, Level level, ArrowType type) {
    super(entityType, level);
     this.type = type;
  }

  public ModArrow(ArrowType type, Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
    super(ModEntities.ARROWS.get(type), x, y, z, level, pickupItemStack, firedFromWeapon);
    // this.updateColor();
    this.type = type;
  }

  public ModArrow(ArrowType type, Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
    super(ModEntities.ARROWS.get(type), owner, level, pickupItemStack, firedFromWeapon);
    // this.updateColor();
    this.type = type;
  }

  @Override
  protected ItemStack getDefaultPickupItem() {
    return ModItems.ARROWS.get(this.type) == null ? ItemStack.EMPTY : new ItemStack(ModItems.ARROWS.get(this.type));
  }
}
