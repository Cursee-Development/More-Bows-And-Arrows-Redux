package com.cursee.more_bows_and_arrows;

import com.cursee.more_bows_and_arrows.core.registry.ModRegistryForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class MoreBowsAndArrowsForge {

    public static IEventBus EVENT_BUS;

    public MoreBowsAndArrowsForge(FMLJavaModLoadingContext context) {
        MoreBowsAndArrows.init();
        EVENT_BUS = context.getModEventBus();
        ModRegistryForge.register(EVENT_BUS);
        if (FMLEnvironment.dist == Dist.CLIENT) new MoreBowsAndArrowsClientForge(EVENT_BUS);
    }

    @SuppressWarnings("removal")
    public MoreBowsAndArrowsForge() {
        this(FMLJavaModLoadingContext.get());
    }
}