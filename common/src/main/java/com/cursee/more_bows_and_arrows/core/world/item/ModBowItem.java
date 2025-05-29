package com.cursee.more_bows_and_arrows.core.world.item;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.registry.ModItems;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import com.cursee.more_bows_and_arrows.core.world.item.util.ModParticleFunctions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ModBowItem extends BowItem {

    private final BowType type;

    public ModBowItem(BowType type, Properties properties) {
        super(properties.defaultDurability(type.getUses()));
        this.type = type;
    }

    public BowType getBowType() {
        return type;
    }

    @Override
    public int getEnchantmentValue() {
        return this.type.getEnchantmentValue();
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return this.type.getRepairIngredient().test(repairCandidate);
    }

    @SuppressWarnings("all")
    public void hurtLivingEntity(AbstractArrow abstractArrow, LivingEntity owner, LivingEntity hitEntity) {

        // hurt the hit entity with the original abstractArrow+owner as the source, but apply the bow's damage bonus
        hitEntity.hurt(owner.level().damageSources().arrow(abstractArrow, owner), this.type.getAttackDamageBonus());

        ModArrow modArrow = abstractArrow instanceof ModArrow ? (ModArrow) abstractArrow : null;

        // apply any additional effects onto the hit entity from our bow
        switch (this.getBowType()) {

            case AMETHYST -> {}
            case BLAZE -> setOnFire(hitEntity);
            case BONE -> {}
            case COAL -> {
                if (modArrow != null && (modArrow.getArrowType() == ArrowType.FLINT || modArrow.getArrowType() == ArrowType.FLINT_AND_STEEL)) setOnFire(hitEntity);
            }
            case COPPER -> {}
            case DIAMOND -> {}
            case EMERALD -> {}
            case GOLD -> {}
            case IRON -> {
                if (modArrow != null && (modArrow.getArrowType() == ArrowType.FLINT || modArrow.getArrowType() == ArrowType.FLINT_AND_STEEL)) setOnFire(hitEntity);
            }
            case LAPIS -> {}
            case MOSS -> {}
            case NETHERITE -> {}
            case OBSIDIAN -> {}
            case PAPER -> {}

//            case ACACIA -> {}
//            case BAMBOO -> {}
//            case BIRCH -> {}
//            case CHERRY -> {}
//            case CRIMSON_STEM -> {}
//            case DARK_OAK -> {}
//            case JUNGLE -> {}
//            case MANGROVE -> {}
//            case OAK -> {}
//            case SPRUCE -> {}
//            case WARPED_STEM -> {}
//
//            case STRIPPED_ACACIA -> {}
//            case STRIPPED_BAMBOO -> {}
//            case STRIPPED_BIRCH -> {}
//            case STRIPPED_CHERRY -> {}
//            case STRIPPED_CRIMSON_STEM -> {}
//            case STRIPPED_DARK_OAK -> {}
//            case STRIPPED_JUNGLE -> {}
//            case STRIPPED_MANGROVE -> {}
//            case STRIPPED_OAK -> {}
//            case STRIPPED_SPRUCE -> {}
//            case STRIPPED_WARPED_STEM -> {}
            default -> {}
        }
    }

    private void setOnFire(LivingEntity hitEntity) {
        hitEntity.setSecondsOnFire(2);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        /// This call to the super's implementation does nothing, no need to invoke.
        // super.onUseTick(level, entity, stack, remainingUseDuration);
        if (this.type == BowType.NOCTURNAL) ModParticleFunctions.nocturnalBowParticles(level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {

        BowType.appendHoverText(this.type, tooltipComponents);

        // super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }

//    @Override
//    public FeatureFlagSet requiredFeatures() {
//
//        if (ModConfig.BANNED_BOWS.contains(this.type.name().toLowerCase() + "_bow")) return FeatureFlagSet.of();
//
//        return FeatureFlags.VANILLA_SET;
//    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return !ModConfig.BANNED_BOWS.contains(this.type.name().toLowerCase() + "_bow");
    }
}
