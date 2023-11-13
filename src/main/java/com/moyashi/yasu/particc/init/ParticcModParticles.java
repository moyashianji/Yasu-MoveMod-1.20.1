
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.particc.init;

import com.moyashi.yasu.particc.client.particle.OrisonicParticle;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticcModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ParticcModParticleTypes.ORISONIC.get(), OrisonicParticle::provider);
	}
}
