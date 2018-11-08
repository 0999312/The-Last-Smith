package net.langball.lastsmith.blade.famous;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeTab;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.Yamato;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Blade_bamboo {
	   @SubscribeEvent
	   public void init(InitEvent event) {
		  String name = "wrap.BambooMod.katana_tls";
	      String name2 = "wrap.BambooMod.katana_fake";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(45));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 6.0F);
	      ItemSlashBlade.TextureName.set(tag, "BambooKatana");
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired =BladeLoader.findItemStack(Last_worker.MODID, name2, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),customblade, custombladeReqired, new Object[]{"ADA", "DBD", "AHA",Character.valueOf('H'), ItemLoader.hammer, Character.valueOf('A'),ingot, Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(ItemLoader.sakura)}));

	      ItemStack foxbladeReqired =BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      foxbladeReqired.addEnchantment(Enchantments.LOOTING,1);
	      NBTTagCompound reqTag1 = ItemSlashBlade.getItemTagCompound(foxbladeReqired);
          ItemSlashBlade.KillCount.set(reqTag1,199);
          ItemSlashBlade.ProudSoul.set(reqTag1,1000);
          ItemSlashBlade.RepairCount.set(reqTag1,1);
	      ItemStack fox = SlashBlade.findItemStack(SlashBlade.modid,"flammpfeil.slashblade.named.fox.white", 1);
	      SlashBlade.addRecipe("fox_white_tls", new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"flammpfeil.slashblade.named.fox.white"),fox, foxbladeReqired, new Object[]{"DAD", "DBD", "DHD",Character.valueOf('H'),itemSphereBladeSoul, Character.valueOf('A'),ingot, Character.valueOf('B'), foxbladeReqired, Character.valueOf('D'), new ItemStack(ItemLoader.sakura_full)}));

	      ItemStack foxblade2Reqired =BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      foxblade2Reqired.addEnchantment(Enchantments.SMITE,1);
	      NBTTagCompound reqTag2 = ItemSlashBlade.getItemTagCompound(foxbladeReqired);
          ItemSlashBlade.KillCount.set(reqTag1,199);
          ItemSlashBlade.ProudSoul.set(reqTag1,1000);
          ItemSlashBlade.RepairCount.set(reqTag1,1);
	      ItemStack fox2 = SlashBlade.findItemStack(SlashBlade.modid,"flammpfeil.slashblade.named.fox.black", 1);
	      SlashBlade.addRecipe("fox_black_tls", new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"flammpfeil.slashblade.named.fox.black"),fox2, foxblade2Reqired, new Object[]{"DAD", "DBD", "DHD",Character.valueOf('H'),itemSphereBladeSoul, Character.valueOf('A'),ingot, Character.valueOf('B'), foxblade2Reqired, Character.valueOf('D'), new ItemStack(ItemLoader.sakura)}));

	   }

}
