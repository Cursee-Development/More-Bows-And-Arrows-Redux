package io.github.jason13official.more_bows_and_arrows.impl.common.entity;

import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.IModArrow;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEntities;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.Nullable;

public class ModArrow extends AbstractArrow implements IModArrow {

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

  public ArrowType getArrowType() {
    return type;
  }

  @Override
  protected void onHitBlock(BlockHitResult result) {
    super.onHitBlock(result);
    this.processBlockInteraction(this, result);
  }

  @Override
  protected void onHitEntity(EntityHitResult result) {
    super.onHitEntity(result);
    this.processEntityInteraction(this, result);
  }
}
