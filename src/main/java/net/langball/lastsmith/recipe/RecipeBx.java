package net.langball.lastsmith.recipe;


import java.util.Map;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;

import mods.flammpfeil.slashblade.TagPropertyAccessor;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;

import net.minecraft.inventory.InventoryCrafting;

import net.minecraft.item.ItemStack;

import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.util.NonNullList;

import net.minecraft.world.World;

import net.minecraftforge.common.ForgeHooks;

import net.minecraftforge.oredict.ShapedOreRecipe;

import net.minecraft.enchantment.Enchantment;

import net.minecraft.enchantment.EnchantmentHelper;

import net.minecraft.util.ResourceLocation;
public class RecipeBx extends ShapedOreRecipe {
	
	final protected ItemStack requiredBladeMain;
	final protected ItemStack requiredBladeSub;
	final protected boolean remainedBladeSub;
	final protected ItemStack requiredBladeTri;
	final protected boolean remainedBladeTri;
	final protected int posXMain;
	final protected int posYMain;
	final protected int posXSub;
	final protected int posYSub;
	final protected int posXTri;
	final protected int posYTri;

	public RecipeBx(ResourceLocation group,
								 ItemStack result,
								 ItemStack requiredBladeMain,
								 int posXMain,
								 int posYMain,
								 ItemStack requiredBladeSub,
								 int posXSub,
								 int posYSub,
								 boolean remainedBladeSub,
								 ItemStack requiredBladeTri,
								 int posXTri,
								 int posYTri,
								 boolean remainedBladeTri,
								 Object... recipe) {
		super(group, result, recipe);
		this.requiredBladeMain = requiredBladeMain;
		this.posXMain = posXMain;
		this.posYMain = posYMain;
		this.requiredBladeSub = requiredBladeSub;
		this.posXSub = posXSub;
		this.posYSub = posYSub;
		this.remainedBladeSub = remainedBladeSub;
		this.requiredBladeTri = requiredBladeTri;
		this.posXTri = posXTri;
		this.posYTri = posYTri;
		this.remainedBladeTri = remainedBladeTri;
	}
	protected static int tagValueCompare(TagPropertyAccessor.TagPropertyInteger access,NBTTagCompound tag1, NBTTagCompound tag2){
		return access.get(tag1).compareTo(access.get(tag2));
	}
	protected static boolean isValidBlade(ItemStack item){
		return !item.isEmpty() &&item.getItem() instanceof ItemSlashBlade &&item.hasTagCompound();
	}
	
    @Override
	public boolean matches(InventoryCrafting inv, World world)
	{
		boolean result = super.matches(inv, world);
        if (!result)
			return false;
		if (requiredBladeMain.isEmpty() || requiredBladeSub.isEmpty()||requiredBladeTri.isEmpty())
			return false;
		requiredBladeMain.setItemDamage(Short.MAX_VALUE);
		requiredBladeSub.setItemDamage(Short.MAX_VALUE);
		requiredBladeTri.setItemDamage(Short.MAX_VALUE);
		
		ItemStack sub = inv.getStackInRowAndColumn(posXSub, posYSub);
		if (!isValidBlade(sub))
			return false;
		if (!matchesName(sub, requiredBladeSub))
			return false;
		if (!matchesCount(sub, requiredBladeSub))
			return false;
		
		ItemStack tri = inv.getStackInRowAndColumn(posXTri, posYTri);
		if (!isValidBlade(tri))
			return false;
		if (!matchesName(tri, requiredBladeTri))
			return false;
		if (!matchesCount(tri, requiredBladeTri))
			return false;
		
		ItemStack main = inv.getStackInRowAndColumn(posXMain, posYMain);
		if (!isValidBlade(main))
			return false;
		if (!matchesName(main, requiredBladeMain))
			return false;
		if (!matchesCount(main, requiredBladeMain))
			return false;
		
		return true;
	}


