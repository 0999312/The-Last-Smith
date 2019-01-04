package net.langball.lastsmith.items;

import java.util.List;

import net.langball.lastsmith.blocks.BlockLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHeattedBlade extends ItemBase {

	private ItemStack[] cold;
	public ItemHeattedBlade(String[] subNames,ItemStack... colded) {
		super("itemHeattedBlade", 1, subNames);
		cold = colded;
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.getBlockState(pos).getBlock() == BlockLoader.Casket){
			if(!worldIn.isRemote){
				ItemStack item = cold[getDamage(player.getHeldItem(hand))];
				player.setHeldItem(hand, item);
			}
			worldIn.playSound(player,player.posX,player.posY,player.posZ,SoundEvents.BLOCK_FIRE_AMBIENT,SoundCategory.PLAYERS, 1.0F, 1.0F);
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.RED + I18n.format("lastsmith.tip.heatted_blade"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
