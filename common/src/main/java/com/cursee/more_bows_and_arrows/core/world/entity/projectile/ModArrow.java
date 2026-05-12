package com.cursee.more_bows_and_arrows.core.world.entity.projectile;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.registry.ModEntities;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.IModArrow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Supplier;

public class ModArrow extends AbstractArrow implements IModArrow {

    private ArrowType type = ArrowType.NORMAL;

    public ModArrow(EntityType<? extends AbstractArrow> entityType, Level level, ArrowType type) {
        super(entityType, level);
        this.type = type;
    }

//    public ModArrow(ArrowType type, double x, double y, double z, Level level) {
//        // todo last parameter is the weapon the arrrow was fired from
//        super(ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.get(type), x, y, z, level, new ItemStack(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(type)), ItemStack.EMPTY);
//        this.type = type;
//    }

    public ModArrow(ArrowType type, LivingEntity livingEntity, Level level, ItemStack weapon) {
        super(ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.get(type).get(), livingEntity, level, new ItemStack(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(type).get()), weapon);
        this.type = type;
        // System.out.println("mod arrow created");
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    public ArrowType getArrowType() {
        return this.type;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(this.type).get());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public void setCustomName(Component name) {
        super.setCustomName(name); // todo impl
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        processBlockInteraction(this, result);
    }

//    @Override
//    public void setEnchantmentEffectsFromEntity(LivingEntity shooter, float velocity) {
//        super.setEnchantmentEffectsFromEntity(shooter, velocity);
//        if (!(this.type == ArrowType.BLAZE_ROD)) return;
//        this.setSecondsOnFire(100); /// {@link AbstractArrow#setEnchantmentEffectsFromEntity(LivingEntity, float)}
//    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.type == ArrowType.REALLY_BIG && result.getEntity() instanceof LivingEntity living) {
            living.knockback(10.0D, -this.getDeltaMovement().x, -this.getDeltaMovement().z);
        }
        processEntityInteraction(this, result);
    }
}
