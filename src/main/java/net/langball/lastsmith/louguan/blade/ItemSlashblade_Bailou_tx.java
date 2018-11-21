package net.langball.lastsmith.louguan.blade;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.sa.SAbailou2;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Bailou_tx {
	@SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.bailou";
	      String nameTx1 = "flammpfeil.slashblade.named.bailou_tx";

	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, nameTx1);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 4.0F + ToolMaterial.IRON.getAttackDamage());
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/bailou/texture_tx");
	      ItemSlashBlade.ModelName.set(tag, "named/bailou/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(269));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	      BladeLoader.registerCustomItemStack(nameTx1, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(nameTx1);
	      customblade.addEnchantment(Enchantments.POWER, 4);
	      customblade.addEnchantment(Enchantments.SMITE,9);
	      customblade.addEnchantment(Enchantments.FIRE_ASPECT, 1);
	      customblade.addEnchantment(Enchantments.THORNS, 1);

	      ItemStack custombladeReqired = BladeLoader.findItemStack(Last_worker.MODID,name,1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(4000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(50000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, nameTx1, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(nameTx1, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,nameTx1),louguan, custombladeReqired, new Object[]{"DED", "EBE", "DED", Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(Blocks.DIAMOND_BLOCK), Character.valueOf('E'), new ItemStack(Blocks.OBSIDIAN)}));
	   }

}
