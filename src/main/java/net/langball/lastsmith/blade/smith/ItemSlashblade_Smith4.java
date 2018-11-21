package net.langball.lastsmith.blade.smith;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeTab;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.Yamato;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.RecipeBx;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.sa.Sakuya2;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Smith4 {
		@SubscribeEvent
	   public void init(InitEvent event) {

		    String name = "flammpfeil.slashblade.named.smith_last";
	      String name2 = "flammpfeil.slashblade.named.smith_3";
	      ItemStack customblade = new ItemStack(BladeLoader.blade_tls, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag,18.0F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/smith/smith_3");
	      ItemSlashBlade.ModelName.set(tag, "named/smith/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(267));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 8);
	      customblade.addEnchantment(Enchantments.UNBREAKING, 5);
	      customblade.addEnchantment(Enchantments.SHARPNESS,15);
	      customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);
	      customblade.addEnchantment(Enchantments.SMITE, 6);
	      customblade.addEnchantment(Enchantments.THORNS, 2);
	      ItemStack reqiredMain = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.louguan_tx");
	      ItemStack reqiredSub = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.silverbamboolight_blood");
	      ItemStack sb = BladeLoader.getCustomBlade(name2);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(sb);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(5000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(50000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(10));
	      NBTTagCompound tag1 = ItemSlashBlade.getItemTagCompound(reqiredMain);
	      if (tag1.hasKey("ench")) {
	        tag1.removeTag("ench");
	      }
	      tag1 = ItemSlashBlade.getItemTagCompound(reqiredSub);
	      if (tag1.hasKey("ench")) {
	        tag1.removeTag("ench");
	      }
	      IRecipe recipe = new RecipeBx(new ResourceLocation(SlashBlade.modid,name),customblade, reqiredMain, reqiredSub, new Object[] { "DDD", "ACB", "DDD", Character.valueOf('A'), reqiredMain, Character.valueOf('B'), reqiredSub, Character.valueOf('C'), sb, Character.valueOf('D'), new ItemStack(Blocks.DIAMOND_BLOCK) });	      
	      SlashBlade.addRecipe(name, recipe);
	   }
}
