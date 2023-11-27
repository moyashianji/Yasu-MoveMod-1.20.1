
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.grappling.init;


import com.moyashi.yasu.grappling.item.GrapplingHookItem;
import com.moyashi.yasu.grappling.item.GrapplingItem;
import com.moyashi.yasu.main.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class GrapplingModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final RegistryObject<Item> GRAPPLING = REGISTRY.register("grappling", () -> new GrapplingItem());
	public static final RegistryObject<Item> GRAPPLING_HOOK = REGISTRY.register("grappling_hook", () -> new GrapplingHookItem());
}
