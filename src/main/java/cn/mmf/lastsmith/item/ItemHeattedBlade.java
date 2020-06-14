package cn.mmf.lastsmith.item;

import java.util.List;

import cn.mcmod_mmf.mmlib.item.ItemBase;
import cn.mmf.lastsmith.TLSMain;
import net.minecraft.block.BlockCauldron;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHeattedBlade extends ItemBase {

	private ItemStack[] cold;
	public ItemHeattedBlade(String[] subNames,ItemStack... colded) {
		super(TLSMain.MODID,"heatted_blade", 1, subNames);
		cold = colded;
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(I18n.format("lastsmith.use_it_on_cauldron.text"));
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.getBlockState(pos).getBlock() instanceof BlockCauldron){
		player.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1.2F, 1.2F);
		if(!worldIn.isRemote){
				BlockCauldron cauldron = (BlockCauldron) worldIn.getBlockState(pos).getBlock();
				int i = worldIn.getBlockState(pos).getValue(BlockCauldron.LEVEL).intValue();
				if(i>0){
					player.addStat(StatList.CAULDRON_USED);
					cauldron.setWaterLevel(worldIn, pos, worldIn.getBlockState(pos), i-1);
					ItemStack item = cold[getDamage(player.getHeldItem(hand))].copy();
					player.setHeldItem(hand, item);
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
