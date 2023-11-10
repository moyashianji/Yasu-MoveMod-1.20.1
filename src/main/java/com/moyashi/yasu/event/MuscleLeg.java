package com.moyashi.yasu.event;

import com.moyashi.yasu.config.MoneyLoad;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;
import static net.minecraft.world.damagesource.DamageTypes.FALL;

public class MuscleLeg {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();


            // プレイヤーがダメージを受けた場合
            if (event.getSource().is(FALL)) { // マグマからのダメージの場合（適切なダメージソースを指定）
                event.setCanceled(true); // ダメージを無効にする
                System.out.println("MuscleLeg");
            }else{
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if(event.player.level().isClientSide){
                LocalPlayer player = (LocalPlayer) event.player;

                if (player.input.jumping && player.onGround()) {
                    // プレイヤーが歩いている間ずっと
                    // プレイヤーが歩いている場合
                    player.setJumping(false);
                    System.out.println("Jumping: ");
                    Vec3 motion = player.getDeltaMovement();
                    player.setDeltaMovement(motion.x, 1.0, motion.z); // バウンス処理
                }
            }
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(MuscleLeg.class);
    }

}