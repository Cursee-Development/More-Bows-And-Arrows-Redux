package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BowItem.class)
public class BowItemMixin {

    private ItemStack more_bows_and_arrows$stack;

    @Redirect(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BowItem;getPowerForTime(I)F"))
    private float injected(int charge) {
        return more_bows_and_arrows$getPowerForTime(more_bows_and_arrows$stack, charge);
    }

    @Inject(method = "releaseUsing", at = @At("HEAD"))
    private void more_bows_and_arrows$releaseUsing$handleDefensiveShot(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft, CallbackInfo ci) {
        more_bows_and_arrows$stack = stack;

        if (!(entityLiving instanceof Player player)) return;
        // if (player.getProjectile(stack).isEmpty() || !(player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0)) return;
        if (player.getProjectile(stack).isEmpty()) return;

        int i = 72000 - timeLeft;
        float f = more_bows_and_arrows$getPowerForTime(stack, i);
        if ((double) f < 0.1) return;

        int defenseShotLevel = 0;

        List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList();

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
            if (entry.getKey().is(ModEnchantments.DEFENSIVE_SHOT)) {
                defenseShotLevel = entry.getIntValue();
                break;
            }
        }

        if (defenseShotLevel > 0) {
            for (int amount=0; amount<defenseShotLevel; amount++) {
                List<LivingEntity> nearby = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, player.getBoundingBox().inflate(8.0D, 0.0D, 8.0D));
                if (nearby.isEmpty()) return;
                LivingEntity nearest = nearby.get(0);
                BlockPos nearestPos = nearby.get(0).blockPosition();

                Arrow arrow = new Arrow(level, player, new ItemStack(Items.ARROW), stack);
                arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
                // arrow.shoot(nearestPos.getX(), nearestPos.getY(), nearestPos.getZ(), 8.0f, 0);

                double d0 = nearestPos.getX() - arrow.getX();
                double d1 = nearest.getY(0.3333333333333333D) - arrow.getY();
                double d2 = nearestPos.getZ() - arrow.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);

                arrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, 6.0f);
                // player.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
                player.level().addFreshEntity(arrow);
            }
        }
    }

    @Inject(method = "releaseUsing", at = @At("HEAD"))
    private void more_bows_and_arrows$releaseUsing$handleBonusShot(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft, CallbackInfo ci) {
        more_bows_and_arrows$stack = stack;

        if (!(entityLiving instanceof Player player)) return;
        if (player.getProjectile(stack).isEmpty()) return;

        int i = 72000 - timeLeft;
        float f = more_bows_and_arrows$getPowerForTime(stack, i);
        if ((double) f < 0.1) return;

        int bonusShotLevel = 0;

        List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList();

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
            if (entry.getKey().is(ModEnchantments.BONUS_SHOT)) {
                bonusShotLevel = entry.getIntValue();
                break;
            }
        }

        if (bonusShotLevel > 0) {
            for (int amount=0; amount<bonusShotLevel; amount++) {
                Arrow arrow = new Arrow(level, player, new ItemStack(Items.ARROW), stack);

                arrow.pickup = AbstractArrow.Pickup.DISALLOWED;

                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, f * 3, f * 20.0f);

                player.level().addFreshEntity(arrow);
            }
        }
    }

//    @Unique
//    private static float more_bows_and_arrows$getPowerForTime(ItemStack stack, int charge) {
////        float f = (float) charge / (
////                EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.QUICK_PULL, stack) > 0 ? 10.0f : 20.0f
////        );
//        float f = 20.0f;
//
//        List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList();
//
//        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
//            if (entry.getKey().is(ModEnchantments.QUICK_PULL)) {
//                // if (charge / entry.getIntValue() > 0) f = 10.0f;
//                f = charge / (10.0f);
//                break;
//            }
//        }
//
//        f = (f * f + f * 2.0F) / 3.0F;
//        if (f > 1.0F) {
//            f = 1.0F;
//        }
//
//        return f;
//    }

    @Unique
    private static float more_bows_and_arrows$getPowerForTime(ItemStack stack, int charge) {

        float amount = 20.0f;

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList()) {
            if (entry.getKey().is(ModEnchantments.QUICK_PULL)) {
                amount = 10.0f;
                break;
            }
        }

        float f = (float)charge / amount;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }
}
