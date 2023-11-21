
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
		replaceBottleWithPotion((Player) entity);


	}

	@Override
	public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier, double health) {
		BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure.execute();
		Reference.size = 3.0F;
		EYEHEIGH = 5.0f;
		replaceBottleWithPotion((Player) entity);

	}
	public static void replaceBottleWithPotion(Player player) {
		// インベントリを取得
		Container inventory = player.getInventory();
		// メインハンドのアイテムを取得
		ItemStack mainHandItem = player.getMainHandItem();

		// メインハンドのアイテムがnullでなく、土以外のアイテムかどうかを確認
		if (mainHandItem != null) {
			System.out.println("potiooooooo");
			// メインハンドのアイテムを土に置き換える
			if(mainHandItem.getItem() == Items.GLASS_BOTTLE) {
				ItemStack healingPotion = new ItemStack(Items.POTION);
				healingPotion.getOrCreateTag().putString("Potion", "yasu:bigportion");

				// ポーションの効果を設定

				player.setItemInHand(player.getUsedItemHand(), healingPotion);
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


	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
