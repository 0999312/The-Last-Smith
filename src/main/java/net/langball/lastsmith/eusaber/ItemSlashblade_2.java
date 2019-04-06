package net.langball.lastsmith.eusaber;

import ic2.api.item.IC2Items;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_2 {
		@SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.quantumsaber";
	      String name1 = "flammpfeil.slashblade.named.nanosaber";
	      ItemStack customblade = new ItemStack(BladeLoader.euBlade, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBlade.setBaseAttackModifier(tag, 21.0f);
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, Boolean.valueOf(false));
	      ItemSlashBlade.TextureName.set(tag, "named/nanosaber/texture_1");
	      ItemSlashBlade_EU.TextureOnName.set(tag, "named/nanosaber/texture_1_on");
	      ItemSlashBlade.ModelName.set(tag, "named/nanosaber/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(1));
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired = BladeLoader.findItemStack(Last_worker.MODID, name1, 1);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(5000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(25000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(10));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);

	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name), louguan, custombladeReqired, new Object[]{"IAI", "ABA", "IAI", Character.valueOf('I'),IC2Items.getItem("crafting", "iridium"), Character.valueOf('B'), custombladeReqired,Character.valueOf('A'),IC2Items.getItem("crafting", "alloy")}));
	   }
	   

}
