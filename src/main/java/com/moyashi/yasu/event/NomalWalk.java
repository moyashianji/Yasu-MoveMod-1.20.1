package com.moyashi.yasu.event;

import com.moyashi.yasu.config.MoneyLoad;
import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;
import static com.moyashi.yasu.main.Reference.JUMPFLAG;
import static com.moyashi.yasu.main.Reference.WALKFLAG;

public class NomalWalk {

    private static boolean isWalking = false;


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if(event.player.level().isClientSide){
                LocalPlayer player = (LocalPlayer) event.player;

                if (player.onGround() && (player.input.left || player.isSwimming() || player.input.right || player.input.up || player.input.down) && !(player.isSprinting())) {
                    if(WALKFLAG == false) {
                        System.out.println("Walk count: ");
                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
                    }else{
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        if (player != null && player.getMainHandItem().getItem() == IroiroModItems.HUTUUHOKOU.get()) { // 例: 右クリックでEnderbodyEntityをキル
            WALKFLAG = true;

            player.getMainHandItem().shrink(1);
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(NomalWalk.class);
    }

}
