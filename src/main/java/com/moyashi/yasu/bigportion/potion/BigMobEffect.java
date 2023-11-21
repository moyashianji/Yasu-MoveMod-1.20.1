
package com.moyashi.yasu.bigportion.potion;

import com.moyashi.yasu.bigportion.procedures.BigposiyonXiaoGuogaQieretaShiProcedure;
import com.moyashi.yasu.bigportion.procedures.BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure;
import com.moyashi.yasu.main.Reference;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;



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
	}

	@Override
	public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier, double health) {
		BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure.execute();
		Reference.size = 3.0F;
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		BigposiyonXiaoGuogaQieretaShiProcedure.execute();
		Reference.size = 0.9375F;

	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
