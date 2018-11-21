package net.langball.lastsmith.blade.others;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeTab;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.Yamato;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.CommonProxy;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_sakuya4 {
	@SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.doubleblade";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(113));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 10.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/sakura/texture_double");
	      ItemSlashBlade.ModelName.set(tag, "named/sakura/work");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(266));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      ItemSlashBlade.SummonedSwordColor.set(tag, Integer.valueOf(0xDD0033));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired = new ItemStack(BladeLoader.blade);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(200));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(500));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"DAD", "FBF", "DAD", Character.valueOf('A'), itemSphereBladeSoul, Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(ItemLoader.sakura_full),'F',"blockRedstone"}));
	   }

}
