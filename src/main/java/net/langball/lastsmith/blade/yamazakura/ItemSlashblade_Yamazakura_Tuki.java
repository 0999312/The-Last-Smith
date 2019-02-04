package net.langball.lastsmith.blade.yamazakura;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeBx;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Yamazakura_Tuki {
	@SubscribeEvent
	   public void init(InitEvent event) {
		  String name = "flammpfeil.slashblade.named.yamazakura.tuki";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(366));
	      ItemSlashBlade.setBaseAttackModifier(tag, 12.0F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/yamazakura/yamazakura_kamuy_tuki");
	      ItemSlashBlade.ModelName.set(tag, "named/yamazakura/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(40));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 2);
	      customblade.addEnchantment(Enchantments.UNBREAKING, 4);
	      customblade.addEnchantment(Enchantments.KNOCKBACK, 3);
	      
	      ItemStack reqiredMain = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.yamazakura.shiwasu");
	      ItemStack reqiredSub = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.yamazakura.shimotsuki");
	      ItemStack sb = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.yamazakura.katura");
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(sb);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(2000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(10000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(10));
	      
	      SlashBlade.addRecipe(name, new RecipeBx(new ResourceLocation(name),
	    		  BladeLoader.getCustomBlade(name),
	    		  sb, 1, 1,
	    		  reqiredMain, 0, 1, false,
	    		  reqiredSub, 2, 1, false, new Object[]{
	    		    "GGG", "ABC", "GGG",
	    			'G',"sphereSakura",
	    			'A',reqiredMain,
	    			'B',sb,
	    			'C',reqiredSub
	    		  }
	      ));
	   }

}
