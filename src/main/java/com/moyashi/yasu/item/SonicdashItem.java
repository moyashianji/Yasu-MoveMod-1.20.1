
package com.moyashi.yasu.item;

import com.moyashi.yasu.event.KeyNether;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
				breaks = true;
				player.setInvisible(true);
				SONICFLAG = true;

			}

			// 切り替え状態をトグル
			nbt.putBoolean("isInNether", !isInNether);
		}

		return InteractionResult.SUCCESS;
	}
	private static void toggleDimension(ServerPlayer player) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level != null && mc.player != null) {
			if (isInSonic) {
				// 元の世界に戻る


				isInSonic = false;
			} else {
				// ネザーにテレポート
				isInSonic = true;
			}
		}
	}
	public static void register() {
		MinecraftForge.EVENT_BUS.register(SonicdashItem.class);
	}
}
