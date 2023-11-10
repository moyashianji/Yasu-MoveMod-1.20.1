package com.moyashi.yasu.event;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Sneaking {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if(event.player.level().isClientSide){
                LocalPlayer player = (LocalPlayer) event.player;

                if (player.input.shiftKeyDown && player.onGround()) {
                    // プレイヤーが歩いている間ずっと
                    // プレイヤーが歩いている場合
                    System.out.println("Sneaking: ");
                    player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.0);


                }
            }
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(Sneaking.class);
    }
}
