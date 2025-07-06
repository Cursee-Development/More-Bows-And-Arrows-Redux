package com.cursee.more_bows_and_arrows;


import com.cursee.more_bows_and_arrows.core.registry.ModRegistryNeoForge;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class MoreBowsAndArrowsNeoForge {

    public static IEventBus EVENT_BUS;

    public MoreBowsAndArrowsNeoForge(final FMLModContainer container) {
        MoreBowsAndArrows.init();
        EVENT_BUS = container.getEventBus();
        ModRegistryNeoForge.register(EVENT_BUS);
        if (FMLEnvironment.dist == Dist.CLIENT) new MoreBowsAndArrowsClientNeoForge(EVENT_BUS);
    }
}