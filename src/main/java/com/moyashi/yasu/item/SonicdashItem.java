
package com.moyashi.yasu.item;

import com.moyashi.yasu.bigportion.init.BigModMobEffects;
import com.moyashi.yasu.bigportion.procedures.EnergyOnProcedure;
import com.moyashi.yasu.event.KeyNether;
import com.moyashi.yasu.main.Reference;
import com.moyashi.yasu.porrr.procedures.OmegapotionYoukuritukusitatokiProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.moyashi.yasu.main.Reference.*;
import static com.moyashi.yasu.main.Reference.NetZ;

public class SonicdashItem extends Item {
	public SonicdashItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
	public static boolean isInSonic = false;

	public static boolean breaks = false;
	private static final int RADIUS = 2;
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && event.player != null) {
			Player player = event.player;
			// プレイヤーの座標を取得
			if (!event.player.level().isClientSide) {
				if(SONICFLAG == true) {
					player.setInvisible(true);
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

		if (!world.isClientSide) {
			// アイテムにNBTタグがなければ初期化
			if (!stack.hasTag()) {
				stack.setTag(new CompoundTag());
			}
			CompoundTag nbt = stack.getTag();

			// ネザーへのテレポートと元の世界へのテレポートを切り替え
			boolean isInNether = nbt.getBoolean("isInNether");
			if (isInNether) {
				breaks = false;

				player.setInvisible(false);
				SONICFLAG = false;

			} else {
				player.setInvisible(true);

				breaks = true;
				SONICFLAG = true;
			}

			// 切り替え状態をトグル
			nbt.putBoolean("isInNether", !isInNether);
		}

		return InteractionResult.SUCCESS;
	}


	public static void register() {
		MinecraftForge.EVENT_BUS.register(SonicdashItem.class);
	}
}
