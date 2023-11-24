
package com.moyashi.yasu.bigportion.potion;

import com.moyashi.yasu.bigportion.procedures.BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure;
import com.moyashi.yasu.bigportion.procedures.EnergyOffProcedure;
import com.moyashi.yasu.bigportion.procedures.EnergyOnProcedure;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import com.moyashi.yasu.main.Reference;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EnergyDrinkEffectMobEffect extends MobEffect {
	public EnergyDrinkEffectMobEffect() {
		super(MobEffectCategory.NEUTRAL, -1);
	}

	@Override
	public String getDescriptionId() {
		return "effect.big.energy_drink_effect";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {

	}
	@Override
	public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier, double health) {


	}
	private static void checkAndReplaceBlock(Player player) {
		for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
			ItemStack stackInSlot = player.getInventory().getItem(slot);

			// プレイヤーのインベントリのスロットが土ブロックであるかどうかを確認
			if (!stackInSlot.isEmpty() && stackInSlot.getItem() == Items.GLASS_BOTTLE) {
				// スロットが見つかったら、石ブロックに置き換える

				ItemStack healingPotion = new ItemStack(Items.POTION);
				healingPotion.getOrCreateTag().putString("Potion", "yasu:energy_drink");
				player.getInventory().setItem(slot,healingPotion);

				// ポーションの効果を設定
				break; // 一つだけ置き換えるため、処理を終了
			}
		}
	}
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);

	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
