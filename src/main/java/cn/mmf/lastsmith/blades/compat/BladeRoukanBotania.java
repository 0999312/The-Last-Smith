package cn.mmf.lastsmith.blades.compat;

import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import cn.mmf.slashblade_addon.specialeffect.SELoader;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.lexicon.page.PageRuneRecipe;

@EventBusSubscriber
public class BladeRoukanBotania {

	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		if (!Loader.isModLoaded("botania"))
			return;
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag1, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.roukan_bot");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 50);
		ItemSlashBlade.setBaseAttackModifier(tag1, 16.0F);
		customblade.addEnchantment(Enchantments.UNBREAKING, 4);
		customblade.addEnchantment(Enchantments.SHARPNESS, 4);
		if (Loader.isModLoaded("slashblade_addon")) {
			SpecialEffects.addEffect(customblade, SELoader.ManaRepair);
			SpecialEffects.addEffect(customblade, SELoader.ManaBurst);
		}
		ItemSlashBlade.TextureName.set(tag1, "named/bot_roukan/texture");
		ItemSlashBlade.ModelName.set(tag1, "named/bot_roukan/model");
		ItemSlashBlade.SpecialAttackType.set(tag1, 266);
		ItemSlashBlade.StandbyRenderType.set(tag1, 2);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.roukan_bot", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.roukan_bot");

	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded("botania"))
			return;
		recipe();
	}

	@Method(modid = "botania")
	private static void recipe() {
		RecipeRuneAltar runeAltarRecipe;
		ItemStack terraBlade = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.roukan");
		ItemStack vineBall = SlashBlade.findItemStack("botania", "vineball", 1);
		ItemStack thornChakram = SlashBlade.findItemStack("botania", "thornchakram", 1);

		runeAltarRecipe = BotaniaAPI.registerRuneAltarRecipe(
				BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.roukan_bot"), 10000, new Object[] { terraBlade,
						"sphereSakura", "gaiaIngot", vineBall, "ingotGold", thornChakram, "ingotTerrasteel", "fullSakura" });
		for (LexiconEntry entry : BotaniaAPI.categoryTools.entries) {
			if (entry.unlocalizedName.equalsIgnoreCase("terraSword")) {
				entry.setLexiconPages(new LexiconPage[] { new PageRuneRecipe("sb_2", runeAltarRecipe) });
				return;
			}
		}
	}
}
