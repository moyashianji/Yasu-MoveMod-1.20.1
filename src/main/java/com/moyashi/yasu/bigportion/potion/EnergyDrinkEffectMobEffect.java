
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
		BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure.execute();
		Reference.ENERGYFLAG = true;
	}
	@Override
	public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier, double health) {
		EnergyOnProcedure.execute();


		System.out.println("addeneryg");

	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		EnergyOffProcedure.execute();
		Reference.ENERGYFLAG = false;
		System.out.println("removeener");
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
