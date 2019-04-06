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

public class ItemSlashblade_Yamazakura_Katura {
	@SubscribeEvent
	   public void init(InitEvent event) {
		  String name = "flammpfeil.slashblade.named.yamazakura.katura";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(31));
	      ItemSlashBlade.setBaseAttackModifier(tag, 7.0F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/yamazakura/yamazakura_kannazuki_katura");
	      ItemSlashBlade.ModelName.set(tag, "named/yamazakura/kannazuki");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.UNBREAKING, 3);
	      customblade.addEnchantment(Enchantments.KNOCKBACK, 2);
	      ItemStack reqiredMain = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.yamazakura.hazuki");
	      ItemStack reqiredSub = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.yamazakura.kiku");
	      ItemStack sb = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.yamazakura.fumizuki");
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(sb);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(5000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
	      
	      SlashBlade.addRecipe(name, new RecipeBx(new ResourceLocation(name),
	    		  BladeLoader.getCustomBlade(name),
	    		  sb, 1, 1,
	    		  reqiredMain, 0, 1, false,
	    		  reqiredSub, 2, 1, false, new Object[]{
	    		    "GGG", "ABC", "GGG",
	    			'G',"blockSakura",
	    			'A',reqiredMain,
	    			'B',sb,
	    			'C',reqiredSub
	    		  }
	      ));
	   }

}
