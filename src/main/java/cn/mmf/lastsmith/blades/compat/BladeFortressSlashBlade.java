package cn.mmf.lastsmith.blades.compat;

import cn.mcmod_mmf.mmlib.util.OreWildcardIngredient;
import cn.mcmod_mmf.mmlib.util.RecipesUtil;
import cn.mmf.lastsmith.TLSMain;
import cn.mmf.lastsmith.blades.BladeLoader;
import cn.mmf.lastsmith.event.RegisterSlashBladeEvent;
import cn.mmf.lastsmith.event.RegisterSlashBladeRecipeEvent;
import cn.mmf.lastsmith.item.ItemLoader;
import cn.mmf.lastsmith.item.ItemSlashBladeNamedTLS;
import cn.mmf.lastsmith.recipe.InfusionRecipeAwakeBlade;
import cn.mmf.lastsmith.recipe.RecipeAwakeBladeTLS;
import cn.mmf.lastsmith.util.BladeUtil;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;

@EventBusSubscriber
public class BladeFortressSlashBlade {

	@SubscribeEvent
	public static void BladeRegister(RegisterSlashBladeEvent event) {
		if (!Loader.isModLoaded("thaumcraft"))
			return;
		ItemStack customblade1 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag1 = new NBTTagCompound();
		customblade1.setTagCompound(tag1);
		BladeUtil.getInstance().IsFakeBlade.set(tag1, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag1, "flammpfeil.slashblade.named.fortress.thaumium");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag1, 400);
		ItemSlashBlade.setBaseAttackModifier(tag1, 10.0F);
		ItemSlashBlade.TextureName.set(tag1, "named/thaum/texture_thaumium");
		ItemSlashBlade.ModelName.set(tag1, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.thaumium", customblade1);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.thaumium");
		
