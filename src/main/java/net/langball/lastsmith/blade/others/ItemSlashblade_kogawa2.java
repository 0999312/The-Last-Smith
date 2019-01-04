package net.langball.lastsmith.blade.others;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_kogawa2 {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.kogawa_rare";
	      String name1 = "flammpfeil.slashblade.named.kogawa_normal";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(61));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 9.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/Kogawa/texture_rare");
	      ItemSlashBlade.ModelName.set(tag, "named/Kogawa/model");
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired =BladeLoader.findItemStack(Last_worker.MODID, name1, 1);
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"ADA", "DBD", "ADA", Character.valueOf('A'),ingot , Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(Items.GOLD_INGOT)}));
	   }

}
