package cn.mmf.lastsmith.util;

import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.TagPropertyAccessor.TagPropertyBoolean;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BladeUtil {
	public static final TagPropertyAccessor.TagPropertyString Username = new TagPropertyAccessor.TagPropertyString("Username");
	  
	public static final TagPropertyAccessor.TagPropertyString CrafterName = new TagPropertyAccessor.TagPropertyString("craftername");
	
	public static final TagPropertyAccessor.TagPropertyString TextureOnName = new TagPropertyAccessor.TagPropertyString("TextureOnName");
	public static final TagPropertyAccessor.TagPropertyString ModelOnName = new TagPropertyAccessor.TagPropertyString("ModelOnName");
	
	public static final TagPropertyAccessor.TagPropertyBoolean IsFakeBlade = new TagPropertyBoolean("IsFakeBlade");
	public static final TagPropertyAccessor.TagPropertyBoolean IsBewitchedActived = new TagPropertyBoolean("IsBewitchedActived");
	public static final TagPropertyAccessor.TagPropertyBoolean HasCrafter = new TagPropertyBoolean("HasCrafter");
	
	public static String getname(NBTTagCompound nbt) {
		if(CrafterName.exists(nbt))
			return CrafterName.get(nbt);
		return null;
	}
	public static void setPlayer(NBTTagCompound nbt, EntityPlayer playerIn) {
		if(HasCrafter.get(nbt)) return;
		HasCrafter.set(nbt, true);
		CrafterName.set(nbt, playerIn.getName().toString());
	}
	public static boolean isMatchedBlade(ItemStack target,ItemStack blade) {
		return target.getItem() instanceof ItemSlashBlade &&
				target.getUnlocalizedName().equals(blade.getUnlocalizedName());
	}

}
