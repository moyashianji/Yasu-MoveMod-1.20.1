package com.moyashi.yasu.event;

import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.item.RocketItem.TARGET_DIMENSION_KEY;

public class mph {

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(player.level().dimension() == TARGET_DIMENSION_KEY) {

                if (!player.onGround() && !player.isFallFlying()) {
                    // プレイヤーがジャンプ中ならy座標をゆっくりと下げる
                    player.setDeltaMovement(player.getDeltaMovement().x, player.getDeltaMovement().y + 0.05, player.getDeltaMovement().z);
                }
            }
            System.out.println(player.getMainHandItem().getItem().toString());
            if(player.getMainHandItem().getItem() == IroiroModItems.MPH.get()) {
                if (player.isInWater()) {
                    System.out.println("water");
                    Vec3 motion = player.getDeltaMovement();
                    player.lerpMotion(motion.x, 0.1D, motion.z); // 上方向に浮上する速度を設定

                }
                if(player.isInWater() && player.isSprinting()){
                    double lookX = -Math.sin(Math.toRadians(player.getYRot()));
                    double lookZ = Math.cos(Math.toRadians(player.getYRot()));

                    // プレイヤーの速度を設定
                    player.setDeltaMovement(lookX * 2, player.getDeltaMovement().y, lookZ * 2);
                }

            }

        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(mph.class);
    }
}
