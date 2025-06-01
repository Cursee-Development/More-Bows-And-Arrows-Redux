package com.cursee.more_bows_and_arrows.core.world.entity.projectile;

import com.cursee.more_bows_and_arrows.client.renderer.entity.ModArrowRenderer;
import com.cursee.more_bows_and_arrows.core.registry.ModEntities;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.IModArrow;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ModArrow extends AbstractArrow implements IModArrow {

    private final ArrowType type;

    public ModArrow(EntityType<? extends AbstractArrow> entityType, Level level, ArrowType type) {
        super(entityType, level);
        this.type = type;
    }

    public ModArrow(ArrowType type, double x, double y, double z, Level level) {
        super(ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.get(type), x, y, z, level);
        this.type = type;
    }

    public ModArrow(ArrowType type, LivingEntity livingEntity, Level level) {
        super(ModEntities.ENTITY_TYPE_FROM_TYPE_MAP.get(type), livingEntity, level);
        this.type = type;
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
        return new ItemStack(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(this.type));
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

    @Override
    public void setEnchantmentEffectsFromEntity(LivingEntity shooter, float velocity) {
        super.setEnchantmentEffectsFromEntity(shooter, velocity);
        if (!(this.type == ArrowType.BLAZE_ROD)) return;
        this.setSecondsOnFire(100); /// {@link AbstractArrow#setEnchantmentEffectsFromEntity(LivingEntity, float)}
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.type == ArrowType.REALLY_BIG && result.getEntity() instanceof LivingEntity living) {
            living.knockback(10.0D, -this.getDeltaMovement().x, -this.getDeltaMovement().z);
        }
        processEntityInteraction(this, result);
    }
}
