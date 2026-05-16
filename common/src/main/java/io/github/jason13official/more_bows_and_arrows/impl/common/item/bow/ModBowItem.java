package io.github.jason13official.more_bows_and_arrows.impl.common.item.bow;

import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BowItem;

public class ModBowItem extends BowItem {

  private final BowType type;

  public ModBowItem(BowType type, Properties properties) {
    super(properties);
    this.type = type;
  }

  public BowType getType() {
    return type;
  }

  @Override
  public boolean isEnabled(FeatureFlagSet enabledFeatures) {
    return !ModConfig.get().BANNED_BOWS.contains(this);
  }
}
