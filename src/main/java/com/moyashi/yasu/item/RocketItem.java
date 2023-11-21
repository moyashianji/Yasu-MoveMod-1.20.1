package com.moyashi.yasu.item;

import com.moyashi.yasu.config.MoneySave;
import com.moyashi.yasu.world.dimension.EndeerdimDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.common.property.Properties;
import com.moyashi.yasu.config.MoneyLoad;

import static com.moyashi.yasu.main.Reference.*;

public class RocketItem extends Item {

    public RocketItem() {
        super(new Item.Properties().stacksTo(64).durability(2).rarity(Rarity.COMMON));
    }



    public static final ResourceKey<Level> TARGET_DIMENSION_KEY =
            ResourceKey.create(Registries.DIMENSION, new ResourceLocation("yasu:endeerdim")); // EndeeeerディメンションのResourceKey

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
                teleportToOverworld((ServerPlayer) player);
            } else {
                teleportToDimension((ServerPlayer) player);
            }

            // 切り替え状態をトグル
            nbt.putBoolean("isInNether", !isInNether);
        }

        return InteractionResult.SUCCESS;
    }

    // ネザーへのテレポート
    private static void teleportToDimension(ServerPlayer player) {
        // テレポート先のディメンションに応じて座標を設定

        MoneyLoad.Money += 100000;
        MoneySave.onSave();
        double x = 100;
        double y = 100;
        double z = 100;
        // プレイヤーを指定のディメンションにテレポート
        player.teleportTo(player.getServer().getLevel(TARGET_DIMENSION_KEY), NewX, NewY, NewZ, player.getYRot(), player.getXRot());
    }
    // 元の世界へのテレポート
    private void teleportToOverworld(ServerPlayer player) {
        MoneyLoad.Money += 100000;
        MoneySave.onSave();
        // テレポート先のディメンションに応じて座標を設定
        double x = 100;
        double y = 100;
        double z = 100;
        // プレイヤーを指定のディメンションにテレポート
        player.teleportTo(player.getServer().getLevel(Level.OVERWORLD), OveX, OveY ,OveZ, player.getYRot(), player.getXRot());
    }

}
