package cn.mmf.lastsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeRoukanken {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		BladeUtil.getInstance().IsFakeBlade.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.kanrou_fake");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 70);
		ItemSlashBlade.setBaseAttackModifier(tag1, 7.0F);
		ItemSlashBlade.TextureName.set(tag1, "named/roukan/texture_fake_0");
		ItemSlashBlade.ModelName.set(tag1, "named/agito");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.kanrou_fake", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.kanrou_fake");
		
		ItemStack customblade2 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag2 = new NBTTagCompound();
		customblade2.setTagCompound(tag2);
		ItemSlashBladeNamed.CurrentItemName.set(tag2, "flammpfeil.slashblade.named.kanrou");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag2, 70);
		ItemSlashBlade.setBaseAttackModifier(tag2, 9.0F);
		ItemSlashBlade.TextureName.set(tag2, "named/roukan/texture_fake");
		ItemSlashBlade.ModelName.set(tag2, "named/agito");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.kanrou", customblade2);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.kanrou");
		
		ItemStack customblade3 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag3 = new NBTTagCompound();
		customblade3.setTagCompound(tag3);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag3, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag3, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag3, "flammpfeil.slashblade.named.roukan");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag3, 40);
		ItemSlashBlade.setBaseAttackModifier(tag3, 15.0F);
		ItemSlashBlade.SpecialAttackType.set(tag3, 266);
		customblade3.addEnchantment(Enchantments.UNBREAKING, 2);
		customblade3.addEnchantment(Enchantments.KNOCKBACK, 1);
		customblade3.addEnchantment(Enchantments.SHARPNESS, 4);
		SpecialEffects.addEffect(customblade3, SELoader.EXTREME_SHARPNESS);
		ItemSlashBlade.TextureName.set(tag3, "named/roukan/texture");
		ItemSlashBlade.ModelName.set(tag3, "named/roukan/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.roukan", customblade3);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.roukan");
		
		ItemStack customblade4 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag4 = new NBTTagCompound();
		customblade4.setTagCompound(tag4);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag4, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag4, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag4, "flammpfeil.slashblade.named.roukan_nether");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag4, 40);
		ItemSlashBlade.AttackAmplifier.set(tag4, 4F);
		ItemSlashBlade.setBaseAttackModifier(tag4, 16.0F);
		ItemSlashBlade.SpecialAttackType.set(tag4, 266);
		customblade4.addEnchantment(Enchantments.UNBREAKING, 3);
		customblade4.addEnchantment(Enchantments.KNOCKBACK, 2);
		customblade4.addEnchantment(Enchantments.SHARPNESS, 5);
		customblade4.addEnchantment(Enchantments.FIRE_ASPECT, 2);
		SpecialEffects.addEffect(customblade4, SELoader.EXTREME_SHARPNESS);
		ItemSlashBlade.TextureName.set(tag4, "named/roukan/texture_tx");
		ItemSlashBlade.ModelName.set(tag4, "named/roukan/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.roukan_nether", customblade4);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.roukan_nether");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request_1 = new ItemStack(BladeLoader.blade);
		NBTTagCompound tag1 = new NBTTagCompound();
		ItemSlashBlade.KillCount.set(tag1, 50);
		request_1.setTagCompound(tag1);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.kanrou_fake", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.kanrou_fake"),
			"sakura_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kanrou_fake"), request_1, 
			new Object[] {
				"PDP",
				"DSD",
				"PBP",
				'D', "leafSakura",
				'P', "ingotGold",
				'S', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
				'B', request_1
		}));
		ItemStack request_2 = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kanrou_fake");
		NBTTagCompound tag2 = ItemSlashBlade.getItemTagCompound(request_2);
		ItemSlashBlade.KillCount.set(tag2, 200);
		ItemSlashBlade.ProudSoul.set(tag2, 1000);
		request_2.setTagCompound(tag2);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.kanrou", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.kanrou"),
			"sakura_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kanrou"), request_2, 
			new Object[] {
				"PSD",
				"SDS",
				"BSP",
				'D', SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr),
				'P', "gemDiamond",
				'S', "fullSakura",
				'B', request_2
		}));
		ItemStack request_3 = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kanrou");
		NBTTagCompound tag3 = ItemSlashBlade.getItemTagCompound(request_3);
		ItemSlashBlade.KillCount.set(tag3, 500);
		ItemSlashBlade.ProudSoul.set(tag3, 5000);
		ItemSlashBlade.RepairCount.set(tag3, 1);
		request_3.addEnchantment(Enchantments.SHARPNESS, 1);
		request_3.setTagCompound(tag3);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.roukan", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.roukan"),
			"sakura_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.roukan"), request_3, 
			new Object[] {
				"SPD",
				"PBP",
				"DPS",
				'D', "blockSakura",
				'P', "blockDiamond",
				'S', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
				'B', request_3
		}));
		ItemStack request_4 = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.roukan");
		NBTTagCompound tag4 = ItemSlashBlade.getItemTagCompound(request_4);
		ItemSlashBlade.KillCount.set(tag4, 1000);
		ItemSlashBlade.ProudSoul.set(tag4, 25000);
		ItemSlashBlade.RepairCount.set(tag4, 5);
		request_4.addEnchantment(Enchantments.FIRE_ASPECT, 1);
		request_4.setTagCompound(tag4);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.roukan_nether", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.roukan_nether"),
			"sharpness", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.roukan_nether"), request_4, 
			new Object[] {
				"DSD",
				"PBP",
				"DSD",
				'D', "sphereSakura",
				'P', "blockDiamond",
				'S', "netherStar",
				'B', request_4
		}));
	}
}
