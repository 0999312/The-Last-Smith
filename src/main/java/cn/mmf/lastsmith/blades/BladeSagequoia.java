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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeSagequoia {
	private static final String name = "flammpfeil.slashblade.named.sagequoia";

	@SubscribeEvent
	public static void init(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		customblade.setTagCompound(tag);
		ItemSlashBladeNamed.CurrentItemName.set(tag, name);
		ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(113));
		ItemSlashBlade.AttackAmplifier.set(tag, 2F);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag, true);
		ItemSlashBlade.setBaseAttackModifier(tag, 15.0F);
		ItemSlashBlade.TextureName.set(tag, "named/sagequoia/texture");
		ItemSlashBlade.ModelName.set(tag, "named/sagequoia/model");
		ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(5));
		ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
		BladeLoader.getInstance().registerCustomItemStack(name, customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add(name);
		customblade.addEnchantment(Enchantments.UNBREAKING, 10);
		customblade.addEnchantment(Enchantments.SHARPNESS, 7);
		SpecialEffects.addEffect(customblade, SELoader.EXTREME_SHARPNESS);
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack custombladeReqired = SlashBlade.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.tagayasan", 1);
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
		ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(2300));
		ItemStack itemSphereBladeSoul = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);

		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,name, new RecipeAwakeBladeTLS(new ResourceLocation(TLSMain.MODID, name), 
			"bewitched_blade", BladeLoader.getInstance().getCustomBlade(name),custombladeReqired,new Object[] {
				"ADA",
				"CBC",
				"ADA",
				'A', itemSphereBladeSoul,
				'B', custombladeReqired,
				'C', Items.ENDER_EYE,
				'D', "enderpearl"
		}));
	}
}
