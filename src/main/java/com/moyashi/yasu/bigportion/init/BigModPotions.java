
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.bigportion.init;

import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;


public class BigModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, Reference.MOD_ID);
	public static final RegistryObject<Potion> BIGPORTION = REGISTRY.register("bigportion", () -> new Potion(new MobEffectInstance(BigModMobEffects.BIG.get(), 3600, 0, false, true)));
}