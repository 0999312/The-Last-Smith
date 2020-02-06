package cn.mmf.lastsmith.util;

import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.TagPropertyAccessor.TagPropertyBoolean;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BladeUtil {
	public static final TagPropertyAccessor.TagPropertyString Username = new TagPropertyAccessor.TagPropertyString("Username");
	  
	public static final TagPropertyAccessor.TagPropertyString TextureOnName = new TagPropertyAccessor.TagPropertyString("TextureOnName");
	public static final TagPropertyAccessor.TagPropertyString ModelOnName = new TagPropertyAccessor.TagPropertyString("ModelOnName");
	
	public static final TagPropertyAccessor.TagPropertyBoolean IsFakeBlade = new TagPropertyBoolean("IsFakeBlade");
	public static final TagPropertyAccessor.TagPropertyBoolean IsBewitchedActived = new TagPropertyBoolean("IsBewitchedActived");
	public static String getname(NBTTagCompound nbt) {
		if(nbt.hasKey("craftername"))
			return nbt.getString("craftername");
		return null;
	}
	public static void setPlayer(NBTTagCompound nbt, EntityPlayer playerIn) {
		setName(nbt, playerIn.getName().toString());
	}
	public static void setName(NBTTagCompound nbt, String playerIn) {
		nbt.setString("craftername", playerIn);
	}
	public static boolean isMatchedBlade(ItemStack target,ItemStack blade) {
		return target.getItem() instanceof ItemSlashBlade &&
				target.getUnlocalizedName().equals(blade.getUnlocalizedName());
	}
}
