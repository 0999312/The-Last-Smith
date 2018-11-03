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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Louguan_old2 {
	   @SubscribeEvent
	   public void init(InitEvent event) {
		    String name = "flammpfeil.slashblade.named.louguan_old";
	      String name2 = "flammpfeil.slashblade.named.louguan_old2";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 9.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/oldlouguan/texture_2");
	      ItemSlashBlade.ModelName.set(tag, "named/agito");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(100));
	      BladeLoader.registerCustomItemStack(name2, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name2);
	      ItemStack custombladeReqired =BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(750));
	      ItemStack louguan =BladeLoader.findItemStack(Last_worker.MODID, name2, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name2, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name2),customblade, custombladeReqired, new Object[]{"ADA", "DBD", "ADA", Character.valueOf('A'), ingot, Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(Blocks.LAPIS_BLOCK)}));
	   }

}
