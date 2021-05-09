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
public class BladeBambooLight {
	@SubscribeEvent
	public static void BambooRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag_top = new NBTTagCompound();
		customblade.setTagCompound(tag_top);
		ItemSlashBladeNamed.CurrentItemName.set(tag_top, "flammpfeil.slashblade.named.bamboolight_top");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag_top, 60);
		BladeUtil.getInstance().IsFakeBlade.set(tag_top, true);
		ItemSlashBlade.setBaseAttackModifier(tag_top, 6.0F);
		ItemSlashBlade.TextureName.set(tag_top, "named/bamboolight/bamboo_top");
		ItemSlashBlade.ModelName.set(tag_top, "blade");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.bamboolight_top", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.bamboolight_top");

		ItemStack customblade_recluse = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade_recluse.setTagCompound(tag);
		customblade_recluse.addEnchantment(Enchantments.UNBREAKING, 3);
		customblade_recluse.addEnchantment(Enchantments.SMITE, 3);
		customblade_recluse.addEnchantment(Enchantments.SHARPNESS, 2);
		ItemSlashBladeNamed.CurrentItemName.set(tag, "flammpfeil.slashblade.named.bamboolight_recluse");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, 70);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag, true);
		ItemSlashBlade.SpecialAttackType.set(tag, 7);
		ItemSlashBlade.setBaseAttackModifier(tag, 10.0F);
		ItemSlashBlade.TextureName.set(tag, "named/bamboolight/bamboo_top");
		ItemSlashBlade.ModelName.set(tag, "named/yamato");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.bamboolight_recluse", customblade_recluse);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.bamboolight_recluse");
	}

	@SubscribeEvent
	public static void SliverBambooRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag_top = new NBTTagCompound();
		customblade.setTagCompound(tag_top);
		ItemSlashBladeNamed.CurrentItemName.set(tag_top, "flammpfeil.slashblade.named.silverbamboolight_top");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag_top, 60);
		ItemSlashBlade.setBaseAttackModifier(tag_top, 7.0F);
		ItemSlashBlade.TextureName.set(tag_top, "named/bamboolight/silverbamboo_top");
		ItemSlashBlade.ModelName.set(tag_top, "named/yamato");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.silverbamboolight_top", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.silverbamboolight_top");

		ItemStack customblade_recluse = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade_recluse.setTagCompound(tag);
		customblade_recluse.addEnchantment(Enchantments.KNOCKBACK, 2);
		customblade_recluse.addEnchantment(Enchantments.SHARPNESS, 3);
		customblade_recluse.addEnchantment(Enchantments.POWER, 2);
		ItemSlashBlade.SpecialAttackType.set(tag, 9);
		ItemSlashBladeNamed.CurrentItemName.set(tag, "flammpfeil.slashblade.named.silverbamboolight_blood");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, 70);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag, true);
		ItemSlashBlade.setBaseAttackModifier(tag, 10.0F);
		ItemSlashBlade.TextureName.set(tag, "named/bamboolight/silverbamboo_blood");
		ItemSlashBlade.ModelName.set(tag, "named/yamato");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.silverbamboolight_blood", customblade_recluse);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.silverbamboolight_blood");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		NBTTagCompound tag_bamboo = new NBTTagCompound();
		ItemSlashBlade.KillCount.set(tag_bamboo, 100);
		ItemStack bamboolight = new ItemStack(SlashBlade.bladeBambooLight);
		bamboolight.setTagCompound(tag_bamboo);
		ItemStack sliverbamboolight = new ItemStack(SlashBlade.bladeSilverBambooLight);
		sliverbamboolight.setTagCompound(tag_bamboo);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.bamboolight_top", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.bamboolight_top"),
			"slashblade_bamboolight", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.bamboolight_top"), bamboolight, 
			new Object[] {
				"PSP",
				"PSP",
				"PBP",
				'P', "leafSakura",
				'S', "stone",
				'B', bamboolight
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.silverbamboolight_top", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.silverbamboolight_top"),
			"slashblade_sliverbamboolight", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.silverbamboolight_top"), sliverbamboolight, 
			new Object[] {
				"PSP",
				"PSP",
				"PBP",
				'P', "leafSakura",
				'S', "stone",
				'B', sliverbamboolight
		}));
		ItemStack bamboolight_top = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.bamboolight_top");
		ItemStack silverbamboolight_top = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.silverbamboolight_top");
		NBTTagCompound tag_upgrade_1 = ItemSlashBlade.getItemTagCompound(bamboolight_top);
		NBTTagCompound tag_upgrade_2 = ItemSlashBlade.getItemTagCompound(silverbamboolight_top);
		ItemSlashBlade.KillCount.set(tag_upgrade_1, 500);
		ItemSlashBlade.ProudSoul.set(tag_upgrade_1, 20000);
		ItemSlashBlade.RepairCount.set(tag_upgrade_1, 5);
		ItemSlashBlade.KillCount.set(tag_upgrade_2, 500);
		ItemSlashBlade.ProudSoul.set(tag_upgrade_2, 20000);
		ItemSlashBlade.RepairCount.set(tag_upgrade_2, 5);
		
		bamboolight_top.setTagCompound(tag_upgrade_1);
		silverbamboolight_top.setTagCompound(tag_upgrade_2);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.bamboolight_recluse", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.bamboolight_recluse"),
				"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.bamboolight_recluse"), bamboolight_top, 
				new Object[] {
					"PSP",
					"SBS",
					"PSP",
					'P', "sphereSakura",
					'S', "fullSakura",
					'B', bamboolight_top
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.silverbamboolight_blood", new RecipeAwakeBladeTLS(
				new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.silverbamboolight_blood"),
				"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.silverbamboolight_blood"), silverbamboolight_top, 
				new Object[] {
					"PSP",
					"SBS",
					"PSP",
					'P', "sphereSakura",
					'S', "fullSakura",
					'B', silverbamboolight_top
		}));
	}
}