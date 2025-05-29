public class Particles {



    ///  spawns particles on a flat square in front of the player...
//    private void nocturnalBowParticles(Level level, LivingEntity entity) {
//
//        // Get yaw and pitch in radians
//        float yaw = entity.getYRot();
//        float pitch = entity.getXRot();
//        float yawRad = (float) Math.toRadians(-yaw);
//        float pitchRad = (float) Math.toRadians(-pitch);
//
//        // Target point in front of the entity (e.g., 1 block ahead)
//        double targetDistance = 1.0;
//        double tx = entity.getX() + Mth.cos(pitchRad) * Mth.sin(yawRad) * targetDistance;
//        double ty = entity.getY() + entity.getEyeHeight() + Mth.sin(pitchRad) * targetDistance;
//        double tz = entity.getZ() + Mth.cos(pitchRad) * Mth.cos(yawRad) * targetDistance;
//
//        // We'll spawn particles in a small flat area (like a fan or wall) in front of the entity
//        int particleCount = 30;
//        double spreadRadius = 0.5; // How wide/tall the spread is
//        double spawnDistance = 1.5; // Distance in front of entity to spawn particles
//        double particleSpeed = 0.2;
//
//        for (int i = 0; i < particleCount; i++) {
//            // Calculate forward base position
//            double forwardX = entity.getX() + Mth.cos(pitchRad) * Mth.sin(yawRad) * spawnDistance;
//            double forwardY = entity.getY() + entity.getEyeHeight() + Mth.sin(pitchRad) * spawnDistance;
//            double forwardZ = entity.getZ() + Mth.cos(pitchRad) * Mth.cos(yawRad) * spawnDistance;
//
//            // Offset in a random direction perpendicular to the look vector (spread)
//            double offsetX = (level.random.nextDouble() - 0.5) * spreadRadius * 2;
//            double offsetY = (level.random.nextDouble() - 0.5) * spreadRadius * 2;
//
//            // Use yaw to get sideways and up vectors (approximate cross product for spread plane)
//            Vec3 look = entity.getLookAngle().normalize();
//            Vec3 up = new Vec3(0, 1, 0);
//            Vec3 right = look.cross(up).normalize();
//            Vec3 upAdjusted = right.cross(look).normalize(); // ensures perpendicular spread
//
//            // Final spawn position in spread plane
//            Vec3 spawnPos = new Vec3(forwardX, forwardY, forwardZ)
//                    .add(right.scale(offsetX))
//                    .add(upAdjusted.scale(offsetY));
//
//            // Direction toward the convergence point
//            double dx = tx - spawnPos.x;
//            double dy = ty - spawnPos.y;
//            double dz = tz - spawnPos.z;
//            double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
//
//            if (length == 0) continue;
//            dx = (dx / length) * particleSpeed;
//            dy = (dy / length) * particleSpeed;
//            dz = (dz / length) * particleSpeed;
//
//            // Spawn particle
//            level.addAlwaysVisibleParticle(
//                    ParticleTypes.HAPPY_VILLAGER, // or any desired particle
//                    spawnPos.x, spawnPos.y, spawnPos.z,
//                    dx, dy, dz
//            );
//        }
//    }

    /// spawns particles all around the entity, with random movement vectors. can be cool?
//    private void nocturnalBowParticles(Level level, LivingEntity entity) {
//
//        // Get entity's look direction
//        float yaw = entity.getYRot();
//        float pitch = entity.getXRot();
//        float yawRad = (float) Math.toRadians(-yaw);
//        float pitchRad = (float) Math.toRadians(-pitch);
//
//        // Compute the target point in front of the entity
//        double targetDist = 2.0;
//        double tx = entity.getX() + Mth.cos(pitchRad) * Mth.sin(yawRad) * targetDist;
//        double ty = entity.getY() + entity.getEyeHeight() + Mth.sin(pitchRad) * targetDist;
//        double tz = entity.getZ() + Mth.cos(pitchRad) * Mth.cos(yawRad) * targetDist;
//
//        // Spawn multiple particles from a small area around the entity
//        int particleCount = 5;
//        double spawnRadius = 1.0;
//        double particleSpeed = 0.1;
//
//        for (int i = 0; i < particleCount; i++) {
//            // Random position around the entity
//            double px = entity.getX() + (level.random.nextDouble() - 0.5) * spawnRadius * 2;
//            double py = entity.getY() + entity.getEyeHeight() + (level.random.nextDouble() - 0.5) * spawnRadius;
//            double pz = entity.getZ() + (level.random.nextDouble() - 0.5) * spawnRadius * 2;
//
//            // Direction vector from spawn to target
//            double dx = tx - px;
//            double dy = ty - py;
//            double dz = tz - pz;
//
//            // Normalize direction vector
//            double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
//            if (length == 0) continue; // Avoid divide by zero
//
//            dx /= length;
//            dy /= length;
//            dz /= length;
//
//            // Scale by speed
//            dx *= particleSpeed;
//            dy *= particleSpeed;
//            dz *= particleSpeed;
//
//            // Spawn the particle
//            level.addAlwaysVisibleParticle(
//                    ParticleTypes.GLOW, // Or any other ParticleOptions
//                    px, py, pz,
//                    dx, dy, dz
//            );
//        }
//    }

    // dolphin, electric spark, glow, scrape, reverse portal, shriek, sonic boom, wax off, witch

    /// working as intended, spawns particles in front of player.
    /// just playing with other particles now. don't edit anything below OR ELSE
//    private void nocturnalBowParticles(Level level, LivingEntity entity) {
//
//        // Get player's yaw and pitch in radians
//        float yaw = entity.getYRot();
//        float pitch = entity.getXRot();
//
//        float yawRad = (float) Math.toRadians(-yaw); // Yaw is clockwise in Minecraft // todo maybe invert these lol
//        float pitchRad = (float) Math.toRadians(-pitch); // Pitch is downward-positive, so invert // todo maybe invert these lol
//
//        // Calculate direction vector from yaw and pitch
//        double dx = Mth.cos(pitchRad) * Mth.sin(yawRad);
//        double dy = Mth.sin(pitchRad);
//        double dz = Mth.cos(pitchRad) * Mth.cos(yawRad);
//
//        // Set particle position a short distance in front of the player's eyes
//        double offset = 1.0;
//        double px = entity.getX() + dx * offset;
//        double py = entity.getY() + entity.getEyeHeight() + dy * offset;
//        double pz = entity.getZ() + dz * offset;
//
//        // Set particle velocity in the same direction
//        double speed = 0.05;
//        double vx = dx * speed;
//        double vy = dy * speed;
//        double vz = dz * speed;
//
//        level.addAlwaysVisibleParticle(
//                ParticleTypes.DOLPHIN, // Replace with your desired ParticleOptions
//                px, py, pz,
//                vx, vy, vz
//        );
//    }
}