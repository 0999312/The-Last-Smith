package net.langball.lastsmith.compat.blades.thaum;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class ItemSlashblade_ElementalFortressUpdate {
	   @SubscribeEvent
	   public void init(InitEvent event) {
		   
	      String name = "flammpfeil.slashblade.named.fortress.elemental.update";
	      ItemStack customblade = new ItemStack(BladeLoader.windBlade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag,7.0F + ThaumcraftMaterials.TOOLMAT_ELEMENTAL.getAttackDamage());
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/thaum/ModelKatanaElementalUpdate");
	      ItemSlashBlade.ModelName.set(tag, "named/smith/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(3));
	      EnumInfusionEnchantment.addInfusionEnchantment(customblade, EnumInfusionEnchantment.ARCING, 2);
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
			ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Last_worker.MODID,name), new InfusionRecipe("BLADEUPDATE", BladeLoader.findItemStack(Last_worker.MODID,name,1), 1, new AspectList()
		    	      .add(Aspect.ENERGY, 75).add(Aspect.SOUL, 75).add(Aspect.AVERSION,75),BladeLoader.getCustomBlade("flammpfeil.slashblade.named.fortress.elemental"), 
		    	      new Object[] {
		    	    		   "obsidian"
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 , "obsidian"
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 }
			));
	     
	   }

}
