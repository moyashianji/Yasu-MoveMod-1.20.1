package com.moyashi.yasu.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SonicDash {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer) {
            if(!event.player.level().isClientSide) {
                spawnParticleSphere((Entity) event.player, (ServerLevel) event.player.level());
            }
        }
    }
    public static void spawnParticleSphere(Entity entity, ServerLevel level) {
        if (entity instanceof Player) {

            double radius = 3.0; // 球体の半径
            System.out.println("particle");
            // プレイヤーの座標
            double playerX = entity.getX();
            double playerY = entity.getY();
            double playerZ = entity.getZ();

            // 球体上の各点にパーティクルを出現させる
            for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 8) {
                for (double phi = 0; phi <= Math.PI; phi += Math.PI / 8) {
                    double x = playerX + radius * Math.sin(phi) * Math.cos(theta);
                    double y = playerY + radius * Math.cos(phi);
                    double z = playerZ + radius * Math.sin(phi) * Math.sin(theta);
                    System.out.println(x);
                    // パーティクルの出現
                    level.sendParticles(ParticleTypes.SONIC_BOOM, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(SonicDash.class);
    }

}
