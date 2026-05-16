package io.github.jason13official.more_bows_and_arrows.impl.common.item.bow;

public enum BowType {

  NORMAL(384, 0.0f),
  NOCTURNAL(369, 4.0f),

  AMETHYST(400, 2.0f),
  BLAZE(750, 3.0f),
  BONE(400, 2.0f),
  COAL(400, 2.0f),
  COPPER(500, 2.0f),
  DIAMOND(1000, 5.0f),
  EMERALD(750, 3.0f),
  GOLD(400, 1.5f),
  IRON(500, 2.5f),
  LAPIS(400, 2.0f),
  MOSS(300, 0.0f),
  NETHERITE(2000, 6.0f),
  OBSIDIAN(750, 4.0f),
  PAPER(300, 0.0f),

  ACACIA(350, 2.0f),
  BAMBOO(350, 2.0f),
  BIRCH(350, 2.0f),
  CHERRY(350, 2.0f),
  CRIMSON_STEM(350, 2.0f),
  DARK_OAK(350, 2.0f),
  JUNGLE(350, 2.0f),
  MANGROVE(350, 2.0f),
  OAK(350, 2.0f),
  SPRUCE(350, 2.0f),
  WARPED_STEM(350, 2.0f),

  STRIPPED_ACACIA(350, 2.0f),
  STRIPPED_BAMBOO(350, 2.0f),
  STRIPPED_BIRCH(350, 2.0f),
  STRIPPED_CHERRY(350, 2.0f),
  STRIPPED_CRIMSON_STEM(350, 2.0f),
  STRIPPED_DARK_OAK(350, 2.0f),
  STRIPPED_JUNGLE(350, 2.0f),
  STRIPPED_MANGROVE(350, 2.0f),
  STRIPPED_OAK(350, 2.0f),
  STRIPPED_SPRUCE(350, 2.0f),
  STRIPPED_WARPED_STEM(350, 2.0f),
  ;

  private final int uses;
  private final float attackDamageBonus;

  BowType(int uses, float attackDamageBonus) {
    this.uses = uses;
    this.attackDamageBonus = attackDamageBonus;
  }

  public int getUses() {
    return uses;
  }

  public float getAttackDamageBonus() {
    return this.attackDamageBonus;
  }
}
