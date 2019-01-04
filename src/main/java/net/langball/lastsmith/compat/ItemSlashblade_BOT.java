package net.langball.lastsmith.compat;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_BOT {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.yingjian";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(91));
	      ItemSlashBlade.setBaseAttackModifier(tag, 7.0f);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/BOTlouguan/texture");
	      ItemSlashBlade.ModelName.set(tag, "named/BOTlouguan/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(56709));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 8);
	      customblade.addEnchantment(Enchantments.SHARPNESS,2);
	      customblade.addEnchantment(Enchantments.SMITE, 2);
	      NBTTagCompound etag = new NBTTagCompound();
	      tag.setTag("SB.SEffect", etag);
	      etag.setInteger("ManaRepair", 1);
	   }
	   

}
