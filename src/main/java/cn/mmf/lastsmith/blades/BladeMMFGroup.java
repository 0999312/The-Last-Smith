package cn.mmf.lastsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeMMFGroup {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.mmf.zheng");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.AttackAmplifier.set(tag1, 3F);
		ItemSlashBlade.setBaseAttackModifier(tag1, 10.0F);
		ItemSlashBlade.SpecialAttackType.set(tag1, 266);
		BladeUtil.getInstance().CrafterName.set(tag1, "Syameimaru_Zheng");
		customblade.addEnchantment(Enchantments.POWER, 1);
		customblade.addEnchantment(Enchantments.SHARPNESS, 3);
		customblade.addEnchantment(Enchantments.SMITE, 2);
		ItemSlashBlade.TextureName.set(tag1, "named/arthurs/texture_zheng");
		ItemSlashBlade.ModelName.set(tag1, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.mmf.zheng", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.mmf.zheng");
		ItemStack customblade1 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag2 = tag1.copy(),tag3 =tag1.copy(),tag4=tag1.copy(),tag5=tag1.copy(),
				tag6=tag1.copy(),tag7=tag1.copy();
		customblade1.setTagCompound(tag2);
		ItemSlashBladeNamed.CurrentItemName.set(tag2, "flammpfeil.slashblade.named.mmf.r_liu");
		ItemSlashBlade.TextureName.set(tag2, "named/arthurs/texture_0");
		ItemSlashBlade.ModelName.set(tag2, "named/arthurs/model_0");
		BladeUtil.getInstance().CrafterName.set(tag2, "R_Liu");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.mmf.r_liu", customblade1);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.mmf.r_liu");
		ItemStack customblade2 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		customblade2.setTagCompound(tag3);
		ItemSlashBladeNamed.CurrentItemName.set(tag3, "flammpfeil.slashblade.named.mmf.limuness");
		ItemSlashBlade.TextureName.set(tag3, "named/arthurs/texture_pear");
		ItemSlashBlade.ModelName.set(tag3, "named/arthurs/model_pear");
		BladeUtil.getInstance().CrafterName.set(tag3, "limuness");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.mmf.limuness", customblade2);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.mmf.limuness");
		ItemStack customblade3 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		customblade3.setTagCompound(tag4);
		ItemSlashBladeNamed.CurrentItemName.set(tag4, "flammpfeil.slashblade.named.mmf.fox");
		ItemSlashBlade.TextureName.set(tag4, "named/arthurs/texture_fox");
		ItemSlashBlade.ModelName.set(tag4, "named/arthurs/model_fox");
		BladeUtil.getInstance().CrafterName.set(tag4, "GodFox");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.mmf.fox", customblade3);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.mmf.fox");
		ItemStack customblade4 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		customblade4.setTagCompound(tag5);
		ItemSlashBladeNamed.CurrentItemName.set(tag5, "flammpfeil.slashblade.named.mmf.lrsoft");
		ItemSlashBlade.TextureName.set(tag5, "named/arthurs/texture_irsoft");
		ItemSlashBlade.ModelName.set(tag5, "named/arthurs/model_irsort");
		BladeUtil.getInstance().CrafterName.set(tag5, "lrsoft");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.mmf.lrsoft", customblade4);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.mmf.lrsoft");
		ItemStack customblade5 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		customblade5.setTagCompound(tag6);
		ItemSlashBladeNamed.CurrentItemName.set(tag6, "flammpfeil.slashblade.named.mmf.nethop");
		ItemSlashBlade.TextureName.set(tag6, "named/arthurs/texture_grand");
		ItemSlashBlade.ModelName.set(tag6, "named/arthurs/model_grand");
		BladeUtil.getInstance().CrafterName.set(tag6, "Nethop");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.mmf.nethop", customblade5);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.mmf.nethop");
		ItemStack customblade6 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		customblade6.setTagCompound(tag7);
		ItemSlashBladeNamed.CurrentItemName.set(tag7, "flammpfeil.slashblade.named.mmf.tartaric_acid");
		ItemSlashBlade.TextureName.set(tag7, "named/arthurs/texture_943");
		ItemSlashBlade.ModelName.set(tag7, "named/sange/sange");
		BladeUtil.getInstance().CrafterName.set(tag7, "tartaric_acid");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.mmf.tartaric_acid", customblade6);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.mmf.tartaric_acid");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		NBTTagCompound tag3 = ItemSlashBlade.getItemTagCompound(request);
		ItemSlashBlade.RepairCount.set(tag3, 10);
		BladeUtil.getInstance().IsBewitchedActived.set(tag3, true);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.mmf.tartaric_acid", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.mmf.tartaric_acid"),
			"final_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.mmf.tartaric_acid"), request, 
			new Object[] {
				"DSD",
				"PBP",
				"DSD",
				'P', "sphereSakura",
				'D', SlashBlade.getCustomBlade(SlashBlade.TrapezohedronBladeSoulStr),
				'S', "glowstone",
				'B', request
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.mmf.zheng", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.mmf.zheng"),
			"final_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.mmf.zheng"), request, 
			new Object[] {
				"DSD",
				"PBP",
				"DSD",
				'P', "sphereSakura",
				'D', SlashBlade.getCustomBlade(SlashBlade.TrapezohedronBladeSoulStr),
				'S', "feather",
				'B', request
		}));
		
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.mmf.limuness", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.mmf.limuness"),
				"final_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.mmf.limuness"), request, 
				new Object[] {
					"DSD",
					"PBP",
					"DSD",
					'P', "sphereSakura",
					'D', SlashBlade.getCustomBlade(SlashBlade.TrapezohedronBladeSoulStr),
					'S', "logWood",
					'B', request
			}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.mmf.r_liu", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.mmf.r_liu"),
			"final_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.mmf.r_liu"), request, 
			new Object[] {
				"DSD",
				"PBP",
				"DSD",
				'P', "sphereSakura",
				'D', SlashBlade.getCustomBlade(SlashBlade.TrapezohedronBladeSoulStr),
				'S', "gemDiamond",
				'B', request
		}));
		
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.mmf.lrsoft", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.mmf.lrsoft"),
				"final_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.mmf.lrsoft"), request, 
				new Object[] {
					"DSD",
					"PBP",
					"DSD",
					'P', "sphereSakura",
					'D', SlashBlade.getCustomBlade(SlashBlade.TrapezohedronBladeSoulStr),
					'S', "dustRedstone",
					'B', request
			}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.mmf.fox", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.mmf.fox"),
			"final_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.mmf.fox"), request, 
			new Object[] {
				"DSD",
				"PBP",
				"DSD",
				'P', "sphereSakura",
				'D', SlashBlade.getCustomBlade(SlashBlade.TrapezohedronBladeSoulStr),
				'S', "gemPrismarine",
				'B', request
		}));
		
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.mmf.nethop", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.mmf.nethop"),
				"final_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.mmf.nethop"), request, 
				new Object[] {
					"DSD",
					"PBP",
					"DSD",
					'P', "sphereSakura",
					'D', SlashBlade.getCustomBlade(SlashBlade.TrapezohedronBladeSoulStr),
					'S', "gemEmerald",
					'B', request
		}));
	}
}
