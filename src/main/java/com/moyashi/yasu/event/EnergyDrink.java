package com.moyashi.yasu.event;

import com.moyashi.yasu.config.MoneyLoad;
import com.moyashi.yasu.init.IroiroModItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.config.MoneySave.onSave;
import static com.moyashi.yasu.main.Reference.*;

public class EnergyDrink {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {


        if (event.phase == TickEvent.Phase.START) {
            if (event.player.level().isClientSide) {
                LocalPlayer player = (LocalPlayer) event.player;
                ItemStack mainHandItem = event.player.getMainHandItem();

            }
            Player player = event.player;

            if (!player.level().isClientSide) {
                player.getAbilities().invulnerable = (false);
                player.onUpdateAbilities();
                if (ENERGYFLAG == true) {


                    if (player.isSprinting()) {
                        event.player.level().setBlock(player.blockPosition().below(), Blocks.VOID_AIR.defaultBlockState(), 2);

                        System.out.println("EnergyDrinkFaster: ");
                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(2.0);

                    } else {

                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);

                        player.onUpdateAbilities();
                    }
                }else{
                    if(WALKFLAG == true) {

                        if (SNEAKPUSHFLAG == false) {
                            ItemStack helmetStack = player.getItemBySlot(EquipmentSlot.FEET);

                            if (player.getMainHandItem().getItem() == IroiroModItems.MPH.get()) {
                                System.out.println("MPHHHHH");
                                System.out.println(player.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(2.0);
                            } else if (player.getMainHandItem().getItem() == IroiroModItems.SONICDASH.get()) {
                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.2);
                            } else if (helmetStack.getItem() == IroiroModItems.SYUNSOKU_BOOTS.get()) {
                                System.out.println("しゅんそく");

                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.0);
                            } else {
                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
                            }
                        }else{

                        }
                    }

                    }
                }
            }


    }


    public static void register() {
        MinecraftForge.EVENT_BUS.register(EnergyDrink.class);
    }
}
