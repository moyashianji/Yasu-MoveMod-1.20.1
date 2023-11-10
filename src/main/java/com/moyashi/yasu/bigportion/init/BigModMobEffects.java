
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.bigportion.init;

import com.moyashi.yasu.bigportion.potion.BigMobEffect;
import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;



public class BigModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Reference.MOD_ID);
	public static final RegistryObject<MobEffect> BIG = REGISTRY.register("big", () -> new BigMobEffect());
}
