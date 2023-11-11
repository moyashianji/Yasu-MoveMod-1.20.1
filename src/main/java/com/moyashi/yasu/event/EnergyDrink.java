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
import static com.moyashi.yasu.main.Reference.ENERGYFLAG;
import static com.moyashi.yasu.main.Reference.WALKFLAG;

public class EnergyDrink {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {


        if (event.phase == TickEvent.Phase.START) {
            if (event.player.level().isClientSide) {
                LocalPlayer player = (LocalPlayer) event.player;
                ItemStack mainHandItem = event.player.getMainHandItem();

            }
                Player player = event.player;
                if (ENERGYFLAG == true) {

                    event.player.level().setBlock(player.blockPosition().below(), Blocks.VOID_AIR.defaultBlockState(), 2);

                    if (player.isSprinting()) {

                        System.out.println("EnergyDrinkFaster: ");
                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.5);

                    } else {

                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);

                        player.onUpdateAbilities();
                    }
                }else{


                }
            }


    }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(EnergyDrink.class);
    }
}
