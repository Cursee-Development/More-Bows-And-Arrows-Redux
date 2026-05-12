package com.cursee.more_bows_and_arrows;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.registry.*;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

public class MoreBowsAndArrows {

    public static void init() {
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        ModConfig.onLoad();

        ModItems.loadClass();
        ModTabs.loadClass();
        ModEntities.loadClass();
        ModEnchantmentEntityEffects.loadClass();

        ModEnchantments.createResourceKeys();
//        TagKey.create(Registries.ITEM, identifier("mod_bows"));
//        for (BowType value : BowType.values()) {
//            System.out.println("creating item tag: " + identifier(value.name().toLowerCase() + "_bow").toString());
//            TagKey.create(Registries.ITEM, identifier(value.name().toLowerCase() + "_bow"));
//        }
    }

    public static void afterRegistration() {
        // ModDispenserBlockBehaviors.init();
    }

    public static Identifier identifier(String path) {
        return Identifier.fromNamespaceAndPath(Constants.MOD_ID, path);
    }
}