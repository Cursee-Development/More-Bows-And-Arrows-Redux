package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
        return EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.QUICK_PULL, more_bows_and_arrows$stack) > 0 ? 10.0f : 20.0f;
    }
}
