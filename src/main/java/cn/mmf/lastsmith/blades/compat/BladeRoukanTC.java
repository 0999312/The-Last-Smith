package cn.mmf.lastsmith.blades.compat;

import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.InfusionRecipeAwakeBlade;
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
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

@EventBusSubscriber
public class BladeRoukanTC {

	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		if (!Loader.isModLoaded("thaumcraft"))
			return;
		ItemStack customblade = new ItemStack(BladeLoader.windBlade, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.roukan_xf");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.setBaseAttackModifier(tag1, 16.0F);
		customblade.addEnchantment(Enchantments.UNBREAKING, 4);
		customblade.addEnchantment(Enchantments.SHARPNESS, 4);
        EnumInfusionEnchantment.addInfusionEnchantment(customblade, EnumInfusionEnchantment.ARCING, 4);
		SpecialEffects.addEffect(customblade, SELoader.EXTREME_SHARPNESS);
		ItemSlashBlade.TextureName.set(tag1, "named/roukan/texture_xf");
		ItemSlashBlade.ModelName.set(tag1, "named/roukan/model");
		ItemSlashBlade.SpecialAttackType.set(tag1, 266);
		ItemSlashBlade.StandbyRenderType.set(tag1, 2);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.roukan_xf", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.roukan_xf");
		
		ItemStack customblade1 = new ItemStack(BladeLoader.windBlade, 1, 0);
		NBTTagCompound tag11 = new NBTTagCompound();
		customblade1.setTagCompound(tag11);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag11, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag11, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag11, "flammpfeil.slashblade.named.hakurou_xf");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag11, 40);
		ItemSlashBlade.setBaseAttackModifier(tag11, 8.0F);
		customblade1.addEnchantment(Enchantments.UNBREAKING, 4);
		customblade1.addEnchantment(Enchantments.SMITE, 4);
        EnumInfusionEnchantment.addInfusionEnchantment(customblade1, EnumInfusionEnchantment.ARCING, 2);
		ItemSlashBlade.TextureName.set(tag11, "named/hakurou/texture_xf");
		ItemSlashBlade.ModelName.set(tag11, "named/hakurou/model");
		ItemSlashBlade.SpecialAttackType.set(tag11, 266);
		ItemSlashBlade.StandbyRenderType.set(tag11, 2);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.hakurou_xf", customblade1);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.hakurou_xf");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded("thaumcraft"))
			return;
		recipe();
	}
	@Method(modid = "thaumcraft")
	private static void recipe() {
		ItemStack request = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.roukan");
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(request);
		ItemSlashBlade.RepairCount.set(tag, 10);
		request.setTagCompound(tag);
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(TLSMain.MODID,"flammpfeil.slashblade.named.roukan_xf"), 
			new InfusionRecipeAwakeBlade("roukan_xf", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.roukan_xf"), 1,
				new AspectList().add(Aspect.AIR, 512).add(Aspect.AVERSION, 512).add(Aspect.ENERGY, 512),request,new Object[]{
				ConfigItems.AIR_CRYSTAL, "fullSakura","blockDiamond", "fullSakura", "sphereSakura", "fullSakura","feather", "fullSakura" 
		}));
		ItemStack request1 = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.hakurou");
		NBTTagCompound tag1 = ItemSlashBlade.getItemTagCompound(request1);
		ItemSlashBlade.RepairCount.set(tag1, 10);
		request1.setTagCompound(tag1);
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(TLSMain.MODID,"flammpfeil.slashblade.named.hakurou_xf"), 
			new InfusionRecipeAwakeBlade("roukan_xf", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.hakurou_xf"), 1,
				new AspectList().add(Aspect.AIR, 512).add(Aspect.AVERSION, 512).add(Aspect.ENERGY, 512),request1,new Object[]{
				ConfigItems.AIR_CRYSTAL, "fullSakura","blockDiamond", "fullSakura", "sphereSakura", "fullSakura","feather", "fullSakura" 
		}));
	}
}
