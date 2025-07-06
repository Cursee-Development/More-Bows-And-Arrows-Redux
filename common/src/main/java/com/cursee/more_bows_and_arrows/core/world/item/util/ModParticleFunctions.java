package com.cursee.more_bows_and_arrows.core.world.item.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ModParticleFunctions {

    public static void nocturnalBowParticles(Level level, LivingEntity entity) {

        if (level.getGameTime() % 2 != 0) return;

        Vec3 look = entity.getLookAngle().normalize();
        Vec3 eyePos = entity.position().add(0, entity.getEyeHeight(), 0);
        Vec3 target = eyePos.add(look.scale(1.5)); // Convergence point
        int particleCount = 5;
        double sphereRadius = 1.0;
        double particleSpeed = 0.3;

        for (int i = 0; i < particleCount; i++) {
            // Get a random point on a sphere in front of the player
            double theta = level.random.nextDouble() * 2 * Math.PI; // azimuth
            double phi = Math.acos(2 * level.random.nextDouble() - 1); // polar

            // Convert Spherical coordinate to Cartesian
            double x = sphereRadius * Math.sin(phi) * Math.cos(theta);
            double y = sphereRadius * Math.sin(phi) * Math.sin(theta);
            double z = sphereRadius * Math.cos(phi);
            Vec3 offset = new Vec3(x, y, z);

            Vec3 spawnPos = target.add(offset); // Spawn around the convergence point
            Vec3 motion = target.subtract(spawnPos).normalize().scale(particleSpeed);

            level.addAlwaysVisibleParticle(
                    ParticleTypes.ELECTRIC_SPARK,
                    spawnPos.x, spawnPos.y, spawnPos.z,
                    motion.x, motion.y, motion.z
            );
        }
    }

//    private static void nocturnalBowParticles(Level level, LivingEntity entity) {
//
//        // spawn particles every other tick
//        if (level.getGameTime() % 2 != 0) return;
//
//        // The player's faced direction
//        Vec3 look = entity.getLookAngle().normalize();
//
//        // Center of the sphere where particles converge
//        Vec3 target = entity.position()
//                .add(0, entity.getEyeHeight(), 0)
//                .add(look.scale(1.5));
//
//        int particleCount = 5;
//        double sphereRadius = 1.0;
//        double particleSpeed = 0.15;
//
//        for (int i = 0; i < particleCount; i++) {
//            // Generate random point on sphere surface
//            double theta = level.random.nextDouble() * 2 * Math.PI;     // azimuth
//            double phi = Math.acos(2 * level.random.nextDouble() - 1);  // polar
//
//            // Convert spherical to Cartesian coordinates
//            double x = sphereRadius * Math.sin(phi) * Math.cos(theta);
//            double y = sphereRadius * Math.sin(phi) * Math.sin(theta);
//            double z = sphereRadius * Math.cos(phi);
//
//            // Rotate this point to be in front of the entity
//            Vec3 offset = new Vec3(x, y, z);
//            Vec3 sphereCenter = entity.position()
//                    .add(0, entity.getEyeHeight(), 0)
//                    .add(look.scale(1.5));
//
//            Vec3 spawnPos = sphereCenter.add(offset);
//
//            // Motion vector: point toward the target
//            Vec3 motion = target.subtract(spawnPos).normalize().scale(particleSpeed).multiply(2, 2, 2);
//
//            // Spawn the particle
//            level.addAlwaysVisibleParticle(
//                    ParticleTypes.ELECTRIC_SPARK, // or another particle type
//                    spawnPos.x, spawnPos.y, spawnPos.z,
//                    motion.x, motion.y, motion.z
//            );
//        }
//    }
}
