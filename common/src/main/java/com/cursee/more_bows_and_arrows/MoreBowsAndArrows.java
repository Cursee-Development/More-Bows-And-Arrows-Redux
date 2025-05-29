package com.cursee.more_bows_and_arrows;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.registry.ModDispenserBlockBehaviors;
import net.minecraft.resources.ResourceLocation;

public class MoreBowsAndArrows {

    public static void init() {
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        ModConfig.onLoad();
    }

    public static void afterRegistration() {
        ModDispenserBlockBehaviors.init();
    }

    public static ResourceLocation identifier(String path) {
        return new ResourceLocation(Constants.MOD_ID, path);
    }
}