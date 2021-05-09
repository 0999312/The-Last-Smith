package cn.mmf.lastsmith.recipe;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.Iterator;
import java.util.Map;

import cn.mmf.lastsmith.TLSConfig;
import cn.mmf.lastsmith.advancement.AdvancementHelper;
import cn.mmf.lastsmith.util.BladeUtil;

public class RecipeAwakeBladeTLS extends ShapedOreRecipe {

    private final ItemStack requiredStateBlade;
    private final String name;
    public RecipeAwakeBladeTLS(ResourceLocation loc,String advancement,ItemStack result, ItemStack requiredStateBlade, Object... recipe) {
        super(loc, result, recipe);
        this.requiredStateBlade = requiredStateBlade;
        this.name = advancement;
    }
	public String getAdvancementName() {
		return name;
	}
    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        boolean result = super.matches(inv, world) && isGoodForCrafting(inv, world, name);
        if(result && !requiredStateBlade.isEmpty()){
        	requiredStateBlade.setItemDamage(OreDictionary.WILDCARD_VALUE);
            for(int idx = 0; idx < inv.getSizeInventory(); idx++){
                ItemStack curIs = inv.getStackInSlot(idx);
                if(!curIs.isEmpty()&& curIs.getItem() instanceof ItemSlashBlade && curIs.hasTagCompound()){
                    Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(requiredStateBlade);
                    for(Map.Entry<Enchantment,Integer> enchant: oldItemEnchants.entrySet()) {
                        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getKey(),curIs);
                        if(level < enchant.getValue()){
                            return false;
                        }
                    }

                    NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(requiredStateBlade);
                    NBTTagCompound srcTag = ItemSlashBlade.getItemTagCompound(curIs);

                    if(!curIs.getUnlocalizedName().equals(requiredStateBlade.getUnlocalizedName()))
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
                    break;
                }
            }
        }
        return result;
    }

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack result = super.getCraftingResult(var1);
		for(int idx = 0; idx < var1.getSizeInventory(); idx++){
			ItemStack curIs = var1.getStackInSlot(idx);
			if(!curIs.isEmpty() && curIs.getItem() instanceof ItemSlashBlade && curIs.hasTagCompound()){

                NBTTagCompound oldTag = curIs.getTagCompound();
				oldTag = oldTag.copy();

                {
                    NBTTagCompound newTag = ItemSlashBlade.getItemTagCompound(result);

                    if(ItemSlashBladeNamed.CurrentItemName.exists(newTag)){
                        ItemStack tmp;
                        String key = ItemSlashBladeNamed.CurrentItemName.get(newTag);
                        tmp = SlashBlade.getCustomBlade(key);

                        if(!tmp.isEmpty())
                            result = tmp.copy();
                    }
                }

                NBTTagCompound newTag;
                newTag = ItemSlashBlade.getItemTagCompound(result);

                ItemSlashBlade.KillCount.set(newTag, ItemSlashBlade.KillCount.get(oldTag));
                ItemSlashBlade.ProudSoul.set(newTag, ItemSlashBlade.ProudSoul.get(oldTag));
                ItemSlashBlade.RepairCount.set(newTag, ItemSlashBlade.RepairCount.get(oldTag));

                if(oldTag.hasUniqueId("Owner"))
                    newTag.setUniqueId("Owner",oldTag.getUniqueId("Owner"));
                if(BladeUtil.getInstance().IsBewitchedActived.exists(oldTag))
                	BladeUtil.getInstance().IsBewitchedActived.set(newTag, BladeUtil.getInstance().IsBewitchedActived.get(oldTag));
                if(oldTag.hasKey(ItemSlashBlade.adjustXStr))
                    newTag.setFloat(ItemSlashBlade.adjustXStr,oldTag.getFloat(ItemSlashBlade.adjustXStr));
                if(oldTag.hasKey(ItemSlashBlade.adjustYStr))
                    newTag.setFloat(ItemSlashBlade.adjustYStr,oldTag.getFloat(ItemSlashBlade.adjustYStr));
                if(oldTag.hasKey(ItemSlashBlade.adjustZStr))
                    newTag.setFloat(ItemSlashBlade.adjustZStr,oldTag.getFloat(ItemSlashBlade.adjustZStr));

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
                }
			}
		}
		return result;
	}
    public boolean isGoodForCrafting(InventoryCrafting inv,World world,String name) {
		if (!TLSConfig.advanced_mode)
			return true;
		if (!TLSConfig.recipe_lock_enable)
			return true;
        Container container = inv.eventHandler;
        if(container == null) {
            return false;
        }
        EntityPlayer foundPlayer = null;
        Iterator<EntityPlayer> var6 = world.playerEntities.iterator();
        while(var6.hasNext()) {
            EntityPlayer entityPlayer = var6.next();
            if(entityPlayer.openContainer == container && container.canInteractWith(entityPlayer) && container.getCanCraft(entityPlayer)) {
                foundPlayer = entityPlayer;
            }
        }
        if(foundPlayer != null) {
            return AdvancementHelper.getInstance().checkAdvancement(foundPlayer, name);
        }
		return false;
    }

}
