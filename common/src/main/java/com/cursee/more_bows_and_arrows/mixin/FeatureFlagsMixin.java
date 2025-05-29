package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagRegistry;
import net.minecraft.world.flag.FeatureFlags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FeatureFlags.class)
public class FeatureFlagsMixin {

    @Unique
    private static FeatureFlag more_bows_and_arrows$banned;

    @Inject(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/flag/FeatureFlagRegistry$Builder;createVanilla(Ljava/lang/String;)Lnet/minecraft/world/flag/FeatureFlag;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void more_bows_and_arrows$clinit(CallbackInfo ci, FeatureFlagRegistry.Builder builder) {
        more_bows_and_arrows$banned = builder.create(MoreBowsAndArrows.identifier("banned"));
    }
}
