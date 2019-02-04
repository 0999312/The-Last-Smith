package net.langball.lastsmith.blade.others;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_sakuya5 {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.shurablade";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(113));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 12.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/sakura/texture_xiuluo");
	      ItemSlashBlade.ModelName.set(tag, "named/sakura/work");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(266));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      ItemSlashBlade.SummonedSwordColor.set(tag, Integer.valueOf(0xFF0033));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 4);
	      customblade.addEnchantment(Enchantments.SHARPNESS, 2);
	      ItemStack custombladeReqired =BladeLoader.findItemStack(Last_worker.MODID, "flammpfeil.slashblade.named.doubleblade", 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(5000));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);

	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"DAD", "CBC", "DAD", Character.valueOf('A'), itemSphereBladeSoul, Character.valueOf('B'), custombladeReqired, Character.valueOf('C'), "sphereSakura", Character.valueOf('D'), new ItemStack(Blocks.NETHER_BRICK)}));

	   }

}
