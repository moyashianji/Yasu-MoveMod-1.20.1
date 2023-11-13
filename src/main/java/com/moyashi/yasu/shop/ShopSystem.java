package com.moyashi.yasu.shop;

import com.moyashi.yasu.config.MoneyLoad;
import com.moyashi.yasu.event.Sneaking;
import com.moyashi.yasu.init.IroiroModItems;
import com.moyashi.yasu.shop.init.ShopModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import static com.moyashi.yasu.config.MoneySave.onSave;

public class ShopSystem {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if(!event.getEntity().level().isClientSide) {
            if (event.getLevel().getBlockState(event.getPos()).getBlock() == ShopModBlocks.HEIKINJUMP.get()) {

            }
        }
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(ShopSystem.class);
    }

}
