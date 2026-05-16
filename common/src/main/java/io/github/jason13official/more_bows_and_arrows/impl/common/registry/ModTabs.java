package io.github.jason13official.more_bows_and_arrows.impl.common.registry;

import io.github.jason13official.more_bows_and_arrows.Constants;
import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import io.github.jason13official.more_bows_and_arrows.platform.Services;
import java.util.function.BiConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModTabs {

  public static CreativeModeTab MORE_BOWS_AND_ARROWS;

  public static void register(BiConsumer<CreativeModeTab, Identifier> consumer) {

    MORE_BOWS_AND_ARROWS = Services.PLATFORM.tabBuilder()
        .icon(() -> new ItemStack(Items.BOW))
        .title(Component.translatable("itemGroup.moreBowsAndArrows"))
        .displayItems((itemDisplayParameters, output) -> {
          ModItems.BOWS.forEach((identifier, item) -> output.accept(item));
          ModItems.ARROWS.forEach((identifier, item) -> output.accept(item));
        })
        .build();

    consumer.accept(MORE_BOWS_AND_ARROWS, MoreBowsAndArrows.identifier(Constants.MOD_ID));
  }
}
