package cn.mmf.lastsmith.item;

import java.util.EnumSet;

import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemSlashBladeTLS extends ItemSlashBlade {
	public ItemSlashBladeTLS(ToolMaterial par2EnumToolMaterial, float defaultBaseAttackModifier) {
		super(par2EnumToolMaterial, defaultBaseAttackModifier);
	}
	
	@Override
	public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
		NBTTagCompound tag = getItemTagCompound(itemStack);
		EnumSet<SwordType> set = super.getSwordType(itemStack);
		if(!BladeUtil.IsBewitchedActived.get(tag)){
			set.remove(SwordType.Bewitched);
			set.remove(SwordType.SoulEeater);
		}
		return set;
	}
}
