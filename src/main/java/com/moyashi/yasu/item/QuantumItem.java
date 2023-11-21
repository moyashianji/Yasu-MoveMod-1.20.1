
package com.moyashi.yasu.item;

import com.mojang.blaze3d.platform.InputConstants;
import com.moyashi.yasu.client.model.Modeltest;
import com.moyashi.yasu.event.NomalWalk;
import com.moyashi.yasu.init.IroiroModItems;
import com.moyashi.yasu.main.Reference;
import com.moyashi.yasu.particc.init.ParticcModParticleTypes;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;


import java.util.function.Consumer;
import java.util.Map;
import java.util.Collections;

import static com.moyashi.yasu.keybind.init.KeybindModKeyMappings.FLYUP;

public abstract class QuantumItem extends ArmorItem {
	public QuantumItem(Type type, Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 25;
			}

			@Override
			public int getDefenseForType(Type type) {
				return new int[]{2, 5, 6, 2}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 9;
			}

			@Override
			public SoundEvent getEquipSound() {
				return SoundEvents.EMPTY;
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of();
			}

			@Override
			public String getName() {
				return "quantum";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, type, properties);
	}

	public static class Chestplate extends QuantumItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties());
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", new Modeltest(Minecraft.getInstance().getEntityModels().bakeLayer(Modeltest.LAYER_LOCATION)).Body,
							"left_arm", new Modeltest(Minecraft.getInstance().getEntityModels().bakeLayer(Modeltest.LAYER_LOCATION)).bone, "right_arm",
							new Modeltest(Minecraft.getInstance().getEntityModels().bakeLayer(Modeltest.LAYER_LOCATION)).bone, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					armorModel.crouching = living.isShiftKeyDown();
					armorModel.riding = defaultModel.riding;
					armorModel.young = living.isBaby();
					return armorModel;
				}
			});
		}


		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "yasu:textures/models/armor/quantum_layer_1.png";
		}
	}

	@Override
	public boolean canElytraFly(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
		return true;
	}

	@Override
	public boolean elytraFlightTick(ItemStack stack, net.minecraft.world.entity.LivingEntity entity, int flightTicks) {
		entity.level().addParticle(ParticcModParticleTypes.ORISONIC.get(), entity.getX(), entity.getY()+0.3, entity.getZ(), 0, 1, 0);

		if (!entity.level().isClientSide) {

			int nextFlightTick = flightTicks + 1;
			if (nextFlightTick % 10 == 0) {
				if (nextFlightTick % 20 == 0) {
					stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.CHEST));
				}
				entity.gameEvent(GameEvent.ELYTRA_GLIDE);
			}
		}
		return true;
	}
	// key event handling

	private static final Vec3 NORMAL_UP = new Vec3(0.0D, 1.0D, 0.0D);

	public static void tryTakeOff(LocalPlayer player) {

		ItemStack helmetStack = player.getItemBySlot(EquipmentSlot.CHEST);

		if (helmetStack.getItem() == IroiroModItems.QUANTUM_CHESTPLATE.get()
				&& player.isFallFlying()) {

				System.out.println("flyuiipp");
				Vec3 motion = player.getDeltaMovement();
				player.setDeltaMovement(motion.x, 1.0, motion.z); // バウンス処理
			}

	}

	public static void register() {
		MinecraftForge.EVENT_BUS.register(QuantumItem.class);
	}

}
