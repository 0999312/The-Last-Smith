package cn.mmf.lastsmith.blades;

import java.util.List;

import com.google.common.collect.Lists;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeMunin;
import cn.mmf.lastsmith.se.SELoader;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeSmith {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.smith.final");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, -1);
		ItemSlashBlade.AttackAmplifier.set(tag1, 3F);
		ItemSlashBlade.setBaseAttackModifier(tag1, 31.0F);
		ItemSlashBlade.SpecialAttackType.set(tag1, 263);
		customblade.addEnchantment(Enchantments.POWER, 9);
		customblade.addEnchantment(Enchantments.FEATHER_FALLING, 2);
		customblade.addEnchantment(Enchantments.THORNS, 3);
		customblade.addEnchantment(Enchantments.SHARPNESS, 9);
		customblade.addEnchantment(Enchantments.SMITE, 9);
		customblade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 9);
		SpecialEffects.addEffect(customblade, SELoader.MUNIN_SHARPNESS);
		if(Loader.isModLoaded("thaumcraft"))
			SpecialEffects.addEffect(customblade, SELoader.SAP);
		ItemSlashBlade.TextureName.set(tag1, "named/smith/texture_final");
		ItemSlashBlade.ModelName.set(tag1, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.smith.final", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.smith.final");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack sb = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.nagasada");
		ItemStack reqiredMain = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.amagumo.wind");
		ItemStack reqiredSub = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.amagumo.cloud");
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(sb);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
		ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(50000));
		ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(10));
		List<String> advancements = Lists.newArrayList("amagumo","roukan_hakurou_nether","fushikiri");
		if(Loader.isModLoaded("thaumcraft")) {
			advancements.add("thaumic_katana");
			advancements.add("crimson_katana");
		}
		if(Loader.isModLoaded("slashblade_addon")) {
			advancements.add("kirisaya");
			advancements.add("nihil");
		}
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.smith.final", new RecipeMunin(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.smith.final"),
			advancements, BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.smith.final"), sb,
			1, 1, reqiredMain, 1, 0, false, reqiredSub, 1, 2, false,
			new Object[] { 
				"GAG",
				"GBG",
				"GCG",
				'G', "sphereSakura",
				'A', reqiredMain,
				'B', sb,
				'C', reqiredSub
		}));
	}
}
