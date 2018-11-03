package net.langball.lastsmith.blade;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mods.flammpfeil.slashblade.ItemSlashBladeDetune;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSlashBladeModel extends ItemSlashBladeDetune{

	public ItemSlashBladeModel(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
	}

	@Override
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		super.addInformation(arg0, arg1, arg2, arg3);
		arg2.add(TextFormatting.WHITE + I18n.format("blades.ps.model"));
	}

}
