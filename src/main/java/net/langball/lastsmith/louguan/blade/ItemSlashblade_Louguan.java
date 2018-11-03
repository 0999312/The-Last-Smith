package net.langball.lastsmith.louguan.blade;

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
import net.langball.lastsmith.sa.SAxianshizhan;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Louguan {
	@SubscribeEvent
	   public void init(InitEvent event) {
		   ItemSlashBlade.specialAttacks.put(Integer.valueOf(159), new SAxianshizhan());
	      String name = "flammpfeil.slashblade.named.roukan";
	      String namefake = "flammpfeil.slashblade.named.louguan_old2";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 4.0F + ToolMaterial.IRON.getAttackDamage()+1);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/louguan/texture");
	      ItemSlashBlade.ModelName.set(tag, "named/louguan/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(159));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 4);
	      customblade.addEnchantment(Enchantments.SHARPNESS, 5);
	      ItemStack custombladeReqired = BladeLoader.findItemStack(Last_worker.MODID,namefake,1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(15000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe("fucking_recipessssssssssssssssssss", new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"fucking_recipessssssssssssssssssss"),louguan, custombladeReqired, new Object[]{"DAD", "CBC", "DCD", Character.valueOf('A'), ingot, Character.valueOf('B'), custombladeReqired, Character.valueOf('C'), itemSphereBladeSoul, Character.valueOf('D'), new ItemStack(Blocks.DIAMOND_BLOCK)}));//标准的合成表

	   }

}
