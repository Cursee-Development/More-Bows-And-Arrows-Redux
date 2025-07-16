package com.cursee.more_bows_and_arrows.mixin;

import net.minecraft.world.item.ArrowItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArrowItem.class)
public class ArrowItemMixin {

//    @Inject(method = "createArrow", at = @At(value = "TAIL"), cancellable = true)
//    private void more_bows_and_arrows$createArrow(Level level, ItemStack ammo, LivingEntity shooter, ItemStack weapon, CallbackInfoReturnable<AbstractArrow> cir) {
//        if (shooter.getMainHandItem().getItem() instanceof ModBowItem modBow && modBow.getBowType() == BowType.BLAZE) {
//            AbstractArrow arrow = cir.getReturnValue();
//            arrow.setRemainingFireTicks(100 * 20);
//            // cir.setReturnValue(arrow); // maybe not needed
//        }
//    }
}
