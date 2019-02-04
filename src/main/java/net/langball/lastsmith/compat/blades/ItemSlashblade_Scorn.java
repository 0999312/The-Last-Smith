package net.langball.lastsmith.compat.blades;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Scorn {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.Scorn";
	      String name2 = "flammpfeil.slashblade.named.Scorn.seald";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(45));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 10.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/Scorn/texture");
	      ItemSlashBlade.ModelName.set(tag, "named/Scorn/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(5));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.UNBREAKING,2);
	      customblade.addEnchantment(Enchantments.SHARPNESS,6);
	      customblade.addEnchantment(Enchantments.SMITE,2);
	      customblade.addEnchantment(Enchantments.POWER,4);

	      ItemStack custombladeReqired = BladeLoader.findItemStack(Last_worker.MODID, name2, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(50000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(10));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      
	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name),
	    		  louguan, custombladeReqired, new Object[]{
	    				  "ACD", "CBC", "DCA", 
	    				  Character.valueOf('A'), "ingotSakura"
	    				, Character.valueOf('B'), custombladeReqired
	    				, Character.valueOf('C'), itemSphereBladeSoul
	    				, Character.valueOf('D'), new ItemStack(ItemLoader.material,1,8)}));
	   }

}
