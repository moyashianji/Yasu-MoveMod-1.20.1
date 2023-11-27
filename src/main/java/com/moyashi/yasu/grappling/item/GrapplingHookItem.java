
package com.moyashi.yasu.grappling.item;


import com.moyashi.yasu.grappling.grappleProcedure;
import com.moyashi.yasu.main.Reference;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


public class GrapplingHookItem extends Item {
	public GrapplingHookItem() {
		super(new Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		entity.startUsingItem(hand);
		return new InteractionResultHolder(InteractionResult.SUCCESS, entity.getItemInHand(hand));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 72000;
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
			if (stack.hurt(1, RandomSource.create(), null)) {
				stack.shrink(1);
				stack.setDamageValue(0);
			}


			CompoundTag nbt = stack.getTag();

			// ネザーへのテレポートと元の世界へのテレポートを切り替え
			boolean isInNether = nbt.getBoolean("isInNether");
			if (isInNether) {
				Reference.GRAPPFLAG = false;
			} else {
				Reference.GRAPPFLAG = true;
			}

			// 切り替え状態をトグル
			nbt.putBoolean("isInNether", !isInNether);
		}

		return InteractionResult.SUCCESS;
	}

	@Override
	public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {

				if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {

					if (true) {
						grappleProcedure.execute(entity);

					}
				}
			}
		}
