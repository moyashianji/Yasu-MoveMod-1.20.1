package com.moyashi.yasu.keybind;

import com.moyashi.yasu.item.QuantumItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import static com.moyashi.yasu.item.QuantumItem.tryTakeOff;
import static com.moyashi.yasu.keybind.init.KeybindModKeyMappings.FLYUP;

public class KeyBind {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;

        // don't react to key presses if a screen or chat is open
        if (mc.screen != null) return;

        if (event.getKey() == FLYUP.getKey().getValue()
                && (event.getAction() == GLFW.GLFW_PRESS)) {
            // && (event.getAction() == GLFW.GLFW_PRESS || event.getAction() == GLFW.GLFW_REPEAT)) {
            tryTakeOff(player);
        }

    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(KeyBind.class);
    }

}
