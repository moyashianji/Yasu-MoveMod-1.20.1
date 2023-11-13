
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.shop.init;

import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;



@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShopModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {

		if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(ShopModBlocks.HEIKINJUMP.get().asItem());
			tabData.accept(ShopModBlocks.HUTUUHOKOUBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.SYUNSOKUBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.MUKIMUKINOASI.get().asItem());
			tabData.accept(ShopModBlocks.SNEAKBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.ENERGYDRINK.get().asItem());
			tabData.accept(ShopModBlocks.BOOSTBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.NETHERBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.OMEGABLOCK.get().asItem());
			tabData.accept(ShopModBlocks.GRAPBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.SONICBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.ROCKETBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.QUANTUMBLOCK.get().asItem());
			tabData.accept(ShopModBlocks.MPHBLOCK.get().asItem());
		}
	}

}
