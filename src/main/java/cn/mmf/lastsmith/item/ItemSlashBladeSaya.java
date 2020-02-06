package cn.mmf.lastsmith.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import mods.flammpfeil.slashblade.ItemSlashBladeWrapper;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import java.util.EnumSet;

public class ItemSlashBladeSaya extends ItemSlashBladeWrapper {

    public ItemSlashBladeSaya(ToolMaterial par2EnumToolMaterial){
        super(par2EnumToolMaterial);
    }

    private ResourceLocationRaw texture = new ResourceLocationRaw("flammpfeil.slashblade","model/wooden_saya.png");
    @Override
    public ResourceLocationRaw getModelTexture(){
        return texture;
    }
    public ItemSlashBladeSaya setModelTexture(ResourceLocationRaw loc){
		this.texture = loc;
		return this;
	}
    
    @Override
    public ComboSequence getNextComboSeq(ItemStack itemStack, ComboSequence current, boolean isRightClick, EntityPlayer player) {
        return ComboSequence.None;
    }

    @Override
    public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
        EnumSet<SwordType> set =  EnumSet.noneOf(SwordType.class);
        set.add(SwordType.Perfect);
        set.add(SwordType.Sealed);
        set.remove(SwordType.FiercerEdge);
        return set;
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        ComboSequence comboSec = getComboSequence(getItemTagCompound(par1ItemStack));
        switch (comboSec) {
            case Saya1:
            case Saya2:
                break;

            default:
                break;
        }

        return super.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
            super.setDamage(stack,0);
    }
    
}

