package net.langball.lastsmith.louguan.blade;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.sa.SAbailou1;
import net.langball.lastsmith.sa.SAxianshizhan3;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Bailou_xf {

	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.bailou_xf";
	      String name1 = "flammpfeil.slashblade.named.bailou";
		  ItemSlashBlade.specialAttacks.put(Integer.valueOf(765432233), new SAxianshizhan3());
	      ItemStack customblade = new ItemStack(BladeLoader.windBlade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(40));
	      ItemSlashBlade.setBaseAttackModifier(tag, 3.0F + ToolMaterial.WOOD.getAttackDamage());
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/bailou/texture_xf");
	      ItemSlashBlade.ModelName.set(tag, "named/bailou/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(765432233));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 4);
	      customblade.addEnchantment(Enchantments.SMITE, 12);
	      customblade.addEnchantment(Enchantments.PUNCH, 5);
	      ItemStack custombladeReqired = new ItemStack(BladeLoader.blade);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	   }

}
