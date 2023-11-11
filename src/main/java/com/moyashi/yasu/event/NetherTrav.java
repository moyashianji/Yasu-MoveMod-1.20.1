package com.moyashi.yasu.event;

import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class NetherTrav {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player != null) {
            Player player = event.player;
            // プレイヤーの座標を取得

            ItemStack helmetStack = player.getItemBySlot(EquipmentSlot.HEAD);

            // サーバーサイドのみで実行されるコード
            if (!player.level().isClientSide) {

                if(helmetStack.getItem() == IroiroModItems.NETHERTRAVEL_HELMET.get()){
                    // プレイヤーの座標を取得
                    BlockPos playerPos = player.blockPosition();

                    // プレイヤーの一マス下の座標を計算
                    BlockPos blockBelowPlayerPos = playerPos.below();

                    // 一マス下のブロックの状態を取得
                    BlockState blockBelowPlayerState = player.level().getBlockState(blockBelowPlayerPos);
                    System.out.println("lava");
                    // プレイヤーの一マス下のブロックが溶岩の場合
                    if (blockBelowPlayerState.getBlock() == Blocks.LAVA) {
                        System.out.println("lava");

                        // 黒曜石に置き換える処理
                        player.level().setBlockAndUpdate(blockBelowPlayerPos, Blocks.OBSIDIAN.defaultBlockState());
                    }
                }
            }
        }

    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(NetherTrav.class);
    }
}
