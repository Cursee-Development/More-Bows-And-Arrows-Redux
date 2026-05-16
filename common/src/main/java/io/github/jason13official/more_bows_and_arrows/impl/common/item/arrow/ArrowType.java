package io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow;

import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import net.minecraft.resources.Identifier;

public enum ArrowType {

  AMETHYST(3, MoreBowsAndArrows.identifier("textures/entity/arrow/amethyst_arrow.png")),
  BAMBOO(1, MoreBowsAndArrows.identifier("textures/entity/arrow/bamboo_arrow.png")),
  BLAZE_ROD(4, MoreBowsAndArrows.identifier("textures/entity/arrow/blaze_rod_arrow.png")),
  BONE(2, MoreBowsAndArrows.identifier("textures/entity/arrow/bone_arrow.png")),
  CACTUS(2, MoreBowsAndArrows.identifier("textures/entity/arrow/cactus_arrow.png")),
  COAL(2, MoreBowsAndArrows.identifier("textures/entity/arrow/coal_arrow.png")),
  COPPER(2, MoreBowsAndArrows.identifier("textures/entity/arrow/copper_arrow.png")),
  DIAMOND(4, MoreBowsAndArrows.identifier("textures/entity/arrow/diamond_arrow.png")),
  EMERALD(3, MoreBowsAndArrows.identifier("textures/entity/arrow/emerald_arrow.png")),
  ENDER_PEARL(2, MoreBowsAndArrows.identifier("textures/entity/arrow/ender_pearl_arrow.png")),
  FLINT_AND_STEEL(2, MoreBowsAndArrows.identifier("textures/entity/arrow/flint_and_steel_arrow.png")),
  FLINT(2, MoreBowsAndArrows.identifier("textures/entity/arrow/flint_arrow.png")),
  GOLD(2, MoreBowsAndArrows.identifier("textures/entity/arrow/gold_arrow.png")),
  IRON(3, MoreBowsAndArrows.identifier("textures/entity/arrow/iron_arrow.png")),
  LAPIS(3, MoreBowsAndArrows.identifier("textures/entity/arrow/lapis_arrow.png")),
  MOSS(0, MoreBowsAndArrows.identifier("textures/entity/arrow/moss_arrow.png")),
  NETHERITE(6, MoreBowsAndArrows.identifier("textures/entity/arrow/netherite_arrow.png")),
  OBSIDIAN(5, MoreBowsAndArrows.identifier("textures/entity/arrow/obsidian_arrow.png")),
  PAPER(0, MoreBowsAndArrows.identifier("textures/entity/arrow/paper_arrow.png")),
  TNT(5, MoreBowsAndArrows.identifier("textures/entity/arrow/tnt_arrow.png")),
  ;

  private final float attackDamageBonus;
  private final Identifier textureLocation;

  ArrowType(float attackDamageBonus, Identifier textureLocation) {
    this.attackDamageBonus = attackDamageBonus;
    this.textureLocation = textureLocation;
  }

  public Identifier getTextureLocation() {
    return textureLocation;
  }

  public float getAttackDamageBonus() {
    return attackDamageBonus;
  }
}
