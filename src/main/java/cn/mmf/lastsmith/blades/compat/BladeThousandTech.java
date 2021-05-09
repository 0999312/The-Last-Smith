package cn.mmf.lastsmith.blades.compat;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.se.SELoader;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

@EventBusSubscriber
public class BladeThousandTech {

	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.rfblade, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.thousand");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, -1);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		BladeUtil.getInstance().MAXENERGY.set(tag1, 6000000);
		ItemSlashBlade.setBaseAttackModifier(tag1, 32.0f);
		ItemSlashBlade.TextureName.set(tag1, "named/thousand/texture");
		BladeUtil.getInstance().TextureOnName.set(tag1, "named/thousand/texture");
		ItemSlashBlade.ModelName.set(tag1, "named/thousand/model");
		BladeUtil.getInstance().ModelOnName.set(tag1, "named/thousand/model_on");
		customblade.addEnchantment(Enchantments.UNBREAKING, 3);
		customblade.addEnchantment(Enchantments.KNOCKBACK, 2);
		customblade.addEnchantment(Enchantments.SHARPNESS, 5);
		customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);
		customblade.addEnchantment(Enchantments.POWER, 5);
		ItemSlashBlade.SpecialAttackType.set(tag1, 266);
		SpecialEffects.addEffect(customblade, SELoader.TLSystem);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.thousand", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.thousand");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request_4 = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.nagasada");
		NBTTagCompound tag4 = ItemSlashBlade.getItemTagCompound(request_4);
		ItemSlashBlade.RepairCount.set(tag4, 10);
		request_4.setTagCompound(tag4);
		String circuit = OreDictionary.doesOreNameExist("circuitAdvanced") ? "circuitAdvanced" : "blockRedstone";
		
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.thousand", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.thousand"),
			"bunshi", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.thousand"), request_4, 
			new Object[] {
				"DPD",
				"SAS",
				"DBD",
				'D', "sphereSakura",
				'A', "netherStar",
				'P', new ItemStack(ItemLoader.SCROLL,1,19),
				'S', circuit,
				'B', request_4
		}));
	}
}
