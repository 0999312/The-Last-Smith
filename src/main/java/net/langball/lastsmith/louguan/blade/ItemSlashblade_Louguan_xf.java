package net.langball.lastsmith.louguan.blade;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeTab;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.Yamato;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSlashblade_Louguan_xf {
	   @SubscribeEvent
	   public void init(InitEvent event) {
		   
	      String name = "flammpfeil.slashblade.named.roukan_xf";
	      String name2 = "flammpfeil.slashblade.named.roukan";
	      ItemStack customblade = new ItemStack(BladeLoader.windBlade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 6.0F + ToolMaterial.IRON.getAttackDamage());
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/louguan/texture_xf");
	      ItemSlashBlade.ModelName.set(tag, "named/louguan/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(765432233));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 6);
	      customblade.addEnchantment(Enchantments.SHARPNESS, 7);
	      customblade.addEnchantment(Enchantments.PUNCH, 5);

	   }

}
