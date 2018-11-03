package net.langball.lastsmith.blade.others;

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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_SilverBambooLight_gold {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.silverbamboolight_gold";
	      String name1 = "flammpfeil.slashblade.named.silverbamboolight_nice";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 7.0F + ToolMaterial.STONE.getAttackDamage());
	      ItemSlashBlade.TextureName.set(tag, "named/silverbamboolight/silverbamboo_gold");
	      ItemSlashBlade.ModelName.set(tag, "named/yamato");
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired =BladeLoader.findItemStack(Last_worker.MODID, name1, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(200));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(100));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade((new ResourceLocation(SlashBlade.modid,name)),louguan, custombladeReqired, new Object[]{ " AD", "ADA", "BA ", Character.valueOf('A'), ingot, Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(Blocks.GOLD_BLOCK)}));
	     
	   }

}
