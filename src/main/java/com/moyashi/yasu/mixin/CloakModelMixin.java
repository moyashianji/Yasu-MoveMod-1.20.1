package com.moyashi.yasu.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.moyashi.yasu.main.Reference.*;

//import xyz.heroesunited.heroesunited.util.HUClientUtil;

@Mixin(PlayerRenderer.class)
public abstract class CloakModelMixin {


    @Inject(method = "scale", at = @At(value = "HEAD"))
    protected void rescale(AbstractClientPlayer p_117798_, PoseStack p_117799_, float p_117800_, CallbackInfo ci) {
        if (!(p_117798_ instanceof Player)) return;
        if(RESIZE == true) {
            float f = 0.9375F;
            p_117799_.scale(2.0F, 2.0F, 2.0F);
        }else{
            p_117799_.scale(1.0F, 1.0F, 1.0F);

        }
    }

}