
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.shop.init;

import com.moyashi.yasu.item.SonicdashItem;
import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;


public class ShopModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final RegistryObject<Item> HEIKINJUMP = block(ShopModBlocks.HEIKINJUMP);
	public static final RegistryObject<Item> HUTUUHOKOUBLOCK = block(ShopModBlocks.HUTUUHOKOUBLOCK);
	public static final RegistryObject<Item> SYUNSOKUBLOCK = block(ShopModBlocks.SYUNSOKUBLOCK);
	public static final RegistryObject<Item> MUKIMUKINOASI = block(ShopModBlocks.MUKIMUKINOASI);
	public static final RegistryObject<Item> SNEAKBLOCK = block(ShopModBlocks.SNEAKBLOCK);
	public static final RegistryObject<Item> ENERGYDRINK = block(ShopModBlocks.ENERGYDRINK);
	public static final RegistryObject<Item> BOOSTBLOCK = block(ShopModBlocks.BOOSTBLOCK);
	public static final RegistryObject<Item> NETHERBLOCK = block(ShopModBlocks.NETHERBLOCK);
	public static final RegistryObject<Item> OMEGABLOCK = block(ShopModBlocks.OMEGABLOCK);
	public static final RegistryObject<Item> GRAPBLOCK = block(ShopModBlocks.GRAPBLOCK);
	public static final RegistryObject<Item> SONICBLOCK = block(ShopModBlocks.SONICBLOCK);
	public static final RegistryObject<Item> ROCKETBLOCK = block(ShopModBlocks.ROCKETBLOCK);
	public static final RegistryObject<Item> QUANTUMBLOCK = block(ShopModBlocks.QUANTUMBLOCK);
	public static final RegistryObject<Item> MPHBLOCK = block(ShopModBlocks.MPHBLOCK);

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
