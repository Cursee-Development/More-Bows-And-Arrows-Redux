package com.cursee.more_bows_and_arrows.core.world.item;

import com.cursee.more_bows_and_arrows.core.ModConfig;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.IModArrow;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ModArrowItem extends ArrowItem implements IModArrow {

    private final ArrowType type;

    public ModArrowItem(ArrowType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public ArrowType getArrowType() {
        return type;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity shooter) {
        ModArrow arrow =new ModArrow(this.type, shooter, level);

        if (this.type == ArrowType.BLAZE_ROD || (shooter.getMainHandItem().getItem() instanceof ModBowItem modBow && modBow.getBowType() == BowType.BLAZE)) {
            arrow.setSecondsOnFire(100);
        }

        return arrow;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        ArrowType.appendHoverText(this.type, tooltipComponents);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return !ModConfig.BANNED_ARROWS.contains(this.type.name().toLowerCase() + "_arrow");
    }
}
