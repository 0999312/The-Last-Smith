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
import mods.flammpfeil.slashblade.SlashBlade;
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
		BladeUtil.IsBewitchedActived.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.roukan_xf");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.setBaseAttackModifier(tag1, 16.0F);
		customblade.addEnchantment(Enchantments.UNBREAKING, 4);
		customblade.addEnchantment(Enchantments.SHARPNESS, 4);
        EnumInfusionEnchantment.addInfusionEnchantment(customblade, EnumInfusionEnchantment.ARCING, 4);
		SpecialEffects.addEffect(customblade, SELoader.EXTREME_SHARPNESS);
		ItemSlashBlade.TextureName.set(tag1, "named/roukan/texture_xf");
		ItemSlashBlade.ModelName.set(tag1, "named/roukan/model");
		ItemSlashBlade.SpecialAttackType.set(tag1, 1);
		ItemSlashBlade.StandbyRenderType.set(tag1, 2);
		BladeLoader.registerCustomItemStack("flammpfeil.slashblade.named.roukan_xf", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.roukan_xf");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded("thaumcraft"))
			return;
		recipe();
	}
	@Method(modid = "thaumcraft")
	private static void recipe() {
		ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1);
		ItemStack request = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.roukan");
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(request);
		ItemSlashBlade.RepairCount.set(tag, 10);
		request.setTagCompound(tag);
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(TLSMain.MODID,"flammpfeil.slashblade.named.roukan_xf"), 
			new InfusionRecipeAwakeBlade("roukan_xf", BladeLoader.getCustomBlade("flammpfeil.slashblade.named.roukan_xf"), 1,
				new AspectList().add(Aspect.AIR, 8).add(Aspect.AVERSION, 8).add(Aspect.ENERGY, 8),request,new Object[]{
				ConfigItems.AIR_CRYSTAL, "fullSakura","blockDiamond", "fullSakura", sphere, "fullSakura","feather", "fullSakura" 
		}));
	}
}
