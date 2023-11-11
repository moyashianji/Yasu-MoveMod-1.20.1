
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.moyashi.yasu.jumping.init;

import com.moyashi.yasu.jumping.block.JumpboostBlock;
import com.moyashi.yasu.main.Reference;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;


public class TrampModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	public static final RegistryObject<Block> JUMPBOOST = REGISTRY.register("jumpboost", () -> new JumpboostBlock());
}
