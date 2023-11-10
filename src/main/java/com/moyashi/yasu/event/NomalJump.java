package com.moyashi.yasu.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;
import com.moyashi.yasu.config.MoneyLoad;
public class NomalJump {

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player) {
            // プレイヤーがジャンプした場合
            MoneyLoad.Money += 1;
            onSave();
            System.out.println("Jump count: " + MoneyLoad.Money);
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(NomalJump.class);
    }

}
