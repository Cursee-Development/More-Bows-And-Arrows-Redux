package com.cursee.more_bows_and_arrows.core.world.entity.projectile.util;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public enum ArrowType {

    /// {@link TippableArrowRenderer#NORMAL_ARROW_LOCATION}
    NORMAL(new ResourceLocation("textures/entity/projectiles/arrow.png")),
    REALLY_BIG(new ResourceLocation("textures/entity/projectiles/arrow.png")),

    AMETHYST(MoreBowsAndArrows.identifier("textures/entity/arrow/amethyst_arrow.png")),
    BAMBOO(MoreBowsAndArrows.identifier("textures/entity/arrow/bamboo_arrow.png")),
    BLAZE_ROD(MoreBowsAndArrows.identifier("textures/entity/arrow/blaze_rod_arrow.png")),
    BONE(MoreBowsAndArrows.identifier("textures/entity/arrow/bone_arrow.png")),
    CACTUS(MoreBowsAndArrows.identifier("textures/entity/arrow/cactus_arrow.png")),
    COAL(MoreBowsAndArrows.identifier("textures/entity/arrow/coal_arrow.png")),
    COPPER(MoreBowsAndArrows.identifier("textures/entity/arrow/copper_arrow.png")),
    DIAMOND(MoreBowsAndArrows.identifier("textures/entity/arrow/diamond_arrow.png")),
    EMERALD(MoreBowsAndArrows.identifier("textures/entity/arrow/emerald_arrow.png")),
    ENDER_PEARL(MoreBowsAndArrows.identifier("textures/entity/arrow/ender_pearl_arrow.png")),
    FLINT_AND_STEEL(MoreBowsAndArrows.identifier("textures/entity/arrow/flint_and_steel_arrow.png")),
    FLINT(MoreBowsAndArrows.identifier("textures/entity/arrow/flint_arrow.png")),
    GOLD(MoreBowsAndArrows.identifier("textures/entity/arrow/gold_arrow.png")),
    IRON(MoreBowsAndArrows.identifier("textures/entity/arrow/iron_arrow.png")),
    LAPIS(MoreBowsAndArrows.identifier("textures/entity/arrow/lapis_arrow.png")),
    MOSS(MoreBowsAndArrows.identifier("textures/entity/arrow/moss_arrow.png")),
    NETHERITE(MoreBowsAndArrows.identifier("textures/entity/arrow/netherite_arrow.png")),
    OBSIDIAN(MoreBowsAndArrows.identifier("textures/entity/arrow/obsidian_arrow.png")),
    PAPER(MoreBowsAndArrows.identifier("textures/entity/arrow/paper_arrow.png")),
    TNT(MoreBowsAndArrows.identifier("textures/entity/arrow/tnt_arrow.png"));

    private final ResourceLocation textureLocation;

    ArrowType(ResourceLocation textureLocation) {
        this.textureLocation = textureLocation;
    }

    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    public static void appendHoverText(ArrowType type, List<Component> tooltipComponents) {
        addLore(type.name().toLowerCase(), tooltipComponents);
    }

    private static void addLore(String loreID, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_arrow_lore1"));
        tooltipComponents.add(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_arrow_lore2"));
        tooltipComponents.add(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_arrow_damage"));
    }
}
