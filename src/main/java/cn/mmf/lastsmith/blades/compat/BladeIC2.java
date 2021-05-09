package cn.mmf.lastsmith.blades.compat;

import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import ic2.api.item.IC2Items;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BladeIC2 {

	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		if (!Loader.isModLoaded("ic2"))
			return;
		ItemStack customblade = new ItemStack(BladeLoader.euBlade, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade.setTagCompound(tag1);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.nanosaber");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, -1);
		ItemSlashBlade.setBaseAttackModifier(tag1, 11.0f);
		ItemSlashBlade.TextureName.set(tag1, "named/nanosaber/texture");
		BladeUtil.getInstance().TextureOnName.set(tag1, "named/nanosaber/texture_on");
		ItemSlashBlade.ModelName.set(tag1, "named/nanosaber/model");
		BladeUtil.getInstance().ModelOnName.set(tag1, "named/nanosaber/model");
		ItemSlashBlade.SpecialAttackType.set(tag1, 1);
		ItemSlashBlade.StandbyRenderType.set(tag1, 2);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.nanosaber", customblade);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.nanosaber");

		ItemStack customblade1 = new ItemStack(BladeLoader.euBlade, 1, 0);
		NBTTagCompound tag11 = new NBTTagCompound();
		customblade1.setTagCompound(tag11);
		ItemSlashBladeNamed.CurrentItemName.set(tag11, "flammpfeil.slashblade.named.quantumsaber");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag11, -1);
		ItemSlashBlade.setBaseAttackModifier(tag11, 21.0f);
		ItemSlashBlade.TextureName.set(tag11, "named/nanosaber/texture_1");
		BladeUtil.getInstance().TextureOnName.set(tag11, "named/nanosaber/texture_1_on");
		ItemSlashBlade.ModelName.set(tag11, "named/nanosaber/model");
		BladeUtil.getInstance().ModelOnName.set(tag11, "named/nanosaber/model");
		ItemSlashBlade.SpecialAttackType.set(tag11, 1);
		ItemSlashBlade.StandbyRenderType.set(tag11, 2);
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.quantumsaber", customblade1);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.quantumsaber");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded("ic2"))
			return;
		ItemStack custombladeReqired = new ItemStack(BladeLoader.blade);
		NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeReqired);
		ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
		ItemStack blade = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.nanosaber");
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.nanosaber",
			new RecipeAwakeBladeTLS(new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.nanosaber"),
				"bewitched_blade", blade, custombladeReqired,
				new Object[] {
						"IAI",
						"CBC",
						"IDI",
						'A', "circuitBasic",
						'B', custombladeReqired,
						'C', "plateDenseIron",
						'D', IC2Items.getItem("energy_crystal"),
						'I', IC2Items.getItem("crafting", "alloy")
		}));
		NBTTagCompound reqTag2 = ItemSlashBlade.getItemTagCompound(blade);
		ItemSlashBlade.RepairCount.set(reqTag2, Integer.valueOf(10));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.quantumsaber",
			new RecipeAwakeBladeTLS(new ResourceLocation(TLSMain.MODID, "flammpfeil.slashblade.named.quantumsaber"),
				"bewitched_blade", BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.quantumsaber"), blade,
				new Object[] {
						"IAI",
						"CBC",
						"IDI",
						'A', "plateDenseSteel",
						'B', blade,
						'C', "circuitAdvanced",
						'D', IC2Items.getItem("lapotron_crystal"),
						'I', IC2Items.getItem("crafting", "iridium")
		}));
	}
}
