package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.core.registry.ModEnchantments;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.core.Holder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(value = ItemInHandRenderer.class, priority = 1)
public class ItemInHandRendererMixin {

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void more_bows_and_arrows$renderArmWithItemInject(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, CallbackInfo ci) {

        ItemInHandRenderer self = (ItemInHandRenderer) (Object) this;

        if (!player.isUsingItem() && !(player.getUseItem().getItem() instanceof BowItem)) return;

        // registry lookups on a stale/leaked ClientLevel (e.g. right after logout/disconnect) can miss
        // modded enchantment entries entirely; bail out instead of letting getOrThrow crash the render thread
        Optional<Holder.Reference<Enchantment>> quickPullHolder =
                player.level().holderLookup(Registries.ENCHANTMENT).get(ModEnchantments.QUICK_PULL);
        if (quickPullHolder.isEmpty()) return;
        if (EnchantmentHelper.getItemEnchantmentLevel(quickPullHolder.get(), stack) == 0) return;

        // player is using a bow with the quick pull enchantment
        assert Minecraft.getInstance().player != null;

        boolean flag = hand == InteractionHand.MAIN_HAND;
        HumanoidArm humanoidarm = flag ? player.getMainArm() : player.getMainArm().getOpposite();
        poseStack.pushPose();

        boolean flag3 = humanoidarm == HumanoidArm.RIGHT;
        if (player.isUsingItem() && player.getUseItemRemainingTicks() > 0 && player.getUsedItemHand() == hand) {
            int k = flag3 ? 1 : -1;

            // we already know we're using a bow so no switch statement

            more_bows_and_arrows$applyItemArmTransform(poseStack, humanoidarm, equippedProgress);
            poseStack.translate((float)k * -0.2785682F, 0.18344387F, 0.15731531F);
            poseStack.mulPose(Axis.XP.rotationDegrees(-13.935F));
            poseStack.mulPose(Axis.YP.rotationDegrees((float)k * 35.3F));
            poseStack.mulPose(Axis.ZP.rotationDegrees((float)k * -9.785F));
            float f8 = (float)stack.getUseDuration(player) - ((float) Minecraft.getInstance().player.getUseItemRemainingTicks() - partialTicks + 1.0F);
            float f12 = f8 / 20.0F;
            f12 = (f12 * f12 + f12 * 2.0F) / 3.0F;
            if (f12 > 1.0F) {
                f12 = 1.0F;
            }

            if (f12 > 0.1F) {
                float f15 = Mth.sin((f8 - 0.1F) * 1.3F);
                float f18 = f12 - 0.1F;
                float f20 = f15 * f18;
                poseStack.translate(f20 * 0.0F, f20 * 0.004F, f20 * 0.0F);
            }

            poseStack.translate(f12 * 0.0F, f12 * 0.0F, f12 * 0.04F);
            poseStack.scale(1.0F, 1.0F, 1.0F + f12 * 0.2F);
            poseStack.mulPose(Axis.YN.rotationDegrees((float)k * 45.0F));
        }

        self.renderItem(player, stack, flag3 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !flag3, poseStack, buffer, combinedLight);

        poseStack.popPose();

        // System.out.println("cancelled further logic");
        ci.cancel();
    }

    @Unique
    private void more_bows_and_arrows$applyItemArmTransform(PoseStack poseStack, HumanoidArm hand, float equippedProg) {
        int i = hand == HumanoidArm.RIGHT ? 1 : -1;
        poseStack.translate((float)i * 0.56F, -0.52F + equippedProg * -0.6F, -0.72F);
    }

//    @ModifyConstant(method = "renderArmWithItem", constant = @Constant(floatValue = 20.0f))
//    private float more_bows_and_arrows$renderArmWithItemModifyConstant(float constant) {
//        return EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.QUICK_PULL, more_bows_and_arrows$stack) > 0 ? 10.0f : 20.0f;
//    }
}