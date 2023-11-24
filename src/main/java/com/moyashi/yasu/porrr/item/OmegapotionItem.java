
package com.moyashi.yasu.porrr.item;

import com.moyashi.yasu.bigportion.init.BigModMobEffects;
import com.moyashi.yasu.bigportion.procedures.BigposiyonXiaoGuogaQieretaShiProcedure;
import com.moyashi.yasu.bigportion.procedures.BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure;
import com.moyashi.yasu.config.MoneyLoad;
import com.moyashi.yasu.config.MoneySave;
import com.moyashi.yasu.main.Reference;
import com.moyashi.yasu.porrr.procedures.OmegapotionYoukuritukusitatokiProcedure;
import com.moyashi.yasu.porrr.procedures.OmegapotionpureiyagaShiYongwosutotupusitaShiProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.main.Reference.*;
import static com.moyashi.yasu.main.Reference.OveZ;

public class OmegapotionItem extends Item {
	public OmegapotionItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 32;
	}

	public static double omegatick;

	public static double musicomegatick = 0;
	public static double musicomegaalltick = 0;

	public static boolean MUSICOMEGAFLAG = false;
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && event.player != null) {
			Player player = event.player;
			// プレイヤーの座標を取得
			if(!event.player.level().isClientSide) {
				if (BIGFLAG == true) {
					omegatick++;
					if (omegatick >= 600) {
						BigposiyonXiaoGuogaQieretaShiProcedure.execute();

						Reference.size = 0.9375F;
						EYEHEIGH = 2.0f;

						BIGFLAG = false;

						omegatick = 0;
						player.removeEffect(new MobEffectInstance(BigModMobEffects.BIG.get()).getEffect());

					}
				}

				if(MUSICOMEGAFLAG == true) {
					musicomegaalltick++;
					musicomegatick++;
					if(musicomegaalltick <= 15) {
						if (musicomegatick >= 5) {
							OmegapotionYoukuritukusitatokiProcedure.execute(player.level(), player.getX(), player.getY(), player.getZ());
							musicomegatick = 0;


						}
					}else{
						musicomegatick = 0;
						musicomegaalltick = 0;
						MUSICOMEGAFLAG = false;
					}
				}
			}
		}
	}
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		Player player = context.getPlayer();
		InteractionHand hand = context.getHand();
		ItemStack stack = player.getItemInHand(hand);
		OmegapotionYoukuritukusitatokiProcedure.execute(world, player.getX(), player.getY(), player.getZ());

		if (!world.isClientSide) {
			// アイテムにNBTタグがなければ初期化
			if (!stack.hasTag()) {
				stack.setTag(new CompoundTag());
			}
			if (stack.hurt(1, RandomSource.create(), null)) {
				stack.shrink(1);
				stack.setDamageValue(0);
			}


			CompoundTag nbt = stack.getTag();

			// ネザーへのテレポートと元の世界へのテレポートを切り替え
			boolean isInNether = nbt.getBoolean("isInNether");
			if (isInNether) {
				BigposiyonXiaoGuogaQieretaShiProcedure.execute();
				Reference.size = 0.9375F;
				EYEHEIGH = 2.0f;
				BIGFLAG = false;
				player.removeEffect(new MobEffectInstance(BigModMobEffects.BIG.get()).getEffect());
				omegatick = 0;
				musicomegaalltick = 0;
				musicomegatick = 0;
				MUSICOMEGAFLAG = false;
			} else {
				BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure.execute();
				Reference.size = 3.0F;
				EYEHEIGH = 5.0f;
				BIGFLAG = true;
				player.addEffect(new MobEffectInstance(BigModMobEffects.BIG.get(), 600, 1, (false), (false)));
				MUSICOMEGAFLAG = true;

			}

			// 切り替え状態をトグル
			nbt.putBoolean("isInNether", !isInNether);
		}

		return InteractionResult.SUCCESS;
	}

	// ネザーへのテレポート
	private static void teleportToDimension(ServerPlayer player) {
		// テレポート先のディメンションに応じて座標を設定

		double x = 100;
		double y = 100;
		double z = 100;
		// プレイヤーを指定のディメンションにテレポート
	}
	// 元の世界へのテレポート
	private void teleportToOverworld(ServerPlayer player) {

		// テレポート先のディメンションに応じて座標を設定
		double x = 100;
		double y = 100;
		double z = 100;
		// プレイヤーを指定のディメンションにテレポート
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
		OmegapotionpureiyagaShiYongwosutotupusitaShiProcedure.execute(entity);
	}
	public static void register() {
		MinecraftForge.EVENT_BUS.register(OmegapotionItem.class);
	}

}
