package cn.mmf.lastsmith.util;

import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.TagPropertyAccessor.TagPropertyBoolean;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public final class BladeUtil {
	private static final BladeUtil instance = new BladeUtil();

	private BladeUtil() {
	}

	public static BladeUtil getInstance() {
		return instance;
	}
	public final TagPropertyAccessor.TagPropertyString Username = new TagPropertyAccessor.TagPropertyString("Username");
	  
	public final TagPropertyAccessor.TagPropertyString CrafterName = new TagPropertyAccessor.TagPropertyString("craftername");
	
	public final TagPropertyAccessor.TagPropertyString TextureOnName = new TagPropertyAccessor.TagPropertyString("TextureOnName");
	public final TagPropertyAccessor.TagPropertyString ModelOnName = new TagPropertyAccessor.TagPropertyString("ModelOnName");
	
	public final TagPropertyAccessor.TagPropertyBoolean IsFakeBlade = new TagPropertyBoolean("IsFakeBlade");
	public final TagPropertyAccessor.TagPropertyBoolean IsBewitchedActived = new TagPropertyBoolean("IsBewitchedActived");
	public final TagPropertyAccessor.TagPropertyBoolean HasCrafter = new TagPropertyBoolean("HasCrafter");
	
	public final TagPropertyAccessor.TagPropertyInteger MAXENERGY = new TagPropertyAccessor.TagPropertyInteger(
			"maxEnergy");
	public final TagPropertyAccessor.TagPropertyInteger MAXTRANSFER = new TagPropertyAccessor.TagPropertyInteger(
			"maxTransfer");
	public final TagPropertyAccessor.TagPropertyInteger ENERGYPERUSE = new TagPropertyAccessor.TagPropertyInteger(
			"energyPerUse");
	public final TagPropertyAccessor.TagPropertyInteger ENERGYPERUSECHARGED = new TagPropertyAccessor.TagPropertyInteger(
			"energyPerUseCharged");
	
	public String getname(NBTTagCompound nbt) {
		if(CrafterName.exists(nbt))
			return CrafterName.get(nbt);
		return null;
	}
	public void setPlayer(NBTTagCompound nbt, EntityPlayer playerIn) {
		if(HasCrafter.get(nbt)) return;
		HasCrafter.set(nbt, true);
		CrafterName.set(nbt, playerIn.getName().toString());
	}
	public boolean isMatchedBlade(ItemStack target,ItemStack blade) {
		return target.getItem() instanceof ItemSlashBlade &&
				target.getUnlocalizedName().equals(blade.getUnlocalizedName());
	}

}
