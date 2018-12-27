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
import net.langball.lastsmith.items.ItemLoader;
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

public class ItemSlashblade_Louguan_tx {
	   @SubscribeEvent
	   public void init(InitEvent event) {

	      String name = "flammpfeil.slashblade.named.roukan";
	      String nametx = "flammpfeil.slashblade.named.louguan_tx";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, nametx);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 13.0F );
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	      ItemSlashBlade.TextureName.set(tag, "named/louguan/texture_tx");
	      ItemSlashBlade.ModelName.set(tag, "named/louguan/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(8));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(nametx, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(nametx);
	      customblade.addEnchantment(Enchantments.POWER, 6);
	      customblade.addEnchantment(Enchantments.SHARPNESS, 10);
	      customblade.addEnchantment(Enchantments.FIRE_ASPECT, 4);
	      ItemStack custombladeReqired = BladeLoader.findItemStack(Last_worker.MODID,name,1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(4000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(50000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(20));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, nametx, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = BladeLoader.findItemStack(Last_worker.MODID, "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(nametx, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,nametx),louguan, custombladeReqired, new Object[]{"DAD", "CBC", "DCD", Character.valueOf('A'),"ingotSakura",Character.valueOf('B'), custombladeReqired, Character.valueOf('C'), itemSphereBladeSoul, Character.valueOf('D'), new ItemStack(Blocks.DIAMOND_BLOCK)}));
	   }

}
