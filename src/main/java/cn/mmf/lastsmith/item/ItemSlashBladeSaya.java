package cn.mmf.lastsmith.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mods.flammpfeil.slashblade.ItemSlashBladeWrapper;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import java.util.EnumSet;
import java.util.List;

import cn.mmf.lastsmith.util.BladeUtil;

public class ItemSlashBladeSaya extends ItemSlashBladeWrapper {

    public ItemSlashBladeSaya(ToolMaterial par2EnumToolMaterial){
        super(par2EnumToolMaterial);
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @SideOnly(Side.CLIENT)
	public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
		NBTTagCompound nbt = getItemTagCompound(arg0);
		if(BladeUtil.getInstance().getname(nbt) != null){
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+BladeUtil.getInstance().getname(nbt));	
		}else{
			arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+I18n.format("lastsmith.name.unnamed_smith"));		
		}
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

