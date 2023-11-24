
package com.moyashi.yasu.porrr.item;

import com.moyashi.yasu.bigportion.init.BigModMobEffects;
import com.moyashi.yasu.bigportion.procedures.BigposiyonXiaoGuogaQieretaShiProcedure;
import com.moyashi.yasu.bigportion.procedures.BigposiyonnoXiaoGuogaKaiShiShiYongsaretatokiProcedure;
import com.moyashi.yasu.bigportion.procedures.EnergyOffProcedure;
import com.moyashi.yasu.bigportion.procedures.EnergyOnProcedure;
import com.moyashi.yasu.event.FaltRunning;
import com.moyashi.yasu.main.Reference;
import com.moyashi.yasu.porrr.procedures.EnergydrinkYoukuritukusitatokiProcedure;
import com.moyashi.yasu.porrr.procedures.OmegapotionYoukuritukusitatokiProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.main.Reference.*;


public class EnergydrinkItem extends Item {
	public EnergydrinkItem() {
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

	public static double usetick = 0;

	public static double musictick = 0;
	public static double musicalltick = 0;

	public static boolean MUSICFLAG = false;
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && event.player != null) {
			Player player = event.player;
			// プレイヤーの座標を取得
			if(!event.player.level().isClientSide) {
				if (ENERGYFLAG == true) {
					usetick++;
					if (usetick >= 600) {

						ENERGYFLAG = false;
						usetick = 0;
						player.removeEffect(new MobEffectInstance(BigModMobEffects.ENERGY_DRINK_EFFECT.get()).getEffect());

					}
					if(MUSICFLAG == true) {
						musicalltick++;
						musictick++;
						if(musicalltick <= 15) {
							if (musictick >= 5) {
								OmegapotionYoukuritukusitatokiProcedure.execute(player.level(), player.getX(), player.getY(), player.getZ());
								musictick = 0;


							}
						}else{
							musictick = 0;
							musicalltick = 0;
							MUSICFLAG = false;
						}
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
				EnergyOnProcedure.execute();
				Reference.ENERGYFLAG = false;
				System.out.println("removeener");
				player.removeEffect(new MobEffectInstance(BigModMobEffects.ENERGY_DRINK_EFFECT.get()).getEffect());
				usetick = 0;
				musicalltick = 0;
				musictick = 0;
				MUSICFLAG = false;

			} else {
				EnergyOnProcedure.execute();
				Reference.ENERGYFLAG = true;
				player.addEffect(new MobEffectInstance(BigModMobEffects.ENERGY_DRINK_EFFECT.get(), 600, 1, (false), (false)));
				MUSICFLAG = true;
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
	public static void register() {
		MinecraftForge.EVENT_BUS.register(EnergydrinkItem.class);
	}

}
