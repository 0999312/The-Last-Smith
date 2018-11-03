package net.langball.lastsmith.louguan.blade;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.sa.SAbailou1;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Bailou_fake {
		@SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.bailou_fake";
		  ItemSlashBlade.specialAttacks.put(Integer.valueOf(423), new SAbailou1());
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(30));
	      ItemSlashBladeNamedSS.IsFakeBlade.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 2.0F + ToolMaterial.WOOD.getAttackDamage());
	      ItemSlashBlade.TextureName.set(tag, "named/bailou/texture");
	      ItemSlashBlade.ModelName.set(tag, "named/bailou/model");
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired = new ItemStack(BladeLoader.blade);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(100));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"DAD", "ABA", "DAD", Character.valueOf('A'),"ingotIron", Character.valueOf('B'), custombladeReqired,Character.valueOf('D'), new ItemStack(ItemLoader.sakura)}));

	   }

}
