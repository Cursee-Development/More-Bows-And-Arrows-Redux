package io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow;

import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import net.minecraft.resources.Identifier;

public enum ArrowType {

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
  TNT(MoreBowsAndArrows.identifier("textures/entity/arrow/tnt_arrow.png")),
  ;

  private final Identifier textureLocation;

  ArrowType(Identifier textureLocation) {
    this.textureLocation = textureLocation;
  }

  public Identifier getTextureLocation() {
    return textureLocation;
  }
}
