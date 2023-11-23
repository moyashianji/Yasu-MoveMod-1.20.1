
package com.moyashi.yasu.bigportion.potion;

import com.moyashi.yasu.bigportion.init.BigModPotions;
import com.moyashi.yasu.bigportion.procedures.BigposiyonXiaoGuogaQieretaShiProcedure;
import com.moyashi.yasu.bigportion.procedures.BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure;
import com.moyashi.yasu.main.Reference;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.main.Reference.BIGFLAG;
import static com.moyashi.yasu.main.Reference.EYEHEIGH;


public class BigMobEffect extends MobEffect {
	public BigMobEffect() {
		super(MobEffectCategory.NEUTRAL, -1);
	}

	@Override
	public String getDescriptionId() {
		return "effect.big.big";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure.execute();
		Reference.size = 3.0F;
		EYEHEIGH = 5.0f;
		checkAndReplaceBlock((Player) entity);
		BIGFLAG = true;

	}

	@Override
	public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier, double health) {
		BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure.execute();
		Reference.size = 3.0F;
		EYEHEIGH = 5.0f;
		checkAndReplaceBlock((Player) entity);
		BIGFLAG = true;

	}
	private static void checkAndReplaceBlock(Player player) {
		for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
			ItemStack stackInSlot = player.getInventory().getItem(slot);

			// プレイヤーのインベントリのスロットが土ブロックであるかどうかを確認
			if (!stackInSlot.isEmpty() && stackInSlot.getItem() == Items.GLASS_BOTTLE) {
				// スロットが見つかったら、石ブロックに置き換える

				ItemStack healingPotion = new ItemStack(Items.POTION);
				healingPotion.getOrCreateTag().putString("Potion", "yasu:bigportion");
				player.getInventory().setItem(slot,healingPotion);

				// ポーションの効果を設定
				break; // 一つだけ置き換えるため、処理を終了
			}
		}
	}
	private void applyPotionEffects(Player player, ItemStack stack) {
		// ここでポーションの効果をプレイヤーに与えるロジックを実装
		// 例: 赤いポーションを与える
		PotionUtils.setPotion(stack, Potions.HEALING);
		player.heal(4.0f); // 任意の回復量を指定

		// ポーションを飲み終わったら元のポーションに戻す
		PotionUtils.setPotion(stack, PotionUtils.getPotion(stack));
	}
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		BigposiyonXiaoGuogaQieretaShiProcedure.execute();
		Reference.size = 0.9375F;
		EYEHEIGH = 2.0f;
		BIGFLAG = false;
		checkAndReplaceBlock((Player) entity);

	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
