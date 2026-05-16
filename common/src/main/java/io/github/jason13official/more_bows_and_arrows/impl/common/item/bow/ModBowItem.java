package io.github.jason13official.more_bows_and_arrows.impl.common.item.bow;

import io.github.jason13official.more_bows_and_arrows.Constants;
import io.github.jason13official.more_bows_and_arrows.impl.common.ModConfig;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

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

  @Override @SuppressWarnings("all")
  public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    appendHoverText(this.type, builder);
  }

  public static void appendHoverText(BowType type, Consumer<Component> tooltipComponents) {

    // wooden bows and their stripped variants should use the same lore text,
    // so we replace the "stripped_" prefix
    switch (type) {
      // case ACACIA, BAMBOO, BIRCH, CHERRY, CRIMSON_STEM, DARK_OAK, JUNGLE, MANGROVE, OAK, SPRUCE, WARPED_STEM,
      case STRIPPED_ACACIA, STRIPPED_BAMBOO, STRIPPED_BIRCH, STRIPPED_CHERRY, STRIPPED_CRIMSON_STEM, STRIPPED_DARK_OAK, STRIPPED_JUNGLE, STRIPPED_MANGROVE, STRIPPED_OAK, STRIPPED_SPRUCE, STRIPPED_WARPED_STEM -> {
        addLore(type.name().toLowerCase().replace("stripped_", ""), tooltipComponents);
        return;
      }
    }
    addLore(type.name().toLowerCase(), tooltipComponents);
  }

  private static void addLore(String loreID, Consumer<Component> tooltipComponents) {
    tooltipComponents.accept(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_bow_lore1"));
    tooltipComponents.accept(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_bow_lore2"));
    tooltipComponents.accept(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_bow_damage"));
  }
}
