package net.langball.lastsmith.blade.others;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.NamedBladeManager;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_lkaruga {
    String name = "flammpfeil.slashblade.named.Ikaruga";
	   @SubscribeEvent
	   public void init(InitEvent event) {

	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(113));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 12.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/Ikaruga/texture");
	      ItemSlashBlade.ModelName.set(tag, "named/Ikaruga/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(5));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.UNBREAKING,5);
	      customblade.addEnchantment(Enchantments.SHARPNESS,7);
	      customblade.addEnchantment(Enchantments.KNOCKBACK,5);
	      NamedBladeManager.registerBladeSoul(tag, "Ikaruga");
	   }
	   @SubscribeEvent
	   public void initrecipe(PostInitEvent event){
		    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1);
		    ItemStack blade = BladeLoader.getCustomBlade(name);
		    ItemStack blade_req = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.darkraven");
		      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(blade_req);
	          ItemSlashBlade.KillCount.set(reqTag,1000);
	          SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name),blade, blade_req, new Object[]{"SFS", "EBE", "SFS",Character.valueOf('S'),soul, Character.valueOf('F'),"feather", Character.valueOf('B'), blade_req, Character.valueOf('E'), new ItemStack(Items.ENDER_EYE)}));

	   }
}
