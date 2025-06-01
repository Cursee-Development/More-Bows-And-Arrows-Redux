package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArrowItem.class)
public class ArrowItemMixin {

    @Inject(method = "createArrow", at = @At(value = "TAIL"), cancellable = true)
    private void more_bows_and_arrows$createArrow(Level level, ItemStack stack, LivingEntity shooter, CallbackInfoReturnable<AbstractArrow> cir) {
        if (shooter.getMainHandItem().getItem() instanceof ModBowItem modBow && modBow.getBowType() == BowType.BLAZE) {
            AbstractArrow arrow = cir.getReturnValue();
            arrow.setSecondsOnFire(100);
            // cir.setReturnValue(arrow); // maybe not needed
        }
    }
}
