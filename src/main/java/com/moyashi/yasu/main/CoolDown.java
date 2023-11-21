package com.moyashi.yasu.main;

import com.moyashi.yasu.keybind.init.KeybindModKeyMappings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CoolDown {

    public static int cooldown = 0; // 何かしらのカウンター変数

    private static int lastX;
    private static int lastY;
    private static int lastZ;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player == null) {
            // イベントが終了フェーズでないか、プレイヤーが存在しない場合は処理しない
            return;
        }

        int currentX = (int) event.player.getX();
        int currentY = (int) event.player.getY();
        int currentZ = (int) event.player.getZ();

        if (currentX == lastX && currentY == lastY && currentZ == lastZ) {
            // 直前の座標と同じ場所にいる場合
            cooldown++;
        } else {
            // 座標が変化した場合
            cooldown = 0; // リセット
        }

        lastX = currentX;
        lastY = currentY;
        lastZ = currentZ;

        if (cooldown >= 10) {
            // 20tick以上同じ座標にいた場合の処理
            // 何かしらのアクションをここに記述
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(CoolDown.class);
    }

}
