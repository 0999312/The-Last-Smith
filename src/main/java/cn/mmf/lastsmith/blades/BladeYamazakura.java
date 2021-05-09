package cn.mmf.lastsmith.blades;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.event.DropEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.recipe.RecipeTriBladeTLS;
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
public class BladeYamazakura {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		BladeLoader.getInstance();
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade.setTagCompound(tag);
		ItemSlashBladeNamed.CurrentItemName.set(tag, "flammpfeil.slashblade.named.yamazakura.aoi_fake");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag, true);
		ItemSlashBlade.setBaseAttackModifier(tag, 5.0F);
		ItemSlashBlade.TextureName.set(tag, "named/yamazakura/yamazakura_aoi");
		ItemSlashBlade.ModelName.set(tag, "named/yamazakura/model_fake");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.aoi_fake", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.aoi_fake");

		BladeLoader.getInstance();
		ItemStack customblade1 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade1.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.yamazakura.kadomatsu");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag1, true);
		ItemSlashBlade.setBaseAttackModifier(tag1, 5.0F);
		ItemSlashBlade.TextureName.set(tag1, "named/yamazakura/yamazakura_mutsuki_kadomatsu");
		ItemSlashBlade.ModelName.set(tag1, "named/yamazakura/model_fake");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.kadomatsu", customblade1);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.kadomatsu");

		BladeLoader.getInstance();
		ItemStack customblade11 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag11 = new NBTTagCompound();
		customblade11.setTagCompound(tag11);
		ItemSlashBladeNamed.CurrentItemName.set(tag11, "flammpfeil.slashblade.named.yamazakura.ume");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag11, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag11, true);
		ItemSlashBlade.setBaseAttackModifier(tag11, 5.0F);
		ItemSlashBlade.TextureName.set(tag11, "named/yamazakura/yamazakura_kisaragi_ume");
		ItemSlashBlade.ModelName.set(tag11, "named/yamazakura/model_fake");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.ume", customblade11);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.ume");

		BladeLoader.getInstance();
		ItemStack customblade2 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag2 = new NBTTagCompound();
		customblade2.setTagCompound(tag2);
		ItemSlashBladeNamed.CurrentItemName.set(tag2, "flammpfeil.slashblade.named.yamazakura.sakura");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag2, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag2, true);
		ItemSlashBlade.setBaseAttackModifier(tag2, 5.0F);
		ItemSlashBlade.TextureName.set(tag2, "named/yamazakura/yamazakura_yayoi_sakura");
		ItemSlashBlade.ModelName.set(tag2, "named/yamazakura/model_fake");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.sakura", customblade2);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.sakura");

		BladeLoader.getInstance();
		ItemStack customblade21 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag21 = new NBTTagCompound();
		customblade21.setTagCompound(tag21);
		ItemSlashBladeNamed.CurrentItemName.set(tag21, "flammpfeil.slashblade.named.yamazakura.uzuki");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag21, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag21, true);
		ItemSlashBlade.setBaseAttackModifier(tag21, 6.0F);
		ItemSlashBlade.TextureName.set(tag21, "named/yamazakura/yamazakura_uzuki");
		ItemSlashBlade.ModelName.set(tag21, "named/yamazakura/uzuki");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.uzuki", customblade21);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.uzuki");

		BladeLoader.getInstance();
		ItemStack customblade22 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag22 = new NBTTagCompound();
		customblade22.setTagCompound(tag22);
		ItemSlashBladeNamed.CurrentItemName.set(tag22, "flammpfeil.slashblade.named.yamazakura.satsuki");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag22, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag22, true);
		ItemSlashBlade.setBaseAttackModifier(tag22, 6.0F);
		ItemSlashBlade.TextureName.set(tag22, "named/yamazakura/yamazakura_satsuki");
		ItemSlashBlade.ModelName.set(tag22, "named/yamazakura/satsuki");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.satsuki", customblade22);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.satsuki");

		BladeLoader.getInstance();
		ItemStack customblade221 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag221 = new NBTTagCompound();
		customblade221.setTagCompound(tag221);
		ItemSlashBladeNamed.CurrentItemName.set(tag221, "flammpfeil.slashblade.named.yamazakura.hazuki");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag221, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag221, true);
		ItemSlashBlade.setBaseAttackModifier(tag221, 6.0F);
		ItemSlashBlade.TextureName.set(tag221, "named/yamazakura/yamazakura_hazuki");
		ItemSlashBlade.ModelName.set(tag221, "named/yamazakura/fumizuki_fake");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.hazuki", customblade221);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.hazuki");

		BladeLoader.getInstance();
		ItemStack customblade222 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag222 = new NBTTagCompound();
		customblade222.setTagCompound(tag222);
		ItemSlashBladeNamed.CurrentItemName.set(tag222, "flammpfeil.slashblade.named.yamazakura.kiku");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag222, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag222, true);
		ItemSlashBlade.setBaseAttackModifier(tag222, 6.0F);
		ItemSlashBlade.TextureName.set(tag222, "named/yamazakura/yamazakura_nagatsuki_kiku");
		ItemSlashBlade.ModelName.set(tag222, "named/yamazakura/fumizuki");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.kiku", customblade222);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.kiku");

		BladeLoader.getInstance();
		ItemStack customblade3 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag3 = new NBTTagCompound();
		customblade3.setTagCompound(tag3);
		ItemSlashBladeNamed.CurrentItemName.set(tag3, "flammpfeil.slashblade.named.yamazakura.shiwasu");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag3, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag3, true);
		ItemSlashBlade.setBaseAttackModifier(tag3, 7.0F);
		ItemSlashBlade.TextureName.set(tag3, "named/yamazakura/yamazakura_shiwasu");
		ItemSlashBlade.ModelName.set(tag3, "named/yamazakura/shimotsuki");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.shiwasu", customblade3);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.shiwasu");

		BladeLoader.getInstance();
		ItemStack customblade31 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag31 = new NBTTagCompound();
		customblade31.setTagCompound(tag31);
		ItemSlashBladeNamed.CurrentItemName.set(tag31, "flammpfeil.slashblade.named.yamazakura.shimotsuki");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag31, 31);
		BladeUtil.getInstance().IsFakeBlade.set(tag31, true);
		ItemSlashBlade.setBaseAttackModifier(tag31, 7.0F);
		ItemSlashBlade.TextureName.set(tag31, "named/yamazakura/yamazakura_shimotsuki");
		ItemSlashBlade.ModelName.set(tag31, "named/yamazakura/shimotsuki");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.shimotsuki", customblade31);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.shimotsuki");

		BladeLoader.getInstance();
		ItemStack customblade4 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag4 = new NBTTagCompound();
		customblade4.setTagCompound(tag4);
		customblade4.addEnchantment(Enchantments.UNBREAKING, 1);
		ItemSlashBladeNamed.CurrentItemName.set(tag4, "flammpfeil.slashblade.named.yamazakura.minazuki");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag4, 41);
		BladeUtil.getInstance().IsFakeBlade.set(tag4, true);
		ItemSlashBlade.setBaseAttackModifier(tag4, 6.0F);
		ItemSlashBlade.TextureName.set(tag4, "named/yamazakura/yamazakura_minazuki");
		ItemSlashBlade.ModelName.set(tag4, "named/yamazakura/model_fake");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.minazuki", customblade4);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.minazuki");

		BladeLoader.getInstance();
		ItemStack customblade41 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag41 = new NBTTagCompound();
		customblade41.setTagCompound(tag41);
		customblade41.addEnchantment(Enchantments.UNBREAKING, 2);
		ItemSlashBladeNamed.CurrentItemName.set(tag41, "flammpfeil.slashblade.named.yamazakura.fumizuki");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag41, 41);
		ItemSlashBlade.setBaseAttackModifier(tag41, 7.0F);
		ItemSlashBlade.TextureName.set(tag41, "named/yamazakura/yamazakura_fumizuki");
		ItemSlashBlade.ModelName.set(tag41, "named/yamazakura/fumizuki");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.fumizuki", customblade41);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.fumizuki");

		BladeLoader.getInstance();
		ItemStack customblade42 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag42 = new NBTTagCompound();
		customblade42.setTagCompound(tag42);
		customblade42.addEnchantment(Enchantments.UNBREAKING, 2);
		customblade42.addEnchantment(Enchantments.KNOCKBACK, 1);
		ItemSlashBladeNamed.CurrentItemName.set(tag42, "flammpfeil.slashblade.named.yamazakura.kannazuki");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag42, 41);
		ItemSlashBlade.setBaseAttackModifier(tag42, 8.0F);
		ItemSlashBlade.TextureName.set(tag42, "named/yamazakura/yamazakura_kannazuki_katura");
		ItemSlashBlade.ModelName.set(tag42, "named/yamazakura/kannazuki");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.kannazuki", customblade42);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.kannazuki");

		BladeLoader.getInstance();
		ItemStack customblade421 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag421 = new NBTTagCompound();
		customblade421.setTagCompound(tag421);
		customblade421.addEnchantment(Enchantments.POWER, 1);
		customblade421.addEnchantment(Enchantments.UNBREAKING, 3);
		customblade421.addEnchantment(Enchantments.SHARPNESS, 2);
		customblade421.addEnchantment(Enchantments.KNOCKBACK, 2);
		ItemSlashBladeNamed.CurrentItemName.set(tag421, "flammpfeil.slashblade.named.yamazakura.kamuy");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag421, 41);
		ItemSlashBlade.setBaseAttackModifier(tag421, 10.0F);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag421, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag421, true);
		ItemSlashBlade.SpecialAttackType.set(tag421, 263);
		ItemSlashBlade.TextureName.set(tag421, "named/yamazakura/yamazakura_kamuy_tuki");
		ItemSlashBlade.ModelName.set(tag421, "named/yamazakura/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.kamuy", customblade421);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.kamuy");

		BladeLoader.getInstance();
		ItemStack customblade5 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag5 = new NBTTagCompound();
		customblade5.setTagCompound(tag5);
		customblade421.addEnchantment(Enchantments.POWER, 2);
		customblade421.addEnchantment(Enchantments.UNBREAKING, 1);
		customblade421.addEnchantment(Enchantments.SHARPNESS, 3);
		ItemSlashBladeNamed.CurrentItemName.set(tag5, "flammpfeil.slashblade.named.yamazakura.aoi");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag5, 41);
		ItemSlashBlade.setBaseAttackModifier(tag5, 9.0F);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag5, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag5, true);
		ItemSlashBlade.SpecialAttackType.set(tag5, 263);
		ItemSlashBlade.TextureName.set(tag5, "named/yamazakura/yamazakura_aoi");
		ItemSlashBlade.ModelName.set(tag5, "named/yamazakura/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.yamazakura.aoi", customblade5);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.yamazakura.aoi");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "evocation_illager"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.5f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.aoi_fake"), true);
		
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "spider"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.35f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.kadomatsu"), true);
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "stray"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.45f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.ume"), true);
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "creeper"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.35f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.sakura"), true);

		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "ghast"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.5f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.uzuki"), true);
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "enderman"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.35f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.satsuki"), true);
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "blaze"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.35f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.hazuki"), true);

		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "guardian"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.45f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.kiku"), true);
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "vex"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.45f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.shiwasu"), true);
		DropEvent.registerEntityDrop(new ResourceLocation("minecraft", "magma_cube"),
				new ResourceLocation(TLSMain.MODID, "slashblade_white"), 0.35f,
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.shimotsuki"), true);
		
		ItemStack sb = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.ume");
		ItemStack reqiredMain = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.kadomatsu");
		ItemStack reqiredSub = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.sakura");
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(sb);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(50));

		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.yamazakura.minazuki", new RecipeTriBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.yamazakura.minazuki"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.minazuki"), sb,
			1, 1, reqiredMain, 0, 1, false, reqiredSub, 2, 1, false,
			new Object[] { 
				"GGG",
				"ABC",
				"GGG",
				'G', "leafSakura",
				'A', reqiredMain,
				'B', sb,
				'C', reqiredSub
			}));
		sb = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.minazuki");
		reqiredMain = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.uzuki");
		reqiredSub = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.satsuki");
		reqTag = ItemSlashBlade.getItemTagCompound(sb);

		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.yamazakura.fumizuki", new RecipeTriBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.yamazakura.fumizuki"),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.fumizuki"), sb,
			1, 1, reqiredMain, 0, 1, false, reqiredSub, 2, 1, false,
			new Object[] { 
				"GGG",
				"ABC",
				"GGG",
				'G', "fullSakura",
				'A', reqiredMain,
				'B', sb,
				'C', reqiredSub
			}));
		sb = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.fumizuki");
		reqiredMain = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.hazuki");
		reqiredSub = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.kiku");
		reqTag = ItemSlashBlade.getItemTagCompound(sb);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(100));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.yamazakura.kannazuki", new RecipeTriBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.yamazakura.kannazuki"),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.kannazuki"), sb,
			1, 1, reqiredMain, 0, 1, false, reqiredSub, 2, 1, false,
			new Object[] { 
				"GGG",
				"ABC",
				"GGG",
				'G', "ingotSakura",
				'A', reqiredMain,
				'B', sb,
				'C', reqiredSub
			}));
		sb = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.kannazuki");
		reqiredMain = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.shiwasu");
		reqiredSub = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.shimotsuki");
		reqTag = ItemSlashBlade.getItemTagCompound(sb);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(500));
		ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(1000));
		ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(1));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.yamazakura.kamuy", new RecipeTriBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.yamazakura.kamuy"),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.kamuy"), sb,
			1, 1, reqiredMain, 0, 1, false, reqiredSub, 2, 1, false,
			new Object[] { 
				"GGG",
				"ABC",
				"GGG",
				'G', "sphereSakura",
				'A', reqiredMain,
				'B', sb,
				'C', reqiredSub
		}));
		sb = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.aoi_fake");
		reqTag = ItemSlashBlade.getItemTagCompound(sb);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(500));
		ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(1000));
		ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(1));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.yamazakura.aoi", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.yamazakura.aoi"),
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.yamazakura.aoi"), sb, 
			new Object[] {
				"SPS",
				"PBP",
				"SPS",
				'P', "fullSakura",
				'S', "sphereSakura",
				'B', sb
		}));
	}
}
