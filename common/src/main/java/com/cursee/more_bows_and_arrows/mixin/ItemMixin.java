package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "onUseTick", at = @At("HEAD"))
    public void more_bows_and_arrows$onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration, CallbackInfo ci) {

        // regular item not detected??
        if (!(stack.getItem() instanceof BowItem) && !(stack.getItem() instanceof ModBowItem)) return;

//        int fluidMovementLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FLUID_MOVEMENT, stack);
//        if (fluidMovementLevel > 0) {
//            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, (fluidMovementLevel+1)*2, false, false, false)); // level 2 or 3
//        }

        List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList();

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
            if (entry.getKey().is(ModEnchantments.FLUID_MOVEMENT)) {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, (entry.getIntValue()+1)*2, false, false, false)); // level 2 or 3
                break;
            }
        }
    }
}
