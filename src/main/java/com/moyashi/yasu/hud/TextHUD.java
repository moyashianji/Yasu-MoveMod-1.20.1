package com.moyashi.yasu.hud;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneyLoad.Money;


public class TextHUD {
    @SubscribeEvent
    public static void eventHandler(RenderGuiEvent.Pre event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        int posX = w / 2;
        int posY = h / 2;
        Level _world = null;
        double _x = 0;
        double _y = 0;
        double _z = 0;
        Player entity = Minecraft.getInstance().player;
        if (entity != null) {
            _world = entity.level();
            _x = entity.getX();
            _y = entity.getY();
            _z = entity.getZ();
        }
        Level world = _world;
        double x = _x;
        double y = _y;
        double z = _z;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (true) {

            event.getGuiGraphics().blit( new ResourceLocation("yasu:textures/screens/ttt.png"),   posX + -209, posY + 75, 0, 0, 112, 32, 112, 32);
            event.getGuiGraphics().drawString(Minecraft.getInstance().font, ""+Money,    posX + -182, posY + 86, -1);

        }
        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);

    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(TextHUD.class);
    }
}