		ItemStack customblade2 = new ItemStack(BladeLoader.windBlade, 1, 0);
		NBTTagCompound tag2 = new NBTTagCompound();
		customblade2.setTagCompound(tag2);
		BladeUtil.getInstance().IsFakeBlade.set(tag2, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag2, "flammpfeil.slashblade.named.fortress.elemental");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag2, 1200);
		ItemSlashBlade.setBaseAttackModifier(tag2, 9.0F);
		ItemSlashBlade.TextureName.set(tag2, "named/thaum/texture_elemental");
		ItemSlashBlade.ModelName.set(tag2, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.elemental", customblade2);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.elemental");
		
		ItemStack customblade3 = new ItemStack(BladeLoader.voidBlade, 1, 0);
		NBTTagCompound tag3 = new NBTTagCompound();
		customblade3.setTagCompound(tag3);
		BladeUtil.getInstance().IsFakeBlade.set(tag3, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag3, "flammpfeil.slashblade.named.fortress.void");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag3, 50);
		ItemSlashBlade.setBaseAttackModifier(tag3, 7.0F);
		ItemSlashBlade.TextureName.set(tag3, "named/thaum/texture_voidmetal");
		ItemSlashBlade.ModelName.set(tag3, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.void", customblade3);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.void");
		
		ItemStack customblade4 = new ItemStack(BladeLoader.crimsonBlade, 1, 0);
		NBTTagCompound tag4 = new NBTTagCompound();
		customblade4.setTagCompound(tag4);
		BladeUtil.getInstance().IsFakeBlade.set(tag4, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag4, "flammpfeil.slashblade.named.fortress.crimson");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag4, 120);
		ItemSlashBlade.setBaseAttackModifier(tag4, 9.0F);
		ItemSlashBlade.TextureName.set(tag4, "named/thaum/texture_crimson");
		ItemSlashBlade.ModelName.set(tag4, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.crimson", customblade4);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.crimson");
		
		
		ItemStack customblade11 = new ItemStack(BladeLoader.bladeNamed, 1, 0);
		NBTTagCompound tag11 = new NBTTagCompound();
		customblade11.setTagCompound(tag11);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag11, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag11, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag11, "flammpfeil.slashblade.named.fortress.thaumium.update");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag11, 600);
		ItemSlashBlade.setBaseAttackModifier(tag11, 11.0F);
		ItemSlashBlade.TextureName.set(tag11, "named/thaum/texture_thaumium_update");
		ItemSlashBlade.ModelName.set(tag11, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.thaumium.update", customblade11);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.thaumium.update");
		
		ItemStack customblade21 = new ItemStack(BladeLoader.windBlade, 1, 0);
		NBTTagCompound tag21 = new NBTTagCompound();
		customblade21.setTagCompound(tag21);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag21, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag21, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag21, "flammpfeil.slashblade.named.fortress.elemental.update");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag21, 1500);
		ItemSlashBlade.setBaseAttackModifier(tag21, 10.0F);
		ItemSlashBlade.TextureName.set(tag21, "named/thaum/texture_elemental_update");
		ItemSlashBlade.ModelName.set(tag21, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.elemental.update", customblade21);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.elemental.update");
		
		ItemStack customblade31 = new ItemStack(BladeLoader.voidBlade, 1, 0);
		NBTTagCompound tag31 = new NBTTagCompound();
		customblade31.setTagCompound(tag31);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag31, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag31, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag31, "flammpfeil.slashblade.named.fortress.void.update");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag31, 120);
		ItemSlashBlade.setBaseAttackModifier(tag31, 9.0F);
		ItemSlashBlade.TextureName.set(tag31, "named/thaum/texture_voidmetal_update");
		ItemSlashBlade.ModelName.set(tag31, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.void.update", customblade31);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.void.update");
		
		ItemStack customblade41 = new ItemStack(BladeLoader.crimsonBlade, 1, 0);
		NBTTagCompound tag41 = new NBTTagCompound();
		customblade41.setTagCompound(tag41);
		ItemSlashBladeNamed.IsDefaultBewitched.set(tag41, true);
		BladeUtil.getInstance().IsBewitchedActived.set(tag41, true);
		ItemSlashBladeNamed.CurrentItemName.set(tag41, "flammpfeil.slashblade.named.fortress.crimson.update");
		ItemSlashBladeNamed.CustomMaxDamage.set(tag41, 300);
		ItemSlashBlade.setBaseAttackModifier(tag41, 12.0F);
		ItemSlashBlade.TextureName.set(tag41, "named/thaum/texture_crimson_update");
		ItemSlashBlade.ModelName.set(tag41, "named/smith/model");
		BladeLoader.getInstance().registerCustomItemStack("flammpfeil.slashblade.named.fortress.crimson.update", customblade41);
		ItemSlashBladeNamedTLS.NamedBlades.add("flammpfeil.slashblade.named.fortress.crimson.update");
	}

	@SubscribeEvent
	public static void onRecipeRegister(RegisterSlashBladeRecipeEvent event) {
		if (!Loader.isModLoaded("thaumcraft"))
			return;
		recipe();
	}
	@Method(modid = "thaumcraft")
	private static void recipe() {
		
		if (!Loader.isModLoaded("tfc")){
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_thaum",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_thaum"),
				BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_thaum"),
				new Object[] { "  W", " B ", "L H", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 33), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer")}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID, "lastsmith.handmade_void",
				new ShapedOreRecipe(new ResourceLocation(SlashBlade.modid, "handmade_void"),
				BladeLoader.getInstance().getCustomBlade("lastsmith.handmade_void"),
				new Object[] { "  W", " B ", "L H", 'W', BladeLoader.wrapper, 'B',
						new ItemStack(ItemLoader.BLADE, 1, 34), 'L', "logWood", 'H',
						new OreWildcardIngredient("toolForginghammer") }));
		}
		
	    ItemStack reqiredBlade = new ItemStack(SlashBlade.wrapBlade);
	    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade);
		ItemSlashBlade.RepairCount.set(tag, 5);
		reqiredBlade.setTagCompound(tag);
		ItemStack thaum = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.thaumium");
		ItemStack element = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.elemental");
		ItemStack voidness = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.void");
		ItemStack crimson = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.crimson");
		
		ItemStack thaum_update = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.thaumium.update");
		ItemStack element_update = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.elemental.update");
		ItemStack voidness_update = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.void.update");
		ItemStack crimson_update = BladeLoader.getInstance().getCustomBlade("flammpfeil.slashblade.named.fortress.crimson.update");
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.thaumium", 
				new RecipeAwakeBladeTLS(new ResourceLocation("lastsmith","flammpfeil.slashblade.named.fortress.thaumium"),"bewitched_blade", 
						thaum, reqiredBlade, 
				new Object[]{
				" 8S",
				"8K8",
				"B8 ",
				'S', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
				'K', reqiredBlade,
				'8', "leafSakura",
				'B', ItemsTC.thaumiumSword
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.elemental", 
				new RecipeAwakeBladeTLS(new ResourceLocation("lastsmith","flammpfeil.slashblade.named.fortress.elemental"),"bewitched_blade", 
						element, reqiredBlade, 
				new Object[]{
				" 8S",
				"8K8",
				"B8 ",
				'S', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
				'K', reqiredBlade,
				'8', "leafSakura",
				'B', ItemsTC.elementalSword
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.void", 
				new RecipeAwakeBladeTLS(new ResourceLocation("lastsmith","flammpfeil.slashblade.named.fortress.void"),"bewitched_blade", 
						voidness, reqiredBlade, 
				new Object[]{
				" 8S",
				"8K8",
				"B8 ",
				'S', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
				'K', reqiredBlade,
				'8', "leafSakura",
				'B', ItemsTC.voidSword
		}));
		RecipesUtil.getInstance().addRecipe(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.crimson", 
				new RecipeAwakeBladeTLS(new ResourceLocation("lastsmith","flammpfeil.slashblade.named.fortress.crimson"),"bewitched_blade", 
						crimson, reqiredBlade, 
				new Object[]{
				" 8S",
				"8K8",
				"B8 ",
				'S', SlashBlade.getCustomBlade(SlashBlade.ProudSoulStr),
				'K', reqiredBlade,
				'8', "leafSakura",
				'B', ItemsTC.crimsonBlade
		}));
		
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.thaumium.update"), 
				new InfusionRecipeAwakeBlade("fortress_blade", thaum_update, 1,
					new AspectList().add(Aspect.SOUL, 256).add(Aspect.AVERSION, 256).add(Aspect.ENERGY, 256),thaum,new Object[]{
					Items.NETHER_STAR, "fullSakura","sphereSakura", "fullSakura", "sphereSakura", "fullSakura",new ItemStack(ItemsTC.nuggets, 1, 10), "fullSakura" 
		}));
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.element.update"), 
				new InfusionRecipeAwakeBlade("fortress_blade", element_update, 1,
					new AspectList().add(Aspect.SOUL, 256).add(Aspect.AVERSION, 256).add(Aspect.ENERGY, 256),element,new Object[]{
					Items.NETHER_STAR, "fullSakura","sphereSakura", "fullSakura", "sphereSakura", "fullSakura",new ItemStack(ItemsTC.nuggets, 1, 10), "fullSakura" 
		}));
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.void.update"), 
				new InfusionRecipeAwakeBlade("fortress_blade", voidness_update, 1,
					new AspectList().add(Aspect.SOUL, 256).add(Aspect.AVERSION, 256).add(Aspect.ENERGY, 256),voidness,new Object[]{
					Items.NETHER_STAR, "fullSakura","sphereSakura", "fullSakura", "sphereSakura", "fullSakura",new ItemStack(ItemsTC.nuggets, 1, 10), "fullSakura" 
		}));
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(TLSMain.MODID,"flammpfeil.slashblade.named.fortress.crimson.update"), 
				new InfusionRecipeAwakeBlade("fortress_blade", crimson_update, 1,
					new AspectList().add(Aspect.SOUL, 256).add(Aspect.AVERSION, 256).add(Aspect.ENERGY, 256),crimson,new Object[]{
					Items.NETHER_STAR, "fullSakura","sphereSakura", "fullSakura", "sphereSakura", "fullSakura",new ItemStack(ItemsTC.nuggets, 1, 10), "fullSakura" 
		}));
	}
}
