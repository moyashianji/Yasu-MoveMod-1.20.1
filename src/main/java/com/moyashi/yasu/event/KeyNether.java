package com.moyashi.yasu.event;

import com.moyashi.yasu.config.MoneySave;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import static com.moyashi.yasu.item.RocketItem.TARGET_DIMENSION_KEY;
import static com.moyashi.yasu.main.Reference.*;

public class KeyNether {
    public static boolean isInNether = false;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if(event.player.level().dimension() == TARGET_DIMENSION_KEY) {
                NewX = event.player.getX();
                NewY = event.player.getY();
                NewZ = event.player.getZ();
                MoneySave.onSave();
            }
            if(event.player.level().dimension() == Level.OVERWORLD) {
                OveX = event.player.getX();
                OveY = event.player.getY();
                OveZ = event.player.getZ();
                MoneySave.onSave();

            }
            if(event.player.level().dimension() == Level.NETHER){
                isInNether = true;
                NetX = event.player.getX();
                NetY = event.player.getY();
                NetZ = event.player.getZ();
                MoneySave.onSave();


            }else{
                isInNether = false;
            }
                if (isKeyPressed(GLFW.GLFW_KEY_X)) {
                    if(!(event.player.level().isClientSide)) {
                        toggleDimension((ServerPlayer) event.player);
                    }
                }
            }
        }

    private static boolean isKeyPressed(int keyCode) {
        return GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), keyCode) == GLFW.GLFW_PRESS;
    }

    private static void toggleDimension(ServerPlayer player) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null && mc.player != null) {
            if (isInNether) {
                // 元の世界に戻る

                player.teleportTo(player.getServer().getLevel(Level.OVERWORLD), OveX, OveY, OveZ, player.getYRot(), player.getXRot());

                isInNether = false;
            } else {
                // ネザーにテレポート
                player.teleportTo(player.getServer().getLevel(Level.NETHER), NetX, NetY, NetZ, player.getYRot(), player.getXRot());
                isInNether = true;
            }
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(KeyNether.class);
    }
}
