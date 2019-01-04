package net.langball.lastsmith.blade.smith;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Smith2 {
	@SubscribeEvent
	   public void init(InitEvent event) {
		    String name = "flammpfeil.slashblade.named.smith_2";
	      String name2 = "flammpfeil.slashblade.named.smith_1";
	      ItemStack customblade = new ItemStack(BladeLoader.blade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(95));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 10.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/smith/smith_1");
	      ItemSlashBlade.ModelName.set(tag, "named/smith/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(0));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired =BladeLoader.findItemStack(Last_worker.MODID, name2, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(250));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(1));
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),customblade, custombladeReqired, new Object[]{" DA", "DA ", "B  ", Character.valueOf('A'), ingot, Character.valueOf('B'), custombladeReqired,Character.valueOf('D'), new ItemStack(Blocks.COAL_BLOCK)}));
	   }

}
