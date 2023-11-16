package com.moyashi.yasu.event;

import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;
import static com.moyashi.yasu.main.Reference.JUMPFLAG;

import com.moyashi.yasu.config.MoneyLoad;
public class NomalJump {



    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if(event.getEntity().level().isClientSide) {
            if (event.getEntity() instanceof LocalPlayer) {
                LocalPlayer player = (LocalPlayer) event.getEntity();


            }
        }
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
         if(event.getEntity().level().isClientSide){
             if(event.getEntity() instanceof Player) {
                 Vec3 motion = event.getEntity().getDeltaMovement();
                 if(JUMPFLAG == false) {

                     event.getEntity().setDeltaMovement(motion.x, 0, motion.z);
                 }

             }
         }
        // this handler will now happen after all other non-LOWEST priority handlers
    }
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        if (player != null && player.getMainHandItem().getItem() == IroiroModItems.AVERAGEJUMP.get()) { // 例: 右クリックでEnderbodyEntityをキル
            JUMPFLAG = true;

            player.getMainHandItem().shrink(1);
        }
    }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(NomalJump.class);
    }

}
