package net.langball.lastsmith.compat.blades;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Scorn_3 {
	   @SubscribeEvent
	   public void initFake(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.Scorn.fake";
	      String name2 = "flammpfeil.slashblade.named.Scorn.rust";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(40));
	      ItemSlashBladeNamedSS.IsFakeBlade.set(tag, true);
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 6.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/Scorn/texture_0");
	      ItemSlashBlade.ModelName.set(tag, "named/Scorn/model");
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired = BladeLoader.findItemStack(Last_worker.MODID, name2, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(50));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(1));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);

	      ItemStack proudSoul = SlashBlade.findItemStack("flammpfeil.slashblade",SlashBlade.ProudSoulStr, 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),
	    		  louguan, custombladeReqired, new Object[]{
	    				  "ADA", "CBC", "ADA", 
	    				  Character.valueOf('A'), proudSoul
	    				, Character.valueOf('B'), custombladeReqired
	    				, Character.valueOf('C'), "ingotIron"
	    				, Character.valueOf('D'), new ItemStack(ItemLoader.material,1,7)}));
	
	   }
	 
}
