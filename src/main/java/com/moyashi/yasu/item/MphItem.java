
package com.moyashi.yasu.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class MphItem extends Item {
	public MphItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
