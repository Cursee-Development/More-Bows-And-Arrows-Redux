package io.github.jason13official.more_bows_and_arrows.impl.common.registry;

import io.github.jason13official.more_bows_and_arrows.Constants;
import io.github.jason13official.more_bows_and_arrows.MoreBowsAndArrows;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.BowType;
import io.github.jason13official.more_bows_and_arrows.platform.Services;
import java.util.function.BiConsumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class ModTabs {

  public static CreativeModeTab MORE_BOWS_AND_ARROWS;

  public static void register(BiConsumer<CreativeModeTab, Identifier> consumer) {

    MORE_BOWS_AND_ARROWS = Services.PLATFORM.tabBuilder()
        .icon(() -> new ItemStack(Items.BOW))
        .title(Component.translatable("itemGroup.moreBowsAndArrows"))
        .displayItems((parameters, output) -> {
          ModItems.BOWS.forEach((identifier, item) -> {
            if (identifier != BowType.NORMAL) {
              output.accept(item);
            }
          });
          parameters.holders().lookup(Registries.ENCHANTMENT).ifPresent((enchantments) -> {
            generateEnchantmentBookTypesOnlyMaxLevel(output, enchantments, TabVisibility.PARENT_TAB_ONLY);
          });
          ModItems.ARROWS.forEach((identifier, item) -> output.accept(item));
        })
        .build();

    consumer.accept(MORE_BOWS_AND_ARROWS, MoreBowsAndArrows.identifier(Constants.MOD_ID));
  }

  private static void generateEnchantmentBookTypesOnlyMaxLevel(CreativeModeTab.Output output, HolderLookup<Enchantment> enchantments, CreativeModeTab.TabVisibility tabVisibility) {
    enchantments.listElements()
        .map((enchantment) -> {

          if (!enchantment.key().identifier().getNamespace().equalsIgnoreCase(Constants.MOD_ID)) {
            return ItemStack.EMPTY;
          }

          return EnchantmentHelper.createBook(new EnchantmentInstance(enchantment, enchantment.value().getMaxLevel()));
        })
        .forEach((stack) -> {
          if (!stack.isEmpty()) {
            output.accept(stack, tabVisibility);
          }
        });
  }
}
