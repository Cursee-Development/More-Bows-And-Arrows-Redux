package io.github.jason13official.more_bows_and_arrows.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.ModBowItem;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEnchantments;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.List;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player implements ClientAvatarEntity {

  public AbstractClientPlayerMixin(Level level, GameProfile gameProfile) {
    super(level, gameProfile);
  }

  @Inject(at = @At(value = "TAIL"), method = "getFieldOfViewModifier", cancellable = true)
  private void more_bows_and_arrows$getFieldOfViewModifier(boolean firstPerson, float effectScale, CallbackInfoReturnable<Float> cir, @Local(name = "modifier") float modifier) {

    AbstractClientPlayer self = (AbstractClientPlayer) (Object) this;

    if (self.isUsingItem()) {

      ItemStack stack = self.getUseItem();

      float duration = 20.0f;

      List<Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList();

      for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
        if (entry.getKey().is(ModEnchantments.QUICK_PULL)) {
          duration = 10.0f;
          break;
        }
      }

      if (stack.getItem() instanceof ModBowItem modBowItem) {
        float scale = Math.min((float) self.getTicksUsingItem() / duration, 1.0F);
        modifier *= 1.0F - Mth.square(scale) * 0.15F;
        cir.setReturnValue(Mth.lerp(effectScale, 1.0F, modifier));
      }
    }
  }

  @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isUsingItem()Z"), method = "getFieldOfViewModifier", cancellable = true)
  private void more_bows_and_arrows$getFOVBow(boolean firstPerson, float effectScale, CallbackInfoReturnable<Float> cir, @Local(name = "modifier") float modifier) {

    AbstractClientPlayer self = (AbstractClientPlayer) (Object) this;

    if (self.isUsingItem()) {

      ItemStack stack = self.getUseItem();

      float duration = 20.0f;

      List<Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(stack).entrySet().stream().toList();

      for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
        if (entry.getKey().is(ModEnchantments.QUICK_PULL)) {
          duration = 10.0f;
          break;
        }
      }

      if (stack.getItem() instanceof BowItem bowItem) {
        float scale = Math.min((float) self.getTicksUsingItem() / duration, 1.0F);
        modifier *= 1.0F - Mth.square(scale) * 0.15F;
        cir.setReturnValue(Mth.lerp(effectScale, 1.0F, modifier));
      }
    }
  }
}
