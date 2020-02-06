package cn.mmf.lastsmith.item;


import java.util.List;

import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeDetune;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSlashBladeDetuneTLS extends ItemSlashBladeDetune{

	public ItemSlashBladeDetuneTLS(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
	}
	@Override
	public ResourceLocationRaw getModel() {
		return new ResourceLocationRaw(SlashBlade.modid, "model/named/yasha/yasha.obj");
	}
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		super.addInformation(arg0, arg1, arg2, arg3);
		NBTTagCompound nbt = getItemTagCompound(arg0);
		if(BladeUtil.getname(nbt) != null){
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+BladeUtil.getname(nbt));	
		}
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		NBTTagCompound nbt = stack.getTagCompound();
		BladeUtil.setPlayer(nbt,playerIn);
	}

}
