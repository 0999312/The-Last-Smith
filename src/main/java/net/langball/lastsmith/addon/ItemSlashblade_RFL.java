package net.langball.lastsmith.addon;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSlashblade_RFL {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.wenshi";
	      String name2 = "flammpfeil.slashblade.named.roukan";
	      ItemStack customblade = new ItemStack(BladeLoader.euBlade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(120));
	      ItemSlashBlade.setBaseAttackModifier(tag, 12.0f);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/RFlouguan/texture");
	      ItemSlashBlade.ModelName.set(tag, "named/RFlouguan/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(324));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 8);
	      customblade.addEnchantment(Enchantments.SHARPNESS, 6);
	      customblade.addEnchantment(Enchantments.LOOTING, 2);
	      ItemStack custombladeReqired = SlashBlade.findItemStack(SlashBlade.modid,name2,1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(500));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
	      ItemStack louguan = SlashBlade.findItemStack("flammpfeil.slashblade", name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(name), louguan, custombladeReqired, new Object[]{"DAD", "CBC", "ECE", Character.valueOf('A'), ingot, Character.valueOf('B'), custombladeReqired, Character.valueOf('C'), itemSphereBladeSoul, Character.valueOf('D'), new ItemStack(Blocks.IRON_BLOCK), Character.valueOf('E'), Items.BOOK}));

	   }
	   

}
