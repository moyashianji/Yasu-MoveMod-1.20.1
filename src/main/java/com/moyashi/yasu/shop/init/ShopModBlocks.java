
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.shop.init;

import com.moyashi.yasu.event.NomalJump;
import com.moyashi.yasu.main.Reference;
import com.moyashi.yasu.shop.block.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;


public class ShopModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	public static final RegistryObject<Block> HEIKINJUMP = REGISTRY.register("heikinjump", () -> new HeikinjumpBlock());
	public static final RegistryObject<Block> HUTUUHOKOUBLOCK = REGISTRY.register("hutuuhokoublock", () -> new HutuuhokoublockBlock());
	public static final RegistryObject<Block> SYUNSOKUBLOCK = REGISTRY.register("syunsokublock", () -> new SyunsokublockBlock());
	public static final RegistryObject<Block> MUKIMUKINOASI = REGISTRY.register("mukimukinoasi", () -> new MukimukinoasiBlock());
	public static final RegistryObject<Block> SNEAKBLOCK = REGISTRY.register("sneakblock", () -> new SneakblockBlock());
	public static final RegistryObject<Block> ENERGYDRINK = REGISTRY.register("energydrink", () -> new EnergydrinkBlock());
	public static final RegistryObject<Block> BOOSTBLOCK = REGISTRY.register("boostblock", () -> new BoostblockBlock());
	public static final RegistryObject<Block> NETHERBLOCK = REGISTRY.register("netherblock", () -> new NetherblockBlock());
	public static final RegistryObject<Block> OMEGABLOCK = REGISTRY.register("omegablock", () -> new OmegablockBlock());
	public static final RegistryObject<Block> GRAPBLOCK = REGISTRY.register("grapblock", () -> new GrapblockBlock());
	public static final RegistryObject<Block> SONICBLOCK = REGISTRY.register("sonicblock", () -> new SonicblockBlock());
	public static final RegistryObject<Block> ROCKETBLOCK = REGISTRY.register("rocketblock", () -> new RocketblockBlock());
	public static final RegistryObject<Block> QUANTUMBLOCK = REGISTRY.register("quantumblock", () -> new QuantumblockBlock());
	public static final RegistryObject<Block> MPHBLOCK = REGISTRY.register("mphblock", () -> new MphblockBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			HeikinjumpBlock.blockColorLoad(event);
			HutuuhokoublockBlock.blockColorLoad(event);
			BoostblockBlock.blockColorLoad(event);
			NetherblockBlock.blockColorLoad(event);
			OmegablockBlock.blockColorLoad(event);
			GrapblockBlock.blockColorLoad(event);
			SonicblockBlock.blockColorLoad(event);
			RocketblockBlock.blockColorLoad(event);
			QuantumblockBlock.blockColorLoad(event);
			MphblockBlock.blockColorLoad(event);
		}
		public static void register() {
			MinecraftForge.EVENT_BUS.register(ClientSideHandler.class);
		}
	}
}
