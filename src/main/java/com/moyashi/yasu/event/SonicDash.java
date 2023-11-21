package com.moyashi.yasu.event;

import com.moyashi.yasu.init.IroiroModItems;
import com.moyashi.yasu.particc.init.ParticcModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.moyashi.yasu.item.SonicdashItem;
public class SonicDash {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer) {
            if(!event.player.level().isClientSide) {
                if(event.player.getMainHandItem().getItem() == IroiroModItems.SONICDASH.get()) {
                    spawnParticleSphere((Entity) event.player, (ServerLevel) event.player.level());
                    //event.player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(2.0);

                }else{
                    event.player.setInvisible(false);
                }
            }
        }
    }
    public static void spawnParticleSphere(Entity entity, ServerLevel level) {
        if (entity instanceof Player) {

            double radius = 2.0; // 球体の半径
            // プレイヤーの座標
            double playerX = entity.getX();
            double playerY = entity.getY();
            double playerZ = entity.getZ();
            if(SonicdashItem.breaks && entity.onGround() && (((Player) entity).getMainHandItem().getItem() == IroiroModItems.SONICDASH.get())){
            // 球体上の各点にパーティクルを出現させる
            for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 26) {
                for (double phi = 0; phi <= Math.PI; phi += Math.PI / 26) {
                    double x = playerX + radius * Math.sin(phi) * Math.cos(theta);
                    double y = playerY + radius * Math.cos(phi);
                    double z = playerZ + radius * Math.sin(phi) * Math.sin(theta);
                    // パーティクルの出現
                    level.sendParticles(ParticcModParticleTypes.ORISONIC.get(), x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
                }

            }

                // プレイヤーの周囲2ブロックの範囲を走査
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            double targetX = playerX + x;
                            double targetY = playerY + y;
                            double targetZ = playerZ + z;

                            // プレイヤーより低いY座標のブロックはスキップ
                            if (targetY < playerY) {
                                continue;
                            }

                            // ブロックを破壊
                            BlockPos targetPos = new BlockPos((int) targetX, (int) targetY, (int) targetZ);
                            Block targetBlock = entity.level().getBlockState(targetPos).getBlock();
                            entity.level().destroyBlock(new BlockHitResult(new Vec3(targetX, targetY, targetZ), entity.getDirection(), targetPos, false).getBlockPos(), true);

                        }
                    }
                }

            }
        }
    }

    public static final int RADIUS = 5; // 半径2マスの円内にいるモブにダメージを与える
    private static final int LEVITATIONRADIUS = 3;
    @SubscribeEvent
    public static void onPlayerrTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player != null) {
            Player player = event.player;
            Level world = player.level();

            // サーバーサイドのみで実行されるコード
            if (!world.isClientSide) {
                if (event.player.getMainHandItem().getItem() == IroiroModItems.SONICDASH.get()) {

                    double playerX = player.getX();
                    double playerY = player.getY();
                    double playerZ = player.getZ();

                    // 半径2マス以内にいるモブにダメージを与える
                    for (Entity entity : world.getEntities(player, player.getBoundingBox().inflate(RADIUS))) {
                        if (entity instanceof LivingEntity && entity != player && !(entity instanceof Player)) {

                            // プレイヤー自身やモンスター以外のエンティティに対してのみ適用
                            LivingEntity livingEntity = (LivingEntity) entity;
                            livingEntity.setHealth(0f);
                        }
                    }

                }
            }
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(SonicDash.class);
    }

}
