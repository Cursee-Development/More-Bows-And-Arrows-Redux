package io.github.jason13official.more_bows_and_arrows.mixin;

import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.BowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.IModBow;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.ModBowItem;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModEnchantments;
import io.github.jason13official.more_bows_and_arrows.impl.common.registry.ModItems;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {

  @Inject(method = "onHitEntity", at = @At(value = "TAIL"))
  private void more_bows_and_arrows$onHitEntity(EntityHitResult result, CallbackInfo ci) {

    AbstractArrow arrow = (AbstractArrow) (Object) this;

    if (arrow.level().isClientSide()) return;

    if (!(arrow.getOwner() instanceof LivingEntity owner)) return;

    ItemStack mainHandItem = owner.getMainHandItem();

    if (!(result.getEntity() instanceof LivingEntity hitEntity)) return;

    if (mainHandItem.getItem() instanceof IModBow bow) bow.hurtLivingEntity(arrow, owner, hitEntity);

    List<Entry<Holder<Enchantment>>> list = EnchantmentHelper.getEnchantmentsForCrafting(mainHandItem).entrySet().stream().toList();

    for (Object2IntMap.Entry<Holder<Enchantment>> entry : list) {
      if (entry.getKey().is(ModEnchantments.TEMPO_THIEF) || mainHandItem.is(ModItems.BOWS.get(BowType.NOCTURNAL))) {
        hitEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 5, 2));
        owner.addEffect(new MobEffectInstance(MobEffects.SPEED, 20 * 5, 1));
        break;
      }
      if (entry.getKey().is(ModEnchantments.ANTI_GRAVITY)) {
        hitEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20 * 4, 1));
        break;
      }
      if (hitEntity instanceof Monster && entry.getKey().is(ModEnchantments.MONSTER_HUNTER)) {
        hitEntity.hurt(owner.damageSources().arrow(arrow, owner), 4.0f);
        break;
      }
    }
  }
}
