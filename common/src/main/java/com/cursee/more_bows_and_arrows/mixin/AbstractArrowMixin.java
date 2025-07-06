package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {



//    @Inject(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
//    private void more_bows_and_arrows$onHitEntity$hurt(EntityHitResult result, CallbackInfo ci) {
    @Inject(method = "onHitEntity", at = @At(value = "TAIL"))
    private void more_bows_and_arrows$onHitEntity$hurt(EntityHitResult result, CallbackInfo ci) {

        AbstractArrow arrow = (AbstractArrow) (Object) this;

        if (arrow.level().isClientSide()) return;

        if (!(arrow.getOwner() instanceof LivingEntity owner)) return;

        ItemStack mainHandItem = owner.getMainHandItem();

        if (!(result.getEntity() instanceof LivingEntity hitEntity)) return;

        if (mainHandItem.getItem() instanceof ModBowItem bow) bow.hurtLivingEntity(arrow, owner, hitEntity);


        List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(mainHandItem).entrySet().stream().toList();

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
            if (entry.getKey().is(ModEnchantments.TEMPO_THIEF) || mainHandItem.is(ModItems.BOW_ITEM_FROM_TYPE_MAP.get(BowType.NOCTURNAL))) {
                hitEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 5, 2));
                owner.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 5, 1));
                break;
            }
        }
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
            if (entry.getKey().is(ModEnchantments.ANTI_GRAVITY)) {
                hitEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20 * 4, 1));
                break;
            }
        }
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
            if (hitEntity instanceof Monster && entry.getKey().is(ModEnchantments.MONSTER_HUNTER)) {
                hitEntity.hurt(owner.damageSources().arrow(arrow, owner), 4.0f);
                break;
            }
        }
    }
}
