package com.moyashi.yasu.grappling;


import com.moyashi.yasu.grappling.entity.GrapplingHookEntity;
import com.moyashi.yasu.grappling.init.GrapplingModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class grappleProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        {
            Entity _shootFrom = entity;
            Level projectileLevel = _shootFrom.level();
            if (!projectileLevel.isClientSide()) {
                Projectile _entityToSpawn = new Object() {
                    public Projectile getArrow(Level level, float damage, int knockback) {
                        AbstractArrow entityToSpawn = new GrapplingHookEntity(GrapplingModEntities.GRAPPLING_HOOK.get(), level);
                        entityToSpawn.setBaseDamage(damage);
                        entityToSpawn.setKnockback(knockback);
                        entityToSpawn.setCritArrow(true);
                        entityToSpawn.pickup = AbstractArrow.Pickup.ALLOWED;


                        return entityToSpawn;
                    }
                }.getArrow(projectileLevel, 5, 1);
                _entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
                _entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 3, 0);
                projectileLevel.addFreshEntity(_entityToSpawn);
            }
        }
    }

}
