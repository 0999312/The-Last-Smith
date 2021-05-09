package cn.mmf.lastsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeAmagumo {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.amagumo.wind");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.AttackAmplifier.set(tag1, 3F);
		ItemSlashBlade.setBaseAttackModifier(tag1, 14.0F);
		ItemSlashBlade.SpecialAttackType.set(tag1, 3);
		customblade.addEnchantment(Enchantments.UNBREAKING, 2);
		customblade.addEnchantment(Enchantments.POWER, 2);
		customblade.addEnchantment(Enchantments.SHARPNESS, 3);
		customblade.addEnchantment(Enchantments.FEATHER_FALLING, 2);
		ItemSlashBlade.TextureName.set(tag1, "named/smith/texture_wind");
		ItemSlashBlade.ModelName.set(tag1, "named/smith/model");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.amagumo.wind", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.amagumo.wind");
		
		ItemStack customblade1 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag11 = new NBTTagCompound();
		customblade1.setTagCompound(tag11);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag11, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag11, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag11, "flammpfeil.slashblade.named.amagumo.cloud");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag11, 40);
		ItemSlashBlade.AttackAmplifier.set(tag11, 3F);
		ItemSlashBlade.setBaseAttackModifier(tag11, 14.0F);
		ItemSlashBlade.SpecialAttackType.set(tag11, 4);
		customblade1.addEnchantment(Enchantments.UNBREAKING, 2);
		customblade1.addEnchantment(Enchantments.POWER, 2);
		customblade1.addEnchantment(Enchantments.SHARPNESS, 3);
		customblade1.addEnchantment(Enchantments.THORNS, 2);
		ItemSlashBlade.TextureName.set(tag11, "named/smith/texture_cloud");
		ItemSlashBlade.ModelName.set(tag11, "named/agito");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.amagumo.cloud", customblade1);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.amagumo.cloud");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		NBTTagCompound tag4 = ItemSlashBlade.getItemTagCompound(request);
		ItemSlashBlade.KillCount.set(tag4, 750);
		ItemSlashBlade.ProudSoul.set(tag4, 25000);
		ItemSlashBlade.RepairCount.set(tag4, 5);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.amagumo.wind", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.amagumo.wind"),
			"sharpness", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.amagumo.wind"), request, 
			new Object[] {
				"DPS",
				"PAP",
				"BPD",
				'P', "sphereSakura",
				'S', "feather",
				'A', "gemEmerald",
				'D', "enderpearl",
				'B', request
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.amagumo.cloud", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.amagumo.cloud"),
			"sharpness", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.amagumo.cloud"), request, 
			new Object[] {
				"SPD",
				"PAP",
				"DPB",
				'P', "sphereSakura",
				'S', "feather",
				'D', "enderpearl",
				'A', "gemQuartz",
				'B', request
		}));
	}
}
