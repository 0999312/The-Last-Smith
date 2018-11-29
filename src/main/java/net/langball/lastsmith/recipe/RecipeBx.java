package net.langball.lastsmith.recipe;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.TagPropertyAccessor.TagPropertyIntegerWithRange;
import mods.flammpfeil.slashblade.TagPropertyAccessor.TagPropertyString;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeBx
  extends ShapedOreRecipe
{

ItemStack requiredBladeMain;
  ItemStack requiredBladeSub;
  public RecipeBx(ResourceLocation group, ItemStack result, ItemStack requiredBladeMain, ItemStack requiredBladeSub,Object[] recipe) {
		super(group, result, recipe);
this.requiredBladeMain = requiredBladeMain;
this.requiredBladeSub = requiredBladeSub;
	}

  int tagValueCompare(TagPropertyAccessor access, NBTTagCompound reqTag, NBTTagCompound srcTag)
  {
    return access.get(reqTag).compareTo(access.get(srcTag));
  }
  
  public boolean matches(InventoryCrafting inv, World world)
  {
    boolean result = super.matches(inv, world);
    if ((result) && (this.requiredBladeMain != null) && (this.requiredBladeSub != null))
    {
      ItemStack curIs = inv.getStackInRowAndColumn(0, 1);
      if ((curIs != null) && ((curIs.getItem() instanceof ItemSlashBlade)) && (curIs.hasTagCompound()))
      {
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(this.requiredBladeMain);
        NBTTagCompound srcTag = ItemSlashBlade.getItemTagCompound(curIs);
        if (!curIs.getUnlocalizedName().equals(this.requiredBladeMain.getUnlocalizedName())) {
          return false;
        }
      }
      else
      {
        return false;
      }
      ItemStack curIs1 = inv.getStackInRowAndColumn(2, 1);
      if ((curIs1 != null) && ((curIs1.getItem() instanceof ItemSlashBlade)) && (curIs1.hasTagCompound()))
      {
        if (!curIs1.getUnlocalizedName().equals(this.requiredBladeSub.getUnlocalizedName())) {
          return false;
        }
      }
      else {
        return false;
      }
      return true;
    }
    return false;
  }
  
  public ItemStack getCraftingResult(InventoryCrafting var1)
  {
    ItemStack result = super.getCraftingResult(var1);
    
    ItemStack curIs = var1.getStackInRowAndColumn(1, 1);
    NBTTagCompound oldTag = curIs.getTagCompound();
    oldTag = (NBTTagCompound)oldTag.copy();
    NBTTagCompound newTag = ItemSlashBlade.getItemTagCompound(result);
    if (ItemSlashBladeNamed.CurrentItemName.exists(newTag))
    {
      String key = ItemSlashBladeNamed.CurrentItemName.get(newTag);
      ItemStack tmp = SlashBlade.getCustomBlade(key);
      if (tmp != null) {
        result = tmp;
      }
    }
    newTag = ItemSlashBlade.getItemTagCompound(result);
    ItemSlashBlade.KillCount.set(newTag, ItemSlashBlade.KillCount.get(oldTag));
    ItemSlashBlade.ProudSoul.set(newTag, ItemSlashBlade.ProudSoul.get(oldTag));
    ItemSlashBlade.RepairCount.set(newTag, ItemSlashBlade.RepairCount.get(oldTag));
    if (oldTag.hasKey("adjustX")) {
      newTag.setFloat("adjustX", oldTag.getFloat("adjustX"));
    }
    if (oldTag.hasKey("adjustY")) {
      newTag.setFloat("adjustY", oldTag.getFloat("adjustY"));
    }
    if (oldTag.hasKey("adjustZ")) {
      newTag.setFloat("adjustZ", oldTag.getFloat("adjustZ"));
    }
    Map<Enchantment,Integer> newItemEnchants = EnchantmentHelper.getEnchantments(result);
    Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(curIs);
    for(Enchantment enchantIndex : oldItemEnchants.keySet())
    {
    	 Enchantment enchantment = enchantIndex;

         int destLevel = newItemEnchants.containsKey(enchantIndex) ? newItemEnchants.get(enchantIndex) : 0;
         int srcLevel = oldItemEnchants.get(enchantIndex);

         srcLevel = Math.max(srcLevel, destLevel);
         srcLevel = Math.min(srcLevel, enchantment.getMaxLevel());


         boolean canApplyFlag = enchantment.canApply(result);
         if(canApplyFlag){
             for(Enchantment curEnchantIndex : newItemEnchants.keySet()){
                 if (curEnchantIndex != enchantIndex && !enchantment.isCompatibleWith(curEnchantIndex) /*canApplyTogether*/)
                 {
                     canApplyFlag = false;
                     break;
                 }
             }
             if (canApplyFlag)
                 newItemEnchants.put(enchantIndex, Integer.valueOf(srcLevel));
         }
    }
    EnchantmentHelper.setEnchantments(newItemEnchants, result);
    return result;
  }
}
