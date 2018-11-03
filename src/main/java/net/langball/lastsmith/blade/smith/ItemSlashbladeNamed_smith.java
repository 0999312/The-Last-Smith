package net.langball.lastsmith.blade.smith;

import java.util.List;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemSlashbladeNamed_smith extends ItemSlashBladeNamedSS {

	public ItemSlashbladeNamed_smith(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		super.addInformation(arg0, arg1, arg2, arg3);
		NBTTagCompound nbt = arg0.getTagCompound();
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.RED + I18n.format("the_last_smith.name"));	
	}
}
