package com.moyashi.yasu.PerBlock;

import com.moyashi.yasu.config.MoneyLoad;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;
import static com.moyashi.yasu.main.Reference.MONEYPERBLOCK;

public class PerSystem {

    private static int a = 0;
    private static Vec3 lastPosition;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        Player player = event.player;
        float currentMovementSpeed = (float) player.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
        if(event.player.level().isClientSide){
            LocalPlayer playe = (LocalPlayer) event.player;

            if (playe.onGround() && (playe.input.left || player.isSwimming() || playe.input.right || playe.input.up || playe.input.down) && !(player.isSprinting())) {
                    System.out.println(currentMovementSpeed);
                    // 前回の移動速度と比較して速度が変わるたびに処理
                    // 1/MovementSpeed ごとに a に 1 を加算
                if(currentMovementSpeed > 0.0) {
                    int ticksPerUpdate = Math.round(1.0f / (currentMovementSpeed * 2));
                    if (ticksPerUpdate > 0) {
                        if (event.player.tickCount % ticksPerUpdate == 0) {
                            MoneyLoad.Money+=MONEYPERBLOCK;
                            onSave();

                        }
                    } else {
                        MoneyLoad.Money+=MONEYPERBLOCK;
                        onSave();
                    }
                }
            }

            if (playe.onGround() && (playe.input.left || player.isSwimming() || playe.input.right || playe.input.up || playe.input.down) && player.isSprinting()) {

                    // 前回の移動速度と比較して速度が変わるたびに処理
                // 1/MovementSpeed ごとに a に 1 を加算
                System.out.println(currentMovementSpeed);

                if(currentMovementSpeed > 0.0) {

                    int ticksPerUpdate = (int) Math.round(1.0f / (currentMovementSpeed * 2 * 1.3));
                System.out.println(ticksPerUpdate);
                    System.out.println(ticksPerUpdate);
                    if (ticksPerUpdate > 0) {
                        if (event.player.tickCount % ticksPerUpdate == 0) {
                            MoneyLoad.Money+=MONEYPERBLOCK;
                            onSave();

                        }
                    }
                    if(ticksPerUpdate == 0){
                        MoneyLoad.Money+=MONEYPERBLOCK;
                        onSave();
                    }
                }
            }
        }

        // 現在の移動速度を保存
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(PerSystem.class);
    }
}
