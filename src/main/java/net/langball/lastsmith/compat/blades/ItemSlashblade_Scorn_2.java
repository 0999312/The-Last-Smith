package net.langball.lastsmith.compat.blades;

import mods.flammpfeil.slashblade.event.DropEventHandler;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Scorn_2 {
	   @SubscribeEvent
	   public void initRust(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.Scorn.rust";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(40));
	      ItemSlashBladeNamedSS.IsFakeBlade.set(tag, true);
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 5.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/Scorn/texture_1");
	      ItemSlashBlade.ModelName.set(tag, "named/Scorn/model");
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      DropEventHandler.registerEntityDrop(new ResourceLocation("twilightforest","lich"), 0.3f, BladeLoader.findItemStack(Last_worker.MODID, "flammpfeil.slashblade.named.Scorn.rust", 1));
	   }
	   
}
