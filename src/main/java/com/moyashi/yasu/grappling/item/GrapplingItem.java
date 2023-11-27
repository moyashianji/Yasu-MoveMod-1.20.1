
package com.moyashi.yasu.grappling.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class GrapplingItem extends Item {
	public GrapplingItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

}