	private static boolean matchesName(ItemStack slot, ItemStack required)	{
		return slot.getUnlocalizedName().equals(required.getUnlocalizedName());
	}

	private static boolean matchesCount(ItemStack slot, ItemStack required)	{
		NBTTagCompound tagSlot = ItemSlashBlade.getItemTagCompound(slot);
		NBTTagCompound tagReq  = ItemSlashBlade.getItemTagCompound(required);
		return
			tagValueCompare(ItemSlashBlade.ProudSoul, tagSlot, tagReq) >= 0 &&
			tagValueCompare(ItemSlashBlade.KillCount, tagSlot, tagReq) >= 0 &&
			tagValueCompare(ItemSlashBlade.RepairCount, tagSlot, tagReq) >= 0;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1)
	{
		ItemStack result = super.getCraftingResult(var1);
		ItemStack curIs = var1.getStackInRowAndColumn(1, 1);
		if (!isValidBlade(curIs))			return result;
		NBTTagCompound oldTag = curIs.getTagCompound();
		oldTag = (NBTTagCompound)oldTag.copy();
		NBTTagCompound newTag = ItemSlashBlade.getItemTagCompound(result);
		if (ItemSlashBladeNamed.CurrentItemName.exists(newTag)) {
			String key = ItemSlashBladeNamed.CurrentItemName.get(newTag);
			ItemStack tmp = SlashBlade.getCustomBlade(key);
			if (!tmp.isEmpty())
				result = tmp;
		}
		newTag = ItemSlashBlade.getItemTagCompound(result);
		ItemSlashBlade.KillCount.set(newTag, ItemSlashBlade.KillCount.get(oldTag));
		ItemSlashBlade.ProudSoul.set(newTag, ItemSlashBlade.ProudSoul.get(oldTag));
		ItemSlashBlade.RepairCount.set(newTag, ItemSlashBlade.RepairCount.get(oldTag));

		if (oldTag.hasUniqueId("Owner"))
			newTag.setUniqueId("Owner", oldTag.getUniqueId("Owner"));

		if(oldTag.hasKey(ItemSlashBlade.adjustXStr))
			newTag.setFloat(ItemSlashBlade.adjustXStr, oldTag.getFloat(ItemSlashBlade.adjustXStr));

		if(oldTag.hasKey(ItemSlashBlade.adjustYStr))
			newTag.setFloat(ItemSlashBlade.adjustYStr, oldTag.getFloat(ItemSlashBlade.adjustYStr));

		if(oldTag.hasKey(ItemSlashBlade.adjustZStr))
			newTag.setFloat(ItemSlashBlade.adjustZStr, oldTag.getFloat(ItemSlashBlade.adjustZStr));

		{
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
						if (curEnchantIndex != enchantIndex && !enchantment.isCompatibleWith(curEnchantIndex))
						{
							canApplyFlag = false;
							break;
						}
					}
					if (canApplyFlag)	newItemEnchants.put(enchantIndex, Integer.valueOf(srcLevel));
				}

			}
			EnchantmentHelper.setEnchantments(newItemEnchants, result);
		}
		
		return result;

	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		if (!remainedBladeSub&&!remainedBladeTri) return super.getRemainingItems(inv);
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < ret.size(); i++) {
        	if(remainedBladeSub){
			ItemStack slot = inv.getStackInSlot(i);
			if (isValidBlade(slot) && matchesName(slot, requiredBladeSub))
				ret.set(i, slot.copy());
			else
				ret.set(i, ForgeHooks.getContainerItem(inv.getStackInSlot(i)));
			}
        	if(remainedBladeTri){
        		ItemStack slot = inv.getStackInSlot(i);
    			if (isValidBlade(slot) && matchesName(slot, requiredBladeTri))
    				ret.set(i, slot.copy());
    			else
    				ret.set(i, ForgeHooks.getContainerItem(inv.getStackInSlot(i)));
        	}
        }
        return ret;
	}
	
}
