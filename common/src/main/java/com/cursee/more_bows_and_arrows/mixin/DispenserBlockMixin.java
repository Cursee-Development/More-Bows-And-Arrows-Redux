package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {

    @Unique
    private static boolean more_bows_and_arrows$registeredDispenseBehaviors = false;

    @Inject(method = "getDispenseMethod", at = @At("HEAD"))
    private void more_bows_and_arrows$getDispenseMethod(Level level, ItemStack item, CallbackInfoReturnable<DispenseItemBehavior> cir) {
        if (more_bows_and_arrows$registeredDispenseBehaviors) return;
        for (ArrowType type : ArrowType.values()){
            DispenserBlock.registerBehavior(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(type), new ProjectileDispenseBehavior(item.getItem()));
//            DispenserBlock.registerBehavior(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(type), new ProjectileDispenseBehavior(item.getItem()) {
//
//                private Projectile getProjectile(Level level, Position position, ItemStack itemStack) {
//                    ModArrow arrow = new ModArrow(type, position.x(), position.y(), position.z(), level);
//                    arrow.pickup = AbstractArrow.Pickup.ALLOWED;
//                    return arrow;
//                }
//
//            });
        }
        more_bows_and_arrows$registeredDispenseBehaviors = true;
    }
}
