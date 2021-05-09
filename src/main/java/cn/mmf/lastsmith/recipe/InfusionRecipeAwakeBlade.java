package cn.mmf.lastsmith.recipe;

import java.util.List;
import java.util.Map;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.blades.BladeLoader;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemProudSoul;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.crafting.InfusionRecipe;

public class InfusionRecipeAwakeBlade extends InfusionRecipe {
	ItemStack requiredStateBlade = ItemStack.EMPTY;

	public InfusionRecipeAwakeBlade(String research, Object outputResult, int inst, AspectList aspects2,
			ItemStack centralItem, Object[] recipe) {
		super(research, outputResult, inst, aspects2, centralItem, recipe);
		this.sourceInput = CraftingHelper.getIngredient(centralItem);
		this.components.clear();
		for (Object in : recipe) {
			Ingredient ing = ThaumcraftApiHelper.getIngredient(in);
			if (in != null && in instanceof ItemStack) {
				if (((ItemStack) in).getItem() instanceof ItemSlashBlade
						|| ((ItemStack) in).getItem() instanceof ItemProudSoul)
					ing = CraftingHelper.getIngredient(in);
			}
			if (ing != null) {
				this.components.add(ing);
			} else {
				String ret = "Invalid infusion recipe: ";
				for (Object tmp : recipe)
					ret = ret + tmp + ", ";
				ret = ret + outputResult;
				throw new RuntimeException(ret);
			}
		}
		this.requiredStateBlade = centralItem;
	}

	@Override
	public Object getRecipeOutput(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
		Object result = super.getRecipeOutput(player, input, comps);
		if (!input.isEmpty() && input.getItem() instanceof ItemSlashBlade && input.hasTagCompound()) {
			NBTTagCompound oldTag = input.getTagCompound();
			oldTag = oldTag.copy();

			{
				NBTTagCompound newTag;
				newTag = ItemSlashBlade.getItemTagCompound((ItemStack) result);

				if (ItemSlashBladeNamed.CurrentItemName.exists(newTag)) {
					ItemStack tmp;
					String key = ItemSlashBladeNamed.CurrentItemName.get(newTag);
					tmp = BladeLoader.getInstance().getCustomBlade(key);

					if (!tmp.isEmpty())
						result = tmp;
				}
			}

			NBTTagCompound newTag;
			newTag = ItemSlashBlade.getItemTagCompound((ItemStack) result);

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
				Map<Enchantment, Integer> newItemEnchants = EnchantmentHelper.getEnchantments((ItemStack) result);
				Map<Enchantment, Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(input);
				for (Enchantment enchantIndex : oldItemEnchants.keySet()) {
					Enchantment enchantment = enchantIndex;

					int destLevel = newItemEnchants.containsKey(enchantIndex) ? newItemEnchants.get(enchantIndex) : 0;
					int srcLevel = oldItemEnchants.get(enchantIndex);

					srcLevel = Math.max(srcLevel, destLevel);
					srcLevel = Math.min(srcLevel, enchantment.getMaxLevel());

					boolean canApplyFlag = enchantment.canApply((ItemStack) result);
					if (canApplyFlag) {
						for (Enchantment curEnchantIndex : newItemEnchants.keySet()) {
							if (curEnchantIndex != enchantIndex
									&& !enchantment.isCompatibleWith(curEnchantIndex) /* canApplyTogether */) {
								canApplyFlag = false;
								break;
							}
						}
						if (canApplyFlag)
							newItemEnchants.put(enchantIndex, Integer.valueOf(srcLevel));
					}
				}
				EnchantmentHelper.setEnchantments(newItemEnchants, (ItemStack) result);
			}
		}
		return result;
	}

	@Override
	public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
		if (!ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(this.research))
			return false;
		
		if (RecipeMatcher.findMatches(input, getComponents()) == null)
			return false;
		
		if (!central.isEmpty() && central.getItem() instanceof ItemSlashBlade && central.hasTagCompound()) {
			Map<Enchantment, Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(requiredStateBlade);
			for (Map.Entry<Enchantment, Integer> enchant : oldItemEnchants.entrySet()) {
				int level = EnchantmentHelper.getEnchantmentLevel(enchant.getKey(), central);
				if (level < enchant.getValue()) {
					return false;
				}
			}
			requiredStateBlade.setItemDamage(Short.MAX_VALUE);
			NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(requiredStateBlade);
			NBTTagCompound srcTag = ItemSlashBlade.getItemTagCompound(central);

			if (!central.getUnlocalizedName().equals(requiredStateBlade.getUnlocalizedName()))
				return false;
			
            int require_kill_count = ItemSlashBlade.KillCount.get(reqTag);
            if(TLSConfig.advanced_mode) {
            	require_kill_count *= (TLSConfig.kill_count_multiplier/100);
            }
            
            if(ItemSlashBlade.ProudSoul.get(reqTag) > ItemSlashBlade.ProudSoul.get(srcTag))
                return false;
            if(require_kill_count > ItemSlashBlade.KillCount.get(srcTag))
                return false;
            if(ItemSlashBlade.RepairCount.get(reqTag) > ItemSlashBlade.RepairCount.get(srcTag))
                return false;
            
		} else {
			return false;
		}
		return true;
	}

}
