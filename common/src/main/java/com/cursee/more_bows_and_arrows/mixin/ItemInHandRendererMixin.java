package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Unique
    private ItemStack more_bows_and_arrows$stack;

    @Inject(method = "renderArmWithItem", at = @At("HEAD"))
    private void more_bows_and_arrows$renderArmWithItemInject(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, CallbackInfo ci) {
        this.more_bows_and_arrows$stack = stack;
    }

    @ModifyConstant(method = "renderArmWithItem", constant = @Constant(floatValue = 20.0f))
    private float more_bows_and_arrows$renderArmWithItemModifyConstant(float constant) {

        float duration = 20.0f;

        List<Object2IntMap.Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(more_bows_and_arrows$stack).entrySet().stream().toList();

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
            if (entry.getKey().is(ModEnchantments.QUICK_PULL)) {
                duration = 10.0f;
                break;
            }
        }

        return duration;
    }
}
