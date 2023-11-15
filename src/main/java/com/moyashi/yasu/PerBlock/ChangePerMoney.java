package com.moyashi.yasu.PerBlock;

import com.moyashi.yasu.event.NetherTrav;
import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.main.Reference.MONEYPERBLOCK;

public class ChangePerMoney {


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        // プレイヤーがダイヤモンドのヘルメットをかぶっているかどうかをチェック

        ItemStack helmetStack = event.player.getItemBySlot(EquipmentSlot.HEAD);

        // ヘルメットが存在し、かつそのアイテムがダイヤモンドのヘルメットであればtrueを返す
        if(!helmetStack.isEmpty() && helmetStack.getItem() == IroiroModItems.NETHERTRAVEL_HELMET.get() && !(event.player.getMainHandItem().getItem() == IroiroModItems.MPH.get())){
            MONEYPERBLOCK = 16;
        }else if(event.player.getMainHandItem().getItem() == IroiroModItems.MPH.get() && !helmetStack.isEmpty() && !(helmetStack.getItem() == IroiroModItems.NETHERTRAVEL_HELMET.get())){
            MONEYPERBLOCK = 10000;

        }else if(event.player.getMainHandItem().getItem() == IroiroModItems.MPH.get() && !helmetStack.isEmpty() && helmetStack.getItem() == IroiroModItems.NETHERTRAVEL_HELMET.get()){
            MONEYPERBLOCK = 160000;

        }else{
            MONEYPERBLOCK = 1;
        }

    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(ChangePerMoney.class);
    }
}
