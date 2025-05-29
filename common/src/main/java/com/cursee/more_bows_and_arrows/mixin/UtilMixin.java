package com.cursee.more_bows_and_arrows.mixin;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.platform.Services;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Util.class)
public class UtilMixin {

    @Unique
    private static boolean more_bows_and_arrows$sent = false;

    @Inject(method = "doFetchChoiceType", at = @At(value = "HEAD"))
    private static void more_bows_and_arrows$doFetchChoiceType(DSL.TypeReference p_type, String choiceName, CallbackInfoReturnable<Type<?>> cir) {
        // if (Services.PLATFORM.getEnvironmentName().equalsIgnoreCase("fabric") && !more_bows_and_arrows$sent && choiceName.contains("more_bows_and_arrows:")) {
        if (!more_bows_and_arrows$sent && choiceName.contains("more_bows_and_arrows:")) {
            Constants.LOG.info("(you can ignore these errors: \"No data fixer registered for more_bows_and_arrows:...\")");
            more_bows_and_arrows$sent = true;
        }
    }
}
