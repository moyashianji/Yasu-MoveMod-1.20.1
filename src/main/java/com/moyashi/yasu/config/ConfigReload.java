package com.moyashi.yasu.config;

import com.moyashi.yasu.config.speed.SpeedLoad;
import com.moyashi.yasu.event.FaltRunning;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ConfigReload {


    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("speed")

                .executes(arguments -> {
                    ServerLevel world = arguments.getSource().getLevel();

                    double x = arguments.getSource().getPosition().x();
                    double y = arguments.getSource().getPosition().y();
                    double z = arguments.getSource().getPosition().z();

                    Entity entity = arguments.getSource().getEntity();
                    if (entity == null)
                        entity = FakePlayerFactory.getMinecraft(world);

                    Direction direction = entity.getDirection();


                    SpeedLoad.SPextractDoubleFromTextFile();
                    return 0;
                }));
    }
    public static void register() {
        MinecraftForge.EVENT_BUS.register(ConfigReload.class);
    }

}
