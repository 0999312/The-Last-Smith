package cn.mmf.lastsmith.recipe;

import java.util.Iterator;
import java.util.Map;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.advancement.AdvancementHelper;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;

import mods.flammpfeil.slashblade.TagPropertyAccessor;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

import net.minecraft.item.ItemStack;

import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.util.NonNullList;

import net.minecraft.world.World;

import net.minecraftforge.common.ForgeHooks;

import net.minecraftforge.oredict.ShapedOreRecipe;

import net.minecraft.enchantment.Enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RecipeTriBladeTLS extends ShapedOreRecipe {

	private final String name;
	private final ItemStack requiredBladeMain;
	private final ItemStack requiredBladeSub;
	private final boolean remainedBladeSub;
	private final ItemStack requiredBladeTri;
	private final boolean remainedBladeTri;
	private final int posXMain;
	private final int posYMain;
	private final int posXSub;
	private final int posYSub;
	private final int posXTri;
	private final int posYTri;

	public RecipeTriBladeTLS(ResourceLocation group, String name, ItemStack result, ItemStack requiredBladeMain, int posXMain,
			int posYMain, ItemStack requiredBladeSub, int posXSub, int posYSub, boolean remainedBladeSub,
			ItemStack requiredBladeTri, int posXTri, int posYTri, boolean remainedBladeTri, Object... recipe) {
		super(group, result, recipe);
		this.name = name;
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

	protected static int tagValueCompare(TagPropertyAccessor.TagPropertyInteger access, NBTTagCompound tag1,
			NBTTagCompound tag2) {
		return access.get(tag1).compareTo(access.get(tag2));
	}

	protected static boolean isValidBlade(ItemStack item) {
		return !item.isEmpty() && item.getItem() instanceof ItemSlashBlade && item.hasTagCompound();
	}
	
	public String getAdvancementName() {
		return name;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		boolean result = super.matches(inv, world) && isGoodForCrafting(inv, world, name);
		if (!result)
			return false;
		if (requiredBladeMain.isEmpty() || requiredBladeSub.isEmpty() || requiredBladeTri.isEmpty())
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

	private static boolean matchesName(ItemStack slot, ItemStack required) {
		return slot.getUnlocalizedName().equals(required.getUnlocalizedName());
	}

	private static boolean matchesCount(ItemStack slot, ItemStack required) {
		NBTTagCompound tagSlot = ItemSlashBlade.getItemTagCompound(slot);
		NBTTagCompound tagReq = ItemSlashBlade.getItemTagCompound(required);
		
        int require_kill_count = ItemSlashBlade.KillCount.get(tagReq);
        if(TLSConfig.advanced_mode) {
        	require_kill_count = (int) (require_kill_count * (TLSConfig.kill_count_multiplier / 100F));
        }
		
		return tagValueCompare(ItemSlashBlade.ProudSoul, tagSlot, tagReq) >= 0
				&& (ItemSlashBlade.KillCount.get(tagSlot) >= require_kill_count)
				&& tagValueCompare(ItemSlashBlade.RepairCount, tagSlot, tagReq) >= 0;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack result = super.getCraftingResult(var1);
		ItemStack curIs = var1.getStackInRowAndColumn(1, 1);
		if (!isValidBlade(curIs))
			return result;
		NBTTagCompound oldTag = curIs.getTagCompound();
		oldTag = oldTag.copy();
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

		if (oldTag.hasKey(ItemSlashBlade.adjustXStr))
			newTag.setFloat(ItemSlashBlade.adjustXStr, oldTag.getFloat(ItemSlashBlade.adjustXStr));

		if (oldTag.hasKey(ItemSlashBlade.adjustYStr))
			newTag.setFloat(ItemSlashBlade.adjustYStr, oldTag.getFloat(ItemSlashBlade.adjustYStr));

		if (oldTag.hasKey(ItemSlashBlade.adjustZStr))
			newTag.setFloat(ItemSlashBlade.adjustZStr, oldTag.getFloat(ItemSlashBlade.adjustZStr));

		{
			Map<Enchantment, Integer> newItemEnchants = EnchantmentHelper.getEnchantments(result);
			Map<Enchantment, Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(curIs);
			for (Enchantment enchantIndex : oldItemEnchants.keySet()) {
				Enchantment enchantment = enchantIndex;
				int destLevel = newItemEnchants.containsKey(enchantIndex) ? newItemEnchants.get(enchantIndex) : 0;
				int srcLevel = oldItemEnchants.get(enchantIndex);
				srcLevel = Math.max(srcLevel, destLevel);
				srcLevel = Math.min(srcLevel, enchantment.getMaxLevel());
				boolean canApplyFlag = enchantment.canApply(result);

				if (canApplyFlag) {
					for (Enchantment curEnchantIndex : newItemEnchants.keySet()) {
						if (curEnchantIndex != enchantIndex && !enchantment.isCompatibleWith(curEnchantIndex)) {
							canApplyFlag = false;
							break;
						}
					}
					if (canApplyFlag)
						newItemEnchants.put(enchantIndex, Integer.valueOf(srcLevel));
				}

			}
			EnchantmentHelper.setEnchantments(newItemEnchants, result);
		}

		return result;

	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		if (!remainedBladeSub && !remainedBladeTri)
			return super.getRemainingItems(inv);
		NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < ret.size(); i++) {
			if (remainedBladeSub) {
				ItemStack slot = inv.getStackInSlot(i);
				if (isValidBlade(slot) && matchesName(slot, requiredBladeSub))
					ret.set(i, slot.copy());
				else
					ret.set(i, ForgeHooks.getContainerItem(inv.getStackInSlot(i)));
			}
			if (remainedBladeTri) {
				ItemStack slot = inv.getStackInSlot(i);
				if (isValidBlade(slot) && matchesName(slot, requiredBladeTri))
					ret.set(i, slot.copy());
				else
					ret.set(i, ForgeHooks.getContainerItem(inv.getStackInSlot(i)));
			}
		}
		return ret;
	}

	public boolean isGoodForCrafting(InventoryCrafting inv, World world, String name) {
		if (!TLSConfig.advanced_mode)
			return true;
		if (!TLSConfig.recipe_lock_enable)
			return true;
		Container container = inv.eventHandler;
		if (container == null) {
			return false;
		}
		EntityPlayer foundPlayer = null;
		Iterator<EntityPlayer> var6 = world.playerEntities.iterator();
		while (var6.hasNext()) {
			EntityPlayer entityPlayer = var6.next();
			if (entityPlayer.openContainer == container && container.canInteractWith(entityPlayer)
					&& container.getCanCraft(entityPlayer)) {
				foundPlayer = entityPlayer;
			}
		}
		if (foundPlayer != null) {
			return AdvancementHelper.getInstance().checkAdvancement(foundPlayer, name);
		}
		return false;
	}
}
