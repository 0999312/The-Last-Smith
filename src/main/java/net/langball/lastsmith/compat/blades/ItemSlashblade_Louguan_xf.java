package net.langball.lastsmith.compat.blades;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeTab;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.Yamato;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.InfusionRecipeAwakeBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class ItemSlashblade_Louguan_xf {
	   @SubscribeEvent
	   public void init(InitEvent event) {
		   
	      String name = "flammpfeil.slashblade.named.roukan_xf";
	      String name2 = "flammpfeil.slashblade.named.roukan";
	      ItemStack customblade = new ItemStack(BladeLoader.windBlade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 6.0F + ToolMaterial.IRON.getAttackDamage());
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/louguan/texture_xf");
	      ItemSlashBlade.ModelName.set(tag, "named/louguan/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 3);
	      customblade.addEnchantment(Enchantments.SHARPNESS, 7);
	      EnumInfusionEnchantment.addInfusionEnchantment(customblade, EnumInfusionEnchantment.ARCING, 3);
	      ItemStack blade=BladeLoader.findItemStack(Last_worker.MODID,name2,1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(blade);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(2500));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(25000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
	      ItemStack blade1 = blade.copy();
	      blade1.setItemDamage(OreDictionary.WILDCARD_VALUE);
			ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(SlashBlade.modid,name), new InfusionRecipeAwakeBlade("ROUKANSENPUUZIN",BladeLoader.findItemStack(Last_worker.MODID,name,1), 1, new AspectList()
		    	      .add(Aspect.ENERGY, 30).add(Aspect.SOUL, 30).add(Aspect.AVERSION,30),blade1, 
		    	      new Object[] {
		    	    		  SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 ,SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 ,SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 ,SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , new ItemStack(ItemsTC.nuggets, 1, 10)
		    	    		 , ConfigItems.AIR_CRYSTAL
		    	    		 }
			));
			ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation(SlashBlade.modid,name+"_fake"), new InfusionRecipe("ROUKANSENPUUZIN", BladeLoader.findItemStack(Last_worker.MODID,name,1), 1, new AspectList()
		    	      .add(Aspect.ENERGY, 30).add(Aspect.SOUL, 30).add(Aspect.AVERSION,30),blade, 
		    	      new Object[] {
		    	    		  SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 ,SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1)
		    	    		 ,SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 ,SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1)
		    	    		 , new ItemStack(ItemsTC.nuggets, 1, 10)
		    	    		 , ConfigItems.AIR_CRYSTAL
		    	    		 }
			));

	   }

}
