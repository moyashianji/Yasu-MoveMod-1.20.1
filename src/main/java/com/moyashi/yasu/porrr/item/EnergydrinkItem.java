
package com.moyashi.yasu.porrr.item;

import com.moyashi.yasu.porrr.procedures.EnergydrinkYoukuritukusitatokiProcedure;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;


public class EnergydrinkItem extends Item {
	public EnergydrinkItem() {
			super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));

	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		EnergydrinkYoukuritukusitatokiProcedure.execute(world, x, y, z);
		return ar;
	}
}
