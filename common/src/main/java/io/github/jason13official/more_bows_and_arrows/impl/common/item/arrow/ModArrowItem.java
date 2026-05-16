package io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow;

import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ArrowItem;

public class ModArrowItem extends ArrowItem {

  private final ArrowType type;

  public ModArrowItem(ArrowType type, Properties properties) {
    super(properties);
    this.type = type;
  }

  public ArrowType getType() {
    return type;
  }

  @Override
  public boolean isEnabled(FeatureFlagSet enabledFeatures) {
    return !ModConfig.get().BANNED_ARROWS.contains(this);
  }
}
