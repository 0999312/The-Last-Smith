package net.langball.lastsmith.blade.others;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_BambooLight_yin {
	@SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.bamboolight_yin";
	      String name1 = "flammpfeil.slashblade.named.bamboolight_nice";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(120));
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.setBaseAttackModifier(tag, 9.0F + ToolMaterial.IRON.getAttackDamage());
	      ItemSlashBlade.TextureName.set(tag, "named/bamboolight/bamboo_yin");
	      ItemSlashBlade.ModelName.set(tag, "named/yamato");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(270));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 8);
	      customblade.addEnchantment(Enchantments.SMITE, 4);
	      customblade.addEnchantment(Enchantments.SHARPNESS, 3);
	      customblade.addEnchantment(Enchantments.KNOCKBACK, 3);
	      ItemStack custombladeReqired = BladeLoader.findItemStack(Last_worker.MODID, name1, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(2000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(10000));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"DFD", "FBF", "AFA", Character.valueOf('A'), ingot, Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(Items.ENDER_PEARL), Character.valueOf('F'), "sphereSakura"}));
	   }

}
