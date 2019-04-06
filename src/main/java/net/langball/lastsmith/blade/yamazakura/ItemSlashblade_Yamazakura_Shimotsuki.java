package net.langball.lastsmith.blade.yamazakura;

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

public class ItemSlashblade_Yamazakura_Shimotsuki {
	@SubscribeEvent
	   public void init(InitEvent event) {
		  String name = "flammpfeil.slashblade.named.yamazakura.shimotsuki";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(31));
	      ItemSlashBladeNamedSS.IsFakeBlade.set(tag, true);
	      ItemSlashBlade.setBaseAttackModifier(tag, 7.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/yamazakura/yamazakura_shimotsuki");
	      ItemSlashBlade.ModelName.set(tag, "named/yamazakura/shimotsuki_fake");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(0));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      DropEventHandler.registerEntityDrop(new ResourceLocation("minecraft","enderman"), -0.08f, BladeLoader.findItemStack(Last_worker.MODID, name, 1));
	      
	   }

}
