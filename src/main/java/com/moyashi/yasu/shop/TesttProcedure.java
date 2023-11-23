package com.moyashi.yasu.shop;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.ItemHandlerHelper;

public class TesttProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
        if (world instanceof Level _level && !_level.isClientSide()) {
            ItemEntity entityToSpawn = new ItemEntity(_level, x, y+3.0, z, (ItemArgument.getItem(arguments, "item").getItem().getDefaultInstance()));
            entityToSpawn.setPickUpDelay(100000);
            entityToSpawn.setUnlimitedLifetime();
            _level.addFreshEntity(entityToSpawn);

        }


    }

}
