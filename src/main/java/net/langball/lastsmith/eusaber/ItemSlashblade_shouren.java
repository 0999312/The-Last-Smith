package net.langball.lastsmith.eusaber;

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

public class ItemSlashblade_shouren {
	@SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.shouren";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(50));
	      ItemSlashBladeNamedSS.IsDefaultBewitched.set(tag, false);
	      ItemSlashBlade.setBaseAttackModifier(tag, 6.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/shouren/shouren");
	      ItemSlashBlade.ModelName.set(tag, "named/shouren/shouren");
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired = new ItemStack(BladeLoader.blade);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(500));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(500));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(1));
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);

	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"ADA", "DBD", "ADA", Character.valueOf('A'), "ingotSakura", Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), "blockIron"}));

	   }

}
