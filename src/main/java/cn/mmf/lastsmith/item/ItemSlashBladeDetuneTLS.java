package cn.mmf.lastsmith.item;


import java.util.EnumSet;
import java.util.List;

import cn.mmf.lastsmith.blades.BladeLoader;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.util.ReflectionAccessHelper;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSlashBladeDetuneTLS extends ItemSlashBladeTLS{

	public ItemSlashBladeDetuneTLS(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
	}
//	
	public ResourceLocationRaw model = new ResourceLocationRaw(SlashBlade.modid, "model/blade.obj");
	
	@Override
	public ResourceLocationRaw getModel() {
		return model;
	}
	public ItemSlashBladeDetuneTLS setModel(ResourceLocationRaw loc){
		this.model = loc;
		return this;
	}
	
	public ResourceLocationRaw texture;
	@Override
	public ResourceLocationRaw getModelTexture(){
		return texture;
	}
	public ItemSlashBladeDetuneTLS setModelTexture(ResourceLocationRaw loc){
		this.texture = loc;
		return this;
	}
	
	private boolean isDestructable = true;
	public ItemSlashBladeDetuneTLS setDestructable(boolean enabled){
		this.isDestructable = enabled;
		return this;
	}
	@Override
	public void onUpdate(ItemStack arg0, World arg1, Entity arg2, int arg3, boolean arg4) {
        if (this == BladeLoader.bladeSilverBambooLight) {
        	NBTTagCompound tag = getItemTagCompound(arg0);
            int killCount = KillCount.get(tag);
        	if(IsBroken.get(tag)&&killCount>=100) {
        		ReflectionAccessHelper.setItem(arg0, SlashBlade.wrapBlade);
        		arg0.setItemDamage(0);
            }
        }
		super.onUpdate(arg0, arg1, arg2, arg3, arg4);
	}
    @Override
    public boolean isDestructable(ItemStack stack) {
        return super.isDestructable(stack) || isDestructable;
    }
    
    
    
	@Override
	public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
		EnumSet<SwordType> set = super.getSwordType(itemStack);
		set.remove(SwordType.Enchanted);
		set.remove(SwordType.Bewitched);
		set.remove(SwordType.SoulEeater);
		return set;
	}

	@SuppressWarnings("rawtypes")
	@Override
    public void addInformationSwordClass(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		
	}


}
