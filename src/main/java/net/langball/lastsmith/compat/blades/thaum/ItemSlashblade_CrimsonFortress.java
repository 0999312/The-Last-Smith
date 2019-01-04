package net.langball.lastsmith.compat.blades.thaum;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ConfigItems;

public class ItemSlashblade_CrimsonFortress {
	   @SubscribeEvent
	   public void init(InitEvent event) {
		   
	      String name = "flammpfeil.slashblade.named.fortress.crimson";
	      ItemStack customblade = new ItemStack(BladeLoader.crimsonBlade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 5.0F + ToolMaterial.IRON.getAttackDamage());
	      ItemSlashBladeNamedSS.IsFakeBlade.set(tag, true);
	      ItemSlashBlade.TextureName.set(tag, "named/thaum/ModelKatanaCrimson");
	      ItemSlashBlade.ModelName.set(tag, "named/smith/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
			ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Last_worker.MODID,name), new InfusionRecipe("THAUMBLADE", BladeLoader.findItemStack(Last_worker.MODID,name,1), 1, new AspectList()
		    	      .add(Aspect.ENERGY, 50).add(Aspect.SOUL, 50).add(Aspect.AVERSION,50),ItemsTC.elementalSword, 
		    	      new Object[] {
		    	    		   "obsidian"
		    	    		 , ConfigItems.FLUX_CRYSTAL
		    	    		 , "ingotVoid"
		    	    		 , "ingotGold"
		    	    		 , "gemEmerald"
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , "ingotVoid"
		    	    		 , ConfigItems.FLUX_CRYSTAL
		    	    		 , "obsidian"
		    	    		 , ConfigItems.FLUX_CRYSTAL
		    	    		 , "ingotVoid"
		    	    		 , "ingotGold"
		    	    		 , "gemEmerald"
		    	    		 , SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , "ingotVoid"
		    	    		 , ConfigItems.FLUX_CRYSTAL
		    	    		 }
			));
	   }

}
