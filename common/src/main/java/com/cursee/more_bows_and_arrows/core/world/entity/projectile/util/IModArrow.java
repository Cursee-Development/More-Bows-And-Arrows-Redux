package com.cursee.more_bows_and_arrows.core.world.entity.projectile.util;

import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.item.ModBowItem;
import com.cursee.more_bows_and_arrows.core.world.item.util.BowType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

import static net.minecraft.world.level.levelgen.feature.Feature.isDirt;

@FunctionalInterface
public interface IModArrow {

    ArrowType getArrowType();

    default void processBlockInteraction(ModArrow arrow, BlockHitResult result) {

        Entity owner = arrow.getOwner();
        Level level = arrow.level();
        BlockPos pos = result.getBlockPos();
        BlockState state = arrow.level().getBlockState(pos);

        ItemStack stack = owner instanceof LivingEntity ? ((LivingEntity) owner).getMainHandItem() : ItemStack.EMPTY;

        ModBowItem bow = stack.getItem() instanceof ModBowItem ? (ModBowItem) stack.getItem() : null;

        switch (getArrowType()) {
            case BAMBOO -> {
                if (bow != null && bow.getBowType() == BowType.BAMBOO) {
                    bambooArrowHitsBlock(level, pos, state);
                }
            }
            case BLAZE_ROD -> {
                igniteBlockOnHit(result, level, pos, state);
            }
            case COPPER -> {

                if (level.random.nextFloat() < 0.01f && level.isRainingAt(pos)) {
                    LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(level);
                    lightningBolt.moveTo(Vec3.atBottomCenterOf(pos.above()));
                    lightningBolt.setCause(null);
                    level.addFreshEntity(lightningBolt);
                }

                if (bow != null && bow.getBowType() == BowType.COPPER) {
                    LightningBolt lightningBolt = (LightningBolt) EntityType.LIGHTNING_BOLT.create(level);
                    lightningBolt.moveTo(Vec3.atBottomCenterOf(pos.above()));
                    lightningBolt.setCause(null);
                    level.addFreshEntity(lightningBolt);
                }
            }
            case ENDER_PEARL -> {
                BlockPos relativePosition = pos.relative(result.getDirection());
                if (owner != null && !arrow.level().isClientSide()) owner.teleportTo((ServerLevel) arrow.level(), relativePosition.getX(), relativePosition.getY(), relativePosition.getZ(), RelativeMovement.ROTATION, owner.getYRot(), owner.getXRot());
            }
            case FLINT_AND_STEEL -> igniteBlockOnHit(result, level, pos, state);
            case FLINT -> {
                if (bow != null && bow.getBowType() == BowType.IRON) {
                    igniteBlockOnHit(result, level, pos, state);
                }
            }
            case MOSS -> {
                if (bow != null && (bow.getBowType() == BowType.MOSS || bow.getBowType() == BowType.PAPER)) {
                    paperArrowHitsBlock(owner, level, pos);
                }
            }
            case PAPER -> {
                if (bow != null && (bow.getBowType() == BowType.PAPER || bow.getBowType() == BowType.MOSS)) {
                    paperArrowHitsBlock(owner, level, pos);
                }
            }
            case TNT -> {
                arrow.discard();
                level.explode(owner, result.getBlockPos().getX(), result.getBlockPos().getY() + 1, result.getBlockPos().getZ(), 2.0f, true, Level.ExplosionInteraction.TNT);
            }
        }
    }

