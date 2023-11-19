
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.init;

import com.moyashi.yasu.client.model.Modelnether;
import com.moyashi.yasu.client.model.Modelsyunsoku;
import com.moyashi.yasu.client.model.Modeltest;
import com.moyashi.yasu.item.QuantumItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class IroiroModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modeltest.LAYER_LOCATION, Modeltest::createBodyLayer);
		event.registerLayerDefinition(Modelsyunsoku.LAYER_LOCATION, Modelsyunsoku::createBodyLayer);
		event.registerLayerDefinition(Modelnether.LAYER_LOCATION, Modelnether::createBodyLayer);
	}
	public static void register() {
		MinecraftForge.EVENT_BUS.register(IroiroModModels.class);
	}

}
