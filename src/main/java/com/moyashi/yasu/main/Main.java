package com.moyashi.yasu.main;

import com.moyashi.yasu.PerBlock.ChangePerMoney;
import com.moyashi.yasu.PerBlock.PerSystem;
import com.moyashi.yasu.bigportion.init.BigModMobEffects;
import com.moyashi.yasu.bigportion.init.BigModPotions;
import com.moyashi.yasu.event.*;
import com.moyashi.yasu.hud.TextHUD;
import com.moyashi.yasu.init.IroiroModBlocks;
import com.moyashi.yasu.init.IroiroModItems;
import com.moyashi.yasu.init.IroiroModTabs;
import com.moyashi.yasu.item.QuantumItem;
import com.moyashi.yasu.jumping.init.TrampModBlocks;
import com.moyashi.yasu.jumping.init.TrampModItems;
import com.moyashi.yasu.keybind.FlyupMessage;
import com.moyashi.yasu.keybind.KeyBind;
import com.moyashi.yasu.keybind.init.KeybindModKeyMappings;
import com.moyashi.yasu.particc.init.ParticcModParticleTypes;
import com.moyashi.yasu.shop.ShopSystem;
import com.moyashi.yasu.shop.init.ShopModBlocks;
import com.moyashi.yasu.shop.init.ShopModItems;
import com.moyashi.yasu.shop.init.ShopModTabs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
        NetherTrav.register();
        KeyNether.register();
        TrampModItems.REGISTRY.register(bus);
        TrampModBlocks.REGISTRY.register(bus);
        JumpBoost.register();
        mph.register();
        SonicDash.register();
        ShopModBlocks.REGISTRY.register(bus);
        ShopModItems.REGISTRY.register(bus);
        ShopModTabs.REGISTRY.register(bus);
        ShopSystem.register();
        ParticcModParticleTypes.REGISTRY.register(bus);
        ShopModBlocks.ClientSideHandler.register();
        PerSystem.register();
        ChangePerMoney.register();
        QuantumItem.register();
        KeybindModKeyMappings.register();
        KeyBind.register();

        bus.addListener(this::onClientSetup);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event){

        generateConfigFile();
    }
    private void onClientSetup(FMLClientSetupEvent event) {


    }
}
