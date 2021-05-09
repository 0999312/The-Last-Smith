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
public class BladeKogawa {
	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		BladeUtil.getInstance().IsFakeBlade.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.kogawa_normal");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 40);
		ItemSlashBlade.setBaseAttackModifier(tag1, 6.0F);
		ItemSlashBlade.TextureName.set(tag1, "named/namedblade/texture_normal");
		ItemSlashBlade.ModelName.set(tag1, "named/namedblade/model");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.kogawa_normal", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.kogawa_normal");

		ItemStack customblade2 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag2 = new NBTTagCompound();
		customblade2.setTagCompound(tag2);
		ItemSlashBladeNamed.CurrentItemName.set(tag2, "flammpfeil.slashblade.named.kogawa_rare");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag2, 50);
		ItemSlashBlade.setBaseAttackModifier(tag2, 7.0F);
		ItemSlashBlade.TextureName.set(tag2, "named/namedblade/texture_rare");
		ItemSlashBlade.ModelName.set(tag2, "named/namedblade/model");

		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.kogawa_rare", customblade2);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.kogawa_rare");
		
		ItemStack customblade3 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag3 = new NBTTagCompound();
		customblade3.setTagCompound(tag3);
		customblade3.addEnchantment(Enchantments.SHARPNESS, 2);
		customblade3.addEnchantment(Enchantments.UNBREAKING, 1);
		ItemSlashBladeNamed.CurrentItemName.set(tag3, "flammpfeil.slashblade.named.kogawa_super");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag3, 50);
		ItemSlashBlade.setBaseAttackModifier(tag3, 7.0F);
		ItemSlashBlade.TextureName.set(tag3, "named/namedblade/texture_super");
		ItemSlashBlade.ModelName.set(tag3, "named/namedblade/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.kogawa_super", customblade3);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.kogawa_super");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		ItemStack request = new ItemStack(BladeLoader.blade);
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(request);
		request.setTagCompound(tag);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.kogawa_normal", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.kogawa_normal"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kogawa_normal"), request, 
			new Object[] {
				"PSP",
				"SBS",
				"PSP",
				'P', "leafSakura",
				'S', SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr),
				'B', request
		}));
		ItemStack request_rare = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kogawa_normal");
		NBTTagCompound tag1 = ItemSlashBlade.getItemTagCompound(request_rare);
		ItemSlashBlade.KillCount.set(tag1, 50);
		ItemSlashBlade.ProudSoul.set(tag1, 1000);
		request_rare.setTagCompound(tag1);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.kogawa_rare", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.kogawa_rare"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kogawa_rare"), request_rare, 
			new Object[] {
				"PSP",
				"SBS",
				"PSP",
				'P', SlashBlade.getCustomBlade(SlashBlade.IngotBladeSoulStr),
				'S', "fullSakura",
				'B', request_rare
		}));
		ItemStack request_super = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kogawa_rare");
		NBTTagCompound tag2 = ItemSlashBlade.getItemTagCompound(request_super);
		ItemSlashBlade.KillCount.set(tag2, 250);
		ItemSlashBlade.ProudSoul.set(tag2, 5000);
		request_super.setTagCompound(tag2);
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.kogawa_super", new RecipeAwakeBladeTLS(
			new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.kogawa_super"),
			"slashblade_white", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.kogawa_super"), request_super, 
			new Object[] {
				"PSP",
				"PSP",
				"SBS",
				'P', SlashBlade.getCustomBlade(SlashBlade.SphereBladeSoulStr),
				'S', "ingotSakura",
				'B', request_super
		}));
	}
}
