package com.moyashi.yasu.main;

import com.moyashi.yasu.bigportion.init.BigModMobEffects;
import com.moyashi.yasu.bigportion.init.BigModPotions;
import com.moyashi.yasu.event.*;
import com.moyashi.yasu.hud.TextHUD;
import com.moyashi.yasu.init.IroiroModBlocks;
import com.moyashi.yasu.init.IroiroModItems;
import com.moyashi.yasu.init.IroiroModTabs;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.targets.FMLServerDevLaunchHandler;

import static com.moyashi.yasu.config.Config.generateConfigFile;

@Mod(Reference.MOD_ID)

public class Main {
    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        generateConfigFile();
        NomalJump.register();
        NomalWalk.register();
        TextHUD.register();
        FaltRunning.register();
        MuscleLeg.register();
        Sneaking.register();
        EnergyDrink.register();
        BigModMobEffects.REGISTRY.register(bus);
        BigModPotions.REGISTRY.register(bus);

        IroiroModBlocks.REGISTRY.register(bus);

        IroiroModItems.REGISTRY.register(bus);

        IroiroModTabs.REGISTRY.register(bus);


        bus.addListener(this::onClientSetup);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event){

        System.out.println("[NEKORUN]サーバーが起動しました");
        generateConfigFile();
    }
    private void onClientSetup(FMLClientSetupEvent event) {


    }
}
