
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.porrr.init;

import com.moyashi.yasu.main.Reference;
import com.moyashi.yasu.porrr.item.EnergydrinkItem;
import com.moyashi.yasu.porrr.item.OmegapotionItem;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

public class PorrrModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final RegistryObject<Item> OMEGAPOTION = REGISTRY.register("omegapotion", () -> new OmegapotionItem());
	public static final RegistryObject<Item> ENERGYDRINK = REGISTRY.register("energydrinkk", () -> new EnergydrinkItem());
}
