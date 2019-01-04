package net.langball.lastsmith.blade.arthurs;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_nethop {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.nethop";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(71));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 10.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/Arthurs/texture_grand");
	      ItemSlashBlade.ModelName.set(tag, "named/Arthurs/model_grand");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(267));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.UNBREAKING,6);
	      customblade.addEnchantment(Enchantments.SHARPNESS,6);
	      customblade.addEnchantment(Enchantments.POWER,3);
	      ItemStack custombladeReqired = new ItemStack(SlashBlade.weapon);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);
	      ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	      SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"ADA", "CBF", "ADA", Character.valueOf('A'), itemSphereBladeSoul, Character.valueOf('B'), custombladeReqired, Character.valueOf('C'), Items.CLOCK,Character.valueOf('F'), Items.FLINT_AND_STEEL, Character.valueOf('D'), new ItemStack(ItemLoader.material,1,11)}));
	
	   }

}
