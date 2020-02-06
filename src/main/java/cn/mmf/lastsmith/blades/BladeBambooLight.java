package cn.mmf.lastsmith.blades;

import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeBambooLight {
	@SubscribeEvent
	public static void BambooRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag_top = new NBTTagCompound();
		customblade.setTagCompound(tag_top);
		ItemSlashBladeNamed.CurrentItemName.set(tag_top, "flammpfeil.slashblade.named.bamboolight_top");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag_top, 60);
		ItemSlashBlade.setBaseAttackModifier(tag_top, 7.0F);
		ItemSlashBlade.TextureName.set(tag_top, "named/bamboolight/bamboo_top");
		ItemSlashBlade.ModelName.set(tag_top, "blade");

		BladeLoader.registerCustomItemStack("flammpfeil.slashblade.named.bamboolight_top", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.bamboolight_top");
		
		ItemStack customblade_recluse = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade_recluse.setTagCompound(tag);
		customblade_recluse.addEnchantment(Enchantments.POWER, 1);
		customblade_recluse.addEnchantment(Enchantments.SMITE, 1);
		customblade_recluse.addEnchantment(Enchantments.SHARPNESS, 2);
		ItemSlashBladeNamed.CurrentItemName.set(tag, "flammpfeil.slashblade.named.bamboolight_recluse");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, 70);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
		BladeUtil.IsBewitchedActived.set(tag, true);
		ItemSlashBlade.setBaseAttackModifier(tag, 8.0F);
		ItemSlashBlade.TextureName.set(tag, "named/bamboolight/bamboo_top");
		ItemSlashBlade.ModelName.set(tag, "named/yamato");

		BladeLoader.registerCustomItemStack("flammpfeil.slashblade.named.bamboolight_recluse", customblade_recluse);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.bamboolight_recluse");
	}
	@SubscribeEvent
	public static void SliverBambooRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag_top = new NBTTagCompound();
		customblade.setTagCompound(tag_top);
		ItemSlashBladeNamed.CurrentItemName.set(tag_top, "flammpfeil.slashblade.named.silverbamboolight_top");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag_top, 60);
		ItemSlashBlade.setBaseAttackModifier(tag_top, 7.0F);
		ItemSlashBlade.TextureName.set(tag_top, "named/bamboolight/silverbamboolight_top");
		ItemSlashBlade.ModelName.set(tag_top, "named/yamato");

		BladeLoader.registerCustomItemStack("flammpfeil.slashblade.named.silverbamboolight_top", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.silverbamboolight_top");
		
		ItemStack customblade_recluse = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade_recluse.setTagCompound(tag);
		customblade_recluse.addEnchantment(Enchantments.POWER, 1);
		customblade_recluse.addEnchantment(Enchantments.SMITE, 1);
		customblade_recluse.addEnchantment(Enchantments.SHARPNESS, 2);
		ItemSlashBladeNamed.CurrentItemName.set(tag, "flammpfeil.slashblade.named.silverbamboolight_blood");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, 70);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
		BladeUtil.IsBewitchedActived.set(tag, true);
		ItemSlashBlade.setBaseAttackModifier(tag, 8.0F);
		ItemSlashBlade.TextureName.set(tag, "named/bamboolight/silverbamboo_blood");
		ItemSlashBlade.ModelName.set(tag, "named/yamato");

		BladeLoader.registerCustomItemStack("flammpfeil.slashblade.named.silverbamboolight_blood", customblade_recluse);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.silverbamboolight_blood");
	}
	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		
	}
}