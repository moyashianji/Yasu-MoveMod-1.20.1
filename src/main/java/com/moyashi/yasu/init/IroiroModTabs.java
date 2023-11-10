
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.init;

import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;



@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IroiroModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {

		if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(IroiroModItems.NETHERTRAVEL_HELMET.get());
			tabData.accept(IroiroModItems.SYUNSOKU_BOOTS.get());
			tabData.accept(IroiroModItems.QUANTUM_CHESTPLATE.get());
		}

		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(IroiroModItems.ENDEERDIM.get());
		}
	}
}
