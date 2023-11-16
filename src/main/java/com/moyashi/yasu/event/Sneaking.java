package com.moyashi.yasu.event;

import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.main.Reference.*;

public class Sneaking {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if(event.player.level().isClientSide){
                LocalPlayer player = (LocalPlayer) event.player;

                if (player.input.shiftKeyDown && player.onGround()) {
                    if(SNEAKFLAG == true) {
                        // プレイヤーが歩いている間ずっと
                        // プレイヤーが歩いている場合
                        SNEAKPUSHFLAG = true;
                        System.out.println("Sneaking");
                        if(SNEAKPUSHFLAG == true) {
                            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.0);
                        }
                    }

                }else{
                    SNEAKPUSHFLAG = false;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();

        if (player != null && player.getMainHandItem().getItem() == IroiroModItems.SNEAKSPEED.get()) { // 例: 右クリックでEnderbodyEntityをキル
            SNEAKFLAG = true;

            player.getMainHandItem().shrink(1);
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(Sneaking.class);
    }
}
