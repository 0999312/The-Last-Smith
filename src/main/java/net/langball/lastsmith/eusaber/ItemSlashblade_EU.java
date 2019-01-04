package net.langball.lastsmith.eusaber;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IItemHudInfo;
import java.util.LinkedList;
import java.util.List;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSlashblade_EU
  extends ItemSlashBladeNamedSS
  implements IElectricItem, IItemHudInfo
{
  public static int maxEnergy = 2000000;
  public static int maxTransfer = 2048;
  
  public ItemSlashblade_EU(Item.ToolMaterial par2EnumToolMaterial, float baseAttackModifiers)
  {
    super(par2EnumToolMaterial, baseAttackModifiers);
  }
  
  public boolean canProvideEnergy(ItemStack itemStack)
  {
    return false;
  }
  
  @Override
public boolean getIsRepairable(ItemStack arg0, ItemStack arg1) {
	// TODO Auto-generated method stub
	return false;
}

public Item getChargedItem(ItemStack itemStack)
  {
    return this;
  }
  
  public Item getEmptyItem(ItemStack itemStack)
  {
    return this;
  }
  
  public double getMaxCharge(ItemStack itemStack)
  {
    return maxEnergy;
  }
  
  public int getTier(ItemStack itemStack)
  {
    return 2;
  }
  
  public double getTransferLimit(ItemStack itemStack)
  {
    return maxTransfer;
  }
  

  
  @SuppressWarnings({ "unchecked", "rawtypes" })
@Override
public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
	super.addInformation(arg0, arg1, arg2, arg3);
	arg2.add("Stored EU" + (int)ElectricItem.manager.getCharge(arg0) + "/" + maxEnergy);
    arg2.add("PowerTier 2");
}

public void onUpdate(ItemStack sitem, World par2World, Entity par3Entity, int indexOfMainSlot, boolean isCurrent)
  {
    super.onUpdate(sitem, par2World, par3Entity, indexOfMainSlot, isCurrent);
    if (!par2World.isRemote)
    {
      sitem.getTagCompound().setDouble("charge", getMaxCharge(sitem) * (getMaxDamage(sitem) - getDamage(sitem)) / getMaxDamage(sitem));
      if ((int)ElectricItem.manager.getCharge(sitem) >= 50000)
      {
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(sitem);
        IsBroken.set(reqTag, Boolean.valueOf(false));
      }
    }
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
public List<String> getHudInfo(ItemStack stack, boolean advanced)
  {
	LinkedList info = new LinkedList();
    info.add(ElectricItem.manager.getToolTip(stack));
    return info;
  }
  
}
