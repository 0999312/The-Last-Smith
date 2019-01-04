package net.langball.lastsmith.blade.famous;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Blade_basic {

	   @SubscribeEvent
	   public void init(InitEvent event) {
		  String name = "flammpfeil.slashblade.named.namedblade_basic";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(90));
	      ItemSlashBladeNamedSS.IsFakeBlade.set(tag, true);
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 7.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/oldtime/2");
	      ItemSlashBlade.ModelName.set(tag, "named/oldtime/soda");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(5));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired =new ItemStack(BladeLoader.blade);

	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),customblade, custombladeReqired, new Object[]{" DA", "DA ", "B H", Character.valueOf('A'), "ingotSteel", Character.valueOf('B'), custombladeReqired, Character.valueOf('H'), ItemLoader.hammer, Character.valueOf('D'),"logWood"}));

	   }

}
