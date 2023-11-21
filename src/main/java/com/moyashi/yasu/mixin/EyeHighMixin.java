package com.moyashi.yasu.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.moyashi.yasu.main.Reference.EYEHEIGH;

@Mixin(Entity.class)
public class EyeHighMixin {

    @Inject(method = "getEyeHeight()F",  at = @At(value = "HEAD"),cancellable = true)
    private void getSss(CallbackInfoReturnable<Float> cir){
            cir.setReturnValue(EYEHEIGH);

    }
}