    default void processEntityInteraction(ModArrow arrow, EntityHitResult result) {

        Entity owner = arrow.getOwner();
        Level level = arrow.level();

        if (!(result.getEntity() instanceof LivingEntity hitEntity)) {
            return;
        }

        ItemStack stack = owner instanceof LivingEntity ? ((LivingEntity) owner).getMainHandItem() : ItemStack.EMPTY;

        ModBowItem bow = stack.getItem() instanceof ModBowItem ? (ModBowItem) stack.getItem() : null;

        switch (getArrowType()) {
            case BLAZE_ROD -> {
                hitEntity.setSecondsOnFire(2);
            }
            case BONE -> hitEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 1));
            case COPPER -> {

                if (level.random.nextFloat() < 0.01f && level.isRainingAt(hitEntity.blockPosition().above())) {
                    LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(level);
                    lightningBolt.moveTo(Vec3.atBottomCenterOf(hitEntity.blockPosition().above()));
                    lightningBolt.setCause(null);
                    level.addFreshEntity(lightningBolt);
                }

                if (bow != null && bow.getBowType() == BowType.COPPER) {
                    LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(level);
                    lightningBolt.moveTo(Vec3.atBottomCenterOf(hitEntity.blockPosition().above()));
                    lightningBolt.setCause(null);
                    level.addFreshEntity(lightningBolt);
                }
            }
            case ENDER_PEARL -> {
                if (owner != null && !arrow.level().isClientSide()) hitEntity.teleportTo((ServerLevel) owner.level(), owner.xo, owner.yo, owner.zo, RelativeMovement.ROTATION, hitEntity.getYRot(), hitEntity.getXRot());
            }
            case FLINT_AND_STEEL -> hitEntity.setSecondsOnFire(2);
            case FLINT -> {
                if (bow != null && bow.getBowType() == BowType.IRON) hitEntity.setSecondsOnFire(2);
            }
            case MOSS -> {
                if (bow != null && (bow.getBowType() == BowType.MOSS || bow.getBowType() == BowType.PAPER)) paperArrowHitsEntity(owner, level, hitEntity);
            }
            case PAPER -> {
                if (bow != null && (bow.getBowType() == BowType.PAPER || bow.getBowType() == BowType.MOSS)) paperArrowHitsEntity(owner, level, hitEntity);
            }
            case TNT -> {
                level.explode(null, hitEntity.xo, hitEntity.yo + 1, hitEntity.zo, 2.0f, true, Level.ExplosionInteraction.TNT);
                arrow.discard();
            }
        }
    }

    private static void igniteBlockOnHit(BlockHitResult result, Level level, BlockPos pos, BlockState state) {
        if (!state.hasProperty(BlockStateProperties.LIT)) {
            BlockPos relativePosition = pos.relative(result.getDirection());
            if (level.isEmptyBlock(relativePosition)) {
                level.setBlockAndUpdate(relativePosition, BaseFireBlock.getState(level, relativePosition));
            }
        }
        else {
            level.setBlock(pos, state.setValue(BlockStateProperties.LIT, true), Block.UPDATE_ALL);
        }
    }

    private static void bambooArrowHitsBlock(Level level, BlockPos pos, BlockState state) {
        if (Blocks.BAMBOO.defaultBlockState().canSurvive(level, pos)) {
            if (isDirt(state)) {
                level.setBlock(pos, Blocks.PODZOL.defaultBlockState(), Block.UPDATE_ALL);
                level.setBlock(pos.above(), Blocks.BAMBOO.defaultBlockState(), Block.UPDATE_ALL);
            } else if (state.is(Blocks.BAMBOO)) {
                level.setBlock(pos.above(), Blocks.BAMBOO.defaultBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    private static void paperArrowHitsBlock(Entity owner, Level level, BlockPos pos) {

        Random random = new Random();

        switch (random.nextInt(1, 9)) {
            case 1 -> level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 2.0f, true, Level.ExplosionInteraction.TNT);
            case 2 -> {
                if (owner != null) owner.teleportTo(owner.xo, owner.yo + 10, owner.zo);
            }
            case 3 -> {
                if (owner != null) owner.teleportTo(pos.getX(), pos.getY(), pos.getZ());
            }
            case 4 -> {
                if (owner != null) owner.teleportTo(pos.getX(), pos.getY() + 10, pos.getZ());
            }
            case 5 -> {
                LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(level);
                lightningBolt.moveTo(Vec3.atBottomCenterOf(pos.above()));
                lightningBolt.setCause(null);
                level.addFreshEntity(lightningBolt);
            }
            case 6 -> level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.0f, true, Level.ExplosionInteraction.TNT);
            case 7 -> {
                for (int i=0; i<4; ++i) {
                    Chicken chicken = (Chicken)EntityType.CHICKEN.create(level);
                    chicken.moveTo(pos.getX(), pos.getY()+i, pos.getZ());
                    level.addFreshEntity(chicken);
                }
            }
            case 8 -> {
                for (int i=0; i<4; ++i) {
                    Zombie zombie = (Zombie)EntityType.ZOMBIE.create(level);
                    zombie.moveTo(pos.getX(), pos.getY(), pos.getZ());
                    level.addFreshEntity(zombie);
                }
            }
        }
    }

    private static void paperArrowHitsEntity(Entity owner, Level level, Entity entity) {

        Random random = new Random();

        switch (random.nextInt(1, 9)) {
            case 1 -> entity.setSecondsOnFire(2);
            case 2 -> {
                if (owner != null) entity.teleportTo(owner.xo, owner.yo, owner.zo);
            }
            case 3 -> {
                if (owner != null) owner.teleportTo(entity.xo, entity.yo, entity.zo);
            }
            case 4 -> {
                if (owner != null) entity.teleportTo(entity.xo, entity.yo + 10, entity.zo);
            }
            case 5 -> {
                LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(level);
                lightningBolt.moveTo(Vec3.atBottomCenterOf(entity.blockPosition().above()));
                lightningBolt.setCause(null);
                level.addFreshEntity(lightningBolt);
            }
            case 6 -> level.explode(null, entity.xo, entity.yo, entity.zo, 2.0f, true, Level.ExplosionInteraction.TNT);
            case 7 -> {
                for (int i=0; i<4; ++i) {
                    Chicken chicken = (Chicken)EntityType.CHICKEN.create(level);
                    chicken.moveTo(entity.xo, entity.yo+i, entity.zo);
                    level.addFreshEntity(chicken);
                }
            }
            case 8 -> {
                for (int i=0; i<4; ++i) {
                    Zombie zombie = (Zombie)EntityType.ZOMBIE.create(level);
                    zombie.moveTo(entity.xo, entity.yo, entity.zo);
                    level.addFreshEntity(zombie);
                }
            }
        }
    }
}
