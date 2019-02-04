package net.langball.lastsmith.blade.yamazakura;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.event.DropEventHandler;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Yamazakura_Aoi {
	@SubscribeEvent
	   public void init(InitEvent event) {
		  String name = "flammpfeil.slashblade.named.yamazakura.aoi_fake";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(29));
	      ItemSlashBladeNamedSS.IsFakeBlade.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 6.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/yamazakura/yamazakura_aoi");
	      ItemSlashBlade.ModelName.set(tag, "named/yamazakura/model_fake");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(0));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      DropEventHandler.registerEntityDrop(new ResourceLocation("minecraft","vindication_illager"), 0.01f, BladeLoader.findItemStack(Last_worker.MODID, name, 1));
	      
	   }

		@SubscribeEvent
	   public void initFull(InitEvent event) {
		  String name = "flammpfeil.slashblade.named.yamazakura.aoi";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(30));
	      ItemSlashBlade.setBaseAttackModifier(tag, 8.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/yamazakura/yamazakura_aoi");
	      ItemSlashBlade.ModelName.set(tag, "named/yamazakura/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(40));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired =BladeLoader.getCustomBlade("flammpfeil.slashblade.named.yamazakura.aoi_fake");
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(100));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(10));
	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name)
	    		  ,customblade, custombladeReqired, 
	    		  new Object[]{
	    				  "ADA",
	    				  "DBD",
	    				  "ADA",
	    				  Character.valueOf('A'), "ingotSakura",
	    				  Character.valueOf('B'), custombladeReqired,
	    				  Character.valueOf('D'), "sphereSakura",
	    				  }));
	   }

}
