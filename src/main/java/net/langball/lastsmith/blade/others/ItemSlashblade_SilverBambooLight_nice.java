package net.langball.lastsmith.blade.others;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.Last_worker;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_SilverBambooLight_nice {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.silverbamboolight_nice";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(80));
	      ItemSlashBlade.setBaseAttackModifier(tag, 7.0F + ToolMaterial.WOOD.getAttackDamage());
	      ItemSlashBlade.TextureName.set(tag, "named/silverbamboolight/silverbamboo_nice");
	      ItemSlashBlade.ModelName.set(tag, "named/yamato");
	      ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired = new ItemStack(SlashBlade.bladeSilverBambooLight);
	      ItemStack louguan = BladeLoader.findItemStack(Last_worker.MODID, name, 1);

	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name),louguan, custombladeReqired, new Object[]{"CDC", "CDC", "CBC", Character.valueOf('B'), custombladeReqired, Character.valueOf('C'), new ItemStack(ItemLoader.material,1,7), Character.valueOf('D'), new ItemStack(Blocks.STONE)}));
	   
	   }

}
