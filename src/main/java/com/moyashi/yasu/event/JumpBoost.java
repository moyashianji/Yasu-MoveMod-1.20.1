package com.moyashi.yasu.event;

import com.moyashi.yasu.jumping.init.TrampModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class JumpBoost {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        // プレイヤーの現在位置を取得
        BlockPos playerPos = player.blockPosition();

        // プレイヤーの一マス下の位置を計算
        BlockPos blockBelowPos = playerPos.below((int) 0.2);
        BlockState blockBelowPlayerState = player.level().getBlockState(blockBelowPos);

        // 一マス下にゾンビがいるかどうかを確認

        if (blockBelowPlayerState.getBlock() == TrampModBlocks.JUMPBOOST.get()) {

                Vec3 motion = player.getDeltaMovement();
                player.setDeltaMovement(motion.x, 2.0, motion.z); // バウンス処理
            }
        }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(JumpBoost.class);
    }

}
