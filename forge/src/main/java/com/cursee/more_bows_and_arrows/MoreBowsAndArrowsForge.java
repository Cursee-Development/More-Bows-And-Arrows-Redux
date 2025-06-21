package com.cursee.more_bows_and_arrows;

import com.cursee.more_bows_and_arrows.core.registry.ModDispenserBlockBehaviors;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.registry.ModRegistryForge;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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

        // MoreBowsAndArrows.afterRegistration();
        MinecraftForge.EVENT_BUS.addListener(this::onCommonSetup);

//        MinecraftForge.EVENT_BUS.addListener((Consumer<ServerAboutToStartEvent>) event -> {
//            ServerConfig.onLoad();
//        });
    }

    public void onCommonSetup(final FMLCommonSetupEvent event) {
        // ModDispenserBlockBehaviors.BEHAVIOR_FROM_ITEM_MAP.forEach(DispenserBlock::registerBehavior);
    }

    @SuppressWarnings("removal")
    public MoreBowsAndArrowsForge() {
        this(FMLJavaModLoadingContext.get());
    }
}