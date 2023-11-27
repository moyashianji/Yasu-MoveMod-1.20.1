
package com.moyashi.yasu.grappling.entity;


import com.google.common.collect.Sets;
import com.moyashi.yasu.grappling.init.GrapplingModEntities;
import com.moyashi.yasu.grappling.init.GrapplingModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PlayMessages;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.moyashi.yasu.main.Reference.GRAPPFLAG;


@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class GrapplingHookEntity extends AbstractArrow implements ItemSupplier {
	private static final int EXPOSED_POTION_DECAY_TIME = 600;
	private static final int NO_EFFECT_COLOR = -1;
	private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(Arrow.class, EntityDataSerializers.INT);
	private static final byte EVENT_POTION_PUFF = 0;
	private Potion potion = Potions.EMPTY;
	private final Set<MobEffectInstance> effects = Sets.newHashSet();
	private boolean fixedColor;


	public GrapplingHookEntity(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {
		super(GrapplingModEntities.GRAPPLING_HOOK.get(), p_36862_, p_36863_, p_36864_, p_36861_);
	}

	public GrapplingHookEntity(Level p_36866_, LivingEntity p_36867_) {
		super(GrapplingModEntities.GRAPPLING_HOOK.get(), p_36867_, p_36866_);
	}
	public GrapplingHookEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(GrapplingModEntities.GRAPPLING_HOOK.get(), world);
	}

	public GrapplingHookEntity(EntityType<? extends GrapplingHookEntity> type, Level world) {
		super(type, world);
	}

	public GrapplingHookEntity(EntityType<? extends GrapplingHookEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public GrapplingHookEntity(EntityType<? extends GrapplingHookEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}



	public void setEffectsFromItem(ItemStack p_36879_) {
		if (p_36879_.is(Items.TIPPED_ARROW)) {
			this.potion = PotionUtils.getPotion(p_36879_);
			Collection<MobEffectInstance> collection = PotionUtils.getCustomEffects(p_36879_);
			if (!collection.isEmpty()) {
				for(MobEffectInstance mobeffectinstance : collection) {
					this.effects.add(new MobEffectInstance(mobeffectinstance));
				}
			}

			int i = getCustomColor(p_36879_);
			if (i == -1) {
				this.updateColor();
			} else {
				this.setFixedColor(i);
			}
		} else if (p_36879_.is(Items.ARROW)) {
			this.potion = Potions.EMPTY;
			this.effects.clear();
			this.entityData.set(ID_EFFECT_COLOR, -1);
		}

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return new ItemStack(GrapplingModItems.GRAPPLING.get());
	}



	public static int getCustomColor(ItemStack p_36885_) {

		CompoundTag compoundtag = p_36885_.getTag();
		return compoundtag != null && compoundtag.contains("CustomPotionColor", 99) ? compoundtag.getInt("CustomPotionColor") : -1;
	}

	private void updateColor() {
		this.fixedColor = false;
		if (this.potion == Potions.EMPTY && this.effects.isEmpty()) {
			this.entityData.set(ID_EFFECT_COLOR, -1);
		} else {
			this.entityData.set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
		}

	}

	public void addEffect(MobEffectInstance p_36871_) {
		this.effects.add(p_36871_);
		this.getEntityData().set(ID_EFFECT_COLOR, PotionUtils.getColor(PotionUtils.getAllEffects(this.potion, this.effects)));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(ID_EFFECT_COLOR, -1);
	}

	public void tick() {
		super.tick();
		if (this.level().isClientSide) {
			if (this.inGround) {
				if (this.inGroundTime % 5 == 0) {
					this.makeParticle(1);
				}
			} else {
				this.makeParticle(2);
			}
		} else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
			this.level().broadcastEntityEvent(this, (byte)0);
			this.potion = Potions.EMPTY;
			this.effects.clear();
			this.entityData.set(ID_EFFECT_COLOR, -1);
		}

	}

	private void makeParticle(int p_36877_) {
		int i = this.getColor();
		if (i != -1 && p_36877_ > 0) {
			double d0 = (double)(i >> 16 & 255) / 255.0D;
			double d1 = (double)(i >> 8 & 255) / 255.0D;
			double d2 = (double)(i >> 0 & 255) / 255.0D;

			for(int j = 0; j < p_36877_; ++j) {
				this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
			}

		}
	}

	public int getColor() {
		return this.entityData.get(ID_EFFECT_COLOR);
	}

	private void setFixedColor(int p_36883_) {
		this.fixedColor = true;
		this.entityData.set(ID_EFFECT_COLOR, p_36883_);
	}


	public void addAdditionalSaveData(CompoundTag p_36881_) {
		super.addAdditionalSaveData(p_36881_);
		if (this.potion != Potions.EMPTY) {
			p_36881_.putString("Potion", BuiltInRegistries.POTION.getKey(this.potion).toString());
		}

		if (this.fixedColor) {
			p_36881_.putInt("Color", this.getColor());
		}

		if (!this.effects.isEmpty()) {
			ListTag listtag = new ListTag();

			for(MobEffectInstance mobeffectinstance : this.effects) {
				listtag.add(mobeffectinstance.save(new CompoundTag()));
			}

			p_36881_.put("CustomPotionEffects", listtag);
		}

	}
	public void readAdditionalSaveData(CompoundTag p_36875_) {
		super.readAdditionalSaveData(p_36875_);
		if (p_36875_.contains("Potion", 8)) {
			this.potion = PotionUtils.getPotion(p_36875_);
		}

		for(MobEffectInstance mobeffectinstance : PotionUtils.getCustomEffects(p_36875_)) {
			this.addEffect(mobeffectinstance);
		}

		if (p_36875_.contains("Color", 99)) {
			this.setFixedColor(p_36875_.getInt("Color"));
		} else {
			this.updateColor();
		}

	}



	protected ItemStack getPickupItem() {
		if (this.effects.isEmpty() && this.potion == Potions.EMPTY) {
			return new ItemStack(GrapplingModItems.GRAPPLING.get());
		} else {
			ItemStack itemstack = new ItemStack(Items.TIPPED_ARROW);
			PotionUtils.setPotion(itemstack, this.potion);
			PotionUtils.setCustomEffects(itemstack, this.effects);
			if (this.fixedColor) {
				itemstack.getOrCreateTag().putInt("CustomPotionColor", this.getColor());
			}

			return itemstack;
		}
	}

	public void handleEntityEvent(byte p_36869_) {
		if (p_36869_ == 0) {
			int i = this.getColor();
			if (i != -1) {
				double d0 = (double)(i >> 16 & 255) / 255.0D;
				double d1 = (double)(i >> 8 & 255) / 255.0D;
				double d2 = (double)(i >> 0 & 255) / 255.0D;

				for(int j = 0; j < 20; ++j) {
					this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
				}
			}
		} else {
			super.handleEntityEvent(p_36869_);
		}

	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Player player = Minecraft.getInstance().player;
			if (player != null) {
				Vec3 playerPos = player.position();
				List<GrapplingHookEntity> arrows = getArrowsNearPlayer(player, 100); // 10ブロック以内の矢を検出
				for (GrapplingHookEntity arrow : arrows) {
					Vec3 arrowPos = arrow.position();
					if(GRAPPFLAG == true) {
						double speed = 0.3; // 移動速度の調整
						Vec3 direction = arrowPos.subtract(playerPos).normalize().scale(speed);
						player.setDeltaMovement(direction);
					}
					spawnParticlesBetween(playerPos, arrowPos, ParticleTypes.SMOKE);



				}



			}
		}
	}

	private static List<GrapplingHookEntity> getArrowsNearPlayer(Player player, double radius) {
		AABB boundingBox = player.getBoundingBox().inflate(radius);
		return player.level().getEntitiesOfClass(GrapplingHookEntity.class, boundingBox);
	}
	private static void spawnParticlesBetween(Vec3 start, Vec3 end, SimpleParticleType particleType) {
		double distance = start.distanceTo(end);
		int count = (int) (distance * 5);  // パーティクルの数を調整
		Player player = Minecraft.getInstance().player;

		for (int i = 0; i < count; i++) {
			double ratio = i / (double) count;
			double x = start.x + (end.x - start.x) * ratio;
			double y = start.y + (end.y - start.y) * ratio;
			double z = start.z + (end.z - start.z) * ratio;
			player.level().addParticle(ParticleTypes.SMOKE, x, y, z, 0.0,0.0,.0);

		}
	}
	public static void register() {
		MinecraftForge.EVENT_BUS.register(GrapplingHookEntity.class);
	}
}
