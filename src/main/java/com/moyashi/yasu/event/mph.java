package com.moyashi.yasu.event;

import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class mph {

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(player.getMainHandItem().getItem() == IroiroModItems.MPH.get()) {
                if (player.isInWater()) {
                    System.out.println("water");
                    Vec3 motion = player.getDeltaMovement();
                    player.lerpMotion(motion.x, 0.1D, motion.z); // 上方向に浮上する速度を設定
                }
                if(player.isSprinting()) {
                    player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.0);
                }
            }

        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(mph.class);
    }
}
