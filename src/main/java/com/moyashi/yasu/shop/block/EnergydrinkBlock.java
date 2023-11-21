
package com.moyashi.yasu.shop.block;

import com.moyashi.yasu.bigportion.init.BigModMobEffects;
import com.moyashi.yasu.bigportion.init.BigModPotions;
import com.moyashi.yasu.config.MoneyLoad;
import com.moyashi.yasu.init.IroiroModItems;
import com.moyashi.yasu.jumping.init.TrampModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Collections;

import static com.moyashi.yasu.config.MoneySave.onSave;

public class EnergydrinkBlock extends Block {
	public EnergydrinkBlock() {
		super(Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.GRAVEL).strength(1f, 10f).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}
	@Override
	public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);

		if(!entity.level().isClientSide) {

			if(MoneyLoad.Money > 500) {
				world.playSound(null, new BlockPos(entity.getBlockX(), entity.getBlockY(), entity.getBlockZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.levelup")),
						SoundSource.NEUTRAL, 1, 1);
				System.out.println("energy");
				MoneyLoad.Money -= 500;
				onSave();
				ItemStack potionStack = new ItemStack(Items.POTION);

				// ポーションのエフェクトを設定（ヒーリング効果を追加）
				PotionUtils.setPotion(potionStack, BigModPotions.ENERGY_DRINK.get());

				// プレイヤーのインベントリにポーションアイテムを追加
				entity.addItem(potionStack);
			}

		}
		return InteractionResult.SUCCESS;
	}
	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}
}
