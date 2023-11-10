package com.moyashi.yasu.event;

import com.moyashi.yasu.config.MoneyLoad;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;

public class NomalWalk {

    private static boolean isWalking = false;


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if(event.player.level().isClientSide){
                LocalPlayer player = (LocalPlayer) event.player;

                if (player.onGround() && (player.input.left || player.isSwimming() || player.input.right || player.input.up || player.input.down) && !(player.isSprinting())) {
                    // プレイヤーが歩いている間ずっと
                    // プレイヤーが歩いている場合
                    MoneyLoad.Money += 5;
                    onSave();
                    System.out.println("Walk count: " + MoneyLoad.Money);

                }
            }
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(NomalWalk.class);
    }

}
