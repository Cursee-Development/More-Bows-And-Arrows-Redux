package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin {

    @Inject(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isUsingItem()Z"), cancellable = true)
    private void more_bows_and_arrows$getFieldOfViewModifier(CallbackInfoReturnable<Float> cir) {
        AbstractClientPlayer self = (AbstractClientPlayer) (Object) this;
        ItemStack stack = self.getUseItem();
        if (stack.getItem() instanceof ModBowItem) {

            float f = 1.0F;
            if (self.getAbilities().flying) {
                f *= 1.1F;
            }

            f *= ((float)self.getAttributeValue(Attributes.MOVEMENT_SPEED) / self.getAbilities().getWalkingSpeed() + 1.0F) / 2.0F;
            if (self.getAbilities().getWalkingSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
                f = 1.0F;
            }

            int i = self.getTicksUsingItem();
            float duration = 20.0f;

            List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList();

            for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
                if (entry.getKey().is(ModEnchantments.QUICK_PULL)) {
                    duration = 10.0f;
                    break;
                }
            }

            float f1 = (float)i / duration;
            if (f1 > 1.0F) {
                f1 = 1.0F;
            } else {
                f1 *= f1;
            }

            f *= 1.0F - f1 * 0.15F;
            cir.setReturnValue(f);
        }
    }
}
