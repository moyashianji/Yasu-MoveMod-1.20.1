
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.particc.init;

import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;


public class ParticcModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Reference.MOD_ID);
	public static final RegistryObject<SimpleParticleType> ORISONIC = REGISTRY.register("orisonic", () -> new SimpleParticleType(false));
}
