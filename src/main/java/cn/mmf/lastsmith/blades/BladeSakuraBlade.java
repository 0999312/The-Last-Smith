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
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeSakuraBlade {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.sakura");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 70);
		ItemSlashBlade.setBaseAttackModifier(tag1, 7.0F);
		ItemSlashBlade.SummonedSwordColor.set(tag1, 0xffc6c8);
		customblade.addEnchantment(Enchantments.UNBREAKING, 1);
		customblade.addEnchantment(Enchantments.SMITE, 2);
		customblade.addEnchantment(Enchantments.SHARPNESS, 1);
		ItemSlashBlade.TextureName.set(tag1, "named/sakura/texture");
		ItemSlashBlade.ModelName.set(tag1, "named/sakura/model");
		ItemSlashBlade.SpecialAttackType.set(tag1, 265);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.sakura", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.sakura");
		
		ItemStack customblade2 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag2 = new NBTTagCompound();
		customblade2.setTagCompound(tag2);
		ItemSlashBladeNamed.CurrentItemName.set(tag2, "flammpfeil.slashblade.named.ginkgo");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag2, 70);
		ItemSlashBlade.setBaseAttackModifier(tag2, 7.0F);
		customblade2.addEnchantment(Enchantments.UNBREAKING, 1);
		customblade2.addEnchantment(Enchantments.SMITE, 2);
		customblade2.addEnchantment(Enchantments.SHARPNESS, 1);
		ItemSlashBlade.TextureName.set(tag2, "named/sakura/texture_1");
		ItemSlashBlade.ModelName.set(tag2, "named/sakura/model");
		ItemSlashBlade.SummonedSwordColor.set(tag2, 0xf8bb28);
		ItemSlashBlade.SpecialAttackType.set(tag2, 265);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag2, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag2, true);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.ginkgo", customblade2);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.ginkgo");
		
		ItemStack customblade3 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag3 = new NBTTagCompound();
		customblade3.setTagCompound(tag3);
		ItemSlashBladeNamed.CurrentItemName.set(tag3, "flammpfeil.slashblade.named.yukikage");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag3, 70);
		ItemSlashBlade.setBaseAttackModifier(tag3, 7.0F);
		customblade3.addEnchantment(Enchantments.UNBREAKING, 1);
		customblade3.addEnchantment(Enchantments.SMITE, 2);
		customblade3.addEnchantment(Enchantments.SHARPNESS, 1);
		ItemSlashBlade.TextureName.set(tag3, "named/sakura/texture_2");
		ItemSlashBlade.ModelName.set(tag3, "named/sakura/model");
		ItemSlashBlade.SummonedSwordColor.set(tag3, 0xF0F8FF);
		ItemSlashBlade.SpecialAttackType.set(tag3, 265);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag3, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag3, true);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yukikage", customblade3);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yukikage");
		
		ItemStack customblade4 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag4 = new NBTTagCompound();
		customblade4.setTagCompound(tag4);
		ItemSlashBladeNamed.CurrentItemName.set(tag4, "flammpfeil.slashblade.named.kataware");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag4, 70);
		ItemSlashBlade.setBaseAttackModifier(tag4, 7.0F);
		customblade4.addEnchantment(Enchantments.UNBREAKING, 1);
		customblade4.addEnchantment(Enchantments.FIRE_ASPECT, 1);
		customblade4.addEnchantment(Enchantments.SHARPNESS, 2);
		ItemSlashBlade.TextureName.set(tag4, "named/sakura/texture_3");
		ItemSlashBlade.ModelName.set(tag4, "named/sakura/model");
		ItemSlashBlade.SummonedSwordColor.set(tag4, 0xDD0033);
		ItemSlashBlade.SpecialAttackType.set(tag4, 265);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag4, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag4, true);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.kataware", customblade4);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.kataware");
		
		ItemStack customblade5 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag5 = new NBTTagCompound();
		customblade5.setTagCompound(tag5);
		ItemSlashBladeNamed.CurrentItemName.set(tag5, "flammpfeil.slashblade.named.shura");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag5, 70);
		ItemSlashBlade.setBaseAttackModifier(tag5, 9.0F);
		customblade5.addEnchantment(Enchantments.UNBREAKING, 2);
		customblade5.addEnchantment(Enchantments.FIRE_ASPECT, 1);
		customblade5.addEnchantment(Enchantments.SHARPNESS, 2);
		ItemSlashBlade.TextureName.set(tag5, "named/sakura/texture_4");
		ItemSlashBlade.ModelName.set(tag5, "named/sakura/model");
		ItemSlashBlade.SummonedSwordColor.set(tag5, 0xDD0033);
		ItemSlashBlade.SpecialAttackType.set(tag5, 265);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag5, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag5, true);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.shura", customblade5);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.shura");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		NBTTagCompound tag = new NBTTagCompound();
		ItemSlashBlade.KillCount.set(tag, 200);
		ItemSlashBlade.ProudSoul.set(tag, 5000);
		ItemSlashBlade.RepairCount.set(tag, 1);
		request.setTagCompound(tag);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.sakura", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.sakura"),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.sakura"), request, 
			new Object[] {
				"SDS",
				"PBP",
				"SDS",
				'P', "fullSakura",
				'D', "dyeRed",
				'S', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
				'B', request
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.ginkgo", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.ginkgo"),
				"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.ginkgo"), request, 
				new Object[] {
					"SDS",
					"PBP",
					"SDS",
					'P', "fullSakura",
					'D', "dyeYellow",
					'S', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
					'B', request
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.yukikage", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.yukikage"),
				"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yukikage"), request, 
				new Object[] {
					"SDS",
					"PBP",
					"SDS",
					'P', "fullSakura",
					'D', Blocks.SNOW,
					'S', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
					'B', request
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.kataware", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.kataware"),
				"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kataware"), request, 
				new Object[] {
					"SDS",
					"PBP",
					"SDS",
					'P', "fullSakura",
					'D', "dustRedstone",
					'S', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
					'B', request
		}));
		ItemStack requestShura = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kataware");
		NBTTagCompound tagShura = ItemSlashBlade.getItemTagCompound(requestShura);
		ItemSlashBlade.KillCount.set(tagShura, 750);
		ItemSlashBlade.ProudSoul.set(tagShura, 20000);
		ItemSlashBlade.RepairCount.set(tagShura, 10);
		requestShura.setTagCompound(tagShura);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.shura", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.shura"),
				"sharpness", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.shura"), requestShura, 
				new Object[] {
					"SDS",
					"PBP",
					"SDS",
					'P', "ingotSakura",
					'D', "enderpearl",
					'S', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
					'B', requestShura
		}));
	}
}
