package com.cursee.more_bows_and_arrows.core.registry;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.LinkedHashMap;

public class ModDispenserBlockBehaviors {

//    public static final LinkedHashMap<Item, AbstractProjectileDispenseBehavior> BEHAVIOR_FROM_ITEM_MAP = new LinkedHashMap<>();

//    public static void init() {
//        for (ArrowType type : ArrowType.values()) {
//            BEHAVIOR_FROM_ITEM_MAP.put(ModItems.ARROW_ITEM_FROM_TYPE_MAP.get(type), new AbstractProjectileDispenseBehavior() {
//                @Override
//                protected Projectile getProjectile(Level level, Position position, ItemStack itemStack) {
////                    ModArrow arrow = new ModArrow(type, position.x(), position.y(), position.z(), level);
////                    arrow.pickup = AbstractArrow.Pickup.ALLOWED;
////                    return arrow;
//
//                    return Util.make(new ModArrow(type, position.x(), position.y(), position.z(), level), (arrow) -> {});
//                }
//            });
//        }
//    }
}
