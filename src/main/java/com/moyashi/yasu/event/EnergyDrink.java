package com.moyashi.yasu.event;

import com.moyashi.yasu.config.MoneyLoad;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;

public class EnergyDrink {
    public static boolean flag = false;
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {


        if (event.phase == TickEvent.Phase.START) {
            if (event.player.level().isClientSide) {
                LocalPlayer player = (LocalPlayer) event.player;
                ItemStack mainHandItem = event.player.getMainHandItem();
                if (!mainHandItem.isEmpty() && mainHandItem.getItem() == Items.DIRT) {

                    if (player.onGround() && (player.input.left || player.input.right || player.input.up || player.input.down)) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            }
                Player player = event.player;
                if (flag == true) {

                    event.player.level().setBlock(player.blockPosition().below(), Blocks.VOID_AIR.defaultBlockState(), 2);

                    if (player.isSprinting()) {

                        System.out.println("EnergyDrinkFaster: ");
                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.5);

                    } else {

                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);

                        player.onUpdateAbilities();
                    }
                }
            }


    }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(EnergyDrink.class);
    }
}
