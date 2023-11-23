package com.moyashi.yasu.event;

import com.moyashi.yasu.config.MoneyLoad;
import com.moyashi.yasu.init.IroiroModItems;
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
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;
import static com.moyashi.yasu.main.Reference.*;
import static net.minecraft.world.damagesource.DamageTypes.FALL;

public class MuscleLeg {

    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        // イベントがプレイヤーに関連しているか確認
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(MUSCLEFLAG == true) {

                // 落下ダメージを受けた場合
                if (event.getDistance() > 3.0f) {
                    // invulnerable を true に設定
                    player.getAbilities().invulnerable = true;
                } else {
                    if(!player.isCreative()) {
                        // 落下ダメージを受けていない場合は invulnerable を false に設定
                        player.getAbilities().invulnerable = false;
                    }
                }
            }else{
                if(!player.isCreative()) {
                    player.getAbilities().invulnerable = false;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        if(event.getEntity().level().isClientSide){
            if(event.getEntity() instanceof Player) {
                Vec3 motion = event.getEntity().getDeltaMovement();
                if(MUSCLEFLAG == true) {

                    event.getEntity().setDeltaMovement(motion.x, 1.0, motion.z); // バウンス処理
                }

            }
        }
        // this handler will now happen after all other non-LOWEST priority handlers
    }
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        if (player != null && player.getMainHandItem().getItem() == IroiroModItems.MUSCLELEG.get()) { // 例: 右クリックでEnderbodyEntityをキル
            MUSCLEFLAG = true;

            player.getMainHandItem().shrink(1);
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(MuscleLeg.class);
    }

}
