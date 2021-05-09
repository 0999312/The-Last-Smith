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
public class BladeFushikiri {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.fushikiri");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.AttackAmplifier.set(tag1, 3F);
		ItemSlashBlade.setBaseAttackModifier(tag1, 10.0F);
		customblade.addEnchantment(Enchantments.UNBREAKING, 2);
		customblade.addEnchantment(Enchantments.POWER, 2);
		customblade.addEnchantment(Enchantments.SHARPNESS, 3);
		customblade.addEnchantment(Enchantments.SMITE, 4);
		ItemSlashBlade.SpecialAttackType.set(tag1, 7);
		SpecialEffects.addEffect(customblade, SELoader.EXTREME_SHARPNESS);
		ItemSlashBlade.TextureName.set(tag1, "named/sekiro/fushigiri");
		ItemSlashBlade.ModelName.set(tag1, "named/sekiro/fushigiri");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fushikiri", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fushikiri");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kusabimaru");
		NBTTagCompound tag4 = ItemSlashBlade.getItemTagCompound(request);
		ItemSlashBlade.KillCount.set(tag4, 1000);
		ItemSlashBlade.ProudSoul.set(tag4, 25000);
		ItemSlashBlade.RepairCount.set(tag4, 5);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.fushikiri", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.fushikiri"),
			"silverbamboo_blood", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fushikiri"), request, 
			new Object[] {
				"DPS",
				"PSP",
				"BPD",
				'P', "sphereSakura",
				'D', "netherrack",
				'S', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
				'B', request
		}));

	}
}
