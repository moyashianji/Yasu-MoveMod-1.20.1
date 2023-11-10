
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.init;

import com.moyashi.yasu.item.*;
import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;



public class IroiroModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final RegistryObject<Item> NETHERTRAVEL_HELMET = REGISTRY.register("nethertravel_helmet", () -> new NethertravelItem.Helmet());
	public static final RegistryObject<Item> SYUNSOKU_BOOTS = REGISTRY.register("syunsoku_boots", () -> new SyunsokuItem.Boots());
	public static final RegistryObject<Item> QUANTUM_CHESTPLATE = REGISTRY.register("quantum_chestplate", () -> new QuantumItem.Chestplate());
	public static final RegistryObject<Item> AVERAGEJUMP = REGISTRY.register("averagejump", () -> new AveragejumpItem());
	public static final RegistryObject<Item> HUTUUHOKOU = REGISTRY.register("hutuuhokou", () -> new HutuuhokouItem());
	public static final RegistryObject<Item> MUSCLELEG = REGISTRY.register("muscleleg", () -> new MusclelegItem());
	public static final RegistryObject<Item> SNEAKSPEED = REGISTRY.register("sneakspeed", () -> new SneakspeedItem());
	public static final RegistryObject<Item> ENDEERDIM = REGISTRY.register("endeerdim", () -> new EndeerdimItem());
}
