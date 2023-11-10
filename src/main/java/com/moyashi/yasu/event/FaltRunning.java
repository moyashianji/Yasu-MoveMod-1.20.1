package com.moyashi.yasu.event;

import com.google.common.collect.Multimap;
import com.moyashi.yasu.config.MoneyLoad;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;

public class FaltRunning {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player instanceof Player) {
            if (!event.player.level().isClientSide) {
                ServerPlayer player = (ServerPlayer) event.player;
                ItemStack feetItem = player.getInventory().armor.get(3);

                // 足に"fast"アイテムを装備しているかどうかを確認
                // 装備している場合は速さを変更
                if (player.isSprinting()) {
                    MoneyLoad.Money += 10;
                    onSave();
                    System.out.println("Fast count: " + MoneyLoad.Money);
                    player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.5);

                } else {

                    player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);

                    player.onUpdateAbilities();
                }

                // 装備していない場合は速さを元に戻す
            }
        }
    }



    public static void register() {
        MinecraftForge.EVENT_BUS.register(FaltRunning.class);
    }

}
