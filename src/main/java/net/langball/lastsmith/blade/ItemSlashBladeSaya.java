package net.langball.lastsmith.blade;

import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraftforge.oredict.OreDictionary;
import org.omg.CORBA.Current;

import java.util.EnumSet;
import java.util.List;

public class ItemSlashBladeSaya extends ItemSlashBlade {

    public ItemSlashBladeSaya(ToolMaterial par2EnumToolMaterial){
        super(par2EnumToolMaterial, 4.0f);
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
    public int getMaxDamage(ItemStack stack)
    {
        return this.getMaxDamage();
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
        NBTTagCompound tag = getItemTagCompound(stack);
            super.setDamage(stack,0);
    }

  
}

